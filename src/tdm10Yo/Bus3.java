package tdm10Yo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Bus3
{
	//initialize a list that will be modify by methods
	static List<String> list = new ArrayList<String>();

	public static void main(String[] args) throws IOException, ParseException
	{
		String url1 = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm10Yo/citea4.html";;
		String url2 = "C:/Users/B03073/Documents/ESISAR/Workspace/NE449/src/tdm10Yo/bus13.html";
		String stringList, stringDate, printBus;

		ConcatHtml(url1,"cite4");
		ConcatHtml(url2, "bus13");

		// Compare the list before/after order, it's possible to make several childs list in a parents list and sort them with a comparator<T> class (more complicated)
		System.out.println("list NON triée:  " + list);
		Collections.sort(list);	
		System.out.println("list triée:      " + list);

		//format the list to print in
		for(int i=0;i<list.size();i++){
			stringList = (String) list.get(i);
			stringDate = stringList.substring(0,stringList.indexOf(" "));
			printBus = stringList.substring(stringList.indexOf(" "));
			System.out.println("Le bus "+ printBus + " passera à "+ stringDate );
		}	
	}

	public static void ConcatHtml(String url, String busName) throws IOException, ParseException{

		byte[] buffer = new byte[2048];
		int sizeBuffer; 
		String reponse = null;
		InputStream is = new FileInputStream(url);	
		int i=0;

		//URL url = new URL("http://www.citea.info/mobile/horaires/index.asp?rub_code=23&typeSearch=stop&stopPoint=229%24POLE+BUS%242%2426362%24&lign_id=52&pa_id=10314&sens=1");
		//URLConnection connection = url.openConnection();
		//connection.connect();
		//InputStream is = connection.getInputStream();

		sizeBuffer = is.read(buffer);
		do
		{
			reponse += new String(buffer, 0 , sizeBuffer);
			sizeBuffer = is.read(buffer);			
		}while (sizeBuffer != -1);

		//Select only the part interesting
		reponse = reponse.substring(reponse.indexOf("monitoring"));
		reponse = reponse.substring(reponse.indexOf("<ul>"),reponse.indexOf("</ul>"));
		reponse = reponse.substring(0,reponse.indexOf("*")-20);

		//add to the static list with format <hours> <namebus>
		do
		{		
			list.add((reponse.substring(reponse.indexOf(":")-2,reponse.indexOf(":")+3))+" "+busName);
			reponse = reponse.substring(reponse.indexOf(":")+3);
			i++;
		}while (reponse.indexOf(":") != -1);	
	}
}


// list example use case
//
//List parentList = new ArrayList();
//
//List child1 = new ArrayList();
//child1.add("a1");
//child1.add("a2");
//child1.add("a3");
//
//List child2 = new ArrayList();
//child2.add("b1");
//child2.add("b2");
//
//List child3 = new ArrayList();
//child3.add("c1");
//
//parentList.add(child1);
//parentList.add(child2);
//parentList.add(child3);
//
//for (int i = 0; i < parentList.size(); i++) {
//	List child = (List) parentList.get(i);
//	for (int j = 0; j < child.size(); j++) {
//		System.out.println(child.get(j));
//	}
//}