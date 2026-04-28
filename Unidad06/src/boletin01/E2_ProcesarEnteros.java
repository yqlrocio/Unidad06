package boletin01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class E2_ProcesarEnteros {

	public static void main(String[] args) {

        File fichero = new File("src/boletin01/Enteros.txt");
        
        double suma = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(fichero)) {
            // Usamos Locale.US para asegurar que el punto (.) se use como separador decimal
            sc.useLocale(Locale.US);

            while (sc.hasNextDouble()) {
                suma += sc.nextDouble();
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("       Resultados:       ");
                System.out.println("-------------------------");
                System.out.println("Suma total: " + suma);
                System.out.println("Media aritmética: " + media);
            } else {
                System.out.println("El fichero está vacío o no contiene números válidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se pudo encontrar el archivo en la ruta especificada.");
            e.printStackTrace();
        }
    }
}