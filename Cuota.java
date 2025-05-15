/**
 * Nombre de la clase: Cuota
 * Autor: Manuel Murguía
 * Fecha de creación: 10/mar/2025
 * Última modificación: 10/abr/2025
 * Descripción: Representa una cuota de un crédito, con número de pago, abono a capital,
 *              intereses, monto total de la cuota y saldo restante.
 * Entradas: valores calculados por el sistema de amortización
 * Salidas: getters para los datos de la cuota
 */


package aproyecto;

public class Cuota {
	private int numero;
	private double abonoCapital;
	private double intereses;
	private double cuotaTotal;
	private double saldoRestante;

	public Cuota(int numero, double abonoCapital, double intereses, double cuotaTotal, double saldoRestante) {
		this.numero = numero;
		this.abonoCapital = abonoCapital;
		this.intereses = intereses;
		this.cuotaTotal = cuotaTotal;
		this.saldoRestante = saldoRestante;
	}

	// Getters
	public int getNumero() {
		return numero;
	}

	public double getAbonoCapital() {
		return abonoCapital;
	}

	public double getIntereses() {
		return intereses;
	}

	public double getCuotaTotal() {
		return cuotaTotal;
	}

	public double getSaldoRestante() {
		return saldoRestante;
	}

	@Override
	public String toString() {
		return String.format(
				"Pago %d → Abono a Deuda: $%.2f | Intereses: $%.2f | Pago Mensuals: $%.2f | Deuda Restante: $%.2f",
				numero, abonoCapital, intereses, cuotaTotal, saldoRestante
				);
	}

}
