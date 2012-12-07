package ie.soundwave.dcounter.server.operations;

import ie.soundwave.dcounter.server.state.NodeState;

public class Merge implements Operation {
	private NodeState state;
	
	@Override
	public void ApplyOperation(NodeState inputState) {
		inputState.merge(state);
	}
}