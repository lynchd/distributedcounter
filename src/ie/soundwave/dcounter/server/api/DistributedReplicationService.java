package ie.soundwave.dcounter.server.api;

public interface DistributedReplicationService 
{
  public boolean replicate(State state);
}
