package tdm4ex3TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class ClientTCP
{
	String host;
	int port;
	Socket socket;
	InetSocketAddress adrDest;


	ClientTCP(String host, int port){
		this.host = host;
		this.port = port;
	}

	/**
	 * Le client cree une socket, envoie un message au serveur
	 * et attend la reponse 
	 * @throws IOException 
	 * 
	 */

	//TODO open wait 
	public void open() throws IOException{
		//
		System.out.println("Demarrage du client ...");

		//Creation de la socket
		socket = new Socket();

		// Connexion au serveur 
		InetSocketAddress adrDest = new InetSocketAddress(host, port);
		socket.connect(adrDest);		
	}

	public void send(String message) throws IOException
	{
		// Envoi de la requete
		byte[] bufE = new String(message).getBytes();
		OutputStream os = socket.getOutputStream();
		os.write(bufE);
		System.out.println("Message envoye");


	}
	public String receive() throws IOException {
		// Attente de la reponse 
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR = is.read(bufR);
		String reponse = new String(bufR, 0 , lenBufR );
		System.out.println("Reponse recue = "+reponse);
		return reponse;
	}

	public void close() throws IOException
	{
		// Fermeture de la socket
		socket.close();
		System.out.println("Arret du client .");
	}

	/**
	 * Methode utilitaire permettant de lire au minimum nbByte octets dans le fux is
	 * 
	 * A noter : si la methode read retourne plus de caracteres que nbByte, 
	 * alors les caracteres lus en plus sont ajoutes dans la reponse 
	 */
	private StringBuffer readInputStream(int nbByte, InputStream is) throws IOException
	{
		StringBuffer buf = new StringBuffer();

		// Nombre de caracteres reellement lus au total
		int nbByteRead=0;

		int nb;
		byte[] bufR = new byte[1024];

		while(nbByteRead<nbByte)
		{
			nb = is.read(bufR);
			if (nb==-1)
			{
				throw new IOException("Fin du stream atteinte avant d'avoir lu "+nbByte+" octets");
			}
			nbByteRead = nbByteRead+nb;
			buf.append(new String(bufR,0,nb));
		}
		return buf;

	}

}
