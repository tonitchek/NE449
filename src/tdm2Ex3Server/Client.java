package tdm2Ex3Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;

public class Client {

	//allow to know the number of instance of this class (i.e. number of client connection)
	static int nbConnection = 0;
	//hostname of the client
	private String clientHost;
	//port of the client
	private int clientPort;
	//UDP socket to communicate with client
	private DatagramSocket socket;
	private InetSocketAddress adrDest;
	
	//First Constructor with no parameters
	Client() {
		++nbConnection;
	}
	
	//Second Constructor with hostname and port parameter
	Client(String host, int port) {
		++nbConnection;
		this.clientHost = host;
		this.clientPort = port;
	}
	
	//open the socket on the client
	public void open() throws IOException
	{
		System.out.println("Demarrage du client ");
		//Creation de la socket
		socket = new DatagramSocket();
		// Creation et envoi du message
		adrDest = new InetSocketAddress(clientHost, clientPort);
		socket.setSoTimeout(500); //500ms
	}
	
	//close the socket
	public void close() {
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client ");
	}
	
	//send a message to the client
	public boolean send(String msg) throws IOException {
		//create a binary buffer from the String parameter
		byte[] bufE = new String(msg).getBytes();
		//convert it in UDP packet
		DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		//send it to the socket
		socket.send(dpE);
		//allocate buffer for receive packet
		byte[] bufR = new byte[2048];
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		try {
			//wait packet within time out time
			socket.receive(dpR);
			//convert UDP packet to String message
			String message = new String(bufR, dpR.getOffset(), dpR.getLength());
			//check the answer is an ACK
			if(message.contains("ACK")) {
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SocketTimeoutException ste) {
			//time out received, no client answer
			return false;
		}
	}
	
	//return the number of client connection
	public int getNbConnection() {
		return nbConnection;
	}
	
	//set the client port
	public void setClientPort(int port) {
		this.clientPort = port;
	}
	
	//set the client hostname
	public void setClientHost(String host) {
		this.clientHost = host;
	}
}
