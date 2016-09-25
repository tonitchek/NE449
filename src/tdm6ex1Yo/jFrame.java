package tdm6ex1Yo;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class jFrame {
	static JProgressBar bar  = new JProgressBar();
	//1. Create the frame.
	public static void open() throws InterruptedException{
		JFrame frame = new JFrame("Transfer");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File transfer progress");
		frame.setVisible(true);

		JProgressBar bar  = new JProgressBar();
		bar.setStringPainted(true);
		bar.setSize(50, 400);
		//bar.setValue(50);

		bar.setStringPainted(true);
		
		frame.add(bar);

		
	}
	
	public static void updateBar(int newValue) {
	    bar.setValue(newValue);
	  }

}
