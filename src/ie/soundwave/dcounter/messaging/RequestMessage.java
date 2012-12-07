package ie.soundwave.dcounter.messaging;

import ie.soundwave.dcounter.server.state.NodeState;

public class RequestMessage 
{
	private Action 	  action;
	private NodeState nodeState;
	
	public RequestMessage(Action action, NodeState nodeState) 
	{
		this.action 	 = action;
		this.nodeState   = nodeState;
	}
	
	public RequestMessage(Action action) 
	{
		this.action 	 = action;
	}
	
	public Action getAction() {
		return action;
	}
	
	public NodeState getNodeState() {
		return nodeState;
	}
	
	public boolean isQuery() {
		return action==Action.QUERY;
	}
}
