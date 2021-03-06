package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public abstract class ObservedModel {

  private ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();
  
  public void addPropertyChangeListener(PropertyChangeListener l) {
    if (!listeners.contains(l)) {
      listeners.add(l);
    }
  }
  
  public void removePropertyChangeListener(PropertyChangeListener l) {
    listeners.remove(l);
  }
  
  protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
    PropertyChangeEvent pce = new PropertyChangeEvent(this, "status", oldValue, newValue);
    firePropertyChanged(pce);
  }
  
  protected void firePropertyChanged(PropertyChangeEvent pce) {
    for (PropertyChangeListener listener : listeners) {
      listener.propertyChange(pce);
    }
  }
}
