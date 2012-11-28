public class Node {
  private string hostName;
  private string port;  
  
  public Node(hostName, port) {
    this.hostName   = hostName;
    this.port       = port;
  }
  
  @Override 
  public void hashCode() {
    return hostName.hashCode() | port.hashCode();
  }
  
  @Override
  public void toString() {
    return "[" + hostName + ":" + port + "]";
  }
}