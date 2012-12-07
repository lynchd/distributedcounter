package ie.soundwave.dcounter.server.replicate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class DiskReplication extends ScheduledReplication
{ 
  private String fileLocation;

  public DiskReplication(int millisecondWait, 
		  				 String fileLocation)
  {
	  super(millisecondWait);
      this.fileLocation = fileLocation;
  }
  
  public void flush() 
  {
    try
    {
      OutputStream file   = new FileOutputStream(fileLocation);
      ObjectOutput output = new ObjectOutputStream(file);
      try
      {
        output.writeObject(state);
      }
      finally
      {
        output.close();
      }
    }  
    catch(IOException ex)
    {
      logger.error("Cannot flush state to disk!", ex);
    }
  
  }
}