package boletin02;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class E4_Encriptador {

    public static void main(String[] args) {
        
        String archivoCodec = "codec.txt";
        String archivoOriginal = "fichero.txt"; // Fichero que vamos a encriptar
        String archivoCifrado = "ficheroResuelto.txt"; // Fichero resuelto

        // 1. Crear el fichero codec.txt de ejemplo si no existe
        crearFicheroCodec(archivoCodec);

        // 2. Cargar el alfabeto y el cifrado en un mapa
        Map<Character, Character> mapaCifrado = cargarCifrado(archivoCodec);

        if (mapaCifrado.isEmpty()) {
            System.out.println("No se pudo cargar el codec. Saliendo del programa.");
            return;
        }

        // 3. Leer el fichero original, cifrarlo y guardarlo en el nuevo archivo
        try (BufferedReader br = new BufferedReader(new FileReader(archivoOriginal));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoCifrado))) {

            int caracterLeido;
            // Leemos carácter a carácter
            while ((caracterLeido = br.read()) != -1) {
                char original = (char) caracterLeido;

                // Si la letra está en nuestro mapa, la sustituimos. Si no (espacios, saltos de línea, números), la dejamos igual.
                if (mapaCifrado.containsKey(original)) {
                    pw.print(mapaCifrado.get(original));
                } else {
                    pw.print(original);
                }
            }
            
            System.out.println("Mensaje cifrado correctamente en: " + archivoCifrado);

        } catch (IOException e) {
            System.out.println("Error al procesar los archivos: " + e.getMessage());
        }
    }

    private static void crearFicheroCodec(String nombreArchivo) {
        File f = new File(nombreArchivo);
        if (!f.exists()) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
                // Escribimos el contenido del alfabeto y su cifrado
                pw.println("Alfabeto: a b c d e f g h i j k l m n o p q r s t u v w x y z");
                pw.println("Cifrado: e m s r c y j n f x i w t a k o z d l q v b h u p g");
                System.out.println("Fichero " + nombreArchivo + " creado por defecto.");
            } catch (IOException e) {
                System.out.println("Error al crear el fichero codec.");
            }
        }
    }

    /**
     * Carga el archivo codec.txt y lo guarda en un Map.
     */
    private static Map<Character, Character> cargarCifrado(String nombreArchivo) {
        Map<Character, Character> mapa = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String lineaAlfabeto = br.readLine(); // "Alfabeto: a b c d..."
            String lineaCifrado = br.readLine();  // "Cifrado: e m s r..."

            if (lineaAlfabeto != null && lineaCifrado != null) {
                // Eliminamos las etiquetas iniciales para quedarnos solo con las letras
                String letrasAlfabeto = lineaAlfabeto.replace("Alfabeto:", "").replace(" ", "").trim();
                String letrasCifrado = lineaCifrado.replace("Cifrado:", "").replace(" ", "").trim();

                // Rellenamos el mapa con cada par de letras
                for (int i = 0; i < letrasAlfabeto.length(); i++) {
                    if (i < letrasCifrado.length()) {
                        mapa.put(letrasAlfabeto.charAt(i), letrasCifrado.charAt(i));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero codec.");
        }
        
        return mapa;
    }
}