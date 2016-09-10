package tdm2Ex3Client;

import java.awt.Color;
import javax.swing.JFrame;

public class ColorFrame {

	private JFrame frame;
	
	ColorFrame(int x, int y, int number)
	{
		frame = new JFrame("R"+number);
		frame.setSize(200,200);
		frame.setLocation(x, y);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
	}
	
	public void setRed() {
		frame.getContentPane().setBackground(Color.RED);
	}

	public void setGreen() {
		frame.getContentPane().setBackground(Color.GREEN);
	}
	
	public void close() {
		frame.dispose();		
	}
}

