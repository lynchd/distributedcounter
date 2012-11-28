public class PNCounter 
{
  private int increments;
  private int decrements;
  
  public PNCounter() {
    increments = 0;
    decrements = 0;
  }
  
  public PNCounter(int increments, int decrements) {
    this.increments = increments;
    this.decrements = decrements;
  }
  
  public void increment() {
    increments+=1;
  }
  
  public void decrement() {
    decrements+=1;
  }
  
  public void getValue() {
    return (increments-decrements);
  }
  
  public void getIncrementCount() {
    return increments;
  }
  
  public void getDecrementCount() {
    return decrements;
  }
  
  public PNCounter merge(PNCounter other) {
    if (other==null) {
      return this;
    }
    int newIncrements = Math.max(increments, other.getIncrementCount());
    int newDecrements = Math.max(decrements, other.getDecrementCount());
    return new PNCounter(newIncrements, newDecrements);
  }
}