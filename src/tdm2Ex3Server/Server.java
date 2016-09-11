package tdm2Ex3Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server {

	//server port
	private int port;
	//socket
	DatagramSocket socket;
	InetSocketAddress adrDest;
	//client index (number form 0 to max connection)
	public String clientIndex;
	//client hostname
	public String clientHost;
	//client port
	public int clientPort;
	//flag for last client connection detection
	public boolean lastClient;
	
	//Constructor
	Server(int port) throws IOException {
		this.port = port;
		this.lastClient = false;
		//open socket
		open();
	}
	
	//open a socket and listen on the specified port
	public void open() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");
		
		// Le serveur se declare aupres de la couche transport
		// sur le port 3000
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(port));
	}

	//wait for client connection
	public boolean waitForClientConnection() throws IOException {
		// Allocate a binary buffer for receive message
		byte[] bufR = new byte[2048];
		//creat UDP packet
		DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
		//wait for message client request (no time out)
		socket.receive(dpR);
		//message has been received. Convert UDP packet to String
		String message = new String(bufR, dpR.getOffset(), dpR.getLength());
		//if first letter is an 'R', client requst for connection
		if(message.charAt(0)=='R') {
			//ckeck the trame has a 'P' letter for port client listening port
			int portIndex = message.indexOf('P');
			//if no 'P' letter, the trame is not correct for a client connection, discard it
			if(portIndex==-1) {
				return false;
			}
			//the client requst trame is right, check if the client is the last one
			if(message.charAt(message.length()-1)=='#') {
				lastClient=true;
				try {
					//try to get index and port from the ASCII request 
					clientIndex = message.substring(1, portIndex);
					clientPort = Integer.parseInt(message.substring(portIndex+1, message.length()-1));
					//get hostname from UDP packet
					clientHost = dpR.getAddress().getHostName();
				}
				catch(NumberFormatException nfe) {
					//discard client connection if the int conversion form ASCII request failed
					return false;
				}
			}
			else {
				try {
					//try to get index and port from the ASCII request 
					clientIndex = message.substring(1, portIndex);
					clientPort = Integer.parseInt(message.substring(portIndex+1, message.length()));
					//get hostname from UDP packet
					clientHost = dpR.getAddress().getHostName();
				}
				catch(NumberFormatException nfe) {
					//discard client connection if the int conversion form ASCII request failed
					return false;
				}
			}
			//create a binary buffer containing String "ACK" ASCII message
			byte[] bufE = new String("ACK").getBytes();
			//create UDP packet with hostname takem from input UDP packet (client request)
			DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, new InetSocketAddress(dpR.getAddress().getHostName(), clientPort));
			//send ACK
			socket.send(dpE);
			//return true for client connection validation
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
