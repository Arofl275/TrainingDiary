package ap.trainingdiary.screencapture;

public class Delayer{
	private final long lDelay;
	private long nextTime;
	public Delayer(long delay){
		this.lDelay = delay;
		this.nextTime = System.currentTimeMillis();
	}
	public long getRemainingTime(){
		long remainingTime = nextTime - System.currentTimeMillis();
		return (remainingTime > 0L) ? remainingTime : 0L;
	}
	public void reset(){
		nextTime = System.currentTimeMillis() + lDelay;
	}
	public void delay(){
		try{
			Thread.sleep(getRemainingTime());
		}
		catch(IllegalArgumentException e){
			// will never throw
		}
		catch(InterruptedException e){
			// will never throw
		}
	}
}
