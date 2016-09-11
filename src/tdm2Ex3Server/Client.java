package tdm2Ex3Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;

public class Client {

	static int nbConnection = 0;
	private String clientHost;
	private int clientPort;
	private DatagramSocket socket;
	private InetSocketAddress adrDest;
	
	Client() {
		++nbConnection;
	}
	
	Client(String host, int port) {
		++nbConnection;
		this.clientHost = host;
		this.clientPort = port;
	}
	
	public void open() throws IOException
	{
		System.out.println("Demarrage du client ");
		//Creation de la socket
		socket = new DatagramSocket();
		// Creation et envoi du message
		adrDest = new InetSocketAddress(clientHost, clientPort);
		socket.setSoTimeout(500); //500ms
	}
	
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client ");
	}
	
	public boolean send(String msg) throws IOException {
		byte[] bufE = new String(msg).getBytes();
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		try {
			socket.receive(dpR);
			String message = new String(bufR, dpR.getOffset(), dpR.getLength());
			if(message.contains("ACK")) {
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SocketTimeoutException ste) {
			return false;
		}
	}
	
	public int getNbConnection() {
		return nbConnection;
	}
	
	public void setClientPort(int port) {
		this.clientPort = port;
	}
	
	public void setClientHost(String host) {
		this.clientHost = host;
	}
}
