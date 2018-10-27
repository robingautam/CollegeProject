package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static String user;
	 

	/*public static void main(String[] args) {
		gameplay game = new gameplay();
		
       JFrame frame = new JFrame();
       frame.setBackground(Color.GRAY);
       frame.setBounds(10, 10, 1205, 700);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		   frame.setVisible(true);
	
       frame.add(game);

	}*/
	public Main(String user) {
		this.user = user;
		gameplay game = new gameplay();
		
	       JFrame frame = new JFrame();
	       frame.setBackground(Color.GRAY);
	       frame.setBounds(10, 10, 1205, 700);
	       frame.setResizable(false);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
			   frame.setVisible(true);
			   
				
	       frame.add(game);
		
	}

}
