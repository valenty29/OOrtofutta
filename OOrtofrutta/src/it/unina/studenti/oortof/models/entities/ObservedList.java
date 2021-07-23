package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ObservedList<E extends ObservedModel> extends ObservedModel implements List<E>, PropertyChangeListener {

  List<E> list = new ArrayList<E>();
  String name;
  
  public ObservedList(String name) {
    this.name = name;
  }
  
  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return toArray(a);
  }

  @Override
  public boolean add(E e) {
    boolean added = list.add(e);
    if (added) {
      firePropertyChange(name, null, e);
      if (e != null) {
        e.addPropertyChangeListener(this);
      }
    }
    return added;
  }

  @Override
  public boolean remove(Object o) {
    int toRemoveIndex = list.indexOf(o);
    if (toRemoveIndex >= 0) {
      remove(toRemoveIndex);
      return true;
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    throw new RuntimeException("containsAll not implemented yet");
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new RuntimeException("containsAll not implemented yet");
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new RuntimeException("containsAll not implemented yet");
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new RuntimeException("containsAll not implemented yet");
  }

  @Override
  public void clear() {
    if (list.size() == 0) {
      return;
    }
    ArrayList<ObservedModel> oldList = new ArrayList<ObservedModel>(list);
    try {
      list.clear();
    } catch (Exception e ){
      System.out.println("wtf");
    }

    firePropertyChange(name, oldList, list);
  }

  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Override
  public E set(int index, E element) {
    List<E> oldList = list;
    E old = list.set(index, element);
    if (old != element) {
      firePropertyChange("listSet", oldList, list);
    }
    return old;
  }

  @Override
  public void add(int index, E element) {
    List<E> oldList = list;
    list.add(index, element);
    firePropertyChange(name, oldList, list);
    element.addPropertyChangeListener(this);
  }

  @Override
  public E remove(int index) {
    E om = list.remove(index);
    if (om != null) {
      om.removePropertyChangeListener(this);
      firePropertyChange(name, om, index);
    }
    return om;
  }

  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }

  @Override
  public ListIterator<E> listIterator() {
    return list.listIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return list.listIterator(index);
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }

  @Override
  public void copyTo(ObservedModel other) {
    for (int i = 0; i < list.size(); i++) {
      ObservedModel om1 = list.get(i);
      ObservedModel om2 = i < ((ObservedList)other).size() ? ((ObservedList)other).get(i) : null;
      if (om1 != null && om2 != null) {
        om1.copyTo(om2);
      }
      else if (om1 == null && om2 != null) {
        ((ObservedList)other).set(i, null);
      }
      else if (om1 != null && om2 == null) {
        ObservedModel newOm = null;
        try {
          newOm = om1.getClass().newInstance();
        }
        catch (Exception ex) {
          throw new RuntimeException("Exception creating class " + om1.getClass().getName());
        }
        om1.copyTo(newOm);
        if (i < ((ObservedList)other).size()) {
          ((ObservedList)other).set(i, newOm);
        }
        else {
          ((ObservedList)other).add(i, newOm);
        }
      }    
    }
    for (int i = ((ObservedList)other).size() - 1; i >= list.size(); i--) {
      ((ObservedList)other).remove(i);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

}
