package boletin02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class E1_ContadorCaracteres {

    public static void main(String[] args) {
        // Ruta del fichero que vamos a leer (se puede ajustar la ruta si es necesario)
        File fichero = new File("carta.txt");

        int contadorCaracteres = 0;
        int contadorLineas = 0;
        int contadorPalabras = 0;

        try (Scanner sc = new Scanner(fichero)) {
            
            // Leemos el fichero línea por línea
            while (sc.hasNextLine()) {
                String lineaActual = sc.nextLine();
                contadorLineas++; // Sumamos una línea por cada iteración

                // Sumamos la longitud de la línea (incluyendo la longitud del salto de línea)
                contadorCaracteres += lineaActual.length() + 1;

                // Para contar las palabras en esta línea, usamos otro Scanner interno
                Scanner scanner = new Scanner(lineaActual);
                while (scanner.hasNext()) {
                    scanner.next(); // Avanzamos a la siguiente palabra
                    contadorPalabras++; // Contamos cada palabra
                }
                scanner.close();
            }

            // Mostramos los resultados por pantalla
            System.out.println("Resultados del fichero carta.txt:");
            System.out.println("---------------------------------");
            System.out.println("Número de líneas: " + contadorLineas);
            System.out.println("Número de palabras: " + contadorPalabras);
            System.out.println("Número de caracteres: " + contadorCaracteres);

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encuentra el fichero carta.txt.");
            // e.printStackTrace(); // Úsalo si tu profesor te lo pide para depurar
        }
    }
}