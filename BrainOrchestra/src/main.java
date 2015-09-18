import java.util.Scanner;

import com.neurosky.thinkgear.ThinkGear;

/**
 * 
 * @author mfink351
 * @author amc
 * 
 * Main class for EEG Brain Orchestra
 * Creates an EEGScheduler and then executes
 */

public class main {

	public static void main(String[] args){
		int numReaders = 1;
		//In seconds
		int pollInterval = 1;
		int pollDuration = 9999;
		
		while(true){
			
			Scanner reader = new Scanner(System.in);
			System.out.print("Enter Subject Name (FirstLast): ");
			String subject = reader.nextLine();
			System.out.print("Ener clip letter: ");
			String clip = reader.nextLine();
			
			
			do{
				System.out.println("Press Enter to Begin and then Enter to Stop");
			}
			while(!reader.nextLine().equals(""));
			
			EEGScheduler sched = new EEGScheduler(numReaders, pollInterval, pollDuration);
			sched.execute();
			
			while(true){
				System.out.println("Press Enter to Stop Polling");
				if(reader.nextLine().equals("")){
					sched.shutdown();
					break;
				}
			}
			
			System.out.println("\n\n");
		}
		
	}

}
