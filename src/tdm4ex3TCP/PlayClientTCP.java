package tdm4ex3TCP;

public class PlayClientTCP {

	static String host;
	static int port;
	static String test;
	static int op1;
	static int num;


	public static void main(String[] args) throws Exception
	{
		String strReceive;
		Question q = new Question();
		host = args[0];
		port = Integer.parseInt(args[1]);

		ClientTCP client = new ClientTCP(host,port);	
		client.open();
		
		/**do{
			strReceive = client.receive();
			q.fill(strReceive);
		}
		while(!q.valid());
		System.out.println(strReceive);**/

	}

	// TODO 
	// TODO analyser la trame
}
