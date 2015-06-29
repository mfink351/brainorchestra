import java.util.ArrayList;
import java.util.Random;

//The main EEGReader Class with 
public class EEGReader {
	
	//Class Constructor
	public EEGReader(){
		
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
		//THIS CODE WILL NEED TO BE MODIFIED ONCE AN EEG IS CHOSEN
		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
