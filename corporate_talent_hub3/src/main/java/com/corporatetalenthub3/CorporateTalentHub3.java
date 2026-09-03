/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.corporatetalenthub3;

import com.corporatetalenthub3.modelo.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CorporateTalentHub3 {

    private static final int CANTIDAD_TRIMESTRES = 3;
    private static final double NOTA_MINIMA = 0.0;
    private static final double NOTA_MAXIMA = 100.0;
    private static final double PROMEDIO_PARA_PROMOCION = 80.0;

    public static void main(String[] args) {
        List<String> tecnologias = List.of("Java", "Spring Boot", "PostgreSQL", "JavaScript");
        Map<String, String> sedes = Map.of(
                "S1", "Sede Principal",
                "S2", "Sede Norte"
        );

        System.out.println("--- Configuración Cargada ---");
        System.out.println("Tecnologías soportadas: " + tecnologias);
        System.out.println("Sedes activas: " + sedes.values() + "\n");

        try (var scanner = new Scanner(System.in)) {
            ArrayList<Empleado> listaEmpleados = new ArrayList<>();
            HashMap<Integer, Empleado> mapaEmpleados = new HashMap<>();
            ArrayList<double[]> listaCalificaciones = new ArrayList<>();
            
            var sistemaActivo = true;

            do {
                mostrarMenu();

                try {
                    System.out.print("Seleccione una opción: ");
                    var opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            registrarEmpleado(scanner, listaEmpleados, mapaEmpleados, listaCalificaciones);
                            break;
                        case 2:
                            mostrarReporte(listaEmpleados, listaCalificaciones);
                            break;
                        case 3:
                            mostrarCategoriasSalariales();
                            break;
                        case 4:
                            probarSequencedCollections(listaEmpleados);
                            break;
                        case 5:
                            filtrarEmpleadosBajoDesempeno(listaEmpleados, mapaEmpleados);
                            break;
                        case 6:
                            probarPatternMatchingRoles(listaEmpleados);
                            break;
                        case 0:
                            sistemaActivo = false;
                            System.out.println("Sesión finalizada.");
                            break;
                        default:
                            System.out.println("Opción fuera del menú.");
                            break;
                    }
                } catch (InputMismatchException excepcion) {
                    System.out.println("Entrada inválida. Debe escribir un valor numérico del tipo solicitado.");
                    scanner.nextLine();
                }
            } while (sistemaActivo);
        }
    }

    private static void mostrarMenu() {
        System.out.println("""

                =====================================
                    CORPORATE TALENT HUB (JAVA 21)
                =====================================
                1. Registrar empleado y calificaciones
                2. Mostrar reporte de desempeño (con Records)
                3. Consultar categorías salariales
                4. Ver primero, último y orden inverso 
                5. Filtrar empleados con promedio 
                6. Ver detalles por rol (Pattern Matching)
                0. Salir
                """);
    }

    private static void registrarEmpleado(
            Scanner scanner,
            ArrayList<Empleado> listaEmpleados,
            HashMap<Integer, Empleado> mapaEmpleados,
            ArrayList<double[]> listaCalificaciones) {

        System.out.print("ID positivo: ");
        var id = scanner.nextInt();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("El ID debe ser mayor que cero.");
            return;
        } else if (mapaEmpleados.containsKey(id)) {
            System.out.println("Ya existe un empleado con ese ID.");
            return;
        }

        System.out.print("Nombre: ");
        var nombre = scanner.nextLine().trim();

        if (nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Edad entre 18 y 100: ");
        var edadIngresada = scanner.nextInt();

        if (edadIngresada < 18 || edadIngresada > 100) {
            System.out.println("La edad está fuera del rango permitido.");
            scanner.nextLine();
            return;
        }
        var edad = (byte) edadIngresada;

        System.out.print("Salario mayor que cero: ");
        var salario = scanner.nextDouble();

        if (salario <= 0) {
            System.out.println("El salario debe ser mayor que cero.");
            scanner.nextLine();
            return;
        }

        System.out.println("Seleccione rol: 1. Desarrollador | 2. Gerente | 3. Estándar");
        var tipoRol = scanner.nextInt();
        scanner.nextLine();

        Empleado nuevoEmpleado;
        if (tipoRol == 1) {
            System.out.print("Lenguaje principal: ");
            var lenguaje = scanner.nextLine();
            nuevoEmpleado = new Desarrollador(id, nombre, edad, salario, lenguaje);
        } else if (tipoRol == 2) {
            System.out.print("Presupuesto mensual asignado: ");
            var presupuesto = scanner.nextDouble();
            scanner.nextLine();
            nuevoEmpleado = new Gerente(id, nombre, edad, salario, presupuesto);
        } else {
            nuevoEmpleado = new Empleado(id, nombre, edad, salario);
        }

        double[] calificacionesEmpleado = new double[CANTIDAD_TRIMESTRES];
        for (var trimestre = 0; trimestre < CANTIDAD_TRIMESTRES; trimestre++) {
            System.out.printf("Calificación del trimestre %d (0 a 100): ", trimestre + 1);
            var calificacion = scanner.nextDouble();

            if (calificacion < NOTA_MINIMA || calificacion > NOTA_MAXIMA) {
                System.out.println("La calificación está fuera del rango permitido.");
                scanner.nextLine();
                return;
            }
            calificacionesEmpleado[trimestre] = calificacion;
        }
        scanner.nextLine();

        listaEmpleados.add(nuevoEmpleado);
        mapaEmpleados.put(id, nuevoEmpleado);
        listaCalificaciones.add(calificacionesEmpleado);

        System.out.println("Empleado registrado correctamente.");
    }

    private static void mostrarReporte(
            ArrayList<Empleado> listaEmpleados,
            ArrayList<double[]> listaCalificaciones) {

        if (listaEmpleados.isEmpty()) {
            System.out.println("Todavía no hay empleados registrados.");
            return;
        }

        System.out.println("\n--- REPORTE DE DESEMPEÑO (USANDO RECORDS) ---");

        for (var i = 0; i < listaEmpleados.size(); i++) {
            var emp = listaEmpleados.get(i);
            var calificacionesEmpleado = listaCalificaciones.get(i);
            var suma = 0.0;

            for (var calificacion : calificacionesEmpleado) {
                suma += calificacion;
            }

            var promedio = suma / CANTIDAD_TRIMESTRES;
            emp.setPromedioDesempeno(promedio);

            var feedback = promedio >= PROMEDIO_PARA_PROMOCION 
                    ? "Aprobado para ascenso" 
                    : "Requiere mejora";
            
            // Creación del Record inmutable para el reporte
            var reporte = new DesempenoReport(emp.getId(), promedio, feedback);
            var categoria = obtenerCategoriaSalarial(emp.getSalario());

            System.out.printf("ID: %d | Nombre: %s | Promedio: %.2f | Feedback: %s | Categoría: %s | Bono: %.2f%n",
                    reporte.idEmpleado(),
                    emp.getNombre(),
                    reporte.promedio(),
                    reporte.feedback(),
                    categoria,
                    emp.calcularBonoAscenso());
        }
    }

    // ==========================================
    // TASK 3: Pattern Matching for instanceof (Java 17/21)
    // ==========================================
    private static void probarPatternMatchingRoles(ArrayList<Empleado> listaEmpleados) {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        System.out.println("\n--- DETALLES ESPECÍFICOS POR ROL (PATTERN MATCHING) ---");
        for (var emp : listaEmpleados) {
            // Estilo Moderno (Java 17/21) con Pattern Matching (evita el casting manual obligatorio)
            if (emp instanceof Desarrollador des) {
                System.out.println("-> Desarrollador: " + des.getNombre() + " | Lenguaje: " + des.getLenguajePrincipal());
            } else if (emp instanceof Gerente ger) {
                System.out.println("-> Gerente: " + ger.getNombre() + " | Presupuesto: $" + ger.getPresupuestoMensual());
            } else {
                System.out.println("-> Empleado estándar: " + emp.getNombre());
            }
        }
    }

    private static void probarSequencedCollections(ArrayList<Empleado> listaEmpleados) {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados para probar Sequenced Collections.");
            return;
        }

        System.out.println("\n--- DEMO JAVA 21: SEQUENCED COLLECTIONS ---");
        var primerEmpleado = listaEmpleados.getFirst();
        var ultimoEmpleado = listaEmpleados.getLast();

        System.out.println("Primer empleado registrado: " + primerEmpleado.getNombre());
        System.out.println("Último empleado registrado: " + ultimoEmpleado.getNombre());

        System.out.println("\nRecorriendo la lista en orden inverso (método .reversed()):");
        for (var emp : listaEmpleados.reversed()) {
            System.out.println("-> " + emp.getNombre() + " (ID: " + emp.getId() + ")");
        }
    }

    private static void filtrarEmpleadosBajoDesempeno(
            ArrayList<Empleado> listaEmpleados, 
            HashMap<Integer, Empleado> mapaEmpleados) {
        
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados para filtrar.");
            return;
        }

        System.out.println("\n--- APLICANDO TASK 4: removeIf ---");
        System.out.println("Eliminando empleados con promedio menor a 80.0...");

        listaEmpleados.removeIf(emp -> emp.getPromedioDesempeno() < PROMEDIO_PARA_PROMOCION);
        mapaEmpleados.values().removeIf(emp -> emp.getPromedioDesempeno() < PROMEDIO_PARA_PROMOCION);

        System.out.println("Filtrado completado. Quedan " + listaEmpleados.size() + " empleados aprobados.");
    }

    public static String obtenerCategoriaSalarial(double salario) {
        var rango = determinarRangoSalarial(salario);

        return switch (rango) {
            case 1 -> "JUNIOR";
            case 2 -> "SEMISENIOR";
            case 3 -> "SENIOR";
            case 4 -> "LÍDER";
            default -> throw new IllegalArgumentException(
                    "Rango salarial no reconocido: " + rango);
        };
    }

    private static int determinarRangoSalarial(double salario) {
        if (salario < 2_000_000.0) {
            return 1;
        } else if (salario < 4_000_000.0) {
            return 2;
        } else if (salario < 7_000_000.0) {
            return 3;
        } else {
            return 4;
        }
    }

    private static void mostrarCategoriasSalariales() {
        System.out.println("""
                Categorías:
                - Menos de $2.000.000: JUNIOR
                - Desde $2.000.000 y menos de $4.000.000: SEMISENIOR
                - Desde $4.000.000 y menos de $7.000.000: SENIOR
                - Desde $7.000.000: LÍDER
                """);
    }
}
