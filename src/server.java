import java.io.*;
import java.net.*;

public class server {
    private String host = "localhost";
    private int port = 28758;
    private ServerSocket server;
    private Socket client;
    private ObjectOutputStream serverOut;
    private ObjectInputStream serverIn;

    public server() {
        try {
            server = new ServerSocket(port, 50, InetAddress.getByName(host));
        } catch (IOException e){
            System.err.println("Server: Kunde inte starta!");
            e.printStackTrace();
        }
        System.out.println("Server: Servern är startad ...");
    }

    public void acceptClients(){
        try {
            client = server.accept();
            System.out.println(client.getInetAddress());
        } catch (IOException e){
            System.err.println("Server: Did not accept clients!");
            e.printStackTrace();
        }
        System.out.println("Server: Clients accepted ...");
    }

    public void getStreams(){
        try {
            serverOut = new ObjectOutputStream(client.getOutputStream());
            serverIn = new ObjectInputStream(client.getInputStream());
        } catch (IOException e){
            System.err.println("Server: Did not get clients streams!");
            e.printStackTrace();
        }
        System.out.println("Server: Have server clients ...");
    }

    public void runProtocol(){
        try {
            String messageOut = "Hej clients";
            serverOut.writeObject(messageOut);
            serverOut.flush();
        } catch (IOException e){
            System.err.println("Server: Kunde inte skicka msg!");
            e.printStackTrace();
        }
        System.out.println("Server: Kunde skicka msg ...");
    }

    public int[] receiveScore() {
        try {
            int[] scores = new int[2];
            scores[0] = serverIn.readInt();
            scores[1] = serverIn.readInt();
            return scores;
        } catch (IOException e) {
            System.err.println("Server: Failed to receive scores!");
            e.printStackTrace();
            return new int[]{0, 0};
        }
    }

    public void close() {
        try {
            if (serverIn != null)
                serverIn.close();
            if (serverOut != null)
                serverOut.close();
            if (client != null)
                client.close();
            if (server != null)
                server.close();
        } catch (IOException e) {
            System.err.println("Server: Error closing connections!");
            e.printStackTrace();
        }
    }
}
