package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Carrello extends ObservedModel implements PropertyChangeListener {
  private ObservedList<Lotto> lista = new ObservedList<>("listaCarrello");

  public Carrello() {
    lista.addPropertyChangeListener(this);
  }

  @Override
  public void copyTo(ObservedModel other) {
    lista.copyTo(other);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

  public void add(Lotto lotto) {
    boolean isPresent = lista.stream().filter(lotto1 -> {

      if (lotto1.getId().equals(lotto.getId())) {
        lotto1.setDisponibilita(lotto.getDisponibilita() + lotto.getDisponibilita());
        return true;
      }
      return false;
    }).findFirst().isPresent();

    if (!isPresent) {
      lista.add(lotto);
    }
  }

  public void remove(Lotto lotto) {
    lista.remove(lotto);
  }

  public void clear() {
    lista.clear();
  }

  public ObservedList<Lotto> getLotti() {
    return lista;
  }
}
