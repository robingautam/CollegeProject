package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.io.File;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.login.LoginContext;
//import javax.print.attribute.standard.Media;
import javax.swing.JPanel;
import javax.swing.Timer;
import jaco.mp3.player.MP3Player;

public class gameplay1 extends JPanel implements ActionListener, KeyListener {
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 40;
	private Timer time;
	private int delay = 8;
	private int playerX = 610;
	private int ballposX = 420;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private GenerateBrick1 arr;
	private int highestScore = totalBricks * 5;
	private int currentScore;
	private int maxScore;
	private int brickMaxScore;
    Connection con = null;
	File img = new File("C:\\pic1.jpg");

	public static Connection getConnnection() throws SQLException {
		Connection connection = null;
		String dburl = "jdbc:sqlite:C:\\databases\\project.db";
		connection = DriverManager.getConnection(dburl);
		System.out.println("Connection Created ");
		return connection;
	}
	
	
	
	

	public gameplay1() {
		arr = new GenerateBrick1(4, 10);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}

	private void playSound() {
		new MP3Player(new File("brick/break1.mp3")).play();
	}

	private void playwonSound() throws InterruptedException {
		// new MP3Player(new File("won.mp3")).play();
		File f = new File("brick/won.mp3");
		MP3Player music = new MP3Player(f);
		music.play();
		Thread.sleep(2000);
		music.stop();
		music.setRepeat(false);

	}

	private void playlostSound() throws InterruptedException {
		// new MP3Player(new File("lost.mp3")).play();
		File f = new File("brick/lost.mp3");
		MP3Player music = new MP3Player(f);
		music.play();
		Thread.sleep(2000);
		music.stop();
		music.setRepeat(false);
	}
	
	
	public void paint(Graphics g) {
		// for background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 992, 592);
		Toolkit tkit = Toolkit.getDefaultToolkit();
		Image img = tkit.getImage("brick/brick1.jpg");
		g.drawImage(img, 1, 1, 992, 592, this);
		// obj of GenerateBrick class
		arr.draw((Graphics2D) g);
		// for border
		/*
		 * g.setColor(Color.RED); g.fillRect(0, 0, 3, 592); g.fillRect(0, 0, 992, 3);
		 * g.fillRect(991, 0, 3, 592);
		 */
		// for the paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 540, 100, 12);
		// display score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString(Brick.name + " Score: " + score, 770, 30);
		g.drawString("Max Score: " + maxScore(), 30, 30);
		if (ballposY > 570) {
			updateScore();
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Game Over Your Score is: " + score, 340, 300);
			try {
				playlostSound();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press enter to restart game", 340, 350);

		}
		if (score >= highestScore) {
			updateScore();
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Congratulatons! You Won", 340, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press enter to Play Again", 340, 350);
			try {
				playwonSound();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// for the ball
		g.setColor(Color.red);
		g.fillOval(ballposX, ballposY, 20, 20);
		g.dispose();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 890) {
				playerX = 890;
			} else {
				moveRight();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 10) {
				playerX = 10;

			} else {
				moveLeft();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 420;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 610;
				score = 0;
				totalBricks = 27;
				arr = new GenerateBrick1(4, 10);
				repaint();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		time.start();
		if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 540, 100, 12))) {
			ballYdir = -ballYdir;

		}
		A: for (int i = 0; i < arr.arr.length; i++) {
			for (int j = 0; j < arr.arr[0].length; j++) {
				if (arr.arr[i][j] > 0) {
					int width = arr.brickwidth;
					int height = arr.brickheight;
					int brickpos_X = 80 + j * width;
					int brickpos_Y = 50 + i * height;
					Rectangle rect = new Rectangle(brickpos_X, brickpos_Y, width, height);
					Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
					Rectangle newrect = rect;
					if (ballrect.intersects(newrect)) {
						playSound();
						arr.setBrick(0, i, j);
						totalBricks--;
						score = score + 5;

						if (ballposX + 19 <= newrect.x || ballposX + 1 >= newrect.x + newrect.width) {
							ballXdir = -ballXdir;
						} else {
							ballYdir = -ballYdir;
						}
						break A;

					}
				}
			}

		}
		if (play) {
			ballposX += ballXdir;
			ballposY += ballYdir;
			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 970) {
				ballXdir = -ballXdir;
			}
		}

		repaint();
	}

	private void moveRight() {
		play = true;
		playerX = playerX + 20;
	}

	private void moveLeft() {
		play = true;
		playerX = playerX - 20;
	}

	public void updateScore() {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {

			con = getConnnection();
			 System.out.println(Brick.user);
			pstmt = con.prepareStatement("SELECT brickscore FROM user WHERE email=?");
			pstmt.setString(1, Brick.user);
			rs = pstmt.executeQuery();
			//Statement st = con.createStatement();
			System.out.println("hello jkhedk");
		    // rs = st.executeQuery("SELECT brickscore FROM user WHERE email='"+Brick.user+"'");
			System.out.println("********************");
			//pstmt.close();
			if (rs.next()) {
				maxScore = rs.getInt("brickscore");
				System.out.println(maxScore);
				if (maxScore <= score) {
					pstmt1 = con.prepareStatement("UPDATE user SET brickscore = '" + score + "' WHERE email = ?");
					pstmt1.setString(1, Brick.user);
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
			pstmt = con.prepareStatement("SELECT brickscore FROM user WHERE email=?");
			pstmt.setString(1, Brick.user);
			rs = pstmt.executeQuery();
			 if (rs.next()) {
				maxScore = rs.getInt("brickscore");
				maximumScore = maxScore;
			 }
			 con.close();
			 rs.close();
			 pstmt.close();
			
			
		}
		catch(Exception e) {
		  System.out.println(e);
		}
		return maximumScore;
		
		
	}

}
