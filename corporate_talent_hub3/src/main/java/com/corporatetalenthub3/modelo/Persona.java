package com.corporatetalenthub3.modelo;


/**
 * Clase abstracta sellada (Sealed Class) que restringe la jerarquía del dominio.
 * Seguridad de API: Evita extensiones maliciosas o no deseadas fuera del paquete/diseño previsto.
 */
public abstract sealed class Persona permits Empleado, ConsultorExterno {
    protected String nombre;
    protected byte edad;

    public Persona(String nombre, byte edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getEdad() {
        return edad;
    }
}