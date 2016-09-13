package tdm4Ex1;

import java.io.IOException;

public class PlayClient {

	public static void main(String[] args) throws IOException, NumberFormatException {

		String answer;
		int index;
		int firstOp;
		int secondOp;
		int result;

		//get args
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		//connect to server
		ClientUdp jeu = new ClientUdp(host,port);

		for(int i=0;i<10;i++) {
			answer = jeu.send("JOUER");

			index = Integer.parseInt(answer.substring(1, answer.indexOf(':')));
			System.out.println(index);
			firstOp = Integer.parseInt(answer.substring(answer.indexOf(':')+1, answer.indexOf('+')));
			System.out.println(firstOp);
			secondOp = Integer.parseInt(answer.substring(answer.indexOf('+')+1, answer.indexOf('=')));
			System.out.println(secondOp);
			result = firstOp + secondOp;
			System.out.println(result);

			answer = "R"+index+":"+result;
			answer = jeu.send(answer);

			System.out.println(answer);
		}//END FOR

		//TODO send SCORE
		answer = jeu.send("SCORE");
		System.out.println(answer);
		jeu.close();
	}

}
