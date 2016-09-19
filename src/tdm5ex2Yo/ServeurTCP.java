package tdm5ex2Yo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */

public class ServeurTCP
{
	static ServerSocket socketEcoute;
	static String queryFile="";
	static FileInputStream inputStream;
	static Socket socketConnexion;

	public static void main(String[] args) throws Exception
	{
		ServeurTCP serveurTCP = new ServeurTCP();
		serveurTCP.open();
		
		serveurTCP.listen();
		//queryFile = serveurTCP.listen();
		//System.out.println("fichier"+queryFile);

		File file = new File(queryFile);    
		if (file.exists()) {
			ServeurTCP.send("File has been found");
			inputStream = new FileInputStream(queryFile);
		}
		else
		{
			ServeurTCP.send("CAN");		
		}

		// Fermeture de la socket de connexion
		//socketConnexion.close();

		serveurTCP.close();
	}

	void open() throws IOException
	{
		//
		System.out.println("Demarrage du serveur ...");

		// Le serveur se declare aupres de la couche transport
		// sur le port 5099
		socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(5099));

	}

	void listen() throws IOException
	{
		Socket socketConnexion = socketEcoute.accept();
		// Un client s'est connecte, le serveur lit le message envoye par le le client (sans garantie de lire tout le message)
		byte[] bufR = new byte[2048];
		InputStream is = socketConnexion.getInputStream();
		int lenBufR = is.read(bufR);
		String message = new String(bufR, 0 , lenBufR);
		System.out.println("Message recu = "+message);
		queryFile = message;

		//return listen();
	}

	static void send(String message) throws IOException
	{
		//Socket socketConnexion = socketEcoute.accept();
		// Emission d'un message en retour
		byte[] bufE = new String(message).getBytes();
		OutputStream os = socketConnexion.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye =" + message);

	}

	void close() throws IOException
	{
		socketEcoute.close();
		System.out.println("Arret du serveur .");
	}
}


