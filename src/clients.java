import java.io.*;
import java.net.*;

public class clients {
    private Socket server;
    private ObjectOutputStream clientOut;
    private ObjectInputStream clientIn;

    private int port = 28758;
    private String ip = "localhost";

    public clients(){
        try {
            server = new Socket(ip, port);
            clientOut = new ObjectOutputStream(server.getOutputStream());
            clientIn = new ObjectInputStream(server.getInputStream());
        } catch (IOException e){
            System.err.println("Clients: Kunde inte koppla till servern!");
            e.printStackTrace();
        }
        System.out.println("Clients: kopplad till servern ...");
    }

    public void runProtocol(){
        try {
            String messageOut = "Hej server";
            clientOut = new ObjectOutputStream(server.getOutputStream());
            //clientOut.writeObject(messageOut);
            //clientOut.flush();
        } catch (IOException e){
            System.err.println("Clients: Kunde inte skicka msg!");
            e.printStackTrace();
        }
        System.out.println("Clients: Kunde skicka msg ...");
    }

    public void sendScore(int player1Score, int player2Score) {
        try {
            clientOut.writeInt(player1Score);
            clientOut.writeInt(player2Score);
            clientOut.flush();
        } catch (IOException e) {
            System.err.println("Clients: Failed to send scores!");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (clientIn != null)
                clientIn.close();
            if (clientOut != null)
                clientOut.close();
            if (server != null)
                server.close();
        } catch (IOException e) {
            System.err.println("Clients: Error closing connections!");
            e.printStackTrace();
        }
    }
}
