package boletin01;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class E7_Agenda {

    public static void main(String[] args) {
        
        // Usamos TreeMap para que los nombres se ordenen solos alfabéticamente
        Map<String, String> agenda = new TreeMap<>();
        int MAX_CONTACTOS = 20;
        String nombreArchivo = "Agenda.txt";

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        // 1. Cargamos los datos del archivo al iniciar
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea; 
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 2) {
                        agenda.put(partes[0], partes[1]);
                    }
                }
                br.close();
                System.out.println("Datos cargados correctamente desde " + nombreArchivo);
            } catch (IOException e) {
                System.out.println("No se pudo cargar el archivo.");
            }
        }

        // 2. Bucle del menú
        do {
            System.out.println("====================");
            System.out.println("       AGENDA       ");
            System.out.println("====================");
            System.out.println("1. Nuevo contacto");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Mostrar todos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción (1-4): ");

            // Leemos la opción del usuario
            opcion = teclado.nextInt();
            teclado.nextLine(); // Salto de línea

            switch (opcion) {
                case 1:
                    if (agenda.size() >= MAX_CONTACTOS) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.print("Introduce el nombre: ");
                        String nombre = teclado.nextLine();
                        
                        System.out.print("Introduce el teléfono: ");
                        String telefono = teclado.nextLine();

                        if (agenda.containsKey(nombre)) {
                            System.out.println("Ese contacto ya existe en la agenda.");
                        } else {
                            agenda.put(nombre, telefono);
                            System.out.println("Contacto añadido correctamente.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Introduce el nombre a buscar: ");
                    String nombreBusqueda = teclado.nextLine();

                    if (agenda.containsKey(nombreBusqueda)) {
                        System.out.println("El teléfono de " + nombreBusqueda + " es: " + agenda.get(nombreBusqueda));
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;

                case 3:
                    if (agenda.isEmpty()) {
                        System.out.println("La agenda está vacía.");
                    } else {
                        System.out.println("--- LISTA DE CONTACTOS ---");
                        for (String clave : agenda.keySet()) {
                            System.out.println("Nombre: " + clave + " | Teléfono: " + agenda.get(clave));
                        }
                    }
                    break;

                case 4:
                    // 3. Guardamos los datos en el archivo antes de salir
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
                        for (String clave : agenda.keySet()) {
                            bw.write(clave + "," + agenda.get(clave) + "\n");
                        }
                        bw.close();
                        System.out.println("Datos guardados en " + nombreArchivo);
                    } catch (IOException e) {
                        System.out.println("Error al guardar el archivo.");
                    }
                    System.out.println("Saliendo de la agenda. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println(); // Espacio para separar menús
        } while (opcion != 4);

        claseCerrarScanner(teclado);
    }

    private static void claseCerrarScanner(Scanner teclado) {
        if (teclado != null) {
            teclado.close();
        }
    }
}