import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/* Name: Ji Eun Han       
 * Assignment Number: Project 04
 * Section: Mon, Wed 12:30 
 * Lab TA: Jack, Sofia, Xena
 * I did not collaborate with anyone. 
 */

public class Ball {

	//BALL instance variables
	protected int size = 5; 
	
	//initial ball position random somewhere on top of screen 
	protected Random rand = new Random();
	protected int initialx = rand.nextInt(Canvas.CANVAS_WIDTH);
	
	protected double ballx = initialx;
	protected double bally = 0;
	
	protected double gravity = 9.8;
	
	//initial velocity until it bounces off the bar 
	protected double xvelocity = 4;
	protected double yvelocity = 0;
	
	Ball(){
		
	}
	

	public void paint(Graphics g){
		//draw ball
		g.setColor(Color.WHITE);
		g.fillOval((int)(ballx), (int)(bally), size, size);
	}
	
	public int getX(){
		return (int) ballx;
	}
	
	public int getY(){
		return (int) bally;
	}
	
	public int getsize(){
		return size; 
	}
	
}
