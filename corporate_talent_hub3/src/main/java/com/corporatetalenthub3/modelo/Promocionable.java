package com.corporatetalenthub3.modelo;


public interface Promocionable {
    double calcularBonoAscenso();

    /**
     * Evolución de interfaces (Java 8+): Permite añadir nuevos comportamientos 
     * con una implementación por defecto sin romper las clases que ya implementan la interfaz.
     */
    default void registrarLogPromocion(String mensaje) {
        System.out.println("[LOG TÉCNICO - PROMO]: " + mensaje);
    }
}