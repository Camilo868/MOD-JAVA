package com.corporatetalenthub3.modelo;


public final class Gerente extends Empleado {
    private final double presupuestoMensual;

    public Gerente(int id, String nombre, byte edad, double salario, double presupuestoMensual) {
        super(id, nombre, edad, salario);
        this.presupuestoMensual = presupuestoMensual;
    }

    public double getPresupuestoMensual() {
        return presupuestoMensual;
    }

    @Override
    public double calcularBonoAscenso() {
        registrarLogPromocion("Calculando bono de ascenso gerencial");
        return getSalario() * 0.30; // 30% para gerentes
    }
}