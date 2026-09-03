package com.riwi.talent.modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/corporate_talent_hub";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres123";

    /* 
     * COMPARATIVA TÉCNICA (Legacy vs Modern):
     * - Estilo Legacy (Java 8 hacia atrás): Se usaban bloques 'finally' anidados para cerrar 
     *   manualmente Connection, PreparedStatement y ResultSet. Si ocurría una excepción al cerrar 
     *   el primer recurso, los siguientes no se cerraban, provocando graves fugas de memoria y 
     *   de recursos (Connection Leaks) que saturaban el pool de la base de datos.
     * - Estilo Moderno (Java 17/21): El bloque 'try-with-resources' implementa la interfaz 
     *   AutoCloseable. Java garantiza el cierre automático de los recursos en orden inverso 
     *   a su apertura, incluso si ocurren excepciones, previniendo fugas de forma limpia y segura.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}