package com.corporatetalenthub3.modelo;


public record DesempenoReport(int idEmpleado, double promedio, String feedback) {
    // Estructura ligera e inmutable para reportes de fin de mes
}