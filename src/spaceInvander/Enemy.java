package spaceInvander;

import java.awt.Graphics;
import java.awt.Color;
//import javafx.scene.paint.Color;

public class Enemy {
	private int x;
	private int y;
	
	public Enemy(int x,int y) {
		this.x=x;
		this.y=y;
		
	}
	public void  tick() {
		y+=1;
	}
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawImage (LoadImages.enemy,x,y,50,50,null);
		
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	}


