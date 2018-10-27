package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class gamePlay extends JPanel implements ActionListener, KeyListener {
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer time;
	private int delay = 8;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private GenerateBrick arr;
	private int highestScore = totalBricks*5;
	
	public gamePlay() {
		arr = new GenerateBrick(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	
	public void paint(Graphics g) {
		//for background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 692, 592);
		// obj of GenerateBrick class
		arr.draw((Graphics2D)g);
		// for border
		g.setColor(Color.RED);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		//for the paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		// display score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("Score: "+score, 570, 30);
		if (ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Game Over Your Score is: "+score, 190, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press enter to restart game", 190, 350);
			
		}
		if (score>=highestScore) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Congratulatons! You Won", 190, 300);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press enter to Play Again", 190, 350);
			
			
		}
		
		//for the ball
		g.setColor(Color.red);
		g.fillOval(ballposX, ballposY, 20, 20);
		g.dispose();
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			if (playerX>=590) {
				playerX = 590;
			}
			else {
				moveRight();
			}
			
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX<=10) {
				playerX = 10;
				
			}
			else {
				moveLeft();
			}
			
		}
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				arr = new GenerateBrick(3, 7);
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
		if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
			ballYdir = -ballYdir;
		}
		A: for (int i=0;i<arr.arr.length;i++) {
			for (int j=0;j<arr.arr[0].length;j++) {
				if (arr.arr[i][j]>0) {
					int width = arr.brickwidth;
					int height = arr.brickheight;
					int brickpos_X = 80+j*width;
					int brickpos_Y = 50+i*height;
					Rectangle rect = new Rectangle(brickpos_X, brickpos_Y, width, height);
					Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
					Rectangle newrect = rect;
					if (ballrect.intersects(newrect)) {
						arr.setBrick(0, i, j);
						totalBricks--;
						score = score+5;
						
						if (ballposX+19 <= newrect.x || ballposX+1>=newrect.x+newrect.width) {
							ballXdir = -ballXdir;
						}
						else {
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
					if (ballposX<0) {
						ballXdir = -ballXdir;
					}
					if (ballposY<0) {
						ballYdir = -ballYdir;
					}
					if (ballposX > 670) {
						ballXdir = -ballXdir;
					}
		}
		
		repaint();
	}
	private void moveRight() {
		play = true;
		playerX = playerX+20;
	}
	private void moveLeft() {
		play = true;
		playerX = playerX-20;
	}

}
