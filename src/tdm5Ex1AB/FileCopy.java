package tdm5Ex1AB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream file = null;
		FileOutputStream newFile = null;
		byte[] buffer= new byte[1024];
		if(args.length != 2){
			System.out.println("Usage: <filename> <new filename>");
			System.exit(1);;
		}
		String fileName = args[0];
		String newFileName = args[1];

		try {
			file = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to open file");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			newFile = new FileOutputStream(newFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to open file");
			e.printStackTrace();
			System.exit(1);
		}
		
		while(file.read(buffer) != -1) {
			newFile.write(buffer);
		}
		
		file.close();
		newFile.close();
	}

}
