package ie.soundwave.dcounter.server.operations;

import ie.soundwave.dcounter.server.state.NodeState;

public class Increment implements Operation {
	
	@Override
	public void ApplyOperation(NodeState inputState) {
		inputState.increment();
	}
}