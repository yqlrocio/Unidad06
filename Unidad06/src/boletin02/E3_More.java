package boletin02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class E3_More {

    public static void main(String[] args) {

    	String nombreFichero = "carta.txt"; 
        File fichero = new File(nombreFichero);
        
        // Comprobamos si el fichero existe antes de leerlo
        if (!fichero.exists()) {
            System.out.println("Error: No se encuentra el fichero " + nombreFichero);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero));
             Scanner teclado = new Scanner(System.in)) {

            String linea;
            int contadorLineas = 0;

            // Leemos el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                contadorLineas++;

                // Cuando llegamos a 24 líneas (o múltiplos), hacemos una pausa
                if (contadorLineas % 24 == 0) {
                    System.out.println("--- Pulse ENTER para continuar ---");
                    teclado.nextLine(); // Espera a que el usuario pulse la tecla Intro
                }
            }

            System.out.println("\n--- Fin del fichero ---");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}