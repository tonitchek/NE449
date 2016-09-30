package tdm6ex1Yo;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;

public class jFrame {
	JFrame frame;
	JProgressBar bar;
	
	jFrame() {
		frame = new JFrame("Transfer");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File transfer progress");
		frame.setVisible(true);
		
		bar  = new JProgressBar();
		bar.setStringPainted(true);
		bar.setSize(500, 50);
		bar.setBorder(new BevelBorder(BevelBorder.RAISED));
		bar.setForeground(Color.getHSBColor(50, 50, 50));
		frame.add(bar);
	}
	
	public void updateBar(int newValue) {
	    bar.setValue(newValue);
	}
	
	public void close() {
		frame.dispose();
	}
}
