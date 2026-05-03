package boletin02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class E5_Comp {

    public static void main(String[] args) {

    	File archivo1 = new File("archivo1.txt");
        File archivo2 = new File("archivo2.txt");

        // Comprobamos que ambos ficheros existen
        if (!archivo1.exists() || !archivo2.exists()) {
            System.out.println("Error: Uno de los ficheros no existe.");
            return;
        }

        try (BufferedReader br1 = new BufferedReader(new FileReader(archivo1));
             BufferedReader br2 = new BufferedReader(new FileReader(archivo2))) {

            String linea1 = "";
            String linea2 = "";
            int numeroLinea = 0;
            boolean sonIguales = true;

            // Leemos ambos ficheros línea a línea 
            while ((linea1 = br1.readLine()) != null | (linea2 = br2.readLine()) != null) {
                
                // Si uno de los ficheros se acaba antes que el otro
                if (linea1 == null) {
                    System.out.println("Los ficheros son distintos. El archivo 1 es más corto.");
                    return;
                } else if (linea2 == null) {
                    System.out.println("Los ficheros son distintos. El archivo 2 es más corto.");
                    return;
                }

                numeroLinea++;

                // Comparamos las dos líneas carácter a carácter
                int longitudMinima = Math.min(linea1.length(), linea2.length());

                for (int i = 0; i < longitudMinima; i++) {
                    if (linea1.charAt(i) != linea2.charAt(i)) {
                        System.out.println("Los ficheros son DISTINTOS.");
                        // Sumamos +1 para que el carácter sea más legible para el usuario (empieza en 1)
                        System.out.println("Primera diferencia en la línea " + numeroLinea + ", carácter " + (i + 1) + ".");
                        sonIguales = false;
                        return; // Salimos del programa tras encontrar la primera diferencia
                    }
                }

                // Si una línea es más larga que la otra pero coinciden en los caracteres comunes
                if (linea1.length() != linea2.length()) {
                    System.out.println("Los ficheros son DISTINTOS.");
                    System.out.println("Primera diferencia en la línea " + numeroLinea + ", carácter " + (longitudMinima + 1) + ".");
                    return;
                }
            }

            // Si el bucle termina sin diferencias
            if (sonIguales) {
                System.out.println("Los ficheros son IGUALES.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer los ficheros: " + e.getMessage());
        }
    }
}