package tdm5Ex2Server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferFileServer {

	public static void main(String[] args) throws IOException {
		//get args
		if(args.length != 2) {
			System.out.println("Usage: <listening port> <max client nb>");
			System.exit(1);
		}
		int port = Integer.parseInt(args[0]);
		int maxNbClient = Integer.parseInt(args[1]);
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress(port));
		
		TransferFile[] tf = new TransferFile[maxNbClient]; 
		int nbClient = 0;
		while(nbClient < maxNbClient) {
			System.out.println("Attente de la connexion du client "+nbClient+"...");
			Socket socketConnexion = server.accept();
			byte[] bufR = new byte[512];
			InputStream is = socketConnexion.getInputStream();
			int lenBufR = is.read(bufR);
			String filename = new String(bufR, 0 , lenBufR);
			tf[nbClient] = new TransferFile("Client"+nbClient,socketConnexion,filename);
			tf[nbClient].start();
			++nbClient;
		}
		
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

		server.close();
	}
}
