package tdm6Ex1;

//in case of writing SD contents to file:
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

import java.io.IOException;

import tdm.tdm06.airbag.Airbag;

public class AirbagEx1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//create airbag object in order to read from SD and write to Broadcom
		Airbag ar = new Airbag();

		//in case of writing SD content to filesystem, create file output stream object
		//FileOutputStream f = new FileOutputStream("sd.txt");
		
		//create 2kB buffer
		byte[] buf = new byte[2048];
		//counter for SD file size
		int fileSize = 0;
		//get current time for flow rate estimation
		long timeStart = System.currentTimeMillis();
		
		//read from SD card
		int nbRead = ar.readDataOnSDCard(buf, 0, 2048);
		do {
			//increment file size counter
			fileSize += nbRead;
			//send buffer from 0 to nbRead to Broadcom
			send2Broadcom(ar,buf,nbRead);
			//evantually write buufer from 0 to nbRead to filesystem
			//f.write(buf,0,nbRead);
			//read from SD card
			nbRead = ar.readDataOnSDCard(buf,0,2048);
		}while(nbRead != -1); //while the end of SD file in not reached
		//get current time after the end of reading from SD and writing to Broadcom 
		long timeEnd = System.currentTimeMillis();
		//output the total elapsed time
		System.out.println("Total time (ms): "+ (timeEnd - timeStart));
		//output the SD file size 
		System.out.println("File size: " + fileSize);
		//calculate and output the flow rate
		System.out.println("Flow: " + (double)fileSize*1000/(timeEnd - timeStart) + " B/s");
		//eventually close file of filesystem
		//f.close();
	}

	//method to send buffer to Broadcom
	public static void send2Broadcom(Airbag ar, byte[] buffer, int size) throws InterruptedException {
		//number of bytes sent to Broadcom. Returned by airbag method 
		int nbSent = 0;
		//flag to quit loop
		boolean done = false;
		while(!done) {
			//send buffer to SPI
			  //increment nbSent at each send
			  //write buffer from nbSent to length of buffer minus nbSent (bytes already sent)
			nbSent += ar.sendSpiData(buffer, nbSent, size - nbSent);
			//spi transfer takes 10ms
			Thread.sleep(10);
			//if number of bytes sent is equal to the length of the buffer to sent, i.e. number of bytes to send
			if(nbSent == size) {
				//quit the loop
				done = true;
			}
		}
	}
}
