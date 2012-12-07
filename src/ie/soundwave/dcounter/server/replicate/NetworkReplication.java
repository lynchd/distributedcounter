package ie.soundwave.dcounter.server.replicate;

import ie.soundwave.dcounter.client.api.CounterService;
import ie.soundwave.dcounter.client.api.TCPCounterService;
import ie.soundwave.dcounter.server.state.NodeState;
import ie.soundwave.dcounter.server.state.Node;

public class NetworkReplication extends ScheduledReplication
{ 
    private NodeState 		state;
    private int 	  		failureCount;
    private CounterService  tcpCounterService;
    
    private int MAX_FAILURES  = 3;
    
    public NetworkReplication(int scheduledSeconds) 
    {
    	super(scheduledSeconds);
    	failureCount = 0;
    }
  
    public void flush() 
    {
    	for(Node node : state.getNodes()) {
    		tcpCounterService = new TCPCounterService(node.getHostName(), node.getPort());
    		tryReplicateState();
    	}
    }  
    
    public void tryReplicateState() {
    	for(failureCount = 0; failureCount < MAX_FAILURES; ++failureCount)
    	{
    		try {
    			tcpCounterService.merge(state);
    		}
    		catch(Exception ex) {
    			logger.error("Failed to replicate state. Failure count is " + failureCount);
    		}
    	}
    }
}