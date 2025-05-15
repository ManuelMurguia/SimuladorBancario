/**
 * Nombre de la clase: CreditoFrances
 * Autor: Manuel Murguía
 * Fecha de creación: 10/mar/2025
 * Última modificación: 10/abr/2025
 * Descripción: Calcula un plan de amortización utilizando el sistema francés,
 *              donde la cuota mensual es fija y se desglosa en abono a capital e intereses.
 * Entradas: monto, tasa de interés anual, plazo en meses
 * Salidas: tabla de cuotas con desgloses mensuales y saldo restante
 */


package aproyecto;

public class CreditoFrances extends Credito {

    public CreditoFrances(double monto, double tasaAnual, int plazoMeses) {
        super(monto, tasaAnual, plazoMeses);
    }

    @Override
    public void calcularCuotas() {
        double r = getTasaMensual();
        double cuotaFija = monto * (r * Math.pow(1 + r, plazoMeses)) / (Math.pow(1 + r, plazoMeses) - 1);
        double saldo = monto;

        for (int i = 1; i <= plazoMeses; i++) {
            double intereses = saldo * r;
            double abonoCapital = cuotaFija - intereses;
            saldo -= abonoCapital;

            if (i == plazoMeses) saldo = 0;

            Cuota cuota = new Cuota(i, abonoCapital, intereses, cuotaFija, saldo);
            tablaAmortizacion.add(cuota);
        }
    }
}
