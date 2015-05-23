/**
 * 
 * @author mfink351
 * 
 * Main class for EEG Brain Orchestra
 * Creates an EEGScheduler and then executes
 */

public class main {

	public static void main(String[] args){
		int numReaders = 1;
		int pollInterval = 2;
		int pollDuration = 20;
		
		EEGScheduler sched = new EEGScheduler(numReaders, pollInterval, pollDuration);
		sched.execute();
	}

}