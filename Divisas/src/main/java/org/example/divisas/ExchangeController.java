package org.example.divisas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.HashMap;

public class ExchangeController {
    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> fromCurrencyCombo;

    @FXML
    private ComboBox<String> toCurrencyCombo;

    @FXML
    private Label resultLabel;

    private HashMap<String, Double> tasasDeCambio = new HashMap<>();

    // Método para manejar la inicialización del controlador
    @FXML
    public void initialize() {
        initComboBoxes();

        agregarTasasDeConversion();
    }

    private void initComboBoxes() {
        fromCurrencyCombo.getItems().addAll("USD", "EUR", "GBP", "JPY");
        toCurrencyCombo.getItems().addAll("USD", "EUR", "GBP", "JPY");

        fromCurrencyCombo.getSelectionModel().selectFirst();
        toCurrencyCombo.getSelectionModel().selectFirst();
    }

    private void agregarTasasDeConversion() {
        tasasDeCambio.put("USD-EUR", 0.92);
        tasasDeCambio.put("EUR-USD", 1.09);
        tasasDeCambio.put("GBP-USD", 1.27);
        tasasDeCambio.put("GBP-EUR", 1.17);
        tasasDeCambio.put("JPY-EUR", 0.0061);
        tasasDeCambio.put("JPY-USD", 0.0067);
    }

    @FXML
    private void convertir() {
        // Verificar si el campo de cantidad está vacío
        String cantidadTexto = amountField.getText().trim();
        if (cantidadTexto.isEmpty()) {

            resultLabel.setText("Por favor, ingresa un monto válido.");
            return;
        }

        try {

            double cantidad = Double.parseDouble(cantidadTexto);

            String monedaOrigen = fromCurrencyCombo.getValue();
            String monedaDestino = toCurrencyCombo.getValue();

            double tasaConversion = tasasDeCambio.getOrDefault(monedaOrigen + "-" + monedaDestino, 1.0);

            double resultado = cantidad * tasaConversion;

            // Mostrar el resultado en el etiqueta de resultado
            resultLabel.setText(String.format("%.2f %s es equivalente a %.2f %s", cantidad, monedaOrigen, resultado, monedaDestino));
        } catch (NumberFormatException e) {
            resultLabel.setText("Por favor, ingresa un monto válido.");
        }
    }

}
