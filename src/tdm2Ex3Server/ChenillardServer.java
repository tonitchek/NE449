package tdm2Ex3Server;

import java.io.IOException;

public class ChenillardServer {

	static int clientNb;
	static int listeningPort;
	static int delayMs;
	static Server server;
	static Client[] clients;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//get args (max client, listening port)
		if(args.length != 3) {
			System.out.println("Usage: <max client connection> <listening port>");
			System.exit(1);
		}
		else {
			clientNb = Integer.parseInt(args[0]);
			listeningPort = Integer.parseInt(args[1]);
			delayMs = Integer.parseInt(args[2]);
			System.out.println(clientNb+" "+listeningPort);
		}
		
		//create server object
		server = new Server(listeningPort);
		//allocate the max client objects
		clients = new Client[clientNb];

		//waits for client registration request R<index>P<port>
		int connectionNb = 0;
		while((connectionNb < clientNb) && (server.lastClient==false)) {
			if(server.waitForClientConnection()) {
				System.out.println(connectionNb);
				System.out.println(server.clientIndex+" "+server.clientHost+" "+server.clientPort);
				clients[connectionNb]= new Client(server.clientHost,server.clientPort);
				clients[connectionNb].open();
				++connectionNb;
			}
			System.out.println(connectionNb);
		}
		
		for(int j=0;j<10;j++) {
			for(int k=0;k<connectionNb;k++) {
				clients[k].send("RED");
				Thread.sleep(delayMs);
				clients[k].send("GREEN");
			}
		}
		
		for(int l=0;l<connectionNb;l++) {
			clients[l].send("SHUTDOWN");
			clients[l].close();
		}
		
		server.close();
	}

}
