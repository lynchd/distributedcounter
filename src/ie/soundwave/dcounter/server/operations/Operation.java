package ie.soundwave.dcounter.server.operations;
import ie.soundwave.dcounter.server.state.NodeState;

public interface Operation 
{
  public void ApplyOperation(NodeState inputState);
}