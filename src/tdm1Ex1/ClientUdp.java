package tdm1Ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ClientUdp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientUdp client = new ClientUdp();
		try {
			client.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Catch exception");
			e.printStackTrace();
		}
	}
	
	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse 
	 * 
	 */
	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du client ...");
		
		//Creation de la socket
		DatagramSocket socket = new DatagramSocket();
		
		// Creation et envoi du message
		InetSocketAddress adrDest = new InetSocketAddress("192.168.130.20", 3000);
		byte[] bufE = new String("hello les ados").getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		System.out.println("Message envoye");

		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

}

