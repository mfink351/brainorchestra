import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


public class EEGScheduler {
	
	private final ScheduledExecutorService scheduler; 
	private final int numPlayers;
	private final int pollDelay;
	private final int pollDuration;
	private final String subject;
	private final String clip;
	
	/**
	 * Creates an EEG Scheduler which will create EEGRunnable Threads
	 * 
	 * @param numRunnables - Number of EEG Threads running
	 * @param pollDelay - How long in seconds between EEG Polls
	 * @param pollDuration - How long in total we will poll for
	 */
	public EEGScheduler(int numPlayers, int pollDelay, int pollDuration, String subject, String clip){
		this.scheduler = Executors.newScheduledThreadPool(numPlayers);
		this.numPlayers = numPlayers;
		this.pollDelay = pollDelay;
		this.pollDuration = pollDuration;
		this.subject = subject;
		this.clip = clip;
	};	
	
	/**
	 * Executes this EEG Scheduler
	 * Creates 'numPlayers' number of EEG Threads
	 */
	public void execute(){

		//Create EEG Poll Threads and Run Them
		for(int i = 0; i < numPlayers; i++){
			//GIve the EEG Runnable a Name
			String name = "EEG Poller " + i;
			final EEGRunnable eegPoll = new EEGRunnable(name, this.subject, this.clip); 
		
			//Handler for the EEG Runnable Thread
	        final ScheduledFuture<?> eegPollHandle =
		            scheduler.scheduleAtFixedRate(eegPoll, pollDelay, pollDelay, SECONDS);
	        
	        //Schedule the newly running EEG Poller Runnable
		    scheduler.schedule(new Runnable() {
		                public void run() { eegPollHandle.cancel(true); }
		            }, this.pollDuration, SECONDS);	
		}
	}
	
	public void shutdown(){
		this.scheduler.shutdown();
	}
	


}

