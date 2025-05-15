package aproyecto;

import java.util.*;

public abstract class Credito {
	
	protected double monto; // $
	protected double tasaAnual; // %
	protected int plazoMeses; // #

	protected List<Cuota> tablaAmortizacion;

	public Credito(double monto, double tasaAnual, int plazoMeses) {
		ValidadorCredito.validar(monto, tasaAnual, plazoMeses);
	    
		this.monto = monto;
		this.tasaAnual = tasaAnual;
		this.plazoMeses = plazoMeses;
		
		this.tablaAmortizacion = new ArrayList<>();
	}

	public double getMonto() {
		return monto;
	}

	public double getTasaAnual() {
		return tasaAnual;
	}

	public int getPlazoMeses() {
		return plazoMeses;
	}

	public List<Cuota> getTablaAmortizacion() {
		return tablaAmortizacion;
	}

	public abstract void calcularCuotas();

	public double getTasaMensual() {
		return tasaAnual / 12 / 100;
	}
}