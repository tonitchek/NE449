package tdm2Ex3Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server {

	private int port;
	DatagramSocket socket;
	InetSocketAddress adrDest;
	//MODIF YO
	public String clientIndex;
	//public int clientIndex;
	public String clientHost;
	public int clientPort;
	public boolean lastClient;
	
	Server(int port) throws IOException {
		this.port = port;
		this.lastClient = false;
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

	public boolean waitForClientConnection() throws IOException {
		// Attente du premier message
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		socket.receive(dpR);
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		if(message.charAt(0)=='R') {
			int portIndex = message.indexOf('P');
			if(portIndex==-1) {
				return false;
			}
			if(message.charAt(message.length()-1)=='#') {
				lastClient=true;
				try {
					//MODIF YO
					clientIndex = message.substring(1, portIndex);
					//clientIndex = Integer.parseInt(message.substring(1, portIndex));
					clientHost = dpR.getAddress().getHostName();
					clientPort = Integer.parseInt(message.substring(portIndex+1, message.length()-1));
				}
				catch(NumberFormatException nfe) {
					return false;
				}
			}
			else {
				try {
					//MODIF YO
					clientIndex = message.substring(1, portIndex);
					//clientIndex = Integer.parseInt(message.substring(1, portIndex));
					clientHost = dpR.getAddress().getHostName();
					clientPort = Integer.parseInt(message.substring(portIndex+1, message.length()));
				}
				catch(NumberFormatException nfe) {
					return false;
				}
			}
			byte[] bufE = new String("ACK").getBytes();
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, new InetSocketAddress(dpR.getAddress().getHostName(), clientPort));
			socket.send(dpE);
			return true;
		}
		return false;
	}
	
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du serveur .");		
	}
	
}
