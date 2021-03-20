package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ObservableList<E> extends ArrayList<E> implements Observable, Cloneable {
	/**
	 * 
	 */
	
	List oldList = new ArrayList<Cliente>();
	private static final long serialVersionUID = 1L;
	List<PropertyChangeListener> observers = new ArrayList<>();
	private String name;
	public ObservableList(String name) {
		this.name = name;
	}
	
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (PropertyChangeListener observer: observers) {
			observer.propertyChange(new PropertyChangeEvent(this, name, oldList , this));
		}
	}

	@Override
	public void addObserver(PropertyChangeListener observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}
	
	@Override
	public void clear() {
		notifyObservers();
		super.clear();
	}
	
	 @Override
	 public boolean add(E element) {
		 oldList.add(element);
		 boolean success = super.add(element);
		 notifyObservers();
		 return success;
	 }
	 
	 @Override
	 public Object clone() {
		return name;
		 
	 }
	 
	
}
