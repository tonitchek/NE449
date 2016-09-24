package tdm6Ex1;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;

import tdm.tdm06.airbag.Airbag;

public class AirbagEx1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Airbag ar = new Airbag();

		//FileOutputStream f = new FileOutputStream("sd.txt");
		byte[] buf = new byte[2048];
		int fileSize = 0;
		long timeStart = System.currentTimeMillis();
		int nbRead = ar.readDataOnSDCard(buf, 0, 2048);
		do {
			fileSize += nbRead;
			send2Broadcom(ar,buf,nbRead);
			//f.write(buf,0,nbRead);
			nbRead = ar.readDataOnSDCard(buf,0,2048);
		}while(nbRead != -1);
		long timeEnd = System.currentTimeMillis();
		System.out.println("Total time (ms): "+ (timeEnd - timeStart));
		System.out.println("File size: " + fileSize);
		System.out.println("Flow: " + (double)fileSize*1000/(timeEnd - timeStart) + " B/s");
		//f.close();
	}

	public static void send2Broadcom(Airbag ar, byte[] buffer, int size) throws InterruptedException {
		int nbSent = 0;
		boolean done = false;
		while(!done) {
			nbSent += ar.sendSpiData(buffer, nbSent, size - nbSent);
			Thread.sleep(10);
			//System.out.println("Sent " + nbSent + " bytes to Broadcom");
			if(nbSent == size) {
				done = true;
			}
		}
	}

}
