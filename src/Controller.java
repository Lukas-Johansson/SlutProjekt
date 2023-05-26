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
        view.addPassButtonListener(new PassButtonListener());
    }

    private void startGame() {
        view.displayQuestion(model.getCurrentQuestion().getQuestion());
        view.updateScore(model.getPlayer1Score(), model.getPlayer2Score());
        view.updateTurn(model.getCurrentPlayer());
    }

    private void processAnswer(String answer) {
        model.answerCurrentQuestion(answer);
        view.displayResult(model.getCurrentQuestion().getAnswer());
        view.updateScore(model.getPlayer1Score(), model.getPlayer2Score());
        if (model.isGameOver()) {
            view.displayFinalScore(model.getPlayer1Score(), model.getPlayer2Score());
            // Perform any other game-over actions
        } else {
            view.displayQuestion(model.getCurrentQuestion().getQuestion());
            view.updateTurn(model.getCurrentPlayer());
        }
    }

    private void processPass() {
        model.passCard();
        view.updateTurn(model.getCurrentPlayer());
        view.updatePass();
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String answer = view.getUserAnswer();
            processAnswer(answer);
        }
    }

    private class PassButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            processPass();
        }
    }
}