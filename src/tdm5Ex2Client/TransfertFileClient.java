package tdm5Ex2Client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransfertFileClient {


	static String host;
	static int port;
	static String filePath;
	
	public static void main(String[] args) throws IOException {
		//get args
		if(args.length != 3) {
			System.out.println("Usage: <hostname> <port_server> <file_path>");
			System.exit(1);
		}

		host = args[0];
		port = Integer.parseInt(args[1]);
		filePath = args[2];

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
				//while server sends data (receive method returns -1 if no more data are available)
				while(bytes != -1) {
					System.out.println(bytes);
					//write buffer to file
					file.write(buf,0,bytes);
					//get socket data
					bytes = client.receive(buf);
				}
				//close file
				file.close();
			}
			catch(FileNotFoundException f) {
				System.out.println("Failed to open file");
			}
		}

		//close socket
		client.close();
	}
}
