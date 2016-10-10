package tdm11Yo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHttpServer {

	public static void main(String[] args) throws Exception {
		int lengthFile;
		int bytes=0;
		byte[] bufR = new byte[2048];
		
		ServerSocket serveurHttp = new ServerSocket(8080);
		System.out.println("Ecoute sur le port 8080");
		while (true){
			try (Socket socket = serveurHttp.accept()) {
				
				//réception du message (pas de boucle, il y a juste le début qui nous intéresse)
				
				InputStream is = socket.getInputStream();
				int lenBufR = is.read(bufR);
				String message = new String(bufR, 0 , lenBufR);
				System.out.println("Message recu = "+message);
				
				//par défaut, on envoi sur la page erreur
				String fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/www/error.html";	
				//si la page demandé correspond, on indique la bonne page
				if (message.contains("GET /index.html HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/www/index.html";
				}
				if (message.contains("GET /page1.html HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/www/page1.html";
				}
				if (message.contains("GET /page2.html HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/www/page2.html";
				}
				if (message.contains("GET /tdm11/NE449-TDM11.pdf HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/files/NE449-TDM11.pdf";
				}
				if (message.contains("GET /tdm10/NE449-TDM10.pdf HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/files/NE449-TDM10.pdf";
				}
				if (message.contains("GET /test.txt HTTP/1.1")==true)
				{	
					fileName = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm11Yo/files/test.txt";
				}
				
				// Send file
				FileInputStream file = new FileInputStream(fileName);
				lengthFile = fileName.length()*1024;
				byte[] buffer = new byte[1024];
				OutputStream os = socket.getOutputStream();

				bytes = file.read(buffer);
				do
				{
					os.write(buffer,0,bytes);
					bytes = file.read(buffer);			
				}while (bytes != -1);
				file.close();
			}
		}
	}

}