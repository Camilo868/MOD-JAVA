/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.corporatetalenthub.modelo;

/**
 *
 * @author Coder
 * 
 * Un Record reduce la verbosidad: Java genera constructor, accesores,
 * equals, hashCode y toString a partir de sus componentes.
 * 
 * Sus componentes son inmutables: despues  de construir el record no se pueden 
 * reasignar. La inmutabilidad es superficial; si un componente fuera un objeto 
 * mutable, su contenido aun podria cambiar
 * 
 * 
 */

    // Record moderno (Java17/21)
    public record EmpresaRecord(
            String nombre,
            String nit,
            int anioFundacion){
}

/** 
 * Un Record no genera metodos con nombres getNombre() o getNit(). Sus accesores son,
 * nit() y anioFundacion(). 
 *
 */ 


