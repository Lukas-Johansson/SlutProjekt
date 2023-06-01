import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientView {
    private JTextArea outputTextArea;
    private JTextField inputTextField;
    private JLabel scoreLabel;

    private ActionListener answerListener;

    public ClientView() {
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        inputTextField = new JTextField();
        inputTextField.addActionListener(answerListener);
        frame.add(inputTextField, BorderLayout.SOUTH);

        scoreLabel = new JLabel("Score: 0");
        frame.add(scoreLabel, BorderLayout.NORTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showMessage(String message) {
        outputTextArea.append(message + "\n");
    }

    public void displayQuestion(Question question) {
        outputTextArea.append("Question: " + question.getQuestionText() + "\n");
    }

    public String getInput() {
        String answer = inputTextField.getText().trim();
        inputTextField.setText("");
        return answer;
    }

    public void displayScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void displayFinalScore(int score) {
        outputTextArea.append("Game Over! Final Score: " + score + "\n");
        inputTextField.setEditable(false);
    }

    public void setAnswerListener(ActionListener listener) {
        this.answerListener = listener;
        inputTextField.addActionListener(answerListener);
    }
}
