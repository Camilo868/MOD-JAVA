/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corporatetalenthub.modelo;

/**
 *Modelo tradicional compatible con la sintaxis de Java 8.
 *
 * Esta clase es más verbosa que un record porque declara campos, constructor, 
 * getters, setters y métodos explicitamente. Esa verbosidad es útil cuando el
 * objeto necesita un estado mutable, como bonoMensual o nombre.
 * 
 */
public class Empleado {
    // Los 8 tipos primitivos :
    private byte nivelAcceso;
    private short anioIngreso;
    private int idEmpleado;
    private long numeroDocumento;
    private float puntajeTest;
    private double salarioBase;
    private char tipoContrato;
    private boolean esActivo;
    
    // String no es primitivo: es una clase y est variable guarda una referencia.
    private String nombre;
    
    //Datos adicionales necesarios para las reglas de negocio.
    private int edad;
    private int idSede;
    private double bonoMensual;
    
    public Empleado (
        byte nivelAcceso,
        short anioIngreso,
        int idEmpleado,
        long numeroDocumento,
        float puntajeTest,
        double salarioBase,
        char tipoContrato,
        boolean esActivo,
        String nombre,
        int edad,
        int idSede,
        double bonoMensual){
        this.nivelAcceso = nivelAcceso;
        this.anioIngreso = anioIngreso;
        this.idEmpleado = idEmpleado;
        this.numeroDocumento = numeroDocumento;
        this.puntajeTest = puntajeTest;
        this.salarioBase = salarioBase;
        this.tipoContrato = tipoContrato;
        this.esActivo = esActivo;
        this.nombre = nombre;
        this.edad = edad;
        this.idSede = idSede;
        this.bonoMensual = bonoMensual;
        
    }
    
    public double calcularSalarioFinal(){
        // Orden: paréntesis internos -> multiplicaciones -> suma -> resta.
        // 1. bonoMensual * 1.10
        // 2. salarioBase + resultado anterior
        // 3. salarioBase * 0.05
        // 4. resta de ambos resultados
        return (salarioBase + (bonoMensual*1.10)-(salarioBase*0.05));
    }
    
    public boolean tieneBonoExtra (){
        
        return idEmpleado % 2==0;
    }
    
    public boolean validarElegibilidad(){
        return (puntajeTest > 85 && edad <30) || (idSede == 1 && !esActivo);
    }
    
    public void actualizarBonoMensual(double incremento){
        bonoMensual += incremento;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getIdEmpleado(){
        return idEmpleado;
    }
    
    public double getBonoMensual(){
        return bonoMensual;
    } 
    
    @Override
    public String toString () {
        return "Empleado {" +
                "Nivel de acceso = "+nivelAcceso+
                ", Año de Ingreso = "+anioIngreso+
                ", idEmpleado = " +idEmpleado+
                ", Numero de documento = "+numeroDocumento+
                ", Puntaje del test = "+puntajeTest+
                ", Sala base = "+salarioBase+
                ", Tipo de contrato = "+tipoContrato+
                ", Es activo = " +esActivo+
                ", Nombre = "+nombre+'\''+
                ", Edad = " + edad +
                ", Id Sede = "+idSede+
                ", Bono Mensual = "+ bonoMensual+
                '}';
                
    }
   
}
