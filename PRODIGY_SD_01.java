import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PRODIGY_SD_01 extends JFrame implements ActionListener {
    private JTextField temperatureField;
    private JComboBox<String> originalUnitComboBox;
    private JLabel resultLabel;

    public PRODIGY_SD_01() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false); // Prevent resizing

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 10)); // Adjusted spacing
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Added padding
        JLabel temperatureLabel = new JLabel("Temperature:");
        temperatureField = new JTextField();
        JLabel unitLabel = new JLabel("Unit:");
        originalUnitComboBox = new JComboBox<>(new String[] { "Celsius", "Fahrenheit", "Kelvin" });
        inputPanel.add(temperatureLabel);
        inputPanel.add(temperatureField);
        inputPanel.add(unitLabel);
        inputPanel.add(originalUnitComboBox);
        add(inputPanel, BorderLayout.NORTH);

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        convertButton.setBackground(new Color(70, 130, 180)); // Set button background color
        convertButton.setForeground(Color.WHITE); // Set button text color
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);
        add(buttonPanel, BorderLayout.CENTER);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Added padding
        add(resultLabel, BorderLayout.SOUTH);

        // Customize font for labels and text fields
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        temperatureLabel.setFont(labelFont);
        unitLabel.setFont(labelFont);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        temperatureField.setFont(fieldFont);
        originalUnitComboBox.setFont(fieldFont);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            double temperature;
            try {
                temperature = Double.parseDouble(temperatureField.getText());
            } catch (NumberFormatException ex) {
                showError("Invalid temperature value!");
                return;
            }

            String originalUnit = (String) originalUnitComboBox.getSelectedItem();

            switch (originalUnit) {
                case "Celsius":
                    convertFromCelsius(temperature);
                    break;
                case "Fahrenheit":
                    convertFromFahrenheit(temperature);
                    break;
                case "Kelvin":
                    convertFromKelvin(temperature);
                    break;
            }
        }
    }

    private void convertFromCelsius(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        double kelvin = celsius + 273.15;
        displayResult(celsius, fahrenheit, kelvin);
    }

    private void convertFromFahrenheit(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        double kelvin = (fahrenheit + 459.67) * 5 / 9;
        displayResult(celsius, fahrenheit, kelvin);
    }

    private void convertFromKelvin(double kelvin) {
        double celsius = kelvin - 273.15;
        double fahrenheit = (kelvin * 9 / 5) - 459.67;
        displayResult(celsius, fahrenheit, kelvin);
    }

    private void displayResult(double celsius, double fahrenheit, double kelvin) {
        resultLabel.setText(String.format(
                "<html>%.2f degrees Celsius is equal to<br> %.2f degrees Fahrenheit and %.2f Kelvin</html>", celsius,
                fahrenheit, kelvin));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverterGUI::new);
    }
}
