package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

//import javafx.scene.paint.Color;

public class GenerateBrick {
	int arr[][];
	int brickwidth;
	 int brickheight;
	
	public GenerateBrick(int row, int column) {
		arr = new int[row][column];
		for (int i=0;i<row;i++) {
			for (int j=0;j<column;j++) {
				arr[i][j] = 1;
			}
		}
		brickwidth = 540/column;
		brickheight = 150/row;
	}
	public void setBrick(int v, int r, int c) {
		arr[r][c] = v;
	}
	public void draw(Graphics2D g) {
		for (int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[0].length;j++) {
				if (arr[i][j]>0) {
					//g.setClip(Color.WHITE);
					g.setColor(Color.WHITE);
					g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
					g.setColor(Color.BLACK);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
				}
			}
		}
		
	}
	

}
