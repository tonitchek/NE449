package tdm5Ex2Client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransfertFileClient {


	static String host;
	static int port;
	static int nbClient;
	static String filePath;
	
	public static void main(String[] args) throws IOException {
		//get args
		if(args.length != 4) {
			System.out.println("Usage: <num> <hostname> <port_server> <file_path>");
			System.exit(1);
		}

		nbClient = Integer.parseInt(args[0]);
		host = args[1];
		port = Integer.parseInt(args[2]);
		filePath = args[3];

		//create TCP client object
		ClientTCP client = new ClientTCP(host,port);
		//open TCP client connection
		client.open();

		//send file path request to server
		client.send(filePath);
		//allocate buffer for socket data
		byte[] buf = new byte[2048];
		//get socket data from server
		int bytes = client.receive(buf);
		//if first char is Cancel char, file does not exist on server
		if(buf[0] == 0x18) {
			System.out.println("File doesn't exist on server");
		}
		else {
			System.out.println("Start transfer");
			try {
				//get filename (without full path)
				StringBuffer filename = new StringBuffer();
				boolean done = false;
				int i = filePath.length()-1;
				while(!done) {
					if(filePath.charAt(i)=='/') {
						done = true;
					}
					else {
						filename.append(filePath.charAt(i));
					}
					i--;
				}
				filename.reverse();
				System.out.println(filename);
				//open file to write in
				FileOutputStream file = new FileOutputStream(filename.toString());
				//first data are the file size
				//create a temporary buffer to store file size
				byte[] tmp = new byte[64];
				//buffer index
				int j=0;
				//while the end of file size specifier is not reached
				while(buf[j]!=';' && j<bytes) {
					//store the content of buffer
					tmp[j] = buf[j];
					++j;
				}
				int fileSize = Integer.parseInt(new String(tmp,0,j));
				System.out.println("File size: "+fileSize+" Bytes");
				//create progress bar
				BarProgress bp = new BarProgress(nbClient);
				int currentSize=0;
				//if there are other bytes after the file size, that means rest of bytes
				//are file data sent by server
				//so write it in the file
				if(j<bytes) {
					//increment j because current j value points on ';' character
					file.write(buf,++j,bytes);
				}
				//right now there are only file data
				bytes = client.receive(buf);
				//while server sends data (receive method returns -1 if no more data are available)
				while(bytes != -1) {
					//update progress bar
					currentSize+=bytes;
					bp.updateBar((int)(currentSize/(double)fileSize*100));
					//write buffer to file
					file.write(buf,0,bytes);
					//get socket data
					bytes = client.receive(buf);
				}
				//close file
				file.close();
				//close progress bar
				bp.close();
			}
			catch(FileNotFoundException f) {
				System.out.println("Failed to open file");
			}
		}

		//close socket
		client.close();
	}
}
