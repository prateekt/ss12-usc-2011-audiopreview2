/*
 * (c) Copyright 2011, Jonathan Sun, Ryan Tenorio, Brehon Humphrey, Zach Boehm,
 * Andy Ybarra, Kyle Mylonakis
 * 
 * This file is part of AudioPreview.
 *
 * AudioPreview is free software: you can redistribute it and/or modify
 * it under the terms of the New BSD License.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED 
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS 
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT 
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * You should have received a copy of the new BSD License along with AudioPreview.  
 * If not, see <http://www.opensource.org/licenses/bsd-license.php>.
 */

package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.vecmath.Point3d;
import engine.SeatSection;

/**
 * State enum to determine the current state of the buttons
 * @author Andy
 *
 */
enum State {NOT_SELECTED,SELECTED};

/**
 * GUISeatSection.java
 * Purpose: Takes the row and column values and map them to the correct 
 * SeatSection objects by finding the corresponding name and vector components
 * @author Andy Ybarra
 *
 */
public class GUISeatSection extends JButton implements ActionListener{
	
	/**	Own SeatSection variable to be instantiated later*/
	SeatSection mySeatSection;
	
	/**	Own Point3d location used for holding the 3 component vector values*/
	Point3d location;
	
	/** String sectionName to be used to load the .wav file and the .png image for the icon */
	String sectionName;
	
	/** File voiceFile to be loaded and passed to the SeatSection */
	File voiceFile;
	
	/** Current enum state of the button */
	State GSS_state;
	
	/** Image icon for the seat */
	ImageIcon seatIcon;
	
	/** Parent panel to report button clicks to*/
	ConcertHallPanel parentPanel;
	
	/** Image to put on the ImageIcon for selected states*/
	Image selectedSeat;
	
	/** Image to put on the ImageIcon for not selected states*/
	Image blueSeat;
	
	/**
	 * Overloaded Constructor for the GUISeatSection class 
	 * @param p panel used so we can report to button clicks.
	 * @param row int used for mapping the location to the actual GUI.
	 * @param col int used for mapping the location to the actual GUI.
	 */
	public GUISeatSection(ConcertHallPanel p,int row, int col){
		parentPanel = p;
		
		sectionName = lookUpNameAndLocation(row, col);
		
		/** This will instantiate the voice file using the file name retrieved 
		 * previously concatenated with the directory*/
		voiceFile = new File("..\\sounds\\ChristinaSounds\\"+ sectionName.concat(".wav"));
		
		mySeatSection = new SeatSection(location, sectionName, voiceFile);
		
		GSS_state = State.NOT_SELECTED;
		addActionListener(this);
		seatIcon = new ImageIcon();
		loadImages();
		setGSS_Color(GSS_state);
	}
	
	/**
	 * Loads the two images using the grabImage method to get the images
	 *  
	 */
	public void loadImages(){
		selectedSeat = grabImage("..\\images\\ConcertHall\\"+sectionName.concat(".PNG"));
		blueSeat = grabImage("..\\images\\seatButtonSelected.png");
	}
	
	/**
	 * Gets the Image from the local directory and returns the image
	 * 
	 * @param filename in directory.
	 */
	public Image grabImage(String filename){
		File sourceimage = new File(filename);
    	Image toReturn;
        toReturn = Toolkit.getDefaultToolkit().getImage(filename);
		return toReturn;
	}
	
	/**
	 * Gets the current enum State of the button
	 * @return the current 
	 */
	public State getState(){
		return GSS_state;
	}
	
	/**
	 * Sets the State based on the State passed in.
	 * @param selectedOrNot
	 */
	public void setState(State selectedOrNot){
		GSS_state = selectedOrNot;
	}
	
	/**
	 * Gets the SeatSection object of this GUISeatSection
	 * @return the SeatSection object to the caller
	 */
	public SeatSection getSeatSection(){
		return mySeatSection;
	}
	
	/**
	 * Sets the seatIcon ImageIcon based on the State enum passed in.
	 * @param selectedOrNot
	 */
	public void setGSS_Color(State selectedOrNot){
		switch(selectedOrNot){
		/** This will display the blue seats  */
		case NOT_SELECTED:
			this.setBackground(Color.BLUE);
			seatIcon.setImage(blueSeat);
			this.setIcon(seatIcon);
			//will have red chains image icon.
			//this.setBackground(Color.BLUE);
			//this.setForeground(Color.BLUE);
			//this.setBorderPainted(false);
			//this.setOpaque(true);
			break;
		/** This will set the ImageIcon to it's corresponding ConcertHall Image */
		case SELECTED:
			this.setBackground(Color.RED);
			seatIcon.setImage(selectedSeat);
			this.setIcon(seatIcon);
			//will have the blue chains image icon.
			///this.setBackground(Color.RED);
			//this.setForeground(Color.RED);
			///this.setBorderPainted(false);
			//this.setOpaque(true);
			break;
		}
	}
	
	/**
	 * This will return the corresponding name and 3 component vector for 
	 * that location based on the row and column
	 * @param row
	 * @param col
	 * @return The string name of that SeatSection as well as sets the location 
	 * vector to it's corresponding 3 components
	 */
	public String lookUpNameAndLocation(int row, int col){
		String name = " ";
		location = new Point3d();
		switch(row){
		case 0:
			switch(col){
			case 0:
				name = "EastBalconyBehind";
				location.set(-1,-2,.5);
				break;
			case 4:
				name = "WestBalconyBehind";
				location.set(-1,2,.5);
				break;
			}
			break;
		case 1:
			switch(col){
			case 0:
				name = "EastBalconyFront";
				location.set(1.5,-2,.5);
				break;
			case 1:
				name = "FrontOrchestraEast";
				location.set(1,-1,0);
				break;
			case 2:
				name = "FrontOrchestraCenter";
				location.set(1,0,0);
				break;
			case 3: 
				name = "FrontOrchestraWest";
				location.set(1,1,0);
				break;
			case 4:
				name = "WestBalconyFront";
				location.set(1.5,2,.5);
				break;
			}
			break;
		case 2: 
			switch(col){
			case 0:
				name = "TerraceEast";
				location.set(4,-1,2);
				break;
			case 1:
				name = "OrchestraLevelEast";
				location.set(2,-1,.5);
				break;
			case 2:
				name = "OrchestraLevelCenter";
				location.set(2,0,.5);
				break;
			case 3: 
				name = "OrchestraLevelWest";
				location.set(2,1,.5);
				break;
			case 4: 
				name = "TerraceWest";
				location.set(4,1,2);
				break;
			}
			break;
		case 3: 
			switch(col){
			case 1:
				name = "MezzanineEast";
				location.set(3,-1,2);
				break;
			case 2:
				name = "MezzanineCenter";
				location.set(3,0,2);
				break;
			case 3:
				name = "MezzanineWest";
				location.set(3,1,2);
				break;
			}
			break;
		}
		return name;
	}

	/**
	 * Will notify the parent panel that this was the button that was clicked.
	 */
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("button clicked");
		parentPanel.buttonClicked(this);
	}
}
