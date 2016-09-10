package tdm2Ex3Client;

import java.io.IOException;

public class ChenillardClient {

	static ColorFrame cf;
	static ClientSend client;
	static ClientListen listenClient;
	static String indexS;
	static int indexI;
	static int last;
	static int portIn;
	static int portOut;
	// Hostname and port of the main server, need to be define by args?
	static String serverHostname="127.0.0.1";
	static int serverPort=8888;
	static boolean connected;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		//get args, get index of the client, use indexI as integer to calculate frame and ports.
		indexS = (args[0]);
		indexI = Character.getNumericValue(indexS.charAt(0)) ; 

		//set client Frame and ports, depending on the indexI
		cf = new ColorFrame(indexI*200+5,200,indexI);
		portIn = Integer.parseInt("800"+indexI);
		portOut = Integer.parseInt("900"+indexI);		
		
		listenClient = new ClientListen(portIn);
		client = new ClientSend(serverHostname,serverPort);
		
		//Try connection to server while no response from server
			// send trame R<index> P<port> while no ACK
		while(listenClient.Connected == false) {
			client.send("R"+indexS+" P"+portIn);
			if(listenClient.waitForServerInstruction()) {
				System.out.println("OK CLIENT ENREGISTRE");
			}			
		}
		
		//Wait for instructions until the server send the shutdown information
		while(listenClient.Shutdown == false) {
			if(listenClient.colorInstruction == "RED") {
				cf.setRed();
				// Client send ACK? If no ACK server consider him dead?
				client.send("ACK");
			}
			if(listenClient.colorInstruction == "GREEN") {
				cf.setGreen();
				// Client send ACK? If no ACK server consider him dead?
				client.send("ACK");
			}
		}
			
		
		//TODO create serverUDP object listening on port arg before	
		// if instruction action on colorframe or S
		//TODO while connected==true listen instruction,
				// if instruction green or red => frame action
				// if S then kill, connected = false (exit while loop)
		// TODO close frame	
		
	}

}


