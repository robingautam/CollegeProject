package brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Brick {
	/*public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();	
		//frame.setSize(700, 600);
		frame.setBounds(10, 10, 1010, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameplay1 gameplay = new gameplay1();
		frame.add(gameplay);
		frame.setForeground(Color.MAGENTA);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.validate();
		
		
		
		
	}*/
	public static String user;
	public static String name;
	
	public Brick(String user, String name) {
		this.user = user;
		this.name = name;
		JFrame frame = new JFrame();	
		//frame.setSize(700, 600);
		frame.setBounds(10, 10, 1010, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameplay1 gameplay = new gameplay1();
		frame.add(gameplay);
		frame.setForeground(Color.MAGENTA);
		//gameplay.updateScore();
		
			
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.validate();
		
	}

	

}
