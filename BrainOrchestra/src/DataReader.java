import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Fink
 *
 *	This class is meant to read the data from a supplied file and then display
 *  the averages of those parsed values.
 *
 */

public class DataReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Double attentionSum = 0.; Double meditationSum = 0.;
		Double deltaSum = 0.; Double thetaSum = 0.;
		Double alpha1Sum = 0.; Double alpha2Sum = 0.;
		Double beta1Sum = 0.; Double beta2Sum = 0.;
		Double gamma1Sum = 0.; Double gamma2Sum = 0.;
		int total = 0;
		
		while(true){
		
			Scanner reader = new Scanner(System.in);
			System.out.print("Enter Subject Name (FirstLast): ");
			String subject = reader.nextLine();
			System.out.print("Ener clip letter: ");
			String clip = reader.nextLine();
		
			try (BufferedReader br = new BufferedReader(new FileReader("Clip"+clip+"-" + subject + ".txt"))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	line = line.replace('[', ' ').replace(']', ' ').replaceAll(" ", "");
			    	String[] vals = line.split(",");
			    	attentionSum += Double.parseDouble(vals[0]);
			    	meditationSum += Double.parseDouble(vals[1]);
			    	deltaSum += Double.parseDouble(vals[2]);
			    	thetaSum += Double.parseDouble(vals[3]);
			    	alpha1Sum += Double.parseDouble(vals[4]);
			    	alpha2Sum += Double.parseDouble(vals[5]);
			    	beta1Sum += Double.parseDouble(vals[6]);
			    	beta2Sum += Double.parseDouble(vals[7]);
			    	gamma1Sum += Double.parseDouble(vals[8]);
			    	gamma2Sum += Double.parseDouble(vals[9]);
			    	total++;
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.print(attentionSum/total + ", ");
			System.out.print(meditationSum/total + ", ");
			System.out.print(deltaSum/total + ", ");
			System.out.print(thetaSum/total + ", ");
			System.out.print(alpha1Sum/total + ", ");
			System.out.print(alpha2Sum/total + ", ");
			System.out.print(beta1Sum/total + ", ");
			System.out.print(beta2Sum/total + ", ");
			System.out.print(gamma1Sum/total + ", ");
			System.out.println(gamma2Sum/total + "\n");
		
		}

	}

}
