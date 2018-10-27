import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class GamePanel {
	JFrame frame;
	String user;
	String name;
	JPanel panel;
	JButton snakebtn, brickBreakerbtn, hangManbtn,  spaceInvaderbtn;
	
  public GamePanel(String user, String name) {
	  this.user = user;
	  this.name = name;
	  Icon icon1 =new ImageIcon("game1.png");
		Icon icon2 =new ImageIcon("game3.jpeg");
		//Icon icon3 =new ImageIcon("game4.jpeg");
		Icon icon4 =new ImageIcon("game4.jpg");
		Icon icon5 = new ImageIcon("game5.jpeg");
		
	    snakebtn = new JButton("");
		snakebtn.setBounds(50, 72, 225, 225);
		snakebtn.setIcon(icon1);
		snakebtn.setBackground(Color.WHITE);
		
		JLabel welcomelbl = new JLabel("WELCOME Click The Image To Play The Game ");
		welcomelbl.setForeground(Color.WHITE);
		welcomelbl.setFont(new Font("Century Gothic", Font.BOLD, 14));
		welcomelbl.setBounds(29, 11, 344, 34);
		
		brickBreakerbtn = new JButton("");
		brickBreakerbtn.setBounds(321, 349, 225, 225);
		brickBreakerbtn.setIcon(icon2);
		brickBreakerbtn.setBackground(Color.WHITE);
		
		JLabel headinglbl = new JLabel("");
		headinglbl.setForeground(Color.WHITE);
		headinglbl.setHorizontalAlignment(SwingConstants.CENTER);
		headinglbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		headinglbl.setBounds(10, 620, 564, 34);
		
		JLabel snakelbl = new JLabel("Snake Game");
		snakelbl.setVerticalAlignment(SwingConstants.BOTTOM);
		snakelbl.setForeground(Color.WHITE);
		snakelbl.setHorizontalAlignment(SwingConstants.CENTER);
		snakelbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		snakelbl.setBounds(71, 308, 170, 24);
		
		
		JLabel brickBreakerlbl = new JLabel("Brick Breaker Game");
		brickBreakerlbl.setForeground(Color.WHITE);
		brickBreakerlbl.setBounds(347, 585, 170, 24);
		brickBreakerlbl.setHorizontalAlignment(SwingConstants.CENTER);
		brickBreakerlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		
		
		brickBreakerbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				brickbreaker.Brick brickbreaker = new brickbreaker.Brick(user, name);
				frame.setVisible(false);
				
			}
		});
		
		
		 hangManbtn = new JButton("");
		hangManbtn.setBounds(50, 349, 225, 225);
		hangManbtn.setIcon(icon5);
		
		JLabel hangManlbl = new JLabel("HangMan Game");
		hangManlbl.setForeground(Color.WHITE);
		hangManlbl.setBounds(87, 585, 170, 24);
		hangManlbl.setHorizontalAlignment(SwingConstants.CENTER);
		hangManlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));
		
		
		hangManbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				hangman.Phantom_Hangman phangman = new hangman.Phantom_Hangman();
				frame.setVisible(false);
//				
			}
		});
		
		
	    spaceInvaderbtn = new JButton("");
		spaceInvaderbtn.setBounds(321, 72, 225, 225);
		spaceInvaderbtn.setIcon(icon4);
		
		
		spaceInvaderbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				spaceInvander.main spcinv = new spaceInvander.main();
				frame.setVisible(false);
			}
		});
		
		JLabel spaceInvaderlbl = new JLabel("Space Inveder Game");
		spaceInvaderlbl.setForeground(Color.WHITE);
		spaceInvaderlbl.setBounds(347, 308, 170, 24);
		spaceInvaderlbl.setHorizontalAlignment(SwingConstants.CENTER);
		spaceInvaderlbl.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 16));

		

		
	  
		
	  frame = new JFrame();
	  panel = new JPanel();
	    frame.setSize(600, 700);
		panel.setBounds(0,0,600,700);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(new Color(20, 20, 12));
		frame.setTitle("Game Zone DashBoard - Welcome");
		frame.setVisible(true);
		panel.setLayout(null);
		panel.setBackground(new Color(44,62,80));
		frame.add(panel);
		panel.add(snakebtn);
		panel.add(brickBreakerbtn);
		panel.add(welcomelbl);
		panel.add(headinglbl);
		panel.add(brickBreakerlbl);
		panel.add(snakelbl);
		panel.add(hangManlbl);
		panel.add(hangManbtn);
		panel.add(spaceInvaderbtn);
		panel.add(spaceInvaderlbl);
		
		
		snakebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.Main snake = new game.Main(user);
				frame.setVisible(false);
				
				
			}
		});
		
		
		
		
		
		
	  
	  
  }
  public static void main(String[] args) {
	  GamePanel gamepanel = new GamePanel("", "");
	  
  }
		  
}
