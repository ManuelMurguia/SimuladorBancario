/**
 * Nombre de la clase: CreditoAleman
 * Autor: Manuel Murguía
 * Fecha de creación: 10/mar/2025
 * Última modificación: 10/abr/2025
 * Descripción: Calcula un plan de amortización alemán,
 *              donde el abono a capital es constante y el interés disminuye progresivamente.
 * Entradas: monto, tasa de interés anual, plazo en meses
 * Salidas: tabla de cuotas con abonos fijos y saldo restante
 */


package aproyecto;

public class CreditoAleman extends Credito {

    public CreditoAleman(double monto, double tasaAnual, int plazoMeses) {
        super(monto, tasaAnual, plazoMeses);
    }

    @Override
    public void calcularCuotas() {
        double r = getTasaMensual();
        double abonoCapital = monto / plazoMeses;
        double saldo = monto;

        for (int i = 1; i <= plazoMeses; i++) {
            double intereses = saldo * r;
            double cuotaTotal = abonoCapital + intereses;
            saldo -= abonoCapital;

            if (i == plazoMeses) saldo = 0;

            Cuota cuota = new Cuota(i, abonoCapital, intereses, cuotaTotal, saldo);
            tablaAmortizacion.add(cuota);
        }
    }
}
