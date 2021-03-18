package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public abstract class ObservedModel {
  
  Object[] attributes;
  boolean sample = false;

  private ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();
  
  public void setSample(boolean sample) {
    this.sample = sample;
  }
  
  public boolean isSample() {
    return sample;
  }
  
  public void setValue(int index, Object value) {
    attributes[index] = value == null || value instanceof ObservedModel || value instanceof List ? value : value.toString();
  }
  
  public Boolean getBoolean(int index) {
    return attributes[index] != null ? Boolean.valueOf((String)attributes[index]) : null;
  }
  
  public boolean isBoolean(int index) {
    return Boolean.valueOf((String)attributes[index]);
  }
  
  public Integer getInteger(int index) {
    try {
      return Integer.valueOf((String)attributes[index]);
    }
    catch (Exception e) {
      return null;
    }
  }
  
  public Float getFloat(int index) {
    try {
      return Float.valueOf((String)attributes[index]);
    }
    catch (Exception e) {
      return null;
    }
  }
  
  public String getString(int index) {
    return (String)attributes[index];
  }
  
  public void addPropertyChangeListener(PropertyChangeListener l) {
    if (!listeners.contains(l)) {
      listeners.add(l);
    }
  }
  
  //Aggiungere gestione tipo Date
  
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
  
  protected static boolean equals(Object a, Object b) {
    return a == null && b == null || a != null && a.equals(b);
  }


}
