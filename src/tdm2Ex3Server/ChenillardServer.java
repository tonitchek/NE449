package tdm2Ex3Server;

import java.io.IOException;

public class ChenillardServer {

	static int clientNb;
	static int listeningPort;
	static Server server;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int connectionNb = 0;
		//TODO get args (max client, listening port)
		if(args.length != 2) {
			System.out.println("Usage: <max client connection> <listening port>");
			System.exit(1);
		}
		else {
			clientNb = Integer.parseInt(args[0]);
			listeningPort = Integer.parseInt(args[1]);
			System.out.println(clientNb+" "+listeningPort);
		}
		//TODO create serverUdp object
		server = new Server(listeningPort);
		//waits for client registration request R<index>P<port>
		while((connectionNb < clientNb) && (server.lastClient==false)) {
			if(server.waitForClientConnection()) {
				System.out.println(server.clientIndex+" "+server.clientPort);
				++connectionNb;
			}
			System.out.println(connectionNb);
		}
		
		server.close();
	}

}
