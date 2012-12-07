package ie.soundwave.dcounter.client.api;

import ie.soundwave.dcounter.messaging.Action;
import ie.soundwave.dcounter.messaging.RequestMessage;
import ie.soundwave.dcounter.messaging.ResponseMessage;
import ie.soundwave.dcounter.server.state.NodeState;
import ie.soundwave.dcounter.sockets.TCPClient;

public class TCPCounterService implements CounterService {

	private TCPClient tcpClient;
	private String 	  hostName;
	private int 	  hostPort;
	
	public TCPCounterService(String hostName, int hostPort) 
	{
		this.hostName = hostName;
		this.hostPort = hostPort;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}
	
	private ResponseMessage InvokeRemote(RequestMessage message) 
		throws Exception
	{
		tcpClient = new TCPClient(hostName, hostPort);
		ResponseMessage response = (ResponseMessage)tcpClient.executeRequest(message);
		return response;
	}
	
	@Override
	public ResponseMessage decrement() throws Exception 
	{
		RequestMessage message = new RequestMessage(Action.DECREMENT);
		return InvokeRemote(message);
	}

	@Override
	public ResponseMessage increment() throws Exception
	{
		RequestMessage message = new RequestMessage(Action.INCREMENT);
		return InvokeRemote(message);
	}

	@Override
	public ResponseMessage merge(NodeState nodeState) throws Exception {
		RequestMessage message = new RequestMessage(Action.MERGE, nodeState);
		return InvokeRemote(message);
	}

	@Override
	public ResponseMessage query() throws Exception {
		RequestMessage message = new RequestMessage(Action.QUERY);
		return InvokeRemote(message);
	}
}
