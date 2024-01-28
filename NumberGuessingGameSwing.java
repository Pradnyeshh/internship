import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameSwing extends JFrame {

    private int generatedNumber;
    private int attempts;

    private JTextField guessField;
    private JLabel feedbackLabel;

    public NumberGuessingGameSwing() {
        setTitle("🎲 Number Guessing Game 🌈");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        generatedNumber = generateRandomNumber();
        attempts = 0;

        // Title Label
        JLabel titleLabel = new JLabel("Can You Guess the Number?");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24)); // Change font to Verdana
        panel.add(titleLabel);

        // Instruction Label
        JLabel instructionLabel = new JLabel("I've picked a number between 1 and 100. Give it a shot!");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(instructionLabel);

        // Guess Input Field
        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(guessField);

        // Feedback Label
        feedbackLabel = new JLabel("");
        feedbackLabel.setHorizontalAlignment(JLabel.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(feedbackLabel);

        // Guess Button
        JButton guessButton = new JButton("Make a Guess!");
        guessButton.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font to Verdana
        guessButton.setBackground(new Color(0, 102, 204)); // Change color to a darker shade of blue
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });
        panel.add(guessButton);

        add(panel);
        setVisible(true);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;

            if (userGuess < generatedNumber) {
                feedbackLabel.setText("Too low! Try again. 📉");
                feedbackLabel.setForeground(new Color(30, 144, 255)); // DodgerBlue color
            } else if (userGuess > generatedNumber) {
                feedbackLabel.setText("Too high! Try again. 📈");
                feedbackLabel.setForeground(new Color(30, 144, 255)); // DodgerBlue color
            } else {
                feedbackLabel.setText("Congratulations! You guessed it in " + attempts + " attempts. 🎉");
                feedbackLabel.setForeground(new Color(50, 205, 50)); // LimeGreen color
                showWinMessage();
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number. ❌");
            feedbackLabel.setForeground(new Color(255, 0, 0)); // Red color
        }
    }

    private void showWinMessage() {
        JOptionPane.showMessageDialog(this, "Congratulations! You guessed the number in " + attempts + " attempts. 🏆",
                "Winner!", JOptionPane.INFORMATION_MESSAGE);
        resetGame();
    }

    private void resetGame() {
        generatedNumber = generateRandomNumber();
        attempts = 0;
        guessField.setText("");
        feedbackLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameSwing());
    }
}
