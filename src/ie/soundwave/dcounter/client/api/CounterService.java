package ie.soundwave.dcounter.client.api;

import ie.soundwave.dcounter.messaging.ResponseMessage;
import ie.soundwave.dcounter.server.state.NodeState;

public interface CounterService 
{
	public ResponseMessage increment()
		throws Exception;
	
	public ResponseMessage decrement()
		throws Exception;
	
	public ResponseMessage query()
		throws Exception;
	
	public ResponseMessage merge(NodeState nodeState)
		throws Exception;
}
