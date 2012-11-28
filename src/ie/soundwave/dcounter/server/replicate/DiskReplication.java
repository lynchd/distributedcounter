public class DiskReplicate extends Thread implements Replicate 
{ 
  private string fileLocation;

  public DiskReplicate(fileLocation) {
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
      logger.Error("Cannot flush state to disk!", ex);
    }
  
  }
}