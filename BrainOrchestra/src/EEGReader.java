import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.neurosky.thinkgear.ThinkGear;

//The main EEGReader Class with 
public class EEGReader {
	
	private int connectionId;
	private String name;
	private String clip;
	
	//Class Constructor
	public EEGReader(String name, String clip){
		this.connectionId  = ThinkGear.GetNewConnectionId();
		this.name = name;
		this.clip = clip;
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
		
		
		ArrayList<Double> readings = this.getReadings();
		
		
		i = getState(readings);
		
		
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
	
	/**
	 * Polls the EEG and collects brain-wave readings returning
	 * a final arraylist containing the averages of all brain-waves
	 * for the set amount of time
	 * 
	 * @return - An array list 'readings' containing brain-wave information
	 * as follows:
	 * readings.get(0) = Attention Average
	 * readings.get(1) = Meditation Average
	 * readings.get(2) = Delta Average
	 * readings.get(3) = Theta Average
	 * readings.get(4) = Alpha1 Average
	 * readings.get(5) = Alpha2 Average
	 * readings.get(6) = Beta1 Average
	 * readings.get(7) = Beta2 Average
	 * readings.get(8) = Gamma1 Average
	 * readings.get(9) = Gamma2 Average
	 */
	private ArrayList<Double> getReadings(){	
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
		while(getSeconds(System.currentTimeMillis(), startTime) < 3){
			do{
				packetsRead = ThinkGear.ReadPackets(connectionId, -1);
				
				//Read in all waves
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ATTENTION) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ATTENTION);
					//System.out.println("Get attention data: " + temp);
					attention.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_MEDITATION) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_MEDITATION);
					//System.out.println("Get meditation data: " + temp);
					meditation.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_DELTA) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_DELTA);
					//System.out.println("Get delta data: " + temp);
					delta.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_THETA) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_THETA);
					//System.out.println("Get theta data: " + temp);
					theta.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ALPHA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ALPHA1);
					//System.out.println("Get alpha1 data: " + temp);
					alpha1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_ALPHA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_ALPHA2);
					//System.out.println("Get alpha2 data: " + temp);
					alpha2.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_BETA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_BETA1);
					//System.out.println("Get beta1 data: " + temp);
					beta1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_BETA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_BETA2);
					//System.out.println("Get beta2 data: " + temp);
					beta2.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_GAMMA1) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_GAMMA1);
					//System.out.println("Get gamma1 data: " + temp);
					gamma1.add(temp);
				}
				if(ThinkGear.GetValueStatus(connectionId, ThinkGear.DATA_GAMMA2) != 0){
					double temp =  ThinkGear.GetValue(connectionId, ThinkGear.DATA_GAMMA2);
					//System.out.println("Get gamma2 data: " + temp);
					gamma2.add(temp);
				}
				
			}while(packetsRead > 0);	
		}
		
		
		ArrayList<Double> readings = new ArrayList<Double>();
		readings.add(getAverage(attention)); readings.add(getAverage(meditation));
		readings.add(getAverage(delta)); readings.add(getAverage(theta));
		readings.add(getAverage(alpha1)); readings.add(getAverage(alpha2));
		readings.add(getAverage(beta1)); readings.add(getAverage(beta2));
		readings.add(getAverage(gamma1)); readings.add(getAverage(gamma2));
		
		System.out.println(readings);
		
		/**
		 * 
		 * Write readings to file
		 * 
		 */
		String write2 = "Clip" + this.clip + "-" + this.name + ".txt";
		this.write2File(write2, readings);
		
		return readings;
	}
	
	/**
	 * Takes in a list of brainwave readings which have been individually
	 * averaged, and computes a single reading value which will be attributed
	 * to a particular cluster of music
	 * 
	 * @param readings - List of Brain Wave Readings
	 * readings.get(0) = Attention Average
	 * readings.get(1) = Meditation Average
	 * readings.get(2) = Delta Average
	 * readings.get(3) = Theta Average
	 * readings.get(4) = Alpha1 Average
	 * readings.get(5) = Alpha2 Average
	 * readings.get(6) = Beta1 Average
	 * readings.get(7) = Beta2 Average
	 * readings.get(8) = Gamma1 Average
	 * readings.get(9) = Gamma2 Average
	 * 
	 * @return A state as determined by our classifier in the futre
	 */
	
	//THIS IS WHERE THE LIBSVM IMPLEMENTATION WILL GO
	private int getState(ArrayList<Double> readings){
		if(readings.get(0) > 50){
			return 1;
		} else {
			return 2;
		}
	}
	
	/**
	 * Returns the average of the values of the array arr
	 * @param arr - An array of doubles
	 * @return Average value of arr
	 */
	private double getAverage(ArrayList<Double> arr){
		double sum = 0;
		for(int i = 0; i < arr.size(); i++){
			sum += arr.get(i);
		}
		return sum/arr.size();
	}
	
	/**
	 * Writes the data to a specified file indicated via parameter 'fileLoc'
	 * @param fileLoc
	 * @param data
	 */
	private void write2File(String fileName, ArrayList<Double> data){
		try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName, true);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(data.toString());
            bufferedWriter.newLine();

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
	}
	
	private static long getSeconds(long msBig, long msLess){
		return (msBig -  msLess)/1000;
	}
}
