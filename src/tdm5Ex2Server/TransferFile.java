package tdm5Ex2Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TransferFile extends Thread {

	private Socket client;
	InputStream fromClient;
	OutputStream toClient;
	private FileInputStream file;
	private boolean canStart;
	private byte[] buffer;

	TransferFile(String name, Socket sck, String filename) throws IOException {
		super(name);
		client = sck;
		fromClient = client.getInputStream();
		toClient = client.getOutputStream();
		try {
			file = new FileInputStream(filename);
			canStart = true;
		}
		catch(FileNotFoundException f) {
			toClient.write(0x18);
			canStart = false;
		}
		buffer = new byte[1024];
	}

	TransferFile(Socket sck, String filename) throws IOException {
		super();
		client = sck;
		fromClient = client.getInputStream();
		toClient = client.getOutputStream();
		try {
			file = new FileInputStream(filename);
			canStart = true;
		}
		catch(FileNotFoundException f) {
			toClient.write(0x18);
			canStart = false;
		}
		buffer = new byte[1024];
	}

	public void run() {
		System.out.println("Thread "+this.getName()+" has been started");
		if(canStart) {
			int bytes=0;
			try {
				while((bytes=file.read(buffer)) != -1) {
					System.out.println(bytes);
					toClient.write(buffer,0,bytes);
					//Thread.sleep(500);
				}
				toClient.write(0x4); //EOT byte
				file.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("Thread "+this.getName()+" finished run()");
		}
	}

	public void finalize() {
		System.out.println("Destroy thread "+this.getName());
	}
}
