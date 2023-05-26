import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton answerButton;
    private JLabel scoreLabel;
    private JLabel turnLabel;
    private JButton passButton;

    public View() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("När då då Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        answerField = new JTextField(20);
        answerButton = new JButton("Answer");
        scoreLabel = new JLabel();
        turnLabel = new JLabel();
        passButton = new JButton("Pass Card");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 1));
        contentPane.add(questionLabel);
        contentPane.add(answerField);
        contentPane.add(answerButton);
        contentPane.add(scoreLabel);
        contentPane.add(turnLabel);
        contentPane.add(passButton);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void addAnswerButtonListener(ActionListener listener) {
        answerButton.addActionListener(listener);
    }

    public void addPassButtonListener(ActionListener listener) {
        passButton.addActionListener(listener);
    }

    public String getUserAnswer() {
        return answerField.getText();
    }

    public void displayQuestion(String question) {
        questionLabel.setText(question);
        answerField.setText("");
        answerField.requestFocus();
    }

    public void displayResult(String result) {
        JOptionPane.showMessageDialog(frame, result);
    }

    public void displayFinalScore(int score1, int score2) {
        JOptionPane.showMessageDialog(frame, "Game over!\nPlayer 1 score: " + score1 + "\nPlayer 2 score: " + score2);
    }

    public void updateScore(int score1, int score2) {
        scoreLabel.setText("Player 1 Score: " + score1 + " | Player 2 Score: " + score2);
    }

    public void updateTurn(int player) {
        turnLabel.setText("Turn: Player " + player);
    }

    public void updatePass() {
        JOptionPane.showMessageDialog(frame, "You passed the card to the next player!");
    }

    public void updateWin(int player) {
        JOptionPane.showMessageDialog(frame, "Player " + player + " wins!");
    }
}