import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterSwing extends JFrame {

    private JTextField temperatureInput;
    private JTextField resultField;

    public TemperatureConverterSwing() {
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Input field for temperature
        temperatureInput = new JTextField();
        temperatureInput.setFont(new Font("Arial", Font.PLAIN, 16));
        temperatureInput.setToolTipText("Enter temperature");
        panel.add(temperatureInput);

        // Labels for result
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(resultLabel);

        // TextField to display result
        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(resultField);

        // Buttons for conversion
        JButton fahrenheitButton = new JButton("Convert to Fahrenheit");
        customizeButton(fahrenheitButton);
        fahrenheitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToFahrenheit();
            }
        });
        panel.add(fahrenheitButton);

        JButton celsiusButton = new JButton("Convert to Celsius");
        customizeButton(celsiusButton);
        celsiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToCelsius();
            }
        });
        panel.add(celsiusButton);

        add(panel);
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(70, 130, 180)); // SteelBlue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    private void convertToCelsius() {
        try {
            double fahrenheit = Double.parseDouble(temperatureInput.getText());
            double celsius = (fahrenheit - 32) * 5 / 9;
            resultField.setText(String.format("%.2f °C", celsius));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    private void convertToFahrenheit() {
        try {
            double celsius = Double.parseDouble(temperatureInput.getText());
            double fahrenheit = celsius * 9 / 5 + 32;
            resultField.setText(String.format("%.2f °F", fahrenheit));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverterSwing());
    }
}
