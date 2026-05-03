package trabajo1;

import java.io.*;
import java.util.*;

public class GestionPacientes {
	
    private SortedSet<Paciente> pacientes;
    private String nombreArchivo = "pacientes.txt";

    public GestionPacientes() {
        // Ordenamos primero por nombre, y si coincide, por teléfono
        this.pacientes = new TreeSet<>(new Comparator<Paciente>() {
            @Override
            public int compare(Paciente p1, Paciente p2) {
                int comparacionNombre = p1.getNombre().compareTo(p2.getNombre());
                if (comparacionNombre != 0) {
                    return comparacionNombre;
                }
                return p1.getTeléfono().compareTo(p2.getTeléfono());
            }
        });
        cargarPacientes();
    }

    private void cargarPacientes() {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length == 4) {
                        String nombre = partes[0];
                        String dirección = partes[1];
                        String teléfono = partes[2];
                        boolean baja = Boolean.parseBoolean(partes[3]);
                        
                        try {
                            Paciente p = new Paciente(nombre, dirección, teléfono, baja);
                            pacientes.add(p);
                        } catch (IllegalArgumentException e) {
                            // Ignoramos registros corruptos
                        }
                    }
                }
                System.out.println("Pacientes cargados correctamente.");
            } catch (IOException e) {
                System.out.println("Error al leer el fichero de pacientes.");
            }
        }
    }

    public void guardarPacientes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Paciente p : pacientes) {
                pw.println(p.toFileString());
            }
            System.out.println("Pacientes guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar en el fichero.");
        }
    }

    public boolean añadirPaciente(String nombre, String dirección, String teléfono, boolean baja) {
        try {
            Paciente p = new Paciente(nombre, dirección, teléfono, baja);
            // TreeSet comprueba la igualdad mediante el comparador, pero podemos asegurarnos manualmente.
            return pacientes.add(p);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public Paciente buscarPaciente(String nombre, String teléfono) {
        for (Paciente p : pacientes) {
            if (p.getNombre().equalsIgnoreCase(nombre) && p.getTeléfono().equals(teléfono)) {
                return p;
            }
        }
        return null;
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        for (Paciente p : pacientes) {
            System.out.println(p.toString());
            System.out.println("----------------------------------------------------");
        }
    }

    public boolean eliminarPaciente(String nombre, String teléfono) {
        Paciente p = buscarPaciente(nombre, teléfono);
        if (p != null) {
            pacientes.remove(p);
            return true;
        }
        return false;
    }

    public SortedSet<Paciente> getPacientes() {
        return pacientes;
    }
}

