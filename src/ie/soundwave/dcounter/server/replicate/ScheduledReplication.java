package ie.soundwave.dcounter.server.replicate;

import org.apache.log4j.Logger;
import ie.soundwave.dcounter.server.state.NodeState;

public abstract class ScheduledReplication extends Thread implements Replication
{
  protected static Logger logger = Logger.getLogger(ScheduledReplication.class);	
  
  private static final int ONE_SECOND_MS=1000;
  
  protected NodeState state;
  private   int       intervalSeconds;
  private   int       secondsUntilFlush;
  private   boolean	  isRunning;
 
  public ScheduledReplication(int intervalSeconds) 
  {
    this.intervalSeconds = intervalSeconds;
    secondsUntilFlush = intervalSeconds;
    isRunning = false;
  }
  
  public boolean isRunning() {
    return isRunning;
  }
  
  public void startReplication() 
  {
    start();
  }
  
  public void stopReplication() 
  {
    isRunning = false;
  }

  public abstract void flush();
  
  @Override
  public void setState(NodeState state) {
    this.state = state;
  }
  
  public void run() {
    isRunning = true;  
    while (isRunning) {
      waitThenFlush();
    }    
  }
  
  public void waitThenFlush() {
    while(secondsUntilFlush>0) {
      try {
        Thread.sleep(intervalSeconds*ONE_SECOND_MS);
      }
      catch(Exception ex) {
          // Ignoring this
      }
    }
    flush();
  }
}