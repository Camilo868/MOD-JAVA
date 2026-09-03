package com.corporatetalenthub3.modelo;


public final class ConsultorExterno extends Persona {
    private final String empresaAsociada;

    public ConsultorExterno(String nombre, byte edad, String empresaAsociada) {
        super(nombre, edad);
        this.empresaAsociada = empresaAsociada;
    }

    public String getEmpresaAsociada() {
        return empresaAsociada;
    }
}