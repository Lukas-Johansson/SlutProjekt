import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {
    private JFrame frame;
    private JButton answerButton;

    public View() {
        frame = new JFrame("När då då");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        answerButton = new JButton("Svara");

        JPanel panel = new JPanel();
        panel.add(answerButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public JButton getAnswerButton() {
        return answerButton;
    }
}