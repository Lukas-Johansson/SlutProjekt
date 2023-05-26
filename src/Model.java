import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Question> questionBank;
    private int currentQuestionIndex;
    private int currentPlayer;
    private int player1Score;
    private int player2Score;
    private boolean cardPassed;

    public Model() {
        questionBank = new ArrayList<>();
        currentQuestionIndex = 0;
        currentPlayer = 1;
        player1Score = 0;
        player2Score = 0;
        cardPassed = false;
        initializeQuestionBank();
    }

    private void initializeQuestionBank() {
        questionBank.add(new Question("När landade människan på månen?", "1969"));
        questionBank.add(new Question("När började andra världskriget?", "1939"));
        questionBank.add(new Question("När släpptes det första iPhone?", "2007"));
        questionBank.add(new Question("När invaderade Sverige Ryssland för första gången?", "1809"));
        questionBank.add(new Question("När skedde 911?", "2001"));
        questionBank.add(new Question("1983?", "1983"));
    }

    public Question getCurrentQuestion() {
        return questionBank.get(currentQuestionIndex);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void answerCurrentQuestion(String answer) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrectAnswer(answer)) {
            if (currentPlayer == 1) {
                player1Score++;
            } else if (currentPlayer == 2) {
                player2Score++;
            }
        }
        currentQuestionIndex++;
    }

    public boolean isGameOver() {
        return currentQuestionIndex >= questionBank.size();
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void passCard() {
        cardPassed = true;
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }
}