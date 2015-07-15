import java.util.ArrayList;
import java.util.Random;

import com.neurosky.thinkgear.ThinkGear;

//The main EEGReader Class with 
public class EEGReader {
	
	private int connectionId;
	
	//Class Constructor
	public EEGReader(){
		this.connectionId  = ThinkGear.GetNewConnectionId();
		String comPortName = "\\\\.\\COM3";
		ThinkGear.Connect(connectionId, comPortName, ThinkGear.BAUD_57600, ThinkGear.STREAM_PACKETS);
	}
	
	/**
	 * This is where the algorithm determining which emotion
	 * to return will be written
	 * 
	 * @return an emotional state
	 */
	public ArrayList<String> read(){
		Emotion e = null;
		String sheetPath = "";
		String vidPath = "";
		
		//Reads the EEG 
		Random rn = new Random();
		int min = 1;
		int max = 5;
		int i = rn.nextInt(max - min + 1) + min;
		
		
		
		
		
		
		/**
		 * EEG TEST CODE
		 */
		
		ArrayList<Double> attention = new ArrayList<Double>();
		ArrayList<Double> meditation = new ArrayList<Double>();
		ArrayList<Double> delta = new ArrayList<Double>();
		ArrayList<Double> theta = new ArrayList<Double>();
		ArrayList<Double> alpha1 = new ArrayList<Double>();
		ArrayList<Double> alpha2 = new ArrayList<Double>();
		ArrayList<Double> beta1 = new ArrayList<Double>();
		ArrayList<Double> beta2 = new ArrayList<Double>();
		ArrayList<Double> gamma1 = new ArrayList<Double>();
		ArrayList<Double> gamma2 = new ArrayList<Double>();
		
		long startTime = System.currentTimeMillis();
		int packetsRead = 0;
		while(getSeconds(System.currentTimeMillis(), startTime) < .5){
			do{
				packetsRead = ThinkGear.ReadPackets(connectionId, -1);
				
				//Read in all waves
				
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ATTENTION) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ATTENTION);
					System.out.println("Get attention data: " + temp);
					attention.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_MEDITATION) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_MEDITATION);
					System.out.println("Get meditation data: " + temp);
					meditation.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_DELTA) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_DELTA);
					System.out.println("Get delta data: " + temp);
					delta.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_THETA) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_THETA);
					System.out.println("Get theta data: " + temp);
					theta.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ALPHA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ALPHA1);
					System.out.println("Get alpha1 data: " + temp);
					alpha1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ALPHA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ALPHA2);
					System.out.println("Get alpha2 data: " + temp);
					alpha2.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_BETA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_BETA1);
					System.out.println("Get beta1 data: " + temp);
					beta1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_BETA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_BETA2);
					System.out.println("Get beta2 data: " + temp);
					beta2.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_GAMMA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_GAMMA1);
					System.out.println("Get gamma1 data: " + temp);
					gamma1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_GAMMA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_GAMMA2);
					System.out.println("Get gamma2 data: " + temp);
					gamma2.add(temp);
				}
				
				
			}while(packetsRead > 0);	
		}
		
		/**
		 * 
		 */
		
		
		
		
		
		if( i == 1){
			e = Emotion.ANGRY;
		} 
		else if( i == 2){
			e = Emotion.EXCITED;
		} 
		else if( i == 3){
			e = Emotion.HAPPY;
		}
		else if( i == 4){
			e = Emotion.SAD;
		}
		else {
			e = Emotion.SCARED;
		}
		
		sheetPath = EEGReader.getSheetPath(e);
		
		ArrayList<String> paths = new ArrayList<String>();
		paths.add(sheetPath); paths.add(vidPath);
		System.out.println(e);
		return paths;
	}
	
	/**
	 * Derives the sheet music image path depending on
	 * the emotion passed
	 * @param em - The emotion the EEG has read
	 * @return - Sheet Music Path
	 */
	private static String getSheetPath(Emotion em){
		switch(em){
			case ANGRY:
				return "emotions/angry.jpg";
			case EXCITED:
				return "emotions/excited.jpg";
			case HAPPY:
				return "emotions/happy.jpg";
			case SAD:
				return "emotions/sad.jpg";
			case SCARED:
				return "emotions/scared.jpg";
			default:
				return "";
		}
	}
	
	private static long getSeconds(long msBig, long msLess){
		return (msBig -  msLess)/1000;
	}


}
