package tdm5ex2Yo;

import java.io.IOException;

import tdm4ex3TCP.ClientTCP;

public class PlayServerClientCopy {
	
	static String host;
	static int port;
	static String queryFile;
	static String serverAnswer;
	
	public static void main(String[] args) throws IOException {
		host = args[0];
		port = Integer.parseInt(args[1]);
		queryFile = args[2];
		
		ClientTCP client = new ClientTCP(host,port);
		client.open();
		client.send(queryFile);
		client.receive();
				
		/*switch (serverAnswer) {
        case "CAN":
                 break;
        case "ACK":
                 break;
        default:
                 break;
    }*/

		
	}

}
