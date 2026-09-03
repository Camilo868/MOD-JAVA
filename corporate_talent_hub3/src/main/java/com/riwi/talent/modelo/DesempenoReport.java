package com.riwi.talent.modelo;

/*
 * TASK 4: Uso de Records para mapeo eficiente y inmutable de consultas SELECT complejas.
 * Frente a los POJOs tradicionales de Java 8 (que requieren código repetitivo de getters, 
 * setters, constructores, equals, hashCode y toString), los records reducen la verbosidad 
 * a una sola línea, garantizando inmutabilidad y facilitando el mantenimiento.
 */
public record DesempenoReport(int idEmpleado, String nombre, double promedio, String feedback) {
    // Estructura inmutable optimizada para lectura de datos
}