package tdm2Ex3Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ClientSend {

	private int port;
	private String host;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	
	ClientSend(String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		open();
	}
	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse 
	 * 
	 */
	public void open() throws IOException
	{
		System.out.println("Demarrage du client ...");
		//Creation de la socket
		socket = new DatagramSocket();
		// Creation et envoi du message
		adrDest = new InetSocketAddress(host, port);	
	}
	
	public void send(String msg) throws IOException {
		byte[] bufE = new String(msg).getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
	}
	
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

}
