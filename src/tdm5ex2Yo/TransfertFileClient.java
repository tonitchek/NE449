package tdm5ex2Yo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TransfertFileClient {


	static String host;
	static int port;
	static String filePath;
	static InetSocketAddress adrDest;
	static Socket socket;
	static String messFromServer;
	//static String newFile = "C:\Users\B03073\Documents\NE449\src\tdm5ex1Yo\test_copy.txt";
	
	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			System.out.println("Usage: <hostname> <port_server> <file_path>");
			System.exit(1);
		}

		host = args[0];
		port = Integer.parseInt(args[1]);
		filePath = args[2];

		System.out.println("Demarrage du client ...");
		//Creation de la socket
		socket = new Socket();

		// Connexion au serveur 
		InetSocketAddress adrDest = new InetSocketAddress(host, port);
		socket.connect(adrDest);
		
		send(filePath);
		messFromServer = receive();
		System.out.println("message recu"+ messFromServer);
		//close();
		//OutputStream os = new FileOutputStream(newFile);
		//os.write(messFromServer);
		
	}



	//send a String on the socket 
	public static void send(String message) throws IOException
	{
		// Envoi de la requete
		byte[] bufE = new String(message).getBytes();
		OutputStream os = socket.getOutputStream();
		os.write(bufE);
	}

	//receive String from socket. Blocking method 
	public static String receive() throws IOException {
		// Attente de la reponse 
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		String reponse = new String(bufR, 0 , lenBufR );
		return reponse;
	}

	//read the code
	public static void close() throws IOException
	{
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}
}
