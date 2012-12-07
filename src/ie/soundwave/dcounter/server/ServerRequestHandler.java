package ie.soundwave.dcounter.server;

import ie.soundwave.dcounter.messaging.ActionResult;
import ie.soundwave.dcounter.messaging.RequestMessage;
import ie.soundwave.dcounter.messaging.ResponseMessage;
import ie.soundwave.dcounter.server.operations.Operation;
import ie.soundwave.dcounter.server.operations.OperationFactory;
import ie.soundwave.dcounter.server.state.NodeState;
import ie.soundwave.dcounter.sockets.RequestHandler;

import java.util.concurrent.BlockingQueue;

public class ServerRequestHandler implements RequestHandler
{
	private BlockingQueue<Operation> operationQueue;
	private NodeState nodeState;
	private OperationFactory operationFactory;
	
	public ServerRequestHandler(BlockingQueue<Operation> operationQueue,
							    NodeState nodeState) 
	{
		this.nodeState = nodeState;
		this.operationQueue = operationQueue;
		this.operationFactory = new OperationFactory();
	}

	@Override
	public Object handleRequest(Object message) {
		RequestMessage request = (RequestMessage)message;
		ResponseMessage response;
		if(request.isQuery()) {
			response = new ResponseMessage(ActionResult.SUCCESS, "", nodeState.value());
		}
		else {
			try {
				Operation operation = operationFactory.getOperation(request);
				operationQueue.put(operation);
				response = new ResponseMessage(ActionResult.SUCCESS, "", -1);
			}
			catch(Exception ex) {
				response = new ResponseMessage(ActionResult.FAIL, ex.getMessage(), -1);
			}
		}
		return response;
	}
}
