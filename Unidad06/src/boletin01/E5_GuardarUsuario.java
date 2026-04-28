package boletin01;

import java.io.*;
import java.util.Scanner;

public class E5_GuardarUsuario {

File fichero = new File("src/boletin01/Datos.txt");

     public static void main(String[] args) {
         Scanner teclado = new Scanner(System.in);

         // 1. Pedir datos al usuario
         System.out.print("Introduce tu nombre: ");
         String nombre = teclado.nextLine();

         System.out.print("Introduce tu edad: ");
         String edad = teclado.nextLine();

         // 2. Guardar en el archivo
         // El true en new FileWriter("datos.txt", true) activa el modo append
         try (FileWriter fw = new FileWriter("Datos.txt", true);
              PrintWriter pw = new PrintWriter(fw)) {
             
             pw.println(nombre + ", " + edad);
             System.out.println("Datos guardados con éxito.");

         } catch (IOException e) {
             System.out.println("Error al manejar el archivo: " + e.getMessage());
         } finally {
             teclado.close();
         }
	}
}	 