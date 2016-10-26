import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

/* Name: Ji Eun Han       
 * Assignment Number: Project 04
 * Section: Mon, Wed 12:30 
 * Lab TA: Jack, Sofia, Xena
 * I did not collaborate with anyone. 
 */

public class Panel extends JPanel implements KeyListener, ActionListener {
	
	//PANEL instance variables
	protected Timer timer;
	protected Bar bar = new Bar(); //call Bar class 
	protected Ball ball = new Ball(); //call Ball class
	protected Random rand = new Random();
	protected int startx = rand.nextInt(Canvas.CANVAS_WIDTH);
	

	//BAR instance variables
	protected boolean rightPressed = false;
	protected boolean leftPressed = false; 
	
	//Count down 
	protected Timer countdown;
	protected int delay = 60;
	protected ActionListener taskperformer;
	
	
	//Game Levels 
	protected int gamelevel = Canvas.userlevel;
	protected int lives = 3;
	protected int points = 0;
	
	//EXTRA CREDIT STATIONARY OBJECTS 
	int randwidth = rand.nextInt(50);
	int randobjectx = rand.nextInt(Canvas.CANVAS_WIDTH-randwidth);
	int randheight = rand.nextInt(50);
	int randobjecty = rand.nextInt(Canvas.CANVAS_HEIGHT-randheight);
	int randnumobject = rand.nextInt(50);
	
	public Panel(){
		//timer for animation
		timer = new Timer(20,this); //millisecond 
		timer.start();
		
		
		this.addKeyListener(this);
		this.setFocusable(true);
		
		//timer on top left corner of screen 
		taskperformer = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
				delay--;
				if (delay == 0){
					countdown.stop();
					timer.stop();
				}
			}
		};
		countdown = new Timer(1000, taskperformer);
		countdown.start();
	}
	
	
	public void updateboth(){
		bar.update();
		
		if (ball.getY() < 0){
			ball.yvelocity = -ball.yvelocity;
		} else if (ball.getX() <= 0){
			ball.xvelocity = -ball.xvelocity;
		} else if (ball.getX() + ball.getsize() >= Canvas.CANVAS_WIDTH){
			ball.xvelocity = -ball.xvelocity;
		} else if (ball.getY() == bar.getY() ){
			if (ball.getX() + ball.getsize() >= bar.getX() && ball.getX() <= bar.getX() + bar.getWidth()) {
				System.out.println("BallBounced");
				ball.yvelocity = -ball.yvelocity;
				points += 1;
			}
		}
		
		ball.ballx += ball.xvelocity;
		ball.yvelocity = ball.yvelocity + (ball.gravity * .01);
		ball.bally += ball.yvelocity;
		
		//makes each level harder by making bar shorter in width
		if (bar.getWidth() > 3){
			bar.setWidth(50 - gamelevel * 5);
		}
		
	}
	
	public void paintComponent(Graphics g){
		
		//background 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Canvas.CANVAS_WIDTH, Canvas.CANVAS_HEIGHT);
		
		
		//count down timer on top left corner
		g.setColor(Color.WHITE);
		Font count = new Font("Dialog",Font.PLAIN, 20);
		g.setFont(count);
		g.drawString(String.valueOf(delay), 40, 40);
		

		if (ball.bally + ball.size > Canvas.CANVAS_HEIGHT){ //when ball goes passed bar 
			lives -= 1;
			
			ball.xvelocity = 3;
			ball.yvelocity = 0;
			ball.ballx = startx;
			ball.bally = 0;
			
			
			System.out.println("Life lost");
		}
		
		if (lives > 0){
			bar.paint(g);
			ball.paint(g);
			
			//Printing the score on top right corner 
			g.setColor(Color.WHITE);
			Font f = new Font("Dialog",Font.PLAIN, 18);
			g.setFont(f);
			g.drawString(Integer.toString(points), Canvas.CANVAS_WIDTH - 100, 40);
			
			g.setColor(Color.YELLOW);
			for (int i=0; i < lives ; i++){
				g.fillOval(Canvas.CANVAS_WIDTH - 45 - i*10, 50, 5, 5);
			}
			
			g.setColor(Color.WHITE);
			g.setFont(f);
			g.drawString("Level " + Integer.toString(gamelevel), Canvas.CANVAS_WIDTH - 100, 20);
			
			
		} else if (lives == 0){
			timer.stop();
			countdown.stop();
			
			//get rid of the count down on top left corner on game over page
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Canvas.CANVAS_WIDTH, Canvas.CANVAS_HEIGHT);
			
			g.setColor(Color.WHITE);
			Font f = new Font("Dialog",Font.PLAIN, 36);
			g.setFont(f);
			g.drawString("Game Over", Canvas.CANVAS_WIDTH/4, Canvas.CANVAS_HEIGHT/4);
			
			Font font = new Font("Dialog",Font.PLAIN, 18);
			g.setFont(font);
			g.drawString("Level " + Integer.toString(gamelevel), Canvas.CANVAS_WIDTH/4, Canvas.CANVAS_HEIGHT/4+40);

			g.drawString("Your final score: " + Integer.toString(points), Canvas.CANVAS_WIDTH/4, Canvas.CANVAS_HEIGHT/4+60);

		}
		
		if (delay == 0){
			//reset the countdown time; 
			delay = 10;
			timer.start();
			countdown = new Timer(1000,taskperformer);
			countdown.start();
			
			ball.xvelocity = 3;
			ball.yvelocity = 0;
			ball.ballx = startx;
			ball.bally = 0;
			bar = new Bar();
			
//			//CREATE RANDOM OBJECTS 
//			for (int i=0; i < 5 * gamelevel; i++){
//				g.setColor(Color.WHITE);
//				g.fillRect(randobjectx, randobjecty, randwidth, randheight);
//			}
			
			//Higher Level -> Shorter bar width 
			if (bar.getWidth() > 3){
				bar.setWidth(50 - gamelevel * 5);
			}
			
			ball.paint(g);
			bar.paint(g);
			
			gamelevel += 1; //level up each time count down timers tops and there are still lives left 
			points += 5; //get bonus points when level is complete 
			
			updateboth();
			repaint();
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		updateboth();
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			bar.setxV(5);
			
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			bar.setxV(-5);
			
			break; 
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch (keyCode){
		case KeyEvent.VK_RIGHT: 
			rightPressed = false;
			if (leftPressed = true){
				bar.setxV(-5);
			} else {
				bar.setxV(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			if (rightPressed){
				bar.setxV(5);
			} else {
				bar.setxV(0);
			}
			break;
		}
		
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
			bar.setxV(0); 
		}
		
	}

	
}
