package com.riwi.talent.controller;


import com.riwi.talent.modelo.*;
import java.util.List;

public class TalentController {
    private final EmpleadoDAO empleadoDAO;

    public TalentController() {
        this.empleadoDAO = new EmpleadoDAOImpl();
    }

    public boolean registrarEmpleado(int id, String nombre, byte edad, double salario, int tipoRol, String extra, double[] calificaciones) {
        Empleado nuevoEmpleado;
        if (tipoRol == 1) {
            nuevoEmpleado = new Desarrollador(id, nombre, edad, salario, extra);
        } else if (tipoRol == 2) {
            double presupuesto = Double.parseDouble(extra);
            nuevoEmpleado = new Gerente(id, nombre, edad, salario, presupuesto);
        } else {
            nuevoEmpleado = new Empleado(id, nombre, edad, salario);
        }
        return empleadoDAO.insertar(nuevoEmpleado, calificaciones);
    }

    public List<Empleado> obtenerEmpleados() {
        return empleadoDAO.listar();
    }

    public boolean actualizarEmpleado(int id, String nombre, byte edad, double salario) {
        Empleado emp = new Empleado(id, nombre, edad, salario);
        return empleadoDAO.actualizar(emp);
    }

    public boolean eliminarEmpleado(int id) {
        return empleadoDAO.eliminar(id);
    }

    public void generarReporteConsolidado(double notaMinima) {
        List<DesempenoReport> reportes = empleadoDAO.obtenerReportesDesempeno(notaMinima);
        
        // TASK 4: Uso de Text Blocks para un formato legible y profesional
        String reporteFormateado = """
                ====================================================
                        REPORTE CONSOLIDADO DE DESEMPEÑO
                ====================================================
                """;
        
        System.out.println(reporteFormateado);
        for (var rep : reportes) {
            System.out.printf("ID: %-4d | Nombre: %-15s | Promedio: %-6.2f | Feedback: %s%n",
                    rep.idEmpleado(), rep.nombre(), rep.promedio(), rep.feedback());
        }
        System.out.println("=====================================================");
    }
}