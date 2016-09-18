package tdm5ex1Yo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
	
	static String fileOrigin;
	static String fileNew;
	private static FileInputStream inputStream;
	private static FileOutputStream outputStream;

	public static void main(String[] args) throws IOException
	{
		// FULL path of the file to copy
		fileOrigin = args[0];
		fileNew = args[1];
		
		// create a new inputStream and outputStream
		inputStream = new FileInputStream(fileOrigin);
		outputStream = new FileOutputStream(fileNew);
	   
		// Write each data from the inputStream to the outputStream. (write int)
		for(int a = inputStream.read(); a!=-1; a = inputStream.read())
	         outputStream.write(a);
		
		inputStream.close();
		outputStream.close();
	}
}
