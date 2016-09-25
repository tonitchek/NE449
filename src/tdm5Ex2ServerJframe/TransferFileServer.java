package tdm5Ex2Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferFileServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		//get args
		if(args.length != 2) {
			System.out.println("Usage: <listening port> <max client nb>");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		int maxNbClient = Integer.parseInt(args[1]);
		//create socket server
		ServerSocket server = new ServerSocket();
		//bind port
		server.bind(new InetSocketAddress(port));
		
		//allocate the number of thread necessary to manage each client
		TransferFile[] tf = new TransferFile[maxNbClient];
		
		//YO
		jFrameServer jf = new jFrameServer();
		jf.open();
		
		//client counter
		int nbClient = 0;
		//while the maximum of client number is not reached
		while(nbClient < maxNbClient) {
			System.out.println("Attente de la connexion du client "+nbClient+"...");
			//get client connection
			Socket socketConnexion = server.accept();
			//allocate buffer to receive file path
			byte[] bufR = new byte[512];
			//get input stream
			InputStream is = socketConnexion.getInputStream();
			//read file path
			int lenBufR = is.read(bufR);
			String filename = new String(bufR, 0 , lenBufR);
			//create thread for client N
			tf[nbClient] = new TransferFile("Client"+nbClient,socketConnexion,filename);
			//start the thread (transfer the file)
			tf[nbClient].start();
			//increment client
			++nbClient;
		}
		
		//wait for all thread finish and check if threads are dead
		for(int i=0;i<nbClient;i++) {
			try {
				tf[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Failed to join "+tf[i].getName());
			}
			System.out.println("Thread "+tf[i].getName()+" alive: "+tf[i].isAlive());
		}

		//close the server
		server.close();
	}
}
