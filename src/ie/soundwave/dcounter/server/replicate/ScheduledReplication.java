public abstract class ScheduledReplication extends Thread implements Replicate
{
  private State   state;
  private int     intervalSeconds;
  private int     secondsUntilFlush;
  private boolean isRunning;
  
  private static int ONE_SECOND = 1000;
  
  public ScheduledReplication(int intervalSeconds) 
  {
    this.intervalSeconds = intervalSeconds;
    secondsUntilFlush = intervalSeconds;
    isRunning = false;
  }
  
  public void isRunning() {
    return isRunning;
  }
  
  public abstract void startReplication() {
    start();
  }
  
  public abstract void stopReplication() {
    stop();
    isRunning = false;
  }

  public abstract void flush();
  
  public void setState(State state) {
    this.state = state;
  }
  
  public void run() {
    isRunning = true;  
    while (isRunning) {
      waitThenFlush()
    }    
  }
  
  public void waitThenFlush() {
    while(secondsUntilFlush>0) {
      try {
        Thread.sleep(ONE_SECOND);
      }
      catch(Exception ex) {
          // Ignoring this
      }
    }
    flush();
  }
}