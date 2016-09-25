package tdm6ex2Yo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import tdm.tdm06.airbag.Airbag;

public class Exo2 {


	public static void main(String[] args) throws IOException, InterruptedException {
		
		Airbag ar = new Airbag();
		byte[] buf = new byte[2048];
		int nbRead = 0;
		int nbSent = 0;
		long timeStart;
		long timeCurrent;
		int sizeFile = 0;
		
		//Init counter
		timeStart = System.currentTimeMillis();	
		//Make a first read outside the loop
		nbRead = ar.readDataOnSDCard(buf, 0, 2048);
		do
		{	
			// add a condition to write in the buffer while at least 128 octet
			if(nbRead >= 128)
			{	
				//set nbSent to 0 before enter the nbSent loop
				nbSent=0;
				while(nbSent != nbRead)
				{   				
					//split the buffer and send it with a buffer offset
					nbSent += ar.sendSpiData(buf, nbSent, nbRead-nbSent);
					//spi link is 10ms speed. Otherwise, it send 0. Sleep 10ms permit to 
					// drop down cpu form 30%-50% to nothing
					Thread.sleep(10);		
				}
				//Add the size nbRead which has been sent to the spi, and init nbRead to 0
				sizeFile += nbRead;
				nbRead = 0;
			}
			else{
				//if less than 128 octets, add from nbRead offset
				nbRead += ar.readDataOnSDCard(buf, nbRead, 2048-nbRead);
			}
		} while (nbRead!=-1);
		
		// Stop counter
		timeCurrent = System.currentTimeMillis()-timeStart;
		//Print result in second and kilo octets
		System.out.println("Time: "+timeCurrent*0.001+"s");
		System.out.println("File size: "+sizeFile*0.001+"Ko");
		System.out.println("Speed: "+(sizeFile/(timeCurrent*0.001))+" o/s  then: "+(sizeFile/(timeCurrent*0.001)*0.001)+" Ko/s");

	}

}
