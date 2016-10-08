package tdm10Yo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
public class Bus2
{
	public static void main(String[] args) throws IOException, ParseException
	{
		String url1;
		String url2;
		Date[] date = null;
		int countDate = 0;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		url1 = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/citea4.html";
		url2 = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/bus13.html";
		date = ConcatHtml(url1);
// TRI PAR DATE (Arrays.sort ne marche pas)
//		for (j=0;j<i;j++){
//			//si date1 est supérieur à date2
//			if (date[j+1]!=null){
//				if (date[j].compareTo(date[j+1]) == 1 ){
//					dateTemp = date[j];
//					date[j] = date[j+1];
//					date[j+1] = dateTemp;
//				}
//			}
//			System.out.println(stringDate = df.format(date[j]));	
//		}
		for (int j=0;j<date.length;j++){
			if (date[j]!=null){
				countDate++;
			}
		}	
		date = ConcatHtml(url2);
		for (int j=0;j<countDate;j++){	
			System.out.println(df.format(date[j]));	
		}
	}
	
	public static Date[] ConcatHtml(String url) throws IOException, ParseException{
		byte[] buffer = new byte[2048];
		int sizeBuffer; 
		String reponse = null;
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date[] date = new Date[10];
		Date dateTemp;
		String stringDate;
		int i = 0;
		int j = 0;

		InputStream is = new FileInputStream(url);	

		sizeBuffer = is.read(buffer);
		do
		{
			reponse += new String(buffer, 0 , sizeBuffer);
			sizeBuffer = is.read(buffer);			
		}while (sizeBuffer != -1);

		reponse = reponse.substring(reponse.indexOf("monitoring"));
		reponse = reponse.substring(reponse.indexOf("<ul>"),reponse.indexOf("</ul>"));
		reponse = reponse.substring(0,reponse.indexOf("*")-20);
		//ajout if à l'approche
		do
		{
			reponse = reponse.substring(reponse.indexOf(":")-2);
			date[i] = df.parse(reponse.substring(reponse.indexOf(":")-2,reponse.indexOf(":")+3));
			reponse = reponse.substring(reponse.indexOf(":")+3);
			i++;
		}while (reponse.indexOf(":") != -1);
		return date;	
	}
}