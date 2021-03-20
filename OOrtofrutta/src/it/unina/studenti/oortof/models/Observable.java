package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeListener;

public interface Observable {
	public void notifyObservers();
	public void addObserver(PropertyChangeListener observer);
}
