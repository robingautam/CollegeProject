package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import brickbreaker.Brick;
import jaco.mp3.player.MP3Player;


public class gameplay extends JPanel implements KeyListener, ActionListener {
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int delay = 100;
	private Timer time;
	private int snakelength = 3;
	private int move = 0;
	
	private ImageIcon snakeupmouth, snakedownmouth, snakeleftmouth, snakerightmouth;
	private ImageIcon titleimage;
	private ImageIcon snakeimage;
	private ImageIcon enemyimage;
	private int enemyXpos[] = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,425,450, 475, 500, 525, 550,575,600,625,650,675,700,
			725, 750, 775, 800, 825, 850, 875};
	private int enemyYpos[] = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550,575,600,625};
	
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int score = 0;
	public int maxScore;
	private void gameOverSound() {
		 new MP3Player(new File("snake/gameover.mp3")).play();
	}
	private void eatSound() {
		 new MP3Player(new File("snake/eatsound.mp3")).play();
	}
    gameplay(){
    	addKeyListener(this);
        setFocusable(true);
        //setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
        new MP3Player(new File("snake/music.mp3")).play();
    	
    }
    @Override
	public void paint(Graphics g) {
    	if (move == 0) {
    		snakeXlength[2] = 50;
    		snakeXlength[1] = 75;
    		snakeXlength[0] = 100;
    		
    		snakeYlength[2] = 100;
    		snakeYlength[1] = 100;
    		snakeYlength[0] = 100;
    	}
    	
    	g.setColor(Color.WHITE);
    	g.drawRect(24, 10, 1151, 55);
    	
    	//titleimage = new ImageIcon("snaketitle.jpg");
    	//titleimage.paintIcon(this, g, 25, 11);
    	Toolkit tkit = Toolkit.getDefaultToolkit( );
	    Image img = tkit.getImage("snake/snaketitle.jpg");
	    g.drawImage(img, 25, 12,1152, 53,this);
	    Image img1 = tkit.getImage("snake/bg.gif");
	    g.drawImage(img1, 0, 0,1205, 700,this);
    	
    	//g.setColor(Color.WHITE);
    	//g.drawRect(24, 74, 1151, 577);
    	
      //draw background for gameplay
    	//g.setColor(Color.BLACK);
    	//g.fillRect(25, 75, 1150, 575);
    	
    	//draw score
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("arial", Font.PLAIN, 14));
    	g.drawString("Score: "+score, 1080, 40);
    	
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("arial", Font.PLAIN, 14));
    	g.drawString("Max Score: "+maxScore(), 180, 40);
    	
    	
    	snakerightmouth = new ImageIcon("snake/rightmouth.png");
    	snakerightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
    	
    	for (int a=0; a<=snakelength; a++) {
    		if (a==0 && right) {
    			snakerightmouth = new ImageIcon("snake/rightmouth.png");
    	    	snakerightmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
    		}
    		if (a==0 && left) {
    			snakeleftmouth = new ImageIcon("snake/leftmouth.png");
    	    	snakeleftmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
    		}
    		if (a==0 && up) {
    			snakeupmouth = new ImageIcon("snake/upmouth.png");
    	    	snakeupmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
    		}
    		if (a==0 && down) {
    			snakedownmouth = new ImageIcon("snake/downmouth.png");
    	    	snakedownmouth.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
    		}
    		if (a!=0) {
        			snakeimage = new ImageIcon("snake/snakeimage.png");
        	    	snakeimage.paintIcon(this, g, snakeXlength[a], snakeYlength[a]);
        		
    		}
    	}
    	enemyimage = new ImageIcon("snake/enemy.png");
    	if (enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]) {
    		eatSound();
    		snakelength++;
    		score++;
    		xpos = random.nextInt(34);
    		ypos = random.nextInt(23);
    		
    	}
    	enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);
    	for (int i=1;i<snakelength;i++) {
    		if (snakeXlength[i]==snakeXlength[0] && snakeYlength[i]==snakeYlength[0]) {
    			updateScore();
    			 gameOverSound();
    			left = false;
    			right = false;
    			up = false;
    			down = false;
    		    
    			
    			g.setColor(Color.GREEN);
    			g.setFont(new Font("arial", Font.BOLD, 50));
    			g.drawString("Game Over", 430, 300);
    			
    			g.setFont(new Font("arial", Font.BOLD, 20));
    			g.drawString("Press Enter to Restart Game", 450, 340);
    		}
    	}
    	
    	g.dispose();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if (right) {
			for (int r=snakelength-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for (int r = snakelength; r>=0; r--) {
				if (r==0) {
					
				snakeXlength[r] = snakeXlength[r] + 25;
				
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				
				if (snakeXlength[r] > 1150) {
					snakeXlength[r] = 25;
				}
			}
			repaint();
			
		}
        if (left) {
        	for (int r=snakelength-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			}
			for (int r = snakelength; r>=0; r--) {
				if (r==0) {
				snakeXlength[r] = snakeXlength[r] - 25;
				
				}
				else {
					snakeXlength[r] = snakeXlength[r-1];
				}
				
				if (snakeXlength[r] < 25) {
					snakeXlength[r] = 1150;
				}
			}
			repaint();
			
		}
        if (down) 
        {
        	for (int r=snakelength-1; r>=0; r--) 
        	{
				snakeXlength[r+1] = snakeXlength[r];
			}
			for (int r = snakelength; r>=0; r--) 
			{
				if (r==0) 
				{
				snakeYlength[r] = snakeYlength[r] + 25;
				
				}
				else 
				{
					snakeYlength[r] = snakeYlength[r-1];
				}
				
				if (snakeYlength[r] > 625) 
				{
					snakeYlength[r] = 75;
				}
			}
			repaint();
	
        }
        if (up) 
        {
        	for (int r=snakelength-1; r>=0; r--) 
        	{
				snakeXlength[r+1] = snakeXlength[r];
			}
			for (int r = snakelength; r>=0; r--) 
			{
				if (r==0) 
				{
				snakeYlength[r] = snakeYlength[r] - 25;
				
				}
				else 
				{
					snakeYlength[r] = snakeYlength[r-1];
				}
				
				if (snakeYlength[r] < 75) 
				{
					snakeYlength[r] = 625;
				}
			}
			repaint();
		
	
        	
         }
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			move = 0;
			score = 0;
			snakelength = 3;
			repaint();
		}
	
	  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		  
		  move++;
		  
		  right = true;
		  if (!left) {
			  right = true;
		  }
		  else {
			  right = false;
			  left = true;
		  }
		  up = false;
		  down = false;
	  }
	  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		  move++;
		  left = true;
		  if (!right) {
			  left = true;
		  }
		  else {
			  left = false;
			  right = true;
		  }
		  up = false;
		  down = false;
	  }
	  if (e.getKeyCode() == KeyEvent.VK_UP) {
		  move++;
		  up = true;
		  if (!down) {
			  up = true;
		  }
		  else {
			  up = false;
			  down = true;
		  }
		  left = false;
		  right = false;
	  }
	  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		  
		  move++;
		  
		  down = true;
		  if (!up) {
			  down = true;
		  }
		  else {
			  up = true;
			  down = false;
		  }
		  left = false;
		  right = false;
	  }
		
	}
	@Override
	public void keyReleased(KeyEvent f) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent g) {
		// TODO Auto-generated method stub
		
	}
	
	public static Connection getConnnection() throws SQLException {
		Connection connection = null;
		String dburl = "jdbc:sqlite:C:\\databases\\project.db";
		connection = DriverManager.getConnection(dburl);
		System.out.println("Connection Created ");
		return connection;
	}
	
	public void updateScore() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {

			con = getConnnection();
			 System.out.println(Main.user);
			pstmt = con.prepareStatement("SELECT snakescore FROM user WHERE email=?");
			pstmt.setString(1, Main.user);
			rs = pstmt.executeQuery();
			//Statement st = con.createStatement();
			
		    // rs = st.executeQuery("SELECT brickscore FROM user WHERE email='"+Brick.user+"'");
			System.out.println("********************");
			//pstmt.close();
			if (rs.next()) {
				maxScore = rs.getInt("snakescore");
				System.out.println(maxScore);
				if (maxScore <= score) {
					pstmt1 = con.prepareStatement("UPDATE user SET snakescore = '" + score + "' WHERE email = ?");
					pstmt1.setString(1, Main.user);
					int i = pstmt1.executeUpdate();
					if (i != 0) {
						System.out.println("score updated");
					} else {
						System.out.println("score not updated");
					}
				} else {
					System.out.println("score is less than current score");
				}
				pstmt1.close();

			} else {
				System.out.println("not found");
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}

	}
	
	
	public int maxScore() {
		int maximumScore = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnnection();
			 //System.out.println(Brick.user);
			//System.out.println("hiii");
			//System.out.println("hiiii");
			pstmt = con.prepareStatement("SELECT snakescore FROM user WHERE email=?");
			//System.out.println("hii2");
			pstmt.setString(1, Main.user);
			//System.out.println("hii3");
			rs = pstmt.executeQuery();
			//System.out.println("hii6");
			 if (rs.next()) {
				maxScore = rs.getInt("snakescore");
				maximumScore = maxScore;
			 }
			 else {
				 System.out.println("not found");
			 }
			 con.close();
			 
			 
			
		}
		catch(Exception e) {
		  System.out.println(e);
		}
		return maximumScore;
		
		
	}
	
}
