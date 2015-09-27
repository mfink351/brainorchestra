import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



public class EEGRunnable implements Runnable{
	
	private final String name;
	private int numTimesRun;
	private final EEGReader eeg;
	
	//Frame, Image, and Video Component
	private final JFrame f;
	private SheetMusic prevSheet;

	public EEGRunnable(String name, String subject, String clip){
		//Set up this thread's name
		this.name = name;
		this.numTimesRun = 1;
		
		//Set up EEG
		this.eeg = new EEGReader(subject, clip);
		
		//Set up JFrame
		this.f = new JFrame("Sheet Music");
        this.f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        
        //Load the default image to display
		BufferedImage img = null;
		try {
			String fileName = "null.jpg";
			System.out.println(fileName);
		    img = ImageIO.read(new File(fileName));		    
		} catch (IOException e) {
			System.out.println(e);
		}
        this.prevSheet = new SheetMusic(img);
	}
	
	
	@Override
	public void run() {
		System.out.println(name + ": is now polling! Has polled " +
				numTimesRun + " times!");
		this.numTimesRun += 1;
		
		/**
		 * THIS NEEDS TO USE THE EEG SCANNER CODE TO DETERMINE WHICH IMAGE
		 * IS LOADED AND DISPLAYED
		 * 
		 * DISPLAYING OF IMAGES IS HANDLED BY THE EEGRUNNABLE
		 */
		
        //Read from EEG to get img and vid paths
		ArrayList<String> paths = this.eeg.read();
		
		//Load the image to display
		BufferedImage img = null;
		try {
			String fileName = paths.get(0);
			System.out.println(fileName);
		    img = ImageIO.read(new File(fileName));		    
		} catch (IOException e) {
			System.out.println(e);
		}

		//Panel update and showing
		this.f.getContentPane().removeAll();
		final SheetMusic sm = new SheetMusic(img);
		this.f.getContentPane().add(this.prevSheet, BorderLayout.WEST);
		this.f.getContentPane().add(sm, BorderLayout.EAST);
		this.f.pack();
		this.f.setVisible(true);
		//Store this sheet as the new previous
		this.prevSheet = sm;
		
		/**AUSTIN IMPLEMENT THIS YOU TARD
		//WRITE VIDEO LOADING AND PLAYING CODE HERE
		 */
		
		
	}

}
