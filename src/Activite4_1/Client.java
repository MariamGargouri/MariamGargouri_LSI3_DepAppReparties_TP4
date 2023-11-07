package Activite4_1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class Client {
	private static final int PORT=1234;//Le client utilise le port 1234 pour établir la connexion avec le serveur
	private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues

	public static void main(String[] args) {

		try {

			DatagramSocket socket = new DatagramSocket(); // création d'une socket
			InetAddress inetAddress = InetAddress.getByName("localhost"); //pour savoir l'adresse du serveur

			
			//traitement
			Scanner scanner = new Scanner(System.in);
			System.out.println("ecrivez votre nom et prenom:");
			String nom =scanner.nextLine();
			byte[] sendData=nom.getBytes();//Convertit la chaîne de caractères entrée par l'utilisateur en un tableau de bytes
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,inetAddress,PORT);
			//Crée un DatagramPacket avec les données à envoyer, l'adresse du serveur et le port spécifié.

			socket.send(sendPacket); // envoit du datagram au serveur


			// réception de la reponse
			DatagramPacket receivePacket= new DatagramPacket(buffer,buffer.length);

			socket.receive(receivePacket); // la reception du datagram envoyé par le serveur
			String reponse = new String(receivePacket.getData(),0,receivePacket.getLength());
			System.out.println(" reponse du serveur :"+reponse);

			socket.close();// fermeture du socket

		} catch (Exception e) {e.printStackTrace();}
	}
}
