import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        attachListeners();
        startGame();
    }

    private void attachListeners() {
        view.addAnswerButtonListener(new AnswerButtonListener());
    }

    private void startGame() {
        view.displayQuestion(model.getCurrentQuestion().getQuestion());
    }

    private void processAnswer(String answer) {
        model.answerCurrentQuestion(answer);
        view.displayResult(model.getCurrentQuestion().getAnswer());
        if (model.isGameOver()) {
            view.displayFinalScore(model.getPlayerScore());
            // Perform any other game-over actions
        } else {
            view.displayQuestion(model.getCurrentQuestion().getQuestion());
        }
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userAnswer = view.getUserAnswer();
            processAnswer(userAnswer);
        }
    }
}
