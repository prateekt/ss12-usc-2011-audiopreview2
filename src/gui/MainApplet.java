package gui;

import java.awt.Color;

import javax.swing.JApplet;

public class MainApplet extends JApplet {
	ConcertHallPanel c;
	 public void init() {
		   /* Container cp = getContentPane();
		    cp.setLayout(new FlowLayout());
		    cp.add(b1);
		    cp.add(b2);*/
		 
		 System.out.println("init called");
		 c = new ConcertHallPanel();
		 setSize(c.WINDOW_WIDTH, c.WINDOW_HEIGHT);
			
			c.setSize( c.WINDOW_WIDTH,c.WINDOW_HEIGHT);	
			c.setVisible(true);
			c.setFocusable(true);
			c.requestFocus();
			/*JFrame frame = new JFrame();
			frame.add(c);
			frame.setVisible(true);*/
			this.add(c);
			c.setFocusable(true);
			c.requestFocus();
			c.setBackground(Color.DARK_GRAY);
			
		  }
}