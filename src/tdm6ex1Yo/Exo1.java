package tdm6ex1Yo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import tdm.tdm06.airbag.Airbag;

public class Exo1 {


	public static void main(String[] args) throws IOException, InterruptedException {
		
		Airbag ar = new Airbag();
		byte[] buf = new byte[1024];
		int nbRead = 0;
		int nbSent = 0;
		long timeStart;
		long timeCurrent;
		int sizeFile = 0;
		
		//Initialize file output to  check the layout datagram
		FileOutputStream out = new FileOutputStream("stringFromSD.txt");
		FileOutputStream out2 = new FileOutputStream("stringToBroadcom.txt");	
		//Init counter
		timeStart = System.currentTimeMillis();	
		//Make a first read outside the loop
		nbRead = ar.readDataOnSDCard(buf, 0, 1024);
		do
		{	
			//Write nbread to file
			out.write(buf);
			//set nbSent to 0 before enter the nbSent loop
			nbSent=0;
			while(nbSent != nbRead)
			{   
				//split the buffer and send it with a buffer offset
				nbSent += ar.sendSpiData(buf, nbSent, nbRead-nbSent);
				//spi link is 10ms speed. Otherwise, it send 0. Sleep 10ms permit to 
				// drop down cpu form 30%-50% to nothing
				Thread.sleep(10);
				//write nbsend to file
				out2.write(buf);
			}

			//Read next tram
			nbRead = ar.readDataOnSDCard(buf, 0, 1024);
			//Count file size
			sizeFile += nbRead;
			
			//Those lines are used to check the good process in the loop
			//System.out.println("nbRead:"+nbRead+" nbSent:"+nbSent);
			//timeCurrent = System.currentTimeMillis()-timeStart;
			//System.out.println("Time check: "+timeCurrent*0.001+"s   File size check:" + sizeFile*0.001+"Ko");
		} while (nbRead!=-1);
		
		// Stop counter
		timeCurrent = System.currentTimeMillis()-timeStart;
		//Print result in second and kilo octets
		System.out.println("Time: "+timeCurrent*0.001+"s");
		System.out.println("File size: "+sizeFile*0.001+"Ko");
		System.out.println("Speed: "+(sizeFile/(timeCurrent*0.001))+" o/s  then: "+(sizeFile/(timeCurrent*0.001)*0.001)+" Ko/s");
		
		
		out.close();
		out2.close();
	}

}
