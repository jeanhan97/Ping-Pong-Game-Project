import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* Name: Ji Eun Han       
 * Assignment Number: Project 04
 * Section: Mon, Wed 12:30 
 * Lab TA: Jack, Sofia, Xena
 * I did not collaborate with anyone. 
 */

public class Canvas extends JFrame implements ActionListener, ChangeListener{
	//initialized variable which values never change
		final static int CANVAS_WIDTH = 600;
		final static int CANVAS_HEIGHT = 600;
		final static int CANVAS_BASELINE = CANVAS_HEIGHT - 70;
		protected JButton startbutton;
		protected JLabel welcomemsg, instructionmsg; 
		protected JSlider startlevel;
		protected JTextField selectindi;
		protected static int userlevel;
		
		public Canvas(){
			setTitle("Bouncing Ball Game App");
			setSize(900,300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new FlowLayout());
			
			//WELCOME MESSAGE 
			welcomemsg = new JLabel("WELCOME TO LOB PONG!");
			add(welcomemsg);
			
			//INSTRUCTION MESSAGE 
			instructionmsg = new JLabel("<html> GAME INSTRUCTIONS.. "
					+ "<br>Press left key to move bar left and press right key to move bar right. "
					+ "<br>If you miss the ball, you lose a life and you get 3 lives to start with. "
					+ "<br>Everytime the countdown counts to 0, the ball and bar will reset and you will get 5+ bonus points for winning the level."
					+ "<br>Each level gets harder as the bar gets shorter. "
					+ "<br>Press start game button to open the game frame window and start the game."
					+ "<br>You have to adjust the slider below to start from a level of your choice upto level 5."
					+ "<br>If you do not choose the starting level, the game will not start."
					+ "<br>Good luck!!<html>");
			add(instructionmsg);

			//LEVEL SLIDER 
			JLabel startlevelprompt = new JLabel("Select the level you want to start with.");
			add(startlevelprompt);
			startlevel = new JSlider(JSlider.HORIZONTAL,1,5,1);
			startlevel.addChangeListener(this);
			add(startlevel);
			selectindi = new JTextField (3);
			add(selectindi);
			
			
			//START BUTTON
			startbutton = new JButton("START!");
			add(startbutton);
			
		}
		
		@Override 
		public void stateChanged(ChangeEvent e){
			System.out.println("statechanged");
			Object slidersource = e.getSource();
			if (slidersource == startlevel){
				startlevel.setMajorTickSpacing(1);
				startlevel.setPaintTicks(true);
				startlevel.setPaintLabels(true);
				startlevel.setLabelTable(startlevel.createStandardLabels(1));
				
				//when the startlevel is chosen, the acitonlistener adds to startbutton
				startbutton.addActionListener(this);
				
				selectindi.setText(Integer.toString(startlevel.getValue()));
				userlevel = startlevel.getValue();
				
				
				
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e){
			System.out.println("ActionPerformed");
			Object source = e.getSource();
			
			//when start button is pressed 
			if (source == startbutton){
				System.out.println("Start game");
				//open the game canvas
				BounceballFrame canvas = new BounceballFrame();
				canvas.setVisible(true);
			}
		}
		
		public class BounceballFrame extends JFrame{
			public BounceballFrame(){
				setTitle("Bouncing Ball");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
				setResizable(false);
			
				Panel panel = new Panel();
				add(panel);
			}
		}
		
	
		
		
		public static void main(String[] args) {
			new Canvas().setVisible(true);
		}
}


