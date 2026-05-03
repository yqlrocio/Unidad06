package boletin02;

import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        GestionClientes gestion = new GestionClientes("clientes.txt");
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n================================");
            System.out.println("        GESTIÓN DE CLIENTES     ");
            System.out.println("================================");
            System.out.println("1. Alta cliente");
            System.out.println("2. Baja cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Salir");
            System.out.print("Elige una opción (1-4): ");

            opcion = teclado.nextInt();
            teclado.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Introduce DNI: ");
                    String dni = teclado.nextLine();
                    System.out.print("Introduce nombre completo: ");
                    String nombre = teclado.nextLine();
                    
                    System.out.print("Introduce fecha de nacimiento (AAAA-MM-DD): ");
                    LocalDate fechaNacimiento = LocalDate.parse(teclado.nextLine());
                    
                    System.out.print("Introduce saldo inicial: ");
                    double saldo = teclado.nextDouble();
                    teclado.nextLine(); 

                    if (gestion.altaCliente(dni, nombre, fechaNacimiento, saldo)) {
                        System.out.println("Cliente dado de alta correctamente.");
                    } else {
                        System.out.println("Error: Ya existe un cliente con ese DNI.");
                    }
                    break;

                case 2:
                    System.out.print("Introduce el DNI del cliente a dar de baja: ");
                    String dniBaja = teclado.nextLine();

                    if (gestion.bajaCliente(dniBaja)) {
                        System.out.println("Cliente eliminado correctamente.");
                    } else {
                        System.out.println("No se ha encontrado ningún cliente con ese DNI.");
                    }
                    break;

                case 3:
                    if (gestion.getClientes().isEmpty()) {
                        System.out.println("No hay clientes registrados.");
                    } else {
                        System.out.println("\n--- LISTA DE CLIENTES ---");
                        double saldoMax = -Double.MAX_VALUE;
                        double saldoMin = Double.MAX_VALUE;
                        double sumaSaldos = 0;
                        int contador = 0;

                        for (var c : gestion.getClientes()) {
                            System.out.printf("DNI: %s | Nombre: %s | Saldo: %.2f € | Edad: %d años\n", 
                                c.getDni(), c.getNombre(), c.getSaldo(), c.getEdad());

                            if (c.getSaldo() > saldoMax) saldoMax = c.getSaldo();
                            if (c.getSaldo() < saldoMin) saldoMin = c.getSaldo();
                            sumaSaldos += c.getSaldo();
                            contador++;
                        }
                        
                        double promedio = contador > 0 ? (sumaSaldos / contador) : 0;
                        System.out.println("--------------------------------");
                        System.out.printf("Saldo máximo: %.2f €\n", saldoMax);
                        System.out.printf("Saldo mínimo: %.2f €\n", saldoMin);
                        System.out.printf("Saldo promedio: %.2f €\n", promedio);
                    }
                    break;

                case 4:
                    gestion.guardarClientes();
                    System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 4);

        teclado.close();
    }
}