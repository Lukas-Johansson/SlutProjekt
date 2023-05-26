public class ClientMain {
    public static void main(String[] args) {
        clients client = new clients();

        client.runProtocol();
        client.sendScore(121212,444);
        client.close();
    }
}
