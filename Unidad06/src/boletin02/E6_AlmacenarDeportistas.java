package boletin02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class E6_AlmacenarDeportistas {

    public static void main(String[] args) {

    	String nombreArchivo = "deportistas.txt";
        File archivo = new File(nombreArchivo);

        // Variables para almacenar los datos del deportista más alto en cada categoría
        String nombreMayorEdad = "";
        int edadMaxima = -1;

        String nombreMayorPeso = "";
        double pesoMaximo = -1.0;

        String nombreMayorEstatura = "";
        double estaturaMaxima = -1.0;

        // Comprobamos si el fichero existe
        if (!archivo.exists()) {
            System.out.println("Error: No se encuentra el fichero " + nombreArchivo);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            
            String linea = br.readLine(); // Leemos y descartamos la primera línea (el encabezamiento)

            while ((linea = br.readLine()) != null) {

            	String[] partes = linea.split(",");
                
                if (partes.length == 4) {
                    String nombre = partes[0].trim();
                    int edad = Integer.parseInt(partes[1].trim());
                    double peso = Double.parseDouble(partes[2].trim());
                    double estatura = Double.parseDouble(partes[3].trim());

                    // 1. Comprobamos si es la mayor edad
                    if (edad > edadMaxima) {
                        edadMaxima = edad;
                        nombreMayorEdad = nombre;
                    }

                    // 2. Comprobamos si es el mayor peso
                    if (peso > pesoMaximo) {
                        MaximoPeso(peso, nombre);
                        MaximoPesoUpdate(pesoMaximo, peso, nombre); // Actualizamos valores internamente
                        pesoMaximo = peso;
                        nombreMayorPeso = nombre;
                    }

                    // 3. Comprobamos si es la mayor estatura
                    if (estatura > estaturaMaxima) {
                        estaturaMaxima = estatura;
                        nombreMayorEstatura = nombre;
                    }
                }
            }

            // Imprimimos los resultados por pantalla
            System.out.println("--------------------------------");
            System.out.println(" Resultados de los deportistas: ");
            System.out.println("--------------------------------");
            System.out.println("Deportista de mayor edad: " + nombreMayorEdad + " (" + edadMaxima + " años)");
            System.out.println("Deportista de mayor peso: " + nombreMayorPeso + " (" + pesoMaximo + " kg)");
            System.out.println("Deportista de mayor estatura: " + nombreMayorEstatura + " (" + estaturaMaxima + " m)");

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los datos del fichero.");
        }
    }

    private static void MaximoPeso(double p, String n) {
    }

    private static void MaximoPesoUpdate(double max, double p, String n) {
    }
}