package boletin01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class E8_RegistroTemperaturas {

    static class Registro {
        String fecha;
        int tempMax;
        int tempMin;

        /**
         * Constructor del registro de temperatura.
         * * @param fecha   Fecha del registro en formato año-mes-día.
         * @param tempMax Temperatura máxima registrada.
         * @param tempMin Temperatura mínima registrada.
         */
        public Registro(String fecha, int tempMax, int tempMin) {
            this.fecha = fecha;
            this.tempMax = tempMax;
            this.tempMin = tempMin;
        }

        /**
         * Devuelve una cadena que recoge la fehca, las temperaturas máximas y mínimas. 
         * * @return Información del registro.
         */
        @Override
        public String toString() {
            return "Fecha: " + fecha + " | Máx: " + tempMax + "ºC | Mín: " + tempMin + "ºC";
        }
    }

    /**
     * Método principal donde se ejecuta la aplicación.
     */
    public static void main(String[] args) {
        ArrayList<Registro> historial = new ArrayList<>();
        String nombreArchivo = "Temperaturas.txt";
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        // Cargar los datos del archivo al iniciar
        cargarDatos(historial, nombreArchivo);

        // Menú principal
        do {
            System.out.println("====================================");
            System.out.println("      REGISTRO DE TEMPERATURAS      ");
            System.out.println("====================================");
            System.out.println("1. Registrar nueva temperatura");
            System.out.println("2. Mostrar historial de registros");
            System.out.println("3. Salir");
            System.out.print("Elige una opción (1-3): ");

            opcion = teclado.nextInt();
            teclado.nextLine(); // Salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la fecha (Por ejemplo: 2020-01-10): ");
                    String fecha = teclado.nextLine();
                    
                    System.out.print("Introduce la temperatura máxima: ");
                    int tempMax = teclado.nextInt();
                    
                    System.out.print("Introduce la temperatura mínima: ");
                    int tempMin = teclado.nextInt();
                    
                    historial.add(new Registro(fecha, tempMax, tempMin));
                    System.out.println("Registro añadido correctamente.");
                    break;

                case 2:
                    if (historial.isEmpty()) {
                        System.out.println("No hay registros guardados.");
                    } else {
                        System.out.println("\n--- HISTORIAL DE REGISTROS ---");
                        int maxAbsoluta = Integer.MIN_VALUE;
                        int minAbsoluta = Integer.MAX_VALUE;

                        for (Registro r : historial) {
                            System.out.println(r);
                            if (r.tempMax > maxAbsoluta) {
                                maxAbsoluta = r.tempMax;
                            }
                            if (r.tempMin < minAbsoluta) {
                                minAbsoluta = r.tempMin;
                            }
                        }
                        
                        System.out.println("------------------------------");
                        System.out.println("Temperatura máxima más alta: " + maxAbsoluta + "ºC");
                        System.out.println("Temperatura mínima más baja: " + minAbsoluta + "ºC");
                    }
                    break;

                case 3:
                    guardarDatos(historial, nombreArchivo);
                    System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
            System.out.println();
        } while (opcion != 3);

        claseCerrarScanner(teclado);
    }

    /**
     * Lee las temperaturas guardadas en el archivo de texto y las añade a nuestra lista en memoria.
     * * @param historial Lista donde se almacenarán los datos en memoria.
     * @param archivo Archivo de texto externo, Temperaturas.txt.
     */
    private static void cargarDatos(ArrayList<Registro> historial, String archivo) {
        File f = new File(archivo);
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linea = br.readLine(); // Leer la cabecera
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length == 3) {
                        String fecha = partes[0];
                        int tempMax = Integer.parseInt(partes[1]);
                        int tempMin = Integer.parseInt(partes[2]);
                        historial.add(new Registro(fecha, tempMax, tempMin));
                    }
                }
                System.out.println("Datos cargados correctamente desde " + archivo);
            } catch (Exception e) {
                System.out.println("Error al leer el archivo de temperaturas.");
            }
        }
    }

    /**
     * Guarda los datos en el fichero de texto.
     * * @param historial Lista de registros a guardar.
     * @param archivo Archivo de texto externo, Temperaturas.txt.
     */
    private static void guardarDatos(ArrayList<Registro> historial, String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("Fecha,Temperatura máxima,Temperatura mínima");
            for (Registro r : historial) {
                pw.println(r.fecha + "," + r.tempMax + "," + r.tempMin);
            }
            System.out.println("Datos guardados correctamente en " + archivo);
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo de temperaturas.");
        }
    }

    private static void claseCerrarScanner(Scanner teclado) {
        if (teclado != null) {
            claseCerrarScannerAux(teclado);
        }
    }

    private static void claseCerrarScannerAux(Scanner teclado) {
        teclado.close();
    }
}
