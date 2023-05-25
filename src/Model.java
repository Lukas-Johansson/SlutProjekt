import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private List<Question> questionBank;
    private int currentQuestionIndex;
    private int playerScore;

    public Model() {
        questionBank = new ArrayList<>();
        currentQuestionIndex = 0;
        playerScore = 0;
        initializeQuestionBank();
    }

    private void initializeQuestionBank() {
        // Skapa och lägg till frågor i frågebanken
        questionBank.add(new Question("När landade människan på månen?", "1969"));
        questionBank.add(new Question("När började andra världskriget?", "1939"));
        questionBank.add(new Question("När släpptes det första iPhone?", "2007"));
        questionBank.add(new Question("När invaderade Sverige Ryssland för första gången?", "1809"));
        questionBank.add(new Question("När skedde 911", "2001"));
        questionBank.add(new Question("1983?", "1983"));
    }

    public Question getCurrentQuestion() {
        return questionBank.get(currentQuestionIndex);
    }

    public void answerCurrentQuestion(String answer) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrectAnswer(answer)) {
            playerScore++;
        }
        currentQuestionIndex++;
    }

    public boolean isGameOver() {
        return currentQuestionIndex >= questionBank.size();
    }

    public int getPlayerScore() {
        return playerScore;
    }
}
