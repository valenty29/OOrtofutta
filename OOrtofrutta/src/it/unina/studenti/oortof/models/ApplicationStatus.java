package it.unina.studenti.oortof.models;

public class ApplicationStatus extends ObservedModel {

  public static final int NAVIGATION = 0;
  public static final int INSERT = 1;
  public static final int UPDATE = 2;
  public static final int SEARCH = 3;

  private int status = NAVIGATION;
  
  private static final ApplicationStatus instance = new ApplicationStatus();
  
  private ApplicationStatus() { 
  }
  
  public static ApplicationStatus getInstance() {
    return instance;
  }
  
  public void setStatus(int status) {
    if (status < NAVIGATION || status > SEARCH) {
      throw new RuntimeException("Invalid status: " + status);
    }
    int oldStatus = this.status;
    if (oldStatus == status) {
      return;
    }
    this.status = status;
    firePropertyChanged("status", oldStatus, status);
  }
  
  public int getStatus() {
    return status;
  }
  
  public boolean isNavigation() {
    return status == NAVIGATION;
  }
  
  public boolean isInsert() {
    return status == INSERT;
  }
  
  public boolean isUpdate() {
    return status == UPDATE;
  }
  
  public boolean isSearch() {
    return status == SEARCH;
  }
  
  public String toString() {
    String string = "";
    switch (status) {
      case NAVIGATION: string = "NAVIGAZIONE"; break;
      case INSERT: string = "INSERIMENTO"; break;
      case UPDATE: string = "MODIFICA"; break;
      case SEARCH: string = "RICERCA"; break;
    }
    return string;
  }
  
  
}
