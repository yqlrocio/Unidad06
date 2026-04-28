package boletin01;

import java.io.*;
import java.util.*;

public class E6_OrdenarNumeros {

	public static void main(String[] args) {
		List<Integer> listaNumeros = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new FileReader("C:\\Users\\qr.ye\\git\\Unidad06\\Unidad06\\src\\boletin01\\Numeros"));
			while (sc.hasNextInt()) {
				listaNumeros.add(sc.nextInt());
			}
			sc.close();
		} catch (IOException e) {
			e.getMessage();
		}

		Collections.sort(listaNumeros);

		try (BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\qr.ye\\git\\Unidad06\\Unidad06\\src\\boletin01\\NumerosOrdenados"))) {
			for (Integer n : listaNumeros) {
				out.write(String.valueOf(n));
				out.newLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}
}