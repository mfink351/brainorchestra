import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class EEGRunnable implements Runnable{
	
	private final String name;
	private int numTimesRun;

	public EEGRunnable(String name){
		this.name = name;
		this.numTimesRun = 1;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name + ": is now polling! Has polled " +
				numTimesRun + " times!");
		this.numTimesRun += 1;
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("strawberry.jpg"));
		    
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
	}

}
