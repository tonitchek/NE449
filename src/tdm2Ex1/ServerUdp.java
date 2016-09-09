package tdm2Ex1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ServerUdp {

	private int port;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	
	ServerUdp(int port) throws IOException {
		this.port = port;
		open();
	}
	
	public void open() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");
		
		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(port));
	}

	public boolean waitForHand() throws IOException {
		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		if(message.contains("OK")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");		
	}
	
}
