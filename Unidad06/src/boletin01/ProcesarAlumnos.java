package boletin01;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ProcesarAlumnos {

    public static void main(String[] args) {
        File fichero = new File("src/boletin01/Alumnos.txt");
        
        int sumaEdades = 0; 
        double sumaAlturas = 0; 
        int contador = 0;
        
        try (Scanner sc = new Scanner(fichero)) {
            sc.useLocale(Locale.US);

            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim(); // .trim() quita los espacios 

                // Si usamos ".split(" ")" lee los espacios
                String frase[] = linea.split(" ");
                
                System.out.println("Nombre: " + frase[0]);
                
                contador += 1;
                sumaEdades += Integer.parseInt(frase[1]);
                sumaAlturas += Double.parseDouble(frase[2]);
            }
         
            if (contador > 0) {
                System.out.println("\n       DATOS DE LOS ALUMNOS:        ");
                System.out.println("--------------------------------------");
                
                // Casteamos a (double) para que la división de la edad sea con menos decimales
                System.out.printf("Media aritmética de las edades: %.2f%n", ((double)sumaEdades / contador));
                System.out.printf("Media aritmética de las alturas: %.2f%n", (sumaAlturas / contador));
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encuentra el archivo Alumnos.txt");
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Revisa que no haya letras donde deben ir números.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Una línea del archivo no tiene los 3 datos requeridos.");
        }
    }
}