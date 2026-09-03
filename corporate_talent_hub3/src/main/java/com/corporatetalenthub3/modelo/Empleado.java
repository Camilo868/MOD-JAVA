/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corporatetalenthub3.modelo;

public non-sealed class Empleado extends Persona implements Promocionable {
    private final int id;
    private final double salario;
    private double promedioDesempeno;

    public Empleado(int id, String nombre, byte edad, double salario) {
        super(nombre, edad);
        this.id = id;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public double getPromedioDesempeno() {
        return promedioDesempeno;
    }

    public void setPromedioDesempeno(double promedioDesempeno) {
        this.promedioDesempeno = promedioDesempeno;
    }

    @Override
    public double calcularBonoAscenso() {
        return salario * 0.15; // Bono base del 15%
    }
}