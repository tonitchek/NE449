package tdm6ex1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import tdm.tdm06.airbag.Airbag;
public class Exo1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Airbag ar = new Airbag();
		byte[] buf = new byte[1024];
		int nbRead = 0;
		int nbSent = 0;
		int nbSentSize = 1;
		
		FileOutputStream out = new FileOutputStream("stringFromSD.txt");
		FileOutputStream out2 = new FileOutputStream("stringToBroadcom.txt");
			
		nbRead = ar.readDataOnSDCard(buf, 0, 1024);
		do 
		{		
			
			nbSent=0;
			nbSentSize=0;
			do
			{   
				System.out.println("read"+nbRead);
				//nbSentSize = ar.sendSpiData(buf, nbSent, 1024);
				//System.out.print(nbSentSize);
 				nbSent = ar.sendSpiData(buf, nbSent, nbRead);
 				//nbSentSize = nbSentSize + nbSent;
 				//nbSentSize = nbSentSize;
 				out2.write(buf);
 				
				System.out.println(nbSent);
				//nbSentSize = nbSentSize + nbSent;
			} while(nbSent==nbRead);
			out.write(buf,0,nbRead);
			nbRead = ar.readDataOnSDCard(buf, 0, 1024);
			
			//System.out.println("nbRead: " + nbRead + ", Text import in buffer: " + new String(buf) + "||||||| SEND:" + stockRead);
		} while (nbRead!=-1);
		out.close();
		out2.close();
		
		
			//System.out.println(stringFromSD);
			//System.out.println("    ");
			//System.out.println(stringToBroadcom);
	}

}
