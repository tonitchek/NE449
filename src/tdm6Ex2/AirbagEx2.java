package tdm6Ex2;

import java.io.IOException;

import tdm.tdm06.airbag.Airbag;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;

public class AirbagEx2 {

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
		//offset in the buffer buf
		int off = 0;
		//get current time for flow rate estimation
		long timeStart = System.currentTimeMillis();

		//read from SD card
		int nbRead = ar.readDataOnSDCard(buf, 0, 2048);
		do {
			//increment file size counter
			fileSize += nbRead;
			//increment offset in the buffer buf
			off += nbRead;
			//if offset is greater or equals to 128 (128bytes is the optimal buffer length to send to Broadcom)
			if(off >= 128) {
				//send buffer from 0 to 127 to Broadcom
				send2Broadcom(ar,buf,128);
				//if offset was greater than 127 meaning there is a rest of bytes didn't sent to Broadcom
				if(off > 127) {
					//retreive this amount of bytes and write it to the beginning of the buffer
					updateBuffer(buf,128,off-128);
					//decrement offset of 128, so next read will start to the beginning + rest of bytes didn't sent
					off -= 128;
				}
				else {
					//offset = 127, so one full packet has been sent, restart buffer to 0
					off = 0;
				}
			}
			//evantually write buufer from 0 to nbRead to filesystem
			//f.write(buf,0,nbRead);
			//read from SD card from offset to the total buffer size minus offset (to not overflow)
			nbRead = ar.readDataOnSDCard(buf,off,2048-off);
		}while(nbRead != -1); //while the end of SD file in not reached
		//the SD file ended but it might rest some bytes not sent. Indeed, if the end of SD
		//file was reached while offset was < 128, those bytes have not been sent
		//send it now
		send2Broadcom(ar,buf,off);
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

	//method allowing to write <len> bytes starting at <offset> of buffer to the beginning of buffer
	public static void updateBuffer(byte[] buffer, int offset, int len) {
		for(int i=0;i<len;i++) {
			buffer[i] = buffer[offset+i];
		}
	}
}
