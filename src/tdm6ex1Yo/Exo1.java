package tdm6ex1Yo;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

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
		JFrame frame = new JFrame("Transfer");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File transfer progress");
		frame.setVisible(true);

		JProgressBar bar  = new JProgressBar();
		bar.setStringPainted(true);
		bar.setSize(500, 300);
		bar.setMaximum(50000);
		bar.setBorder(new BevelBorder(BevelBorder.RAISED));
		bar.setForeground(Color.getHSBColor(50, 50, 50));
		
		JProgressBar bar2  = new JProgressBar();
		bar2.setStringPainted(true);
		bar2.setSize(500, 50);
		bar2.setMaximum(30);
		bar2.setBorder(null);
		bar2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		//bar2.setVisible(true);
		//bar.setVisible(true);
		frame.add(bar2);
		frame.add(bar);
		
		do
		{	
			
			bar.setValue(sizeFile);

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
				bar2.setValue(nbSent);
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
