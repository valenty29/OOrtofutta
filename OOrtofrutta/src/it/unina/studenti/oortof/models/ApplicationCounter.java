package it.unina.studenti.oortof.models;

public class ApplicationCounter extends ObservedModel {

  private int counter = 0;
  private int limit = 0;
  
  private static final ApplicationCounter instance = new ApplicationCounter();
  
  private ApplicationCounter() { 
  }
  
  public static ApplicationCounter getInstance() {
    return instance;
  }
  

  public void setCounter(int counter) {
    int oldCounter = this.counter;
    if (oldCounter == counter) {
      return;
    }
    this.counter = counter;
    firePropertyChanged("counter", oldCounter, counter);
  }
  
  public int getCounter() {
    return counter;
  }
  
  public void setLimit(int limit) {
    int oldLimit = this.limit;
    if (oldLimit == limit) {
      return;
    }
    this.limit = limit;
    firePropertyChanged("limit", oldLimit, limit);
  }
  
  public int getLimit() {
    return limit;
  }
   
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    if (counter > 0) {
      sb.append(counter);
    }
    if (counter > 0 || limit > 0) {
      sb.append('/');
    }
    if (limit > 0) {
      sb.append(limit);
    }
    return sb.toString();
  }
  
}