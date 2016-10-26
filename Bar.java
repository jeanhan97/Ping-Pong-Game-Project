import java.awt.Color;
import java.awt.Graphics;

/* Name: Ji Eun Han       
 * Assignment Number: Project 04 
 * Section: Mon, Wed 12:30 
 * Lab TA: Jack, Sofia, Xena
 * I did not collaborate with anyone.  
 */

public class Bar {
	protected int x = Canvas.CANVAS_WIDTH/2;
	protected int xV = 0;
	
	// size of the moving bar 
	protected int width = 50;
	protected int height = 5; 
	
	public Bar(){
		
	}
	
	public void update(){
		
	
		//setting minimum and maximum value of x (in case bar goes off the frame)
		x = Math.min(x, Canvas.CANVAS_WIDTH - width/2);
		x = Math.max(0 + width, x);
		
		x = x + xV; 
		
	}
	
	public void paint(Graphics g){
		//drawing the bar 
		g.setColor(Color.WHITE);
		g.fillRect(x-width/2, Canvas.CANVAS_BASELINE + height, width, height);
	}
	
	public void setxV(int speed){
		xV = speed;
	}
	
	public int getX(){
		return x-width/2;
	}
	public int getY(){
		return Canvas.CANVAS_BASELINE + height;
	}
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
}
