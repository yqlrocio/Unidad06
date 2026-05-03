package boletin02;

import java.time.LocalDate;
import java.time.Period;

public class Cliente {
    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private double saldo;

    public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    /**
     * Calcula la edad actual del cliente en base a su fecha de nacimiento.
     * @return Edad en años.
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return dni + ";" + nombre + ";" + fechaNacimiento + ";" + saldo;
    }
}