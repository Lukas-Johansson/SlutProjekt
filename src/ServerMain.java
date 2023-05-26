public class ServerMain {
    public static void main(String[] args) {
        server server = new server();

        server.acceptClients();
        server.getStreams();
        server.runProtocol();
        int[] scores = server.receiveScore();
        System.out.println("Player 1 Score: " + scores[0]);
        System.out.println("Player 2 Score: " + scores[1]);

        server.close();
    }
}
