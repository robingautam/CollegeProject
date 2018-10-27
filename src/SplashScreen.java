
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SplashScreen {
	JFrame frame;
	JPanel panel;
	JLabel label, titlelabel;
	static JProgressBar bar;
	int counter;     
	Timer timer;
	private boolean isVisible ; 
	
	public SplashScreen() {
		frame = new JFrame("Gameing Zone");
		panel = new JPanel();
		label = new JLabel();
		bar = new JProgressBar();
		titlelabel = new JLabel("Developed By: Robin");
		panel.setBounds(0, 0, 600, 400);
		label.setBounds(0, 0, 600, 400);
		bar.setBounds(0, 375, 600, 15 );
		bar.setForeground(new Color(255,100,0));
		//bar.setBackground(new Color(128, 128, 128));
		bar.setBackground(Color.CYAN);
		bar.setStringPainted(true);
		bar.setVisible(false);
		titlelabel.setBounds(370, 20, 200, 50);
		titlelabel.setForeground(new Color(218,165,32));
		titlelabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
		
		frame.setSize(600, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		frame.setVisible(true);
		frame.add(panel);
		panel.add(label);
		panel.add(titlelabel);
		ImageIcon img = new ImageIcon("gamezone2.jpg");
		 Image im = img.getImage();
		 Image resize = im.getScaledInstance(600, 400, Image.SCALE_FAST);
		 ImageIcon newimage = new ImageIcon(resize);
		 label.setIcon(newimage);
		 label.add(titlelabel);
		 label.add(bar);

	}
	private void plusProgress(){
		timer = new Timer(60,e->{
		if(counter>=100){
			timer.stop();
			Login login = new Login();
			frame.setVisible(false);
		}
			if(counter<=100){
		counter++ ;
		bar.setValue(counter);
		}
		});
		timer.start();
	}

	
	public static void main(String[] args)  {
		SplashScreen screen = new SplashScreen();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bar.setVisible(true);
		screen.plusProgress();
	   
					
	}
}
