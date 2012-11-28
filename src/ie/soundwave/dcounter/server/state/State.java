public void State() {
  private Map<Node, PNCounter> states;
  private NodeDetails          thisNode;
  
  public StateVector(Node nodeDetails) 
  {
    states = new HashMap<Node, PNCounter>();
    thisNode = nodeDetails;
    states.add(thisNode, new PNCounter());
  }
  
  private void increment() {
    getLocalPNCounter().increment();  
  }
  
  private void decrement() {
    getLocalPNCounter().decrement();  
  }
  
  private void value() {
    int value = 0;
    for(PNCounter counter : states.getValues() {
      value += counter.getValue();
    }
  }
  
  public Node getNeighbourNode() {
    // do this!!!
  }
  
  private void getLocalPNCounter() {
    return states.get(thisNode);
  }

  private void merge(Hashmap<Node, PNCounter>() other) {
    for(Node node : states.keys()) {
        PNCounter other = other.get(node);
        if (other==null) {
          continue;
        }
        PNCounter thisCopy  = states.get(node);
        PNCounter otherCopy = other.get(node);
        PNCounter merged    = thisCopy.merge(otherCopy);
        states.put(node, merged);
    }
    for(Node node : other.keys()) {
        PNCounter here = states.get(node);
        if (here!=null) {
          continue;
        }
        states.put(node, other.get(node));
    }
  }
}