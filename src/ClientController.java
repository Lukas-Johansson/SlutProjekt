import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientController {
    private Socket server;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private ClientModel clientModel;
    private ClientView clientView;

    public ClientController(ClientModel clientModel, ClientView clientView) {
        this.clientModel = clientModel;
        this.clientView = clientView;
    }

    public void start(String ip, int port) {
        try {
            server = new Socket(ip, port);
            clientView.showMessage("Connected to the server...");

            outputStream = new ObjectOutputStream(server.getOutputStream());
            inputStream = new ObjectInputStream(server.getInputStream());

            clientView.setAnswerListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String answer = clientView.getInput();
                    sendAnswer(answer);
                }
            });

            while (true) {
                Question question;
                try {
                    question = receiveQuestion();
                } catch (Exception e) {
                    break;
                }
                clientView.displayQuestion(question);
                int score = receiveScore();
                clientView.displayScore(score);
            }
            int finalScore = receiveScore();
            clientView.displayFinalScore(finalScore);
        } catch (IOException e) {
            clientView.showMessage("Error connecting to the server: " + e.getMessage());
        } finally {
            close();
        }
    }

    public void sendAnswer(String answer) {
        try {
            outputStream.writeObject(answer);
            outputStream.flush();
        } catch (IOException e) {
            clientView.showMessage("Error sending answer to server: " + e.getMessage());
        }
    }

    public Question receiveQuestion() throws IOException, ClassNotFoundException {
        Object object = inputStream.readObject();
        if (object instanceof Question) {
            return (Question) object;
        } else {
            throw new ClassNotFoundException("Invalid object received from server");
        }
    }

    public int receiveScore() {
        try {
            return inputStream.readInt();
        } catch (IOException e) {
            clientView.showMessage("Error receiving score from server: " + e.getMessage());
            return 0;
        }
    }

    public void close() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (server != null) {
                server.close();
            }
        } catch (IOException e) {
            clientView.showMessage("Error closing connections: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String ip = "localhost";
        int port = 12345;
        ClientModel clientModel = new ClientModel();
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(clientModel, clientView);
        clientController.start(ip, port);
    }
}