package tdm5Ex2Server;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class jFrameServer {
	//1. Create the frame.
	public void open() throws InterruptedException{
		JFrame frame = new JFrame("Transfer");
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("File transfer progress");
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		for(int i = 0; i < 4;i++){
			// declare a panel
			JPanel p = new JPanel(true);
			p.setSize(400,300);
			p.setVisible(true);

			JProgressBar bar  = new JProgressBar();
			bar	.setStringPainted(true);
			bar.setSize(0, 300);
			//bar.setMaximum(1);
			
//			bar.setStringPainted(true);
//			if (/*...half way done...*/)
//			    progressBar.setString("Half way there!");

			
		    bar.setMaximum(500);
		    bar.setMinimum(0);
		    bar.setStringPainted(true);
			// declare a test label
			JLabel lblName1 = new JLabel();
			lblName1.setText("LED"+i);
			lblName1.setVisible(true);	
			
			JLabel lblLed = new JLabel("•");
			lblLed.setForeground(Color.GREEN);


			// declare a new led
			//CLed led1 = new CLed();
			//led1.ledOn = true;

			// add led  and label to the panel
			//p.add(led1);
			p.add(lblName1);
			p.add(lblLed);
			p.add(bar);


			// add panel to the frame
			frame.add(p);
			
			for (int j = 0; j<400;j++){
				bar.setValue(j);
				System.out.println(j);
			
			}
		}


	}

}
