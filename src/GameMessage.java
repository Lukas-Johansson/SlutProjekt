import java.io.Serializable;

public class GameMessage implements Serializable {
    private int currentPlayer;
    private int player1Score;
    private int player2Score;
    private boolean cardPassed;

    public GameMessage(int currentPlayer, int player1Score, int player2Score, boolean cardPassed) {
        this.currentPlayer = currentPlayer;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.cardPassed = cardPassed;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public boolean isCardPassed() {
        return cardPassed;
    }
}
