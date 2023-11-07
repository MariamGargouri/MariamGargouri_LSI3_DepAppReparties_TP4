package Activite4_2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Serveur {
	private static final int PORT = 1234;
	private static byte [] buffer=new byte [1024]; // Tampon pour les données reçues
	public static void main(String[] args) 
	{

		try {

			DatagramSocket socket= new DatagramSocket(PORT); // reservation du port 
			System.out.println("Demarrage du serveur");//pour indiquer que le serveur a démarré avec succès.

			while(true) { // pour pouvoir accepter plusieurs clients

				DatagramPacket packet = new DatagramPacket(buffer,buffer.length); 

				socket.receive(packet); //  la reception d'un datagram envoyé par le client
				

				String user= new String(packet.getData(),0,packet.getLength()); // récuperation du message envoyé par le client 
																			   //et  sa conversion de byte en String 

				
				// Réponse au client
				String time = null;
				time = getCurrentTime(); //récupération de la date 
				byte[] sendData=time.getBytes();//La réponse est convertie en tableau de bytes  				   
				DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,packet.getAddress(),packet.getPort());
				socket.send(sendPacket); // l'envoie de la réponse au client
						}
			} catch (Exception e) {e.printStackTrace();}
	}
	
	private static String getCurrentTime()
	{
		SimpleDateFormat dateFormat= new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		Date date = new Date();
		return dateFormat.format(date);
	}
}


