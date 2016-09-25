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
	private int lengthFile;
	private int transferProgress;
	private int bytesCount;

	//constructor with thread name
	TransferFile(String name, Socket sck, String filename) throws IOException {
		//call Thread class constructor we inherits from
		super(name);
		//get socket connection
		client = sck;
		//get In/Out stream
		fromClient = client.getInputStream();
		toClient = client.getOutputStream();
		//try to open requested file
		try {
			file = new FileInputStream(filename);
			lengthFile = filename.length()*1024;
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
	}

	//Constructor without Thread name
	TransferFile(Socket sck, String filename) throws IOException {
		//call Thread class constructor we inherits from
		super();
		//get socket connection
		client = sck;
		//get In/Out stream
		fromClient = client.getInputStream();
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
	}

	public void run() {
		System.out.println("Thread "+this.getName()+" has been started");
		//if thread can start
		if(canStart) {
			int bytes=0;
			//try to read buffer from file
			try {
				//while end of file is not reached
				while((bytes=file.read(buffer)) != -1) {
					System.out.println(bytes);
					//send buffer on socket to client
					toClient.write(buffer,0,bytes);
					//YO progress
					bytesCount = bytesCount + bytes;
					System.out.println("count: "+bytes);
					transferProgress = bytesCount/lengthFile*100;
					System.out.println("Progress: "+transferProgress);
					Thread.sleep(500);
				}
				//end of file has been reached, close file
				file.close();
				//close socket
				client.close();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread "+this.getName()+" finished run()");
		}
	}

	//indicates garbage collector work
	public void finalize() {
		System.out.println("Destroy thread "+this.getName());
	}
}
