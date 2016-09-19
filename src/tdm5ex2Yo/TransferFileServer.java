package tdm5ex2Yo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferFileServer {

	static Socket socketConnexion;
	
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
		
		int nbClient = 1;
		while(nbClient < maxNbClient) {
			System.out.println("Attente de la connexion du client "+nbClient+"...");
			socketConnexion = server.accept();
			byte[] bufR = new byte[512];
			InputStream is = socketConnexion.getInputStream();
			int lenBufR = is.read(bufR);
			//MODIF YO remove -1 @lenBufR-1, the file was not found coz of this. miss last char.
			String filename = new String(bufR, 0 , lenBufR);
			System.out.println(filename);
			File file = new File(filename); 
			if (file.exists()) {
				send(" File has been found, copy proceed");
				TransferFile tf = new TransferFile("Cient"+nbClient,socketConnexion,filename);
				tf.start();
			}
			else
			{
				send("CAN");		
			}
			
			++nbClient;
		}
		
		server.close();
	}
	
	static void send(String message) throws IOException
	{
		byte[] bufE = new String(message).getBytes();
		OutputStream os = socketConnexion.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye =" + message);
	}

}
