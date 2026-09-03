package com.riwi.talent.modelo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Override
    public boolean insertar(Empleado empleado, double[] calificaciones) {
        String sqlEmpleado = "INSERT INTO empleados (id, nombre, edad, salario, tipo, extra) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlCalificacion = "INSERT INTO calificaciones (empleado_id, trim_1, trim_2, trim_3) VALUES (?, ?, ?, ?)";

        // Uso obligatorio de try-with-resources para prevenir fugas de recursos
        try (Connection conn = DbConnection.getConnection()) {
            conn.setAutoCommit(false); // Transacción segura

            try (PreparedStatement pstmtEmp = conn.prepareStatement(sqlEmpleado);
                 PreparedStatement pstmtCal = conn.prepareStatement(sqlCalificacion)) {

                pstmtEmp.setInt(1, empleado.getId());
                pstmtEmp.setString(2, empleado.getNombre());
                pstmtEmp.setByte(3, empleado.getEdad());
                pstmtEmp.setDouble(4, empleado.getSalario());

                if (empleado instanceof Desarrollador d) {
                    pstmtEmp.setString(5, "DESARROLLADOR");
                    pstmtEmp.setString(6, d.getLenguajePrincipal());
                } else if (empleado instanceof Gerente g) {
                    pstmtEmp.setString(5, "GERENTE");
                    pstmtEmp.setString(6, String.valueOf(g.getPresupuestoMensual()));
                } else {
                    pstmtEmp.setString(5, "ESTANDAR");
                    pstmtEmp.setString(6, null);
                }
                pstmtEmp.executeUpdate();

                pstmtCal.setInt(1, empleado.getId());
                pstmtCal.setDouble(2, calificaciones[0]);
                pstmtCal.setDouble(3, calificaciones[1]);
                pstmtCal.setDouble(4, calificaciones[2]);
                pstmtCal.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, edad, salario, tipo, extra FROM empleados";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                byte edad = rs.getByte("edad");
                double salario = rs.getDouble("salario");
                String tipo = rs.getString("tipo");
                String extra = rs.getString("extra");

                Empleado emp;
                if ("DESARROLLADOR".equals(tipo)) {
                    emp = new Desarrollador(id, nombre, edad, salario, extra);
                } else if ("GERENTE".equals(tipo)) {
                    emp = new Gerente(id, nombre, edad, salario, extra != null ? Double.parseDouble(extra) : 0.0);
                } else {
                    emp = new Empleado(id, nombre, edad, salario);
                }
                lista.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean actualizar(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, edad = ?, salario = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setByte(2, empleado.getEdad());
            pstmt.setDouble(3, empleado.getSalario());
            pstmt.setInt(4, empleado.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar empleado: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<DesempenoReport> obtenerReportesDesempeno(double notaMinima) {
        List<DesempenoReport> reportes = new ArrayList<>();
        String sql = """
            SELECT e.id, e.nombre, 
                   (c.trim_1 + c.trim_2 + c.trim_3) / 3.0 AS promedio 
            FROM empleados e 
            JOIN calificaciones c ON e.id = c.empleado_id
            """;

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double promedio = rs.getDouble("promedio");
                String feedback = promedio >= notaMinima ? "Aprobado para ascenso" : "Requiere mejora";

                reportes.add(new DesempenoReport(id, nombre, promedio, feedback));
            }
        } catch (SQLException e) {
            System.out.println("Error al generar reportes: " + e.getMessage());
        }
        return reportes;
    }
}