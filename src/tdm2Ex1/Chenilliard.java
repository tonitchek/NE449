package tdm2Ex1;

import java.io.IOException;

public class Chenilliard {

	static ColorFrame cf;
	static ClientUdp client;
	static ServerUdp server;
	static int pos;
	static int portServer;
	static int port;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		if(args.length != 1) {
			System.out.println("Give args: <pos>");
			System.exit(1);
		}
		pos = Integer.parseInt(args[0]);
		System.out.println(pos);
		if(init(Integer.parseInt(args[0])))
		{
			server = new ServerUdp(portServer);
			client = new ClientUdp("127.0.0.1",port);
			if(pos == 1) {
				int retry=0;
				cf.setMain();
				Thread.sleep(1000);
				do {
					++retry;
				}while(retry<20 && (client.send("OK")==false));
				cf.setSlave();
			}
			
			for(int i=0; i<10; i++) {
				int retry=0;
				while(!server.waitForHand());
				cf.setMain();
				Thread.sleep(1000);
				do {
					++retry;
				}while(retry<20 && (client.send("OK")==false));
				cf.setSlave();
			}
			client.close();
			server.close();
			cf.close();
		}
	}
	

	public static boolean init(int pos) {
		boolean ret = true;
		switch(pos) {
		case 1:
			cf = new ColorFrame(50,50);
			portServer = 6000;
			port = 6001;
			break;
		case 2:
			cf = new ColorFrame(155,50);
			portServer = 6001;
			port = 6002;
			break;
		case 3:
			cf = new ColorFrame(155,155);
			portServer = 6002;
			port = 6003;
			break;
		case 4:
			cf = new ColorFrame(50,155);
			portServer = 6003;
			port = 6000;
			break;
		default:
			System.out.println("Give a position between 1 and 4. Exit");
			ret = false;
			break;
		}
		return ret;
	}
}
