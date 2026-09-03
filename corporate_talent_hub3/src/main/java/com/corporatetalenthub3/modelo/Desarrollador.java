package com.corporatetalenthub3.modelo;


public final class Desarrollador extends Empleado {
    private final String lenguajePrincipal;

    public Desarrollador(int id, String nombre, byte edad, double salario, String lenguajePrincipal) {
        super(id, nombre, edad, salario);
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    @Override
    public double calcularBonoAscenso() {
        registrarLogPromocion("Calculando bono de ascenso para Desarrollador en " + lenguajePrincipal);
        return getSalario() * 0.20; // 20% para desarrolladores
    }
}