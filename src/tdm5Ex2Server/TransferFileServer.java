package tdm5Ex2Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		//client counter
		int nbClient = 0;
		//index in the thread array
		int clientIndex = 0;
		//flag indicating maximum client number has been reached
		boolean maxReached = false;
		//flag indicating a thread is terminated and available
		boolean threadAvalaible = false;
		int i;
		//while the maximum of client number is not reached
		while(true) {
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

			//a client requests a connection and file transfer
			//if all threads have been started
			if(maxReached) {
				//check if a thread is terminated, i.e terminated
				threadAvalaible = false;
				for(i=0;i<maxNbClient;i++) {
					if(tf[i].getState() == Thread.State.TERMINATED) {
						clientIndex = i;
						threadAvalaible = true;
					}
				}
				//if a thread is available, start it
				if(threadAvalaible) {
					//create thread for client N
					tf[clientIndex] = new TransferFile("Client"+nbClient,socketConnexion,filename);
					//start the thread (transfer the file)
					tf[clientIndex].start();
					//increment client number
					++nbClient;
				}
				//else send a retry later message
				else {
					OutputStream os = socketConnexion.getOutputStream();
					os.write(0x4);
				}
			}
			else {
				//create thread for client N
				tf[clientIndex] = new TransferFile("Client"+nbClient,socketConnexion,filename);
				//start the thread (transfer the file)
				tf[clientIndex].start();
				++clientIndex;
				//increment client number
				++nbClient;
			}
			//if the maximum of client is reached, set all threads have been started flag
			if(maxReached==false) {
				if(clientIndex == maxNbClient) {
					maxReached = true;
				}
			}
		}
	}
}
