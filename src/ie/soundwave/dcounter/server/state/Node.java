package ie.soundwave.dcounter.server.state;

public class Node {
  private String hostName;
  private int port;
  
  public Node(String hostName, int port) {
    this.hostName = hostName;
    this.port = port;
  }
  
  public int getPort() {
	  return port;
  }
  
  public String getHostName() {
	  return hostName;
  }
  
  @Override
  public String toString() {
    return "[" + hostName + ":" + port + "]";
  }
}