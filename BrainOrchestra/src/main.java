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
		int pollInterval = 4;
		int pollDuration = 4000;
		
		EEGScheduler sched = new EEGScheduler(numReaders, pollInterval, pollDuration);
		sched.execute();
		
	}

}
