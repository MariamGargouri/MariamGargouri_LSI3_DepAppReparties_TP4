package Activite4_1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class Serveur {
	private static final int PORT = 1234;
	private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues
	public static void main(String[] args) 
	{

		try {

			DatagramSocket socket= new DatagramSocket(PORT); // reservation du port 
			System.out.println("Demarrage du serveur");//pour indiquer que le serveur a démarré avec succès.

			while(true) { // pour pouvoir accepter plusieurs clients

				DatagramPacket userNamePacket = new DatagramPacket(buffer,buffer.length); 

				socket.receive(userNamePacket); //  la reception d'un datagram envoyeé par le client
											   // lit les octet ecrites sur le port et les ecrit dans le buffer de userNamePacket
				

				String userName= new String(userNamePacket.getData(),0,userNamePacket.getLength()); // récuperation du message envoyeé par le client 
																								   //et  sa conversion de byte en String 
				System.out.println(userNamePacket.getAddress()+" : "+userName);// 


				// Réponse au client
				String reponse="Bienvenu "+userName;
				byte[] sendData=reponse.getBytes();//La réponse est convertie en tableau de bytes  
												   
				DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,userNamePacket.getAddress(),userNamePacket.getPort());
				socket.send(sendPacket); // l'envoie de la réponse au client
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
