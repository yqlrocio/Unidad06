package boletin02;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class GestionClientes {
   
	private SortedSet<Cliente> clientes;
    private String nombreArchivo;

    public GestionClientes(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        // Usamos un Comparator para ordenar los clientes por su DNI
        this.clientes = new TreeSet<>(Comparator.comparing(Cliente::getDni));
        cargarClientes();
    }

    private void cargarClientes() {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length == 4) {
                        String dni = partes[0];
                        String nombre = partes[1];
                        LocalDate fechaNacimiento = LocalDate.parse(partes[2]);
                        double saldo = Double.parseDouble(partes[3]);

                        clientes.add(new Cliente(dni, nombre, fechaNacimiento, saldo));
                    }
                }
                System.out.println("Clientes cargados correctamente desde " + nombreArchivo);
            } catch (Exception e) {
                System.out.println("Error al cargar los clientes: " + e.getMessage());
            }
        }
    }

    /**
     * Da de alta un nuevo cliente si no existe uno con el mismo DNI.
     */
    public boolean altaCliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
        Cliente nuevoCliente = new Cliente(dni, nombre, fechaNacimiento, saldo);
        return clientes.add(nuevoCliente); // Devuelve true si no estaba repetido
    }

    /**
     * Da de baja a un cliente buscando por DNI.
     */
    public boolean bajaCliente(String dni) {
        return clientes.removeIf(c -> c.getDni().equalsIgnoreCase(dni));
    }

    /**
     * Devuelve la colección de clientes.
     */
    public SortedSet<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Guarda los clientes actualizados en el archivo de texto.
     */
    public void guardarClientes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Cliente c : clientes) {
                pw.println(c.toString());
            }
            System.out.println("Datos guardados correctamente en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar los clientes en el fichero.");
        }
    }
}