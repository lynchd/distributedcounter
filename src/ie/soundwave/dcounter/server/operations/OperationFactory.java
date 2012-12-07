package ie.soundwave.dcounter.server.operations;

import ie.soundwave.dcounter.messaging.Action;
import ie.soundwave.dcounter.messaging.RequestMessage;

public class OperationFactory 
{
	public Operation getOperation(RequestMessage message) throws Exception {
		Operation newOperation;
		if (message.getAction().equals(Action.INCREMENT)) {
			newOperation = new Increment();
		}
		else if (message.getAction().equals(Action.DECREMENT)) {
			newOperation = new Decrement();
		}
		else if (message.getAction().equals(Action.MERGE)) {
			newOperation = new Merge();
		}
		else {
			throw new Exception("Message does not yield a recognized operation");
		}
		return newOperation;
	}

}
