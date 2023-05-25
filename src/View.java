import javax.swing.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton answerButton;

    public View() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("När då då Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        answerField = new JTextField(20);
        answerButton = new JButton("Answer");

        JPanel contentPane = new JPanel();
        contentPane.add(questionLabel);
        contentPane.add(answerField);
        contentPane.add(answerButton);

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public void addAnswerButtonListener(ActionListener listener) {
        answerButton.addActionListener(listener);
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

    public void displayFinalScore(int score) {
        JOptionPane.showMessageDialog(frame, "Game over! Your score: " + score);
    }
}
