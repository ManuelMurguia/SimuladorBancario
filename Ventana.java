/**
 * Nombre de la clase: Ventana
 * Autor: Manuel Murguía
 * Fecha de creación: 10/abr/2025
 * Última modificación: 28/abr/2025
 * Descripción: Interfaz gráfica principal del simulador de crédito.
 *              Permite ingresar datos, seleccionar sistema de amortización,
 *              mostrar tabla de amortización y exportar resultados.
 * Entradas: monto, tasa, plazo, filtros
 * Salidas: tabla de resultados, resumen y archivo CSV
 */


package aproyecto;

import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Ventana extends JFrame {

	private JTextField txtMonto, txtTasa, txtPlazo, txtLimitePagos, txtDesde, txtHasta;
	private JComboBox<String> comboSistema;
	private JButton btnCalcular, btnExportar, btnLimpiar;
	private JTable tablaResultados;
	private DefaultTableModel modeloTabla;
	private JLabel lblResumen;

	public Ventana() {
		setTitle("Simulador de Crédito Bancario");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel panelInputs = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// -------------------------------------------------------------------
		// Préstamo
		gbc.gridx = 0; gbc.gridy = 0;
		panelInputs.add(new JLabel("Monto del préstamo:"), gbc);

		gbc.gridx = 1;
		txtMonto = new JTextField("Ej: $50000");
		txtMonto.setPreferredSize(new Dimension(150, 25));
		txtMonto.setForeground(Color.GRAY);

		txtMonto.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtMonto.getText().equals("Ej: $50000")) {
					txtMonto.setText("");
					txtMonto.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtMonto.getText().isEmpty()) {
					txtMonto.setForeground(Color.GRAY);
					txtMonto.setText("Ej: $50000");
				}
			}
		});

		panelInputs.add(txtMonto, gbc);

		// -------------------------------------------------------------------
		// Interés
		gbc.gridx = 2;
		panelInputs.add(new JLabel("Tasa de interés anual (%):"), gbc);

		gbc.gridx = 3;
		txtTasa = new JTextField("Ej: 25%");
		txtTasa.setPreferredSize(new Dimension(150, 25));
		txtTasa.setForeground(Color.GRAY);

		txtTasa.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtTasa.getText().equals("Ej: 25%")) {
					txtTasa.setText("");
					txtTasa.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtTasa.getText().isEmpty()) {
					txtTasa.setForeground(Color.GRAY);
					txtTasa.setText("Ej: 25%");
				}
			}
		});

		panelInputs.add(txtTasa, gbc);

		// -------------------------------------------------------------------
		// Plazo de pago
		gbc.gridx = 0; gbc.gridy = 1;
		panelInputs.add(new JLabel("Plazo (meses):"), gbc);

		gbc.gridx = 1;
		txtPlazo = new JTextField("Ej: 24 meses");
		txtPlazo.setPreferredSize(new Dimension(150, 25));
		txtPlazo.setForeground(Color.GRAY);

		txtPlazo.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtPlazo.getText().equals("Ej: 24 meses")) {
					txtPlazo.setText("");
					txtPlazo.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtPlazo.getText().isEmpty()) {
					txtPlazo.setForeground(Color.GRAY);
					txtPlazo.setText("Ej: 24 meses");
				}
			}
		});

		panelInputs.add(txtPlazo, gbc);
		// -------------------------------------------------------------------
		// Cantidad pagos
		gbc.gridx = 2; gbc.gridy = 1;
		panelInputs.add(new JLabel("Mostrar N primeros pagos"), gbc);

		gbc.gridx = 3;
		txtLimitePagos = new JTextField("Ej: 6 pagos");
		txtLimitePagos.setPreferredSize(new Dimension(150, 25));
		txtLimitePagos.setForeground(Color.GRAY);

		txtLimitePagos.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtLimitePagos.getText().equals("Ej: 6 pagos")) {
					txtLimitePagos.setText("");
					txtLimitePagos.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtLimitePagos.getText().isEmpty()) {
					txtLimitePagos.setForeground(Color.GRAY);
					txtLimitePagos.setText("Ej: 6 pagos");
				}
			}
		});

		panelInputs.add(txtLimitePagos, gbc);     

		// -------------------------------------------------------------------
		// Placeholders Desde
		gbc.gridx = 0; gbc.gridy = 2;
		panelInputs.add(new JLabel("Desde pago:"), gbc);

		gbc.gridx = 1;
		gbc.gridwidth = 1;
		txtDesde = new JTextField("Ej: 1");
		txtDesde.setPreferredSize(new Dimension(150, 25));
		txtDesde.setForeground(Color.GRAY);

		txtDesde.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtDesde.getText().equals("Ej: 1")) {
					txtDesde.setText("");
					txtDesde.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtDesde.getText().isEmpty()) {
					txtDesde.setForeground(Color.GRAY);
					txtDesde.setText("Ej: 1");
				}
			}
		});

		panelInputs.add(txtDesde, gbc);

		// -------------------------------------------------------------------
		// Placeholder Hasta
		gbc.gridx = 2; gbc.gridy = 2;
		panelInputs.add(new JLabel("Hasta pago:"), gbc);

		gbc.gridx = 3;
		txtHasta = new JTextField("Ej: 12");
		txtHasta.setPreferredSize(new Dimension(150, 25));
		txtHasta.setForeground(Color.GRAY);

		txtHasta.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtHasta.getText().equals("Ej: 12")) {
					txtHasta.setText("");
					txtHasta.setForeground(Color.BLACK);
				}
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtHasta.getText().isEmpty()) {
					txtHasta.setForeground(Color.GRAY);
					txtHasta.setText("Ej: 12");
				}
			}
		});

		panelInputs.add(txtHasta, gbc);

		// -------------------------------------------------------------------
		// Sistema amortización
		gbc.gridx = 0; gbc.gridy = 3;
		panelInputs.add(new JLabel("Sistema de amortización:"), gbc);

		gbc.gridx = 1;
		comboSistema = new JComboBox<>(new String[] { "Francés", "Alemán" });
		comboSistema.setPreferredSize(new Dimension(150, 25));
		panelInputs.add(comboSistema, gbc);

		// -------------------------------------------------------------------
		// Botón limpiar
		gbc.gridx = 2; gbc.gridy = 3;
		gbc.gridwidth = 2;

		btnLimpiar = new JButton("Limpiar Todo");
		btnLimpiar.setPreferredSize(new Dimension(150, 25));
		panelInputs.add(btnLimpiar, gbc);

		// -------------------------------------------------------------------
		// Calcular botón
		gbc.gridx = 0; gbc.gridy = 4;
		gbc.gridwidth = 2;

		btnCalcular = new JButton("Calcular");
		btnCalcular.setPreferredSize(new Dimension(150, 25));
		panelInputs.add(btnCalcular, gbc);

		// -------------------------------------------------------------------
		// Botón exportar
		gbc.gridx = 2; gbc.gridy = 4;
		gbc.gridwidth = 2;

		btnExportar = new JButton("Exportar a CSV");
		btnExportar.setPreferredSize(new Dimension(150, 25));
		panelInputs.add(btnExportar, gbc);

		// -------------------------------------------------------------------
		// Espacio entre botones y tabla
		panelInputs.add(new JLabel(""));

		add(panelInputs, BorderLayout.NORTH);

		// -------------------------------------------------------------------
		// Tabla
		modeloTabla = new DefaultTableModel(new String [] {
				"N Pago", "Abono a Deuda", "Interes", "Pago Mensual", "Saldo Restante"
		}, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaResultados = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaResultados);
		add(scrollPane, BorderLayout.CENTER);

		lblResumen = new JLabel("Resumen: ");
		lblResumen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lblResumen, BorderLayout.SOUTH);

		// -------------------------------------------------------------------
		// Botón Calcular
		btnCalcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				calcularCredito();
			}
		});

		// -------------------------------------------------------------------
		// Botón Exportar
		btnExportar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportarTablaCSV();
			}
		});

		// -------------------------------------------------------------------
		// Botón Limpiar
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarTodo();
			}
		});

		setVisible(true);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	// Calcular Crédito
	private void calcularCredito() {
		modeloTabla.setRowCount(0);
		try {
			if (txtMonto.getText().isBlank() || txtTasa.getText().isBlank() || txtPlazo.getText().isBlank()) {
				throw new IllegalArgumentException("Todos los campos deben estar llenos.");
			}

			if (txtMonto.getText().equals("Ej: $50000") || txtTasa.getText().equals("Ej: 25%") || txtPlazo.getText().equals("Ej: 24 meses")) {
				throw new IllegalArgumentException("Debes ingresar datos válidos en todos los campos.");
			}

			double monto = Double.parseDouble(txtMonto.getText().replaceAll("[^0-9.]", ""));
			double tasa = Double.parseDouble(txtTasa.getText().replaceAll("[^0-9.]", ""));
			int plazo = Integer.parseInt(txtPlazo.getText().replaceAll("[^0-9]", ""));

			ValidadorCredito.validar(monto, tasa, plazo);

			String tipo = (String) comboSistema.getSelectedItem();
			Credito credito;

			if (tipo.equalsIgnoreCase("Francés")) {
				credito = new CreditoFrances(monto, tasa, plazo);
			} else if (tipo.equalsIgnoreCase("Alemán")) {
				credito = new CreditoAleman(monto, tasa, plazo);
			} else {
				throw new IllegalArgumentException("Sistema no reconocido");
			}

			credito.calcularCuotas();

			List<Cuota> cuotas = credito.getTablaAmortizacion();

			boolean usarLimite = !txtLimitePagos.getText().isBlank() && !txtLimitePagos.getText().equals("Ej: 6 pagos");
			boolean usarRango = !txtDesde.getText().isBlank() && !txtHasta.getText().isBlank() && !txtDesde.getText().equals("Ej: 1") && !txtHasta.getText().equals("Ej: 12");

			int desde = 0;
			int hasta = cuotas.size();

			if (usarRango) {
				desde = Integer.parseInt(txtDesde.getText().replaceAll("[^0-9]", "")) -1;
				hasta = Integer.parseInt(txtHasta.getText().replaceAll("[^0-9]", ""));
				
		        if (desde < 0 || hasta <= desde || hasta > cuotas.size()) {
		            throw new IllegalArgumentException("El rango de pagos es inválido.");
		        }
		    } else if (usarLimite) {
		        int limite = Integer.parseInt(txtLimitePagos.getText().replaceAll("[^0-9]", ""));
		        if (limite <= 0) {
		            throw new IllegalArgumentException("El número de pagos debe ser mayor a cero.");
		        }
		        hasta = Math.min(limite, cuotas.size());
		    }

		    for (int i = desde; i < hasta; i++) {
		        Cuota cuota = cuotas.get(i);
		        
		        modeloTabla.addRow(new Object[] {
		            cuota.getNumero(),
		            String.format("$%.2f", cuota.getAbonoCapital()),
		            String.format("$%.2f", cuota.getIntereses()),
		            String.format("$%.2f", cuota.getCuotaTotal()),
		            String.format("$%.2f", cuota.getSaldoRestante())
		        });
		    }

			double totalPagado = 0;
			double totalIntereses = 0;

			for (int i = 0; i < modeloTabla.getRowCount(); i++) {
				totalPagado += Double.parseDouble(modeloTabla.getValueAt(i, 3).toString().replace("$", ""));
				totalIntereses += Double.parseDouble(modeloTabla.getValueAt(i, 2).toString().replace("$", ""));
			}

			lblResumen.setText(String.format(
					"Resumen: Pagos mostrados: %d | Total pagado: $%.2f | Total intereses: $%.2f",
					modeloTabla.getRowCount(), totalPagado, totalIntereses
					));


		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Ingresa solo números válidos (sin letras ni símbolos extraños).", "Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	// EXPORTAR
	private void exportarTablaCSV() {

		if (modeloTabla.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No hay datos para exportar. Realiza un cálculo primero.", "Exportación vacía", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar como CSV");
		int seleccion = fileChooser.showSaveDialog(this);

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			try {
				java.io.File archivo = fileChooser.getSelectedFile();
				if (!archivo.getName().toLowerCase().endsWith(".csv")) {
					archivo = new java.io.File(archivo.getAbsolutePath() + ".csv");
				}

				try (java.io.PrintWriter writer = new java.io.PrintWriter(archivo)) {
					for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
						writer.print(modeloTabla.getColumnName(i));
						if (i < modeloTabla.getColumnCount() - 1) writer.print(",");
					}
					writer.println();

					for (int i = 0; i < modeloTabla.getRowCount(); i++) {
						for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
							writer.print(modeloTabla.getValueAt(i, j));
							if (j < modeloTabla.getColumnCount() - 1) writer.print(",");
						}
						writer.println();
					}
				}

				JOptionPane.showMessageDialog(this, "CSV guardado con éxito.");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + ex.getMessage());
			}
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	// LIMPIAR
	private void limpiarTodo() {
		txtMonto.setText("");
		txtTasa.setText("");
		txtPlazo.setText("");
		txtLimitePagos.setText("");
		comboSistema.setSelectedIndex(0);
		modeloTabla.setRowCount(0);
		txtDesde.setText("");
		txtHasta.setText("");
	}

	public static void main(String[] args) {
		new Ventana();
	}
}