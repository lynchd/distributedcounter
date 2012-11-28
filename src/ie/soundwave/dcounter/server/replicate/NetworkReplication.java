public class NetworkReplicate extends DiskReplicate
{ 
    // May not want to do this but instead do some best effort
    // replication - if we fail then we just assume we will get
    // it on next shot..... this is chatty on the network though
    private StateNode neighbourNode;
    // After some failures, we might want to rotate
    // neighbour nodes
    private int failureCount;

    public NetworkReplicate(StateNode neighbourNode) 
    {
        this.neighbourNode = neighbourNode;
    }
  
    public void flush() 
    {
        // Load up a client from the above and shoot it off!
    }
    
   

  
  
}