package trabajo1;

public class Paciente {

    private String nombre;
    private String dirección;
    private String teléfono;
    private boolean baja;

    public Paciente(String nombre, String dirección, String teléfono, boolean baja) throws IllegalArgumentException {
        this.nombre = nombre;
        this.dirección = dirección;
        Teléfono(teléfono); // Llama al método que valida la longitud, es decir, longitud de 9 dígitos
        this.baja = baja;
    }

    private void Teléfono(String tel) throws IllegalArgumentException {
        if (tel == null || tel.length() != 9 || !tel.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos numéricos.");
        }
        this.teléfono = tel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        String bajaTexto = baja ? "Sí" : "No";
        return "Nombre: " + nombre + "\n" +
               "Dirección: " + dirección + "\n" +
               "Teléfono: " + teléfono + "\n" +
               "Baja: " + bajaTexto;
    }

    // Método que devuelve el formato para el fichero
    public String toFileString() {
        return nombre + ";" + dirección + ";" + teléfono + ";" + (baja ? "true" : "false");
    }
}

