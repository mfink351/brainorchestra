import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


public class EEGScheduler {
	
	private final ScheduledExecutorService scheduler; 
	private final int numPlayers;
	private final int pollDelay;
	private final int pollDuration;
	
	/**
	 * Creates an EEG Scheduler which will create EEGRunnable Threads
	 * 
	 * @param numRunnables - Number of EEG Threads running
	 * @param pollDelay - How long in seconds between EEG Polls
	 * @param pollDuration - How long in total we will poll for
	 */
	public EEGScheduler(int numPlayers, int pollDelay, int pollDuration){
		this.scheduler = Executors.newScheduledThreadPool(numPlayers);
		this.numPlayers = numPlayers;
		this.pollDelay = pollDelay;
		this.pollDuration = pollDuration;
	};	
	
	/**
	 * Executes this EEG Scheduler
	 * Creates 'numPlayers' number of EEG Threads
	 */
	public void execute(){
		System.out.println("cat");

		//Create EEG Poll Threads and Run Them
		for(int i = 0; i < numPlayers; i++){
			//GIve the EEG Runnable a Name
			String name = "EEG Poller " + i;
			final EEGRunnable eegPoll = new EEGRunnable(name); 
		
			//Handler for the EEG Runnable Thread
	        final ScheduledFuture<?> eegPollHandle =
		            scheduler.scheduleAtFixedRate(eegPoll, pollDelay, pollDelay, SECONDS);
	        
	        //Schedule the newly running EEG Poller Runnable
		    scheduler.schedule(new Runnable() {
		                public void run() { eegPollHandle.cancel(true); }
		            }, this.pollDuration, SECONDS);	
		}
	}

}

