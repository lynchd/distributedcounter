package ie.soundwave.dcounter.server.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NodeState {
  private Map<Node, PNCounter> states;
  private Node          	   thisNode;
  
  public NodeState(Node thisNode) 
  {
    states = new HashMap<Node, PNCounter>();
    this.thisNode = thisNode;
    states.put(thisNode, new PNCounter());
  }
  
  public void increment() {
    getLocalPNCounter().increment();  
  }
  
  public void decrement() {
	getLocalPNCounter().decrement();  
  }
  
  public Set<Node> getNodes() {
	  return states.keySet();
  }
  
  public int value() {
    int value = 0;
    for(PNCounter counter : states.values())
    {
      value += counter.getValue();
    }
    return value;
  }
   
  private PNCounter getLocalPNCounter() 
  {
    return states.get(thisNode);
  }
  
  public Map<Node, PNCounter> getCounters() {
	  return states;
  }

  public void merge(NodeState other) {
	Map<Node, PNCounter> otherCounters  = other.getCounters(); 
    for(Node node : states.keySet()) {
        PNCounter otherCounter = otherCounters.get(node);
        if (otherCounter==null) {
          continue;
        }
        PNCounter thisCopy  = states.get(node);
        PNCounter otherCopy = otherCounters.get(node);
        PNCounter merged    = thisCopy.merge(otherCopy);
        states.put(node, merged);
    }
    for(Node node : otherCounters.keySet()) {
        PNCounter here = states.get(node);
        if (here!=null) {
          continue;
        }
        states.put(node, otherCounters.get(node));
    }
  }
}