package boletin02;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class E2_RegistroFirmas {

    public static void main(String[] args) {
        // Usamos un TreeSet para mantener los nombres ordenados y evitar repetidos
        Set<String> libroFirmas = new TreeSet<>();
        String nombreArchivo = "firmas.txt";
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        // 1. Cargamos las firmas existentes al iniciar el programa
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    // Quitamos los espacios en blanco sobrantes antes de añadir firmas
                    if (!linea.trim().isEmpty()) {
                        libroFirmas.add(linea.trim());
                    }
                }
                br.close();
                System.out.println("Libro de firmas cargado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al cargar el fichero de firmas.");
            }
        }

        // 2. Menú de la aplicación
        do {
            System.out.println("================================");
            System.out.println("        LIBRO DE FIRMAS         ");
            System.out.println("================================");
            System.out.println("1. Mostrar libro de firmas");
            System.out.println("2. Insertar nuevo nombre");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");

            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiamos el salto de línea del teclado

            switch (opcion) {
                case 1:
                    if (libroFirmas.isEmpty()) {
                        System.out.println("El libro de firmas está vacío.");
                    } else {
                        System.out.println("\n--- LISTA DE FIRMAS ---");
                        for (String nombre : libroFirmas) {
                            System.out.println("- " + nombre);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Introduce el nombre del nuevo visitante: ");
                    String nuevoNombre = teclado.nextLine().trim();

                    // Comprobamos si el nombre ya está en el libro
                    if (libroFirmas.contains(nuevoNombre)) {
                        System.out.println("Ese nombre ya ha firmado en el libro.");
                    } else {
                        // Añadimos y guardamos directamente en el fichero
                        libroFirmas.add(nuevoNombre);
                        guardarFirma(nuevoNombre, nombreArchivo);
                        System.out.println("Firma insertada correctamente.");
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del libro de firmas. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println(); // Espacio estético
        } while (opcion != 3);

        teclado.close();
    }

    /**
     * Guarda una nueva firma en el archivo de texto.
     * * @param nombre   Nombre de la persona que firma.
     * @param archivo  Archivo externo de texto.
     */
    private static void guardarFirma(String nombre, String archivo) {
        // Usamos 'true' en FileWriter para añadir la firma al final del archivo sin sobrescribir
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(nombre);
        } catch (IOException e) {
            System.out.println("Error al guardar la firma en el fichero.");
        }
    }
}
