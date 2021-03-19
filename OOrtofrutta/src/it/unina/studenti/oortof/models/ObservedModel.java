package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
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
  
  public Date getDate(int index) {
    try {
      return new Date(Date.parse((String)attributes[index]));
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
  
  
  public void removePropertyChangeListener(PropertyChangeListener l) {
    listeners.remove(l);
  }
  
  protected void firePropertyChanged(String propertyName, Object oldValue, Object newValue) {
    PropertyChangeEvent pce = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
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


  public void copyTo(ObservedModel other) {
    for (int i = 0; i < attributes.length; i++) {
      if (attributes[i] == null || attributes[i] instanceof String) {
        other.attributes[i] = attributes[i];
      }
      else if (attributes[i] instanceof ObservedModel) {
        if (other.attributes[i] == null) {
          try {
            other.attributes[i] = (ObservedModel)attributes[i].getClass().newInstance();
          }
          catch (Exception e) {
            throw new RuntimeException("Exception creating ObservedModel class", e);
          }
        }
        ((ObservedModel)attributes[i]).copyTo((ObservedModel)other.attributes[i]);
      }
      else {
        List<ObservedModel> myList = (List<ObservedModel>)attributes[i];
        List<ObservedModel> otherList = (List<ObservedModel>)other.attributes[i];
        otherList.clear();
        for (ObservedModel om : myList) {
          try {
            ObservedModel newOm = om.getClass().newInstance();
            om.copyTo(newOm);
            otherList.add(newOm);
          }
          catch (Exception e) {
            throw new RuntimeException("Exception creating ObservedModel class", e);
          }
        }
      }
    }
  }
}
