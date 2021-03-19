package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Scontrino extends ObservedModel implements PropertyChangeListener{

  private int id;
  private Cliente cliente;
  private LocalDate dataOrario;
  private float prezzoTotale;
  private List<Acquisto> acquisti;

  public static final int ID = 0;
  public static final int CLIENTE = 1;
  public static final int DATA_ORARIO = 2;
  public static final int PREZZO_TOTALE = 3;
  public static final int ACQUISTI = 4;

  public Scontrino(){

  }

  public Scontrino(int id, Cliente cliente, LocalDate dataOrario, float prezzoTotale, List<Acquisto> acquisti) {
    this.id = id;
    this.cliente = cliente;
    this.dataOrario = dataOrario;
    this.prezzoTotale = prezzoTotale;
    this.acquisti = acquisti;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    int oldId = this.id;
    this.id = id;
    firePropertyChanged("id", oldId, id);
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    Cliente oldCliente = this.cliente;
    this.cliente = cliente;
    oldCliente.removePropertyChangeListener(this);
    firePropertyChanged("cliente", oldCliente, cliente);
  }

  public LocalDate getDataOrario() {
    return dataOrario;
  }

  public void setDataOrario(LocalDate dataOrario) {
    LocalDate oldDataOrario = this.dataOrario;
    this.dataOrario = dataOrario;
    firePropertyChanged("dataOrario", oldDataOrario, dataOrario);
  }

  public float getPrezzoTotale() {
    return prezzoTotale;
  }

  public void setPrezzoTotale(float prezzoTotale) {
    float oldPrezzoTotale = this.prezzoTotale;
    this.prezzoTotale = prezzoTotale;
    firePropertyChanged("prezzoTotale", oldPrezzoTotale, prezzoTotale);
  }
  
  public List<Acquisto> getAcquisti() {
    return acquisti;
  }

  public void setAcquisti(List<Acquisto> acquisti) {
    for (Acquisto acquisto : acquisti) {
      acquisto.addPropertyChangeListener(this);       
    }
    List<Acquisto> oldAcquisti = this.acquisti;
    this.acquisti = acquisti;
    firePropertyChanged("acquisti", oldAcquisti, acquisti);
  }
  
  public void addAcquisto(Acquisto acquisto) {
    acquisti.add(acquisto);
    firePropertyChanged("acquisti", null, acquisto);
    acquisto.addPropertyChangeListener(this);
  }

  public void addAcquisto(int index, Acquisto acquisto) {
    acquisti.add(index, acquisto);
    firePropertyChanged("acquisti", index, acquisto);
    acquisto.addPropertyChangeListener(this);
  }
  
  public Acquisto getAcquistoAt(int index) {
    return acquisti.get(index);
  }
  
  public void removeAcquisto(int index) {
    acquisti.remove(index);
    Acquisto removedAcquisto = acquisti.remove(index);
    removedAcquisto.removePropertyChangeListener(this);
    firePropertyChanged("acquisti", removedAcquisto, index);
  }
  
  public void removeAcquisto(Acquisto acquisto) {
    int toRemoveIndex = acquisti.indexOf(acquisto);
    if (toRemoveIndex >= 0) {
      removeAcquisto(toRemoveIndex);
    }
  }
  
  public int getAcquistiSize() {
    return acquisti.size();
  }

  public void copyTo(Scontrino scontrino) {
    scontrino.setId(getId());
    scontrino.setCliente(getCliente());
    scontrino.setDataOrario(getDataOrario());
    scontrino.setPrezzoTotale(getPrezzoTotale());
    scontrino.getAcquisti().clear();

    for (Acquisto acquisto: ((List<Acquisto>)attributes[ACQUISTI])) {
      Acquisto newAcquisto = new Acquisto();
      acquisto.copyTo(newAcquisto);
      ((List<Acquisto>)attributes[ACQUISTI]).add(newAcquisto);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    
  }

  
  
}
