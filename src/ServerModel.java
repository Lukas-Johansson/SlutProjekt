import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;

    public ServerModel() {
        this.questions = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.score = 0;
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What year did World War II end?", "1945", 10));
        questions.add(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci", 5));
        questions.add(new Question("Which country won the FIFA World Cup in 2018?", "France", 15));
        questions.add(new Question("Meningen med livet?", "Det beror på om korven är tillräckligt tillagad eller inte", 1000));
        questions.add(new Question("Forte i din te?", "Jamen det kan nog funka", 100));
        questions.add(new Question("Vad hände 2005?", "Du det kan man undra", 15));
        questions.add(new Question("Vilket år dog jesus ?", "Typ 0, vilket är lite coolt", 25));
        questions.add(new Question("Är curry baserat eller inte?", "Amen det beror på om man har smaklökar eller inte om man har det så är det gott", 15000));
        questions.add(new Question("Vilken är snyggare?", "Jag", 15));
    }

    public Question getNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            currentQuestionIndex++;
            return question;
        }
        return null;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int points) {
        score += points;
    }
}