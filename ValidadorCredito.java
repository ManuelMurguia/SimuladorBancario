/**
 * Nombre de la clase: ValidadorCredito
 * Autor: Manuel Murguía
 * Fecha de creación: 10/abr/2025
 * Última modificación: 20/abr/2025
 * Descripción: Contiene lógica de validación para los datos del crédito,
 *              asegurando que monto, tasa y plazo estén dentro de rangos aceptables.
 * Entradas: monto, tasa, plazo
 * Salidas: lanza excepciones en caso de error
 */


package aproyecto;

public class ValidadorCredito {

    public static void validar(double monto, double tasa, int plazo) {
        if (monto <= 0 || tasa <= 0 || plazo <= 0) {
            throw new IllegalArgumentException("Todos los valores deben ser mayores a cero.");
        }

        if (monto <= 50000) {
            if (tasa < 20 || tasa > 40)
                throw new IllegalArgumentException("Para montos menores a $50,000, la tasa debe estar entre 20% y 40%.");
            if (plazo < 6 || plazo > 24)
                throw new IllegalArgumentException("Para montos menores a $50,000, el plazo debe ser entre 6 y 24 meses.");
        } else if (monto <= 200000) {
            if (tasa < 10 || tasa > 25)
                throw new IllegalArgumentException("Para montos entre $50,000 y $200,000, la tasa debe estar entre 10% y 25%.");
            if (plazo < 12 || plazo > 60)
                throw new IllegalArgumentException("Para montos entre $50,000 y $200,000, el plazo debe ser entre 12 y 60 meses.");
        } else {
            if (tasa < 7 || tasa > 15)
                throw new IllegalArgumentException("Para montos mayores a $200,000, la tasa debe estar entre 7% y 15%.");
            if (plazo < 60 || plazo > 240)
                throw new IllegalArgumentException("Para montos mayores a $200,000, el plazo debe ser entre 60 y 240 meses.");
        }
    }
}
