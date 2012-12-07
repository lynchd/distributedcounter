package ie.soundwave.dcounter.server.operations;

import ie.soundwave.dcounter.server.state.NodeState;

public class Decrement implements Operation {
	@Override
	public void ApplyOperation(NodeState inputState) {
		inputState.decrement();
	}
}