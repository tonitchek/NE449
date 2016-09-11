package tdm2Ex3Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;


public class ClientListen {

	private int port;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	public String colorInstruction;
	public boolean Connected;
	public boolean Shutdown;
	
	ClientListen(int port) throws IOException {
		this.port = port;
		open();
	}
	
	public void open() throws IOException
	{
		//
		System.out.println("Demarrage du ClientListen sur le port "+port);
		
		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(port));
	}

	// Return true if a good instruction is received
	public boolean waitForServerInstruction() throws IOException {
		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());	
		switch(message) {
		case "ACK":
			Connected = true;
			return true;
		case "GREEN":
			colorInstruction = "GREEN";
			return true;
		case "RED":
			colorInstruction = "RED";
			return true;
		case "SHUTDOWN":
			Shutdown = true;
			return true;
		default:
			return false;
		}
		
	}
	
	
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");		
	}
	
}
