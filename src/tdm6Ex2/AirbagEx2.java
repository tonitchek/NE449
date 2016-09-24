package tdm6Ex2;

import java.io.IOException;

import tdm.tdm06.airbag.Airbag;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

public class AirbagEx2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Airbag ar = new Airbag();

		//FileOutputStream f = new FileOutputStream("sd.txt");
		byte[] buf = new byte[2048];
		int fileSize = 0;
		int off = 0;
		long timeStart = System.currentTimeMillis();
		int nbRead = ar.readDataOnSDCard(buf, 0, 2048);
		do {
			fileSize += nbRead;
			off += nbRead;
			if(off >= 128) {
				send2Broadcom(ar,buf,128);
				//update indexes
				if(off > 127) {
					updateBuffer(buf,128,off-128);
					off -= 128;
				}
				else {
					//lastInd = 127, so one full packet has been sent, restart buffer to 0
					off = 0;
				}
			}
			//f.write(buf,0,nbRead);
			nbRead = ar.readDataOnSDCard(buf,off,2048-off);
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
			if(nbSent == size) {
				done = true;
			}
		}
	}
	
	public static void updateBuffer(byte[] buffer, int offset, int len) {
		for(int i=0;i<len;i++) {
			buffer[i] = buffer[offset+i];
		}
	}
}
