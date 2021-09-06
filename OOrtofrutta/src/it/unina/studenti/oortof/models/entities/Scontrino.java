package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Scontrino extends ObservedModel implements PropertyChangeListener{

  public static final int ID = 0;
  public static final int DATA_ORARIO = 1;
  public static final int CLIENTE = 2;
  public static final int ACQUISTI = 3;
  public static final int TOTALE = 4;


  public Scontrino() {
    attributes = new Object[5];
    //setCliente(new Cliente());
    attributes[ACQUISTI] = new ObservedList<Acquisto>("acquisti");
    addAcquisto(new Acquisto());
  }

  public Scontrino (int id, Date dataOrario, Float totale) {
    this();
    setValue(ID, id);
    setValue(DATA_ORARIO, dataOrario);
    setValue(TOTALE, totale);
  }

  public Scontrino(int id, Date dataOrario, ObservedList<Acquisto> acquisti, Cliente cliente, Float totale) {
    this();
    setValue(ID, id);
    setValue(CLIENTE, cliente);
    setValue(DATA_ORARIO, dataOrario);
    setValue(ACQUISTI, acquisti);
    setValue(TOTALE, totale);
  }

  public Integer getId() {
    return getInteger(ID);
  }

  public void setId(Integer id) {
    Integer oldId = getId();
    if (equals(oldId, id)) {
      return;
    }
    setValue(ID, id);
    firePropertyChange("id", oldId, id);
  }

  public Float getTotale() { return getFloat(TOTALE); }

  public void setTotale(Float totale) {
    Float oldTotale = getTotale();
    if (equals(oldTotale, totale)) {
      return;
    }
    setValue(TOTALE, totale);
    firePropertyChange("totale", oldTotale, totale);
  }

  public Cliente getCliente() {
    return (Cliente)attributes[CLIENTE];
  }

  public void setCliente(Cliente cliente) {
    Cliente oldCliente = getCliente();
    if (equals(oldCliente, cliente)) {
      return;
    }
    if (oldCliente != null) {
    	oldCliente.removePropertyChangeListener(this);
    }
    
    setValue(CLIENTE, cliente);
    cliente.addPropertyChangeListener(this);
    firePropertyChange("cliente", oldCliente, cliente);
  }

  public Date getDataOrario() {
    return getDateTime(DATA_ORARIO);
  }
  

  public void setDataOrario(Date dataOrario) {
    Date oldDataOrario = getDataOrario();
    if (equals(oldDataOrario, dataOrario)) {
      return;
    }
    setValue(DATA_ORARIO, dataOrario);
    firePropertyChange("dataOrario", oldDataOrario, dataOrario);
  }
  
  @SuppressWarnings("unchecked")
  public ObservedList<Acquisto> getAcquisti() {
    return (ObservedList<Acquisto>)attributes[ACQUISTI];
  }


  @SuppressWarnings("unchecked")
  public void setAcquisti(ObservedList<Acquisto> acquisti) {
    if (acquisti == attributes[ACQUISTI]) {
      return;
    }
    ObservedList<Acquisto> oldAcquisti = (ObservedList<Acquisto>)attributes[ACQUISTI];
    oldAcquisti.removePropertyChangeListener(this);
    attributes[ACQUISTI] = acquisti;
    acquisti.addPropertyChangeListener(this);
    firePropertyChange("acquisti", oldAcquisti, acquisti);
  }
  
  @SuppressWarnings("unchecked")
  public void addAcquisto(Acquisto acquisto) {
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).add(acquisto);
  }

  @SuppressWarnings("unchecked")
  public void addAcquisto(int index, Acquisto acquisto) {
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).add(index, acquisto);
  }
  
  @SuppressWarnings("unchecked")
  public Acquisto getAcquistoAt(int index) {
    return ((ObservedList<Acquisto>)attributes[ACQUISTI]).get(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeAcquisto(int index) {
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).remove(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeAcquisto(Acquisto acquisto) {
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).remove(acquisto);
  }
  
  @SuppressWarnings("unchecked")
  public void clearAcquisti() {
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).clear();
  }
  
  @SuppressWarnings("unchecked")
  public int getAcquistiSize() {
    return ((ObservedList<Acquisto>)attributes[ACQUISTI]).size();
  }
  @SuppressWarnings("unchecked")
  public void copyTo(ObservedModel scontrino) {
    ((Scontrino)scontrino).setId(getId());
    /*if (getCliente() != null && ((Scontrino)scontrino).getCliente() != null) {
      getCliente().copyTo(((Scontrino)scontrino).getCliente());
    }
    else if (getCliente() == null && ((Scontrino)scontrino).getCliente() != null) {
      ((Scontrino)scontrino).getCliente().removePropertyChangeListener((Scontrino)scontrino);
      ((Scontrino)scontrino).setCliente(null);
    }
    else if (getCliente() != null && ((Scontrino)scontrino).getCliente() == null) {
      Cliente newCliente = new Cliente();
      getCliente().copyTo(newCliente);
      ((Scontrino)scontrino).setCliente(newCliente);
    }*/
    ((Scontrino)scontrino).setDataOrario(getDataOrario());
    ((ObservedList<Acquisto>)attributes[ACQUISTI]).copyTo(((Scontrino)scontrino).getAcquisti());
    ((Scontrino)scontrino).setTotale(getTotale());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
}
