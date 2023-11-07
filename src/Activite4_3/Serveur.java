package Activite4_3;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashSet;

public class Serveur {
    private static final int PORT = 1234; // Port sur lequel le serveur écoute
    private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues
    private static HashSet<InetSocketAddress> clients = new HashSet<>(); // Ensemble pour stocker les adresses des clients connectés

    public static void main(String[] args) {
        try {
        		
        		DatagramSocket serverSocket = new DatagramSocket(PORT);
        		System.out.println("Démarrage du Serveur sur le port " + PORT);

            while (true) 
            {// pour pouvoir accepter plusieurs clients
                
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                
                // Attends la réception d'un message d'un client
                serverSocket.receive(receivePacket);
                
                // Extrait le message et l'adresse du client expéditeur
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetSocketAddress senderAddress = new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort());

                 
                if (!clients.contains(senderAddress)) // Si le client n'est pas déjà enregistré,
                	{
                    	clients.add(senderAddress);//on l'ajoute à la liste des clients connectés
                    	System.out.println("Nouveau client connecté: " + senderAddress);
                	}

                // Affiche le message reçu avec l'expéditeur
                System.out.println("Message reçu de " + senderAddress + ": " + message);

                // Réachemine le message à tous les autres clients connectés, à l'exception de l'expéditeur
                for (InetSocketAddress client : clients) 
                {
                    if (!client.equals(senderAddress)) 
                    	{
                        	DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), client.getAddress(), client.getPort());
                        	serverSocket.send(sendPacket);
                    	}
                }
            }
        }catch (IOException e) {e.printStackTrace();} 
    }
}
