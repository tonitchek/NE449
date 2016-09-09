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
			Thread.sleep(5);
			if(pos == 1) {
				cf.setMain();
				Thread.sleep(1000);
				client.send("OK");
			}
			
			for(int i=0; i<30; i++) {
				while(!server.waitForHand());
				cf.setMain();
				Thread.sleep(1000);
				client.send("OK");
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
			portServer = 2000;
			port = 2001;
			break;
		case 2:
			cf = new ColorFrame(155,50);
			portServer = 2001;
			port = 2002;
			break;
		case 3:
			cf = new ColorFrame(155,155);
			portServer = 2002;
			port = 2003;
			break;
		case 4:
			cf = new ColorFrame(50,155);
			portServer = 2003;
			port = 2000;
			break;
		default:
			System.out.println("Give a position between 1 and 4. Exit");
			ret = false;
			break;
		}
		return ret;
	}
}
