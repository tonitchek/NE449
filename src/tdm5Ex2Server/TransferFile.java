package tdm5Ex2Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TransferFile extends Thread {

	private Socket client;
	OutputStream toClient;
	private FileInputStream file;
	private boolean canStart;
	private byte[] buffer;
	public boolean busy;

	//constructor with thread name
	TransferFile(String name, Socket sck, String filename) throws IOException {
		//call Thread class constructor we inherits from
		super(name);
		//get socket connection
		client = sck;
		//get Out stream
		toClient = client.getOutputStream();
		//try to open requested file
		try {
			file = new FileInputStream(filename);
			//opening succeeded, thread can start
			canStart = true;
		}
		catch(FileNotFoundException f) {
			//opening failed, send Cancel char to client
			toClient.write(0x18);
			//thread must quit
			canStart = false;
		}
		//allocate socket buffer
		buffer = new byte[1024];
		this.busy = true;
	}

	//Constructor without Thread name
	TransferFile(Socket sck, String filename) throws IOException {
		//call Thread class constructor we inherits from
		super();
		//get socket connection
		client = sck;
		//get Out stream
		toClient = client.getOutputStream();
		//try to open requested file
		try {
			file = new FileInputStream(filename);
			//opening succeeded, thread can start
			canStart = true;
		}
		catch(FileNotFoundException f) {
			//opening failed, send Cancel char to client 
			toClient.write(0x18);
			//thread must quit
			canStart = false;
		}
		//allocate socket buffer
		buffer = new byte[1024];
		this.busy = true;
	}

	public void run() {
		System.out.println("Thread "+this.getName()+" has been started");
		//if thread can start
		if(canStart) {
			int bytes=0;
			//try to read buffer from file
			try {
				//get file size (bytes available on the stream)
				int fileSize = file.available();
				//format the header message containing size to send to client
				String header = new String();
				header += fileSize+";";
				System.out.println(header);
				//send size to client
				toClient.write(header.getBytes());
				//while end of file is not reached
				while((bytes=file.read(buffer)) != -1) {
					//send buffer on socket to client
					toClient.write(buffer,0,bytes);
					Thread.sleep(10);
				}
				//end of file has been reached, close file
				file.close();
				//close socket
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.busy = false;
			System.out.println("Thread "+this.getName()+" finished run()");
		}
	}

	//indicates garbage collector work
	public void finalize() {
		System.out.println("Destroy thread "+this.getName());
	}
}
