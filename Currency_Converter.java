
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

    public class Currency_Converter extends JFrame {
        private final JLabel resultLabel;
        private final JTextField amountField;
        private final JComboBox<String> fromComboBox;
        private final JComboBox<String> toComboBox;
        private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY","INR"};
        private final double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47,87.14};

        public Currency_Converter() {
            setTitle("Currency Converter");
            setLayout(new GridLayout(4, 2));

            JLabel amountLabel = new JLabel("Amount:");
            add(amountLabel);

            amountField = new JTextField();
            add(amountField);

            JLabel fromLabel = new JLabel("From:");
            add(fromLabel);

            fromComboBox = new JComboBox<>(currencies);
            add(fromComboBox);

            JLabel toLabel = new JLabel("To:");
            add(toLabel);

            toComboBox = new JComboBox<>(currencies);
            add(toComboBox);

            JButton convertButton = new JButton("Convert");
            add(convertButton);

            resultLabel = new JLabel();
            add(resultLabel);

            convertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double amount = Double.parseDouble(amountField.getText());
                        String fromCurrency = (String) fromComboBox.getSelectedItem();
                        String toCurrency = (String) toComboBox.getSelectedItem();
                        double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                        double result = amount * exchangeRate;
                        resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                    } catch (Exception ex) {
                        resultLabel.setText("Invalid input");
                    }
                }
            });

            setSize(300, 200);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        private int getIndex(String currency) {
            for (int i = 0; i < currencies.length; i++) {
                if (currency.equals(currencies[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            new Currency_Converter();
        }
    }

