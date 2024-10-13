import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int attempts = 4;
    private JTextField inputField;
    private JLabel feedbackLabel, attemptsLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setUndecorated(true);  // Removes window borders and title bar
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Fullscreen mode

        // Root Panel with Slate-900 Background
        JPanel rootPanel = new JPanel();
        rootPanel.setBackground(new Color(15, 23, 42));  // Slate-900 equivalent
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));  // Padding

        // Title Label
        JLabel titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(135, 206, 250));  // Light blue color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 40, 0));  // Spacing below

        // Feedback Label
        feedbackLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        feedbackLabel.setForeground(Color.LIGHT_GRAY);
        feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Panel with Field and Button
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(15, 23, 42));
        inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setBackground(new Color(30, 30, 30));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));

        JButton submitButton = createStyledButton("Submit");
        inputPanel.add(inputField);
        inputPanel.add(submitButton);

        // Attempts Label
        attemptsLabel = new JLabel("Attempts Left: 4", SwingConstants.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        attemptsLabel.setForeground(Color.LIGHT_GRAY);
        attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        attemptsLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));

        // Add Components to Root Panel
        rootPanel.add(titleLabel);
        rootPanel.add(feedbackLabel);
        rootPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rootPanel.add(inputPanel);
        rootPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rootPanel.add(attemptsLabel);

        // Add Root Panel to Frame
        add(rootPanel);

        // Generate Random Number
        randomNumber = new Random().nextInt(100) + 1;

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        setVisible(true);
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(inputField.getText());
            attempts--;

            if (userGuess == randomNumber) {
                feedbackLabel.setText("Correct! You win 🎉");
                endGame("Congratulations! You guessed the number.");
            } else if (attempts == 0) {
                feedbackLabel.setText("Game Over! The number was: " + randomNumber);
                endGame("You lost! The correct number was: " + randomNumber);
            } else if (userGuess < randomNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            attemptsLabel.setText("Attempts Left: " + attempts);
            inputField.setText("");
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number!");
        }
    }

    private void endGame(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", 
                                                     "Restart Game", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 4;
        feedbackLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts Left: 4");
        inputField.setText("");
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 130, 180));  // Light blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));  // Lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));  // Original color
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGame());
    }
}
 