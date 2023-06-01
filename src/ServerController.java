import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerController {
    private int port;
    private ServerModel serverModel;
    private ServerView serverView;
    private List<ObjectOutputStream> outputStreams;

    public ServerController(int port) {
        this.port = port;
        this.serverModel = new ServerModel();
        this.serverView = new ServerView();
        this.outputStreams = new ArrayList<>();
    }

    public void start() {
        serverView.displayMessage("Server started on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serverView.displayMessage("New client connected: " + clientSocket.getInetAddress());

                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStreams.add(outputStream);

                ClientHandler clientHandler = new ClientHandler(clientSocket, outputStream);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            serverView.displayError("Error starting the server: " + e.getMessage());
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;

        public ClientHandler(Socket clientSocket, ObjectOutputStream outputStream) {
            this.clientSocket = clientSocket;
            this.outputStream = outputStream;
            try {
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                serverView.displayError("Error creating input stream: " + e.getMessage());
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Question question = serverModel.getNextQuestion();
                    if (question == null) {
                        break;
                    }

                    sendQuestion(question);

                    String answer = receiveAnswer();
                    int points = evaluateAnswer(question, answer);
                    updateScore(points);
                    sendScore();

                    if (serverModel.getCurrentQuestionIndex() >= serverModel.getQuestions().size()) {
                        break;
                    }
                }

                sendScore();
                close();

            } catch (IOException | ClassNotFoundException e) {
                serverView.displayError("Error handling client request: " + e.getMessage());
            }
        }

        public void sendQuestion(Question question) throws IOException {
            outputStream.writeObject(question);
            outputStream.flush();
        }

        public String receiveAnswer() throws IOException, ClassNotFoundException {
            return (String) inputStream.readObject();
        }

        public void sendScore() throws IOException {
            for (ObjectOutputStream outputStream : outputStreams) {
                outputStream.writeInt(serverModel.getScore());
                outputStream.flush();
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
                if (clientSocket != null) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                serverView.displayError("Error closing connections: " + e.getMessage());
            }
        }

        public int evaluateAnswer(Question question, String answer) {
            if (question.isCorrect(answer)) {
                return question.getPoints();
            } else {
                return 0;
            }
        }

        public void updateScore(int points) {
            serverModel.updateScore(points);
        }
    }

    public static void main(String[] args) {
        int port = 12345;

        ServerController serverController = new ServerController(port);
        serverController.start();
    }
}
