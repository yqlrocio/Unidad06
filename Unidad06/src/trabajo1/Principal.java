package trabajo1;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
        GestionPacientes gestion = new GestionPacientes();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n================================");
            System.out.println("      GESTIÓN DE PACIENTES        ");
            System.out.println("================================");
            System.out.println("1. Añadir paciente");
            System.out.println("2. Buscar paciente");
            System.out.println("3. Listar pacientes");
            System.out.println("4. Modificar paciente");
            System.out.println("5. Eliminar paciente");
            System.out.println("6. Guardar pacientes");
            System.out.println("7. Salir");
            System.out.print("Elige una opción (1-7): ");

            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Dirección: ");
                    String direccion = sc.nextLine();
                    System.out.print("Teléfono (9 dígitos): ");
                    String telefono = sc.nextLine();
                    
                    System.out.print("¿Está de baja? (sí/no): ");
                    String bajaStr = sc.nextLine();
                    boolean baja = bajaStr.equalsIgnoreCase("sí");

                    if (gestion.añadirPaciente(nombre, direccion, telefono, baja)) {
                        System.out.println("Paciente añadido correctamente.");
                    } else {
                        System.out.println("No se pudo añadir el paciente (puede estar duplicado o el teléfono es incorrecto).");
                    }
                    break;

                case 2:
                    System.out.print("Nombre a buscar: ");
                    String nombreBusqueda = sc.nextLine();
                    System.out.print("Teléfono del paciente: ");
                    String telefonoBusqueda = sc.nextLine();

                    Paciente p = gestion.buscarPaciente(nombreBusqueda, telefonoBusqueda);
                    if (p != null) {
                        System.out.println("\n" + p.toString());
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                    break;

                case 3:
                    gestion.listarPacientes();
                    break;

                case 4:
                    System.out.print("Nombre del paciente a modificar: ");
                    String nombreMod = sc.nextLine();
                    System.out.print("Teléfono del paciente: ");
                    String telMod = sc.nextLine();

                    Paciente pMod = gestion.buscarPaciente(nombreMod, telMod);
                    if (pMod != null) {
                        System.out.print("¿Qué dato quieres modificar? (direccion / baja): ");
                        String campo = sc.nextLine();

                        if (campo.equalsIgnoreCase("direccion")) {
                            System.out.print("Nueva dirección: ");
                            pMod.setDirección(sc.nextLine());
                            System.out.println("Dirección modificada correctamente.");
                        } else if (campo.equalsIgnoreCase("baja")) {
                            System.out.print("¿Está de baja? (s/n): ");
                            pMod.setBaja(sc.nextLine().equalsIgnoreCase("s"));
                            System.out.println("Estado de baja modificado correctamente.");
                        } else {
                            System.out.println("Opción no válida.");
                        }
                    } else {
                        System.out.println("El paciente indicado no existe.");
                    }
                    break;

                case 5:
                    System.out.print("Nombre del paciente a eliminar: ");
                    String nombreEli = sc.nextLine();
                    System.out.print("Teléfono del paciente: ");
                    String telEli = sc.nextLine();

                    if (gestion.eliminarPaciente(nombreEli, telEli)) {
                        System.out.println("Paciente eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró al paciente para eliminar.");
                    }
                    break;

                case 6:
                    gestion.guardarPacientes();
                    break;

                case 7:
                    System.out.println("Saliendo del sistema…");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        sc.close();
    }
}