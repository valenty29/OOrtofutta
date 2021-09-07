package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public abstract class ObservedModel {
  
  protected Object[] attributes;
  private boolean sample = false;

  private ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();

  static final SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  private Calendar cal = Calendar.getInstance();

  public void setSample(boolean sample) {
    this.sample = sample;
  }

  public boolean isSample() {
    return sample;
  }

  public void setValue(int index, Object value) {
    if (value instanceof Date) {
      cal.setTime((Date) value);

      if(cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND) > 0) {
        attributes[index] = sdtf.format((Date)value);
      } else {
        attributes[index] = sdf.format((Date)value);
      }
    } else {
      attributes[index] = value == null || value instanceof ObservedModel || value instanceof ObservedList ? value : value.toString();
    }

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
      return sdf.parse((String)attributes[index]);
    }
    catch (Exception e) {
      return null;
    }
  }

  public Date getDateTime(int index) {
    try {
      return sdtf.parse((String)attributes[index]);
    } catch (Exception e) {
      return null;
    }
  }
  
  public String getString(int index) {
    return (String)attributes[index];
  }
  
  public void addPropertyChangeListener(PropertyChangeListener l) {
    if (l != null && !listeners.contains(l)) {
      listeners.add(l);
    }
  }
  
  public void removePropertyChangeListener(PropertyChangeListener l) {
    listeners.remove(l);
  }
  
  public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    PropertyChangeEvent pce = new PropertyChangeEvent(this, propertyName, oldValue, newValue);
    firePropertyChanged(pce);
  }
  
  protected void firePropertyChanged(PropertyChangeEvent pce) {
    for (PropertyChangeListener listener : listeners) {
      listener.propertyChange(pce);
    }
  }
  
  protected static boolean equals(Object a, Object b) {
    if (a instanceof ObservedModel) {
      return a == b;
    }
    return a == null && b == null || a != null && a.equals(b);
  }

  public abstract void copyTo(ObservedModel other);

}
