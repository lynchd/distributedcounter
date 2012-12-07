package ie.soundwave.dcounter.server.replicate;

import ie.soundwave.dcounter.server.state.NodeState;

public interface Replication
{
  public void startReplication();
  public void stopReplication();
  public void setState(NodeState state);
  public void flush();
}