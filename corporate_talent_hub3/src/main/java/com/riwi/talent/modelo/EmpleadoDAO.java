package com.riwi.talent.modelo;


import java.util.List;

public interface EmpleadoDAO {
    boolean insertar(Empleado empleado, double[] calificaciones);
    List<Empleado> listar();
    boolean actualizar(Empleado empleado);
    boolean eliminar(int id);
    List<DesempenoReport> obtenerReportesDesempeno(double notaMinima);
}