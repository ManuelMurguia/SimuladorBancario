package aproyecto;

import java.util.Scanner;

public class SimuladorCredito {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== Simulador de Crédito Bancario ===");

		double monto = leerDouble(scanner, "Monto del préstamo (puedes incluir $): ");
		double tasa = leerDouble(scanner, "Tasa de interés anual (puedes incluir %): ");
		int plazo = leerEntero(scanner, "Plazo del préstamo (ej. 12 o '12 meses'): ");
		int opcion = leerOpcionSistema(scanner);

		Credito credito;

		try {
			switch (opcion) {
			case 1 -> credito = new CreditoFrances(monto, tasa, plazo);
			case 2 -> credito = new CreditoAleman(monto, tasa, plazo);
			default -> throw new IllegalArgumentException("Tipo de sistema no válido.");
			}

			credito.calcularCuotas();

			System.out.println("\nTabla de Amortización:");
			for (Cuota cuota : credito.getTablaAmortizacion()) {
				System.out.println(cuota);
			}

		} catch (IllegalArgumentException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		}

		scanner.close();
	}

	private static int leerOpcionSistema(Scanner scanner) {
		while (true) {
			System.out.println("\nSeleccione el tipo de amortización:");
			System.out.println("1. Sistema Francés (cuota fija)");
			System.out.println("2. Sistema Alemán (cuota decreciente)");
			System.out.print("Escribe el número o el nombre: ");
			String input = scanner.nextLine().trim().toLowerCase();

			if (input.contains("1") || input.contains("franc")) return 1;
			if (input.contains("2") || input.contains("alem")) return 2;

			System.out.println("Opción inválida. Escribe '1' o '2', o 'Francés' / 'Alemán'.");
		}
	}

	private static int leerEntero(Scanner scanner, String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				String input = scanner.nextLine().replaceAll("[^0-9]", "");
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Ingresa un número entero.");
			}
		}
	}

	private static double leerDouble(Scanner scanner, String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				String input = scanner.nextLine().replaceAll("[^0-9.,]", "").replace(",", ".");
				return Double.parseDouble(input);
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Ingresa un número válido (ej. 12500.75).");
			}
		}
	}
}