package com.riwi.talent.view;


import com.riwi.talent.controller.TalentController;
import com.riwi.talent.modelo.Empleado;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final TalentController controller;
    private final Scanner scanner;

    public ConsoleView() {
        this.controller = new TalentController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        var activo = true;
        do {
            mostrarMenu();
            try {
                var opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> listar();
                    case 3 -> actualizar();
                    case 4 -> eliminar();
                    case 5 -> controller.generarReporteConsolidado(80.0);
                    case 0 -> {
                        activo = false;
                        System.out.println("Sesión finalizada. ¡Hasta luego!");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingrese un valor numérico.");
                scanner.nextLine();
            }
        } while (activo);
    }

    private void mostrarMenu() {
        System.out.println("""

                =====================================
                    CORPORATE TALENT HUB - DB EDITION
                =====================================
                1. Registrar empleado y calificaciones
                2. Listar empleados (desde BD)
                3. Actualizar empleado
                4. Eliminar empleado
                5. Ver reporte consolidado (Text Blocks)
                0. Salir
                Seleccione una opción:""");
    }

    private void registrar() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad (18-100): ");
        byte edad = scanner.nextByte();
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();

        System.out.print("Rol (1. Desarrollador, 2. Gerente, 3. Estándar): ");
        int rol = scanner.nextInt();
        scanner.nextLine();

        String extra = null;
        if (rol == 1) {
            System.out.print("Lenguaje principal: ");
            extra = scanner.nextLine();
        } else if (rol == 2) {
            System.out.print("Presupuesto mensual: ");
            extra = scanner.next();
        }

        double[] calificaciones = new double[3];
        for (int i = 0; i < 3; i++) {
            System.out.printf("Calificación trimestre %d (0-100): ", i + 1);
            calificaciones[i] = scanner.nextDouble();
        }
        scanner.nextLine();

        if (controller.registrarEmpleado(id, nombre, edad, salario, rol, extra, calificaciones)) {
            System.out.println("¡Empleado registrado con éxito en la base de datos!");
        } else {
            System.out.println("No se pudo registrar el empleado.");
        }
    }

    private void listar() {
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        for (Empleado e : controller.obtenerEmpleados()) {
            System.out.printf("ID: %d | Nombre: %s | Edad: %d | Salario: %.2f%n",
                    e.getId(), e.getNombre(), e.getEdad(), e.getSalario());
        }
    }

    private void actualizar() {
        System.out.print("ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva edad: ");
        byte edad = scanner.nextByte();
        System.out.print("Nuevo salario: ");
        double salario = scanner.nextDouble();

        if (controller.actualizarEmpleado(id, nombre, edad, salario)) {
            System.out.println("Empleado actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el empleado.");
        }
    }

    private void eliminar() {
        System.out.print("ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (controller.eliminarEmpleado(id)) {
            System.out.println("Empleado eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el empleado.");
        }
    }
}