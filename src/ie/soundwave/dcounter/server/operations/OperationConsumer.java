package ie.soundwave.dcounter.server.operations;

import ie.soundwave.dcounter.server.state.NodeState;

import java.util.concurrent.BlockingQueue;

public class OperationConsumer extends Thread {
	private boolean isRunning 		 = false;
	private BlockingQueue<Operation> operationsQueue;
	private NodeState				 state;
	
	public OperationConsumer(BlockingQueue<Operation> operationsQueue,
						     NodeState state) 
	{
		this.operationsQueue = operationsQueue;
		this.state 		     = state;
	}
	
	public void startConsuming() {
		isRunning = true;
		start();
	}
	
	public void stopConsuming() {
		isRunning = false;
	}
	
	public void run() {
		while(isRunning) {
			try {
				Operation currentOperation = operationsQueue.take();
				currentOperation.ApplyOperation(state);
			}
			catch(InterruptedException ex) {
				// log this
			}
		}
	}
	
}
