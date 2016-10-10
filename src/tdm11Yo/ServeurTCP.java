package tdm11Yo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import tdm6ex1Yo.jFrame;

/**
 * Serveur basique TCP
 */
public class ServeurTCP
{

	public static void main(String[] args) throws Exception
	{
		ServeurTCP serveurTCP = new ServeurTCP();
		serveurTCP.execute();
	}

	private void execute() throws IOException
	{
		String fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm10Yo/bus13.html";
		int lengthFile;

		System.out.println("Demarrage du serveur ...");

		// Le serveur se declare aupres de la couche transport
		// sur le port 5099
		ServerSocket socketEcoute = new ServerSocket();
		socketEcoute.bind(new InetSocketAddress(5099));
		// Permet de compter le nombre de clients servis
		int nbClient = 1;

		int bytes=0;

		while(nbClient<10)
		{
			// Attente de la connexion d'un client
			System.out.println("Attente de la connexion du client "+nbClient+"...");
			Socket socketConnexion = socketEcoute.accept();

			// Un client s'est connecte, le serveur lit le message envoye par le le client (sans garantie de lire tout le message)
			byte[] bufR = new byte[2048];
			InputStream is = socketConnexion.getInputStream();
			int lenBufR = is.read(bufR);
			String message = new String(bufR, 0 , lenBufR);
			System.out.println("Message recu = "+message);
			if (message.contains("GET /index.html HTTP/1.1")==true)
			{	
				FileInputStream file = new FileInputStream(fileName);
				lengthFile = fileName.length()*1024;
				byte[] buffer = new byte[1024];
				OutputStream os = socketConnexion.getOutputStream();
				//os.write(buffer);

				bytes = file.read(buffer);
				do
				{
					os.write(buffer,0,bytes);
					bytes = file.read(buffer);			
				}while (bytes != -1);
				file.close();

				//				
				//				String response = "<html>\r\n"
				//				+ "<head>\r\n"
				//				+ "<title>My Web Server</title></head>\r\n"
				//				+ "<h1>Welcome to my Web Server!</h1>\r\n"
				//				+ "</html>\r\n";			
				//				// Emission d'un message en retour
				//				byte[] bufE = new String(response).getBytes();
				//				OutputStream os = socketConnexion.getOutputStream();
				//				os.write(response.getBytes("UTF-8"));
				//				System.out.println("Message envoye = ok");

				//				PrintWriter out = new PrintWriter(socketConnexion.getOutputStream(), true);
				//				out.write("HTTP/1.0 200 OK\r\n");
				//				out.write("Content-type: text/html\r\n");
				//				out.write("Server-name: myserver\r\n");
				//				String response = "<html>n\r\n"
				//						+ "<head>n\r\n"
				//						+ "<title>My Web Server</title></head>n\r\n"
				//						+ "<h1>Welcome to my Web Server!</h1>n\r\n"
				//						+ "</html>n\r\n";
				//				out.write("Content-length: " + response.length());
				//				out.write("\r\n");
				//				out.write(response);
				//				out.flush();
				//				out.close();
				//				socketConnexion.close();

			}


			is.close();
			// Fermeture de la socket de connexion
			socketConnexion.close();

			// On incremente le nombre de clients servis
			nbClient++;
		}

		socketEcoute.close();
		System.out.println("Arret du serveur .");
	}

}
