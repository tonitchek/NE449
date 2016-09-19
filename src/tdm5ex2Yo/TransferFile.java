package tdm5ex2Yo;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.FileInputStream;

public class TransferFile extends Thread {

	private Socket client;
	InputStream fromClient;
	OutputStream toClient;
	private FileInputStream file;
	private byte[] buffer;

	TransferFile(String name, Socket sck, String filename) throws IOException {
		super(name);
		System.out.println("Thread "+this.getName()+" has been started");
		client = sck;
		fromClient = client.getInputStream();
		toClient = client.getOutputStream();
		file = new FileInputStream(filename);
		buffer = new byte[1024];
	}

	TransferFile(Socket sck, String filename) throws IOException {
		super();
		System.out.println("Thread "+this.getName()+" has been started");
		client = sck;
		fromClient = client.getInputStream();
		toClient = client.getOutputStream();
		file = new FileInputStream(filename);
		buffer = new byte[1024];
	}
	
	public void run() {
		try {
			while(file.read(buffer) != -1) {
				toClient.write(buffer);
				Thread.sleep(500);
			}
			toClient.write(0x4); //EOT byte
			file.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread "+this.getName()+" finished run()");
	}
	
	public void finalize() {
		System.out.println("Exit thread "+this.getName());
	}
}
