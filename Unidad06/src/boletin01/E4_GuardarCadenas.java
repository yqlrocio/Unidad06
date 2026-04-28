package boletin01;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Scanner;

public class E4_GuardarCadenas {

    public static void main(String[] args) {

    	String nombreFichero = "CadenasUsuario.txt";
        
        try (Scanner teclado = new Scanner(System.in);
             FileWriter fw = new FileWriter(nombreFichero, true); // 'true' para no borrar lo anterior
             PrintWriter pw = new PrintWriter(fw)) {

            System.out.println("Introduce cadenas de texto (escribe 'fin' para terminar):");
            
            String cadena = "";
            boolean terminar = false;

            while (!terminar) {
                System.out.print("> ");
                cadena = teclado.nextLine();

                // Comprobamos si el usuario quiere terminar
                // Usamos equalsIgnoreCase para que acepte "fin", "FIN" o "Fin"
                if (cadena.equalsIgnoreCase("fin")) {
                    terminar = true;
                } else {
                    // Guardamos la cadena en el fichero con un salto de línea
                    pw.println(cadena);
                }
            }

            System.out.println("Proceso finalizado. Las cadenas se han guardado en: " + nombreFichero);

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }
}