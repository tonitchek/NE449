package tdm1Ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServerUdp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerUdp server = new ServerUdp();
		try {
			server.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void execute() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");
		
		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		DatagramSocket socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(3000));

		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		System.out.println("Message recu = "+message+" From "+dpR.getAddress()+" "+dpR.getPort());
		
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");
	}

}
