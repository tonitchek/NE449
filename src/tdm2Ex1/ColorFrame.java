package tdm2Ex1;

import java.awt.Color;
import javax.swing.JFrame;

public class ColorFrame {

	private JFrame frame;
	
	ColorFrame(int x, int y)
	{
		frame = new JFrame("Chenillard");
		frame.setSize(100,100);
		frame.setLocation(x, y);
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
	}
	
	public void setMain() {
		frame.getContentPane().setBackground(Color.RED);
	}

	public void setSlave() {
		frame.getContentPane().setBackground(Color.GREEN);
	}
	
	public void close() {
		frame.dispose();		
	}
}
