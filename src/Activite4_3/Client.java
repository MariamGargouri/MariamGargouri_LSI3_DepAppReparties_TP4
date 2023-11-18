package Activite4_3;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234; // Port d'écoute du serveur
	private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues

    public static void main(String[] args) {
        try {
        	
        	DatagramSocket clientSocket = new DatagramSocket();// Crée un socket client pour la communication UDP

            InetAddress serverAddress = InetAddress.getByName("localhost");// Obtient l'adresse IP du serveur (localhost dans cet exemple)
            
            Scanner scanner = new Scanner(System.in);// Crée un scanner pour la saisie utilisateur
            System.out.print("Entrez votre nom d'utilisateur: ");// Demande à l'utilisateur de saisir son nom d'utilisateur
            String username = scanner.nextLine();
            System.out.print("Message: ");// Demande à l'utilisateur de saisir un message
            String message = scanner.nextLine();

            // Préparation du message qui sera  envoyé au serveur
            String fullMessage = "[" + username + "]: " + message;
            byte[] sendData = fullMessage.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, PORT);
          
            clientSocket.send(sendPacket);// Envoie le paquet au serveur
            
           // réception des messages d'autres clients
         	DatagramPacket receivePacket= new DatagramPacket(buffer,buffer.length);
         	clientSocket.receive(receivePacket); // la reception du datagram envoyé par le serveur
         	String messages = new String(receivePacket.getData(),0,receivePacket.getLength());
         	System.out.println(messages);
         	
         	clientSocket.close();// fermeture du socket
            } catch (IOException e) {e.printStackTrace();}
    }
}
