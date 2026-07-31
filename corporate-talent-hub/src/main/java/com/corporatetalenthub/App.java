/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corporatetalenthub;

import com.corporatetalenthub.modelo.Empleado;
import com.corporatetalenthub.modelo.EmpresaRecord;
/**
 *
 * @author Coder
 */
public class App {
    public static void main (String [] args){
        String encabezado = """
              ===================================
                    CORPORATE TALENT HUB
                 Gestion del talento humano
              ===================================
              """;
        System.out.println(encabezado);
        Empleado empleado= crearEmpleadoDePrueba();
        EmpresaRecord empresa = new EmpresaRecord(
        "codeUp Solutions",
        "987602345-1",
        2015);
        
        System.out.println(empleado);
        System.out.println("Empresa: "+ empresa.nombre());
        System.out.println("Salario final: " +empleado.calcularSalarioFinal());
        System.out.println("¿El ID es par con bono extra?: " +empleado.tieneBonoExtra());
        System.out.println("¿Empleado elegible?: "+empleado.validarElegibilidad());
        
        if (empleado.tieneBonoExtra()){
            empleado.actualizarBonoMensual(100_000.0);
            System.out.println("Bono actualizado con += " + empleado.getBonoMensual());
        }
        
        compararReferencias() ;
        ejecutarLaboratorioDeNulos(empleado);
    }
    private static Empleado crearEmpleadoDePrueba(){
        return new Empleado(
                (byte) 3,
                (short) 2024,
                102,
                1_023_456_789,
                92.5F,
                3_000_000.0,
                'I',
                true,
                "Laura Jimenez",
                27,
                2,
                500_000.0);
    }
    private static void compararReferencias() {
        Empleado primero = crearEmpleadoDePrueba();
        Empleado segundo = crearEmpleadoDePrueba();
        Empleado aliasPrimero = primero;
        
        System.out.println("Primero == Segundo: " + (primero == segundo));
        System.out.println("Primero == aliasPrimero: " + (primero == aliasPrimero) );
    }
    
    private static void ejecutarLaboratorioDeNulos(Empleado empleado){
        empleado.setNombre(null);
        try {
            System.out.println(empleado.getNombre().toUpperCase());
        }catch (NullPointerException exception) {
            System.out.println("NPE controlada: " + exception.getMessage());
            
        }
    }   
}
