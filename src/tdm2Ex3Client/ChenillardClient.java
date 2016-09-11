package tdm2Ex3Client;

import java.io.IOException;

public class ChenillardClient {

	static ColorFrame cf;
	static ClientSend client;
	static ClientListen listenClient;
	static int index;
	static int last;
	static int listenPort;
	static String serverHostname;
	static int serverPort;
	static boolean connected;

	public static void main(String[] args) throws IOException, InterruptedException {

		//Get args, get index of the client, use indexI as integer to calculate frame and ports.
		index = Integer.parseInt(args[0]);
		serverHostname = args[1];
		serverPort = Integer.parseInt(args[2]);
		listenPort =  Integer.parseInt(args[3]);
		last  =  Integer.parseInt(args[4]); 

		//Set client Frame and ports, depending on the indexI, port need to be define on 3 first port number, ex 800 for 8001
		cf = new ColorFrame(index*200+5,200,index);
		listenPort = Integer.parseInt(listenPort+""+index);

		//Startup client & server
		listenClient = new ClientListen(listenPort);
		client = new ClientSend(serverHostname,serverPort);

		//Try connection to server while no response from server
		// send trame R<index>P<port> while no ACK
		while(listenClient.Connected == false) {
			if(last==1) {
				client.send("R"+index+"P"+listenPort+"#");
			}
			else {
				client.send("R"+index+"P"+listenPort);
			}
			if(listenClient.waitForServerInstruction()) {
				System.out.println("OK CLIENT ENREGISTRE");
			}			
		}

		//Wait for instructions until the server send the shutdown information
		while(listenClient.Shutdown == false) {
			if(listenClient.waitForServerInstruction()){
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
		}
		
		// Close
		client.close();
		listenClient.close();
		cf.close();

	}

}


