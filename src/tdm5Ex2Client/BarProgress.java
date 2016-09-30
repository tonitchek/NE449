package tdm5Ex2Client;

//import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

public class BarProgress {
	JFrame frame;
	JProgressBar bar;
	
	BarProgress(int pos) {
		frame = new JFrame("Transfer");
		frame.setLocation(0, 30*pos+5);
		frame.setSize(500,30);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File transfer progress");
		frame.setVisible(true);
		
		bar  = new JProgressBar();
		bar.setStringPainted(true);
		//bar.setSize(50, 50);
		bar.setBorder(new BevelBorder(BevelBorder.RAISED));
		frame.add(bar);
	}
	
	public void updateBar(int newValue) {
	    bar.setValue(newValue);
	}
	
	public void close() {
		frame.dispose();
	}
}
