public interface Replicate() {
  public void startReplication();
  public void stopReplication();
  public void setState(State state);
  public void flush();
}