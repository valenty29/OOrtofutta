package it.unina.studenti.oortof.models;

public class ApplicationStatus extends ObservedModel {

  public static final int STATUS_NAVIGATION = 0;
  public static final int STATUS_INSERT = 1;
  public static final int STATUS_UPDATE = 2;
  public static final int STATUS_SEARCH = 3;
  
  public static final int ACTION_NONE = 0;
  public static final int ACTION_ROLLBACK = 1;
  public static final int ACTION_INSERT = 2;
  public static final int ACTION_UPDATE = 3;
  public static final int ACTION_SEARCH = 4;
  public static final int ACTION_COMMIT = 5;
  public static final int ACTION_PRE_DELETE = 6;
  public static final int ACTION_DELETE = 7;
  
  public static final int TAB_PRODOTTI = 0;
  public static final int TAB_CARRELLO = 1;
  public static final int TAB_CLIENTI = 2;
  

  private int status = STATUS_NAVIGATION;
  private int action = ACTION_NONE;
  private int activeTab = TAB_PRODOTTI;
  
  private static final ApplicationStatus instance = new ApplicationStatus();
  
  private ApplicationStatus() { 
  }
  
  public static ApplicationStatus getInstance() {
    return instance;
  }
  
  public void setAction(int action) {
    int oldAction = this.action;
    if (oldAction == action) {
      return;
    }
    this.action = action;
    firePropertyChange("action", oldAction, action);
  }
  
  public int getAction() {
    return action;
  }
  
  public int getActiveTab() {
    return activeTab;
  }
  
  public void setActiveTab(int activeTab) {
    int oldActiveTab = this.activeTab;
    if (oldActiveTab == activeTab) {
      return;
    }
    this.activeTab = activeTab;
    firePropertyChange("activeTab", oldActiveTab, activeTab);
  }
  
  public void setStatus(int status) {
    if (status < STATUS_NAVIGATION || status > STATUS_SEARCH) {
      throw new RuntimeException("Invalid status: " + status);
    }
    int oldStatus = this.status;
    if (oldStatus == status) {
      return;
    }
    this.status = status;
    firePropertyChange("status", oldStatus, status);
  }
  
  public int getStatus() {
    return status;
  }
  
  public boolean isNavigation() {
    return status == STATUS_NAVIGATION;
  }
  
  public boolean isInsert() {
    return status == STATUS_INSERT;
  }
  
  public boolean isUpdate() {
    return status == STATUS_UPDATE;
  }
  
  public boolean isSearch() {
    return status == STATUS_SEARCH;
  }
  
  public String toString() {
    String string = "";
    switch (status) {
      case STATUS_NAVIGATION: string = "NAVIGAZIONE"; break;
      case STATUS_INSERT: string = "INSERIMENTO"; break;
      case STATUS_UPDATE: string = "MODIFICA"; break;
      case STATUS_SEARCH: string = "RICERCA"; break;
    }
    return string;
  }
  
  @Override
  public void copyTo(ObservedModel other) {
    throw new RuntimeException("ApplicationStatus.copyTo forbidden. It is a singleton.");
  }
  
  
}
