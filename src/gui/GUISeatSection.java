package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.vecmath.Point3d;
import engine.SeatSection;

enum State {NOT_SELECTED,SELECTED};

public class GUISeatSection extends JButton implements ActionListener{
	SeatSection mySeatSection;
	Point3d location;
	File voiceFile;
	State GSS_state;
	ImageIcon redSeats;
	ImageIcon blueSeats;
	ConcertHallPanel parent;
	
	public GUISeatSection(ConcertHallPanel p,int row, int col){
		loadImages();
		parent = p;
		String name = lookUpNameAndLocation(row, col);
		mySeatSection = new SeatSection(location, name, voiceFile);
		GSS_state = State.NOT_SELECTED;
		addActionListener(this);
	}
	
	public void loadImages(){
		
	}
	
	public State getState(){
		return GSS_state;
	}
	public void setState(State selectedOrNot){
		GSS_state = selectedOrNot;
	}
	public SeatSection getSeatSection(){
		return mySeatSection;
	}
	
	public void setGSS_Color(State selectedOrNot){
		switch(selectedOrNot){
		case NOT_SELECTED:
			//will have red chains image icon.
			this.setBackground(Color.BLUE);
			this.setForeground(Color.BLUE);
			this.setBorderPainted(false);
			this.setOpaque(true);
			break;
		case SELECTED:
			//will have the blue chains image icon.
			this.setBackground(Color.RED);
			this.setForeground(Color.RED);
			this.setBorderPainted(false);
			this.setOpaque(true);
			break;
		}
	}
	
	public String lookUpNameAndLocation(int row, int col){
		String name = " ";
		location = new Point3d();
		switch(row){
		case 0:
			switch(col){
			case 0:
				name = "East Balcony Behind";
				location.set(-1,-2,.5);
				break;
			case 4:
				name = "West Balcony Behind";
				location.set(-1,2,.5);
				break;
			}
			break;
		case 1:
			switch(col){
			case 0:
				name = "East Balcony Front";
				location.set(1.5,-2,.5);
				break;
			case 1:
				name = "Front Orchestra East";
				location.set(1,-1,0);
				break;
			case 2:
				name = "Front Orchestra Center";
				location.set(1,0,0);
				break;
			case 3: 
				name = "Front Orchestra West";
				location.set(1,1,0);
				break;
			case 4:
				name = "West Balcony Front";
				location.set(1.5,2,.5);
				break;
			}
			break;
		case 2: 
			switch(col){
			case 0:
				name = "Terrace East";
				location.set(4,-1,2);
				break;
			case 1:
				name = "Orchestra Level East";
				location.set(2,-1,.5);
				break;
			case 2:
				name = "Orchestra Level Center";
				location.set(2,0,.5);
				break;
			case 3: 
				name = "Orchestra Level West";
				location.set(2,1,.5);
				break;
			case 4: 
				name = "Terrace West";
				location.set(4,1,2);
				break;
			}
			break;
		case 3: 
			switch(col){
			case 1:
				name = "Mezzanine East";
				location.set(3,-1,2);
				break;
			case 2:
				name = "Mezzanine Center";
				location.set(3,0,2);
				break;
			case 3:
				name = "Mezzanine West";
				location.set(3,1,2);
				break;
			}
			break;
		}
		return name;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("button clicked");
		parent.buttonClicked(this);
	}
}