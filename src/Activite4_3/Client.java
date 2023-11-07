package Activite4_3;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234; // Port d'écoute du serveur
	private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues

    public static void main(String[] args) {
        try {
        	
        	DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez votre nom d'utilisateur: ");
            String username = scanner.nextLine();
            System.out.print("Message: ");
            String message = scanner.nextLine();

            // Prépare le message à envoyer au serveur
            String fullMessage = "[" + username + "]: " + message;
            byte[] sendData = fullMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
          
            clientSocket.send(sendPacket);
            } catch (IOException e) {e.printStackTrace();}
    }
}
