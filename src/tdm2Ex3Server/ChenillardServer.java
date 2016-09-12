package tdm2Ex3Server;

import java.io.IOException;

public class ChenillardServer {

	//Maximum client number (given in args)
	static int clientNb;
	//Server listening port (given in args)
	static int listeningPort;
	//delay between red and green color state of client window
	static int delayMs;
	//Server object declaration
	static Server server;
	//Array of Client object declaration 
	static Client[] clients;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//get args (max client, listening port)
		//exit if number of input args is not 3 (clientNb, listeningPort, delayMs)
		if(args.length != 3) {
			System.out.println("Usage: <max client connection> <listening port>");
			System.exit(1);
		}
		else {
			//convert string args to int
			clientNb = Integer.parseInt(args[0]);
			listeningPort = Integer.parseInt(args[1]);
			delayMs = Integer.parseInt(args[2]);
			System.out.println(clientNb+" "+listeningPort+" "+delayMs);
		}
		
		//create server object
		server = new Server(listeningPort);
		//allocate the max client objects
		clients = new Client[clientNb];

		//waits for client registration request R<index>P<port>
		int connectionNb = 0;
		//wait for client while maximum has not been reached AND last client didn't registered
		while((connectionNb < clientNb) && (server.lastClient==false)) {
			//if the client request is right
			if(server.waitForClientConnection()) {
				System.out.println(server.clientIndex+" "+server.clientHost+" "+server.clientPort);
				//create client object of array with host and port retrieved by server
				clients[connectionNb]= new Client(server.clientHost,server.clientPort);
				//open the client socket
				clients[connectionNb].open();
				//increment client number (to be checked with maximum capability of the server)
				++connectionNb;
			}
			System.out.println(connectionNb);
		}
		
		//test chenillard for 10 iterations 
		for(int j=0;j<10;j++) {
			
			//Set each client one after one to Red followed by Green after delayMs (given in args)
			for(int k=0;k<connectionNb;k++) {
				clients[k].send("RED");
				Thread.sleep(delayMs);
				clients[k].send("GREEN");
			}
		}
		
		//send shutdown request to all remote client
		for(int l=0;l<connectionNb;l++) {
			clients[l].send("SHUTDOWN");
			//close the client socket
			clients[l].close();
		}
		
		//close the server
		server.close();
	}

}
