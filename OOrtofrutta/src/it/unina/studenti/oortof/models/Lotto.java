package it.unina.studenti.oortof.models;

import java.util.Date;


public class Lotto extends ObservedModel {
  
  public static final int ID = 0;                 //Integer
  public static final int COD_LOTTO = 1;          //String
  public static final int SCADENZA = 2;           //Date
  public static final int DISPONIBILITA = 3;      //Float
  public static final int DATA_PRODUZIONE = 4;    //Date
  public static final int COD_PAESE_ORIGINE = 5;  //String

  
  public Lotto() {
    attributes = new Object[6];
  }

  public Lotto(Integer id, String codLotto, Date scadenza, Float disponibilita, Date dataProduzione, String codPaeseOrigine) {
    this();
    setValue(ID, id);
    setValue(COD_LOTTO, codLotto);
    setValue(SCADENZA, scadenza);
    setValue(DISPONIBILITA, disponibilita);
    setValue(DATA_PRODUZIONE, dataProduzione);
    setValue(COD_PAESE_ORIGINE, codPaeseOrigine);
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
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCodLotto() {
    return getString(COD_LOTTO);
  }

  public void setCodLotto(String codLotto) {
    String oldCodLotto = getCodLotto();
    if (equals(oldCodLotto, codLotto)) {
      return;
    }
    setValue(COD_LOTTO, codLotto);
    firePropertyChanged("colLotto", oldCodLotto, codLotto);
  }

  public Date getScadenza() {
    return getDate(SCADENZA);
  }

  public void setScadenza(Date scadenza) {
    Date oldScadenza = getScadenza();
    if (equals(oldScadenza, scadenza)) {
      return;
    }
    setValue(SCADENZA, scadenza);
    firePropertyChanged("scadenza", oldScadenza, scadenza);
  }

  public Float getDisponibilita() {
    return getFloat(DISPONIBILITA);
  }

  public void setDisponibilita(Float disponibilita) {
    Float oldDisponibilita = getDisponibilita();
    if (equals(oldDisponibilita, disponibilita)) {
      return;
    }
    setValue(DISPONIBILITA, disponibilita);
    firePropertyChanged("disponibilita", oldDisponibilita, disponibilita);
  }

  public Date getDataProduzione() {
    return getDate(DATA_PRODUZIONE);
  }

  public void setDataProduzione(Date dataProduzione) {
    Date oldDataProduzione = getDataProduzione();
    if (equals(oldDataProduzione, dataProduzione)) {
      return;
    }
    setValue(DATA_PRODUZIONE, dataProduzione);
    firePropertyChanged("dataProduzione", oldDataProduzione, dataProduzione);
  }

  public String getCodPaeseOrigine() {
    return getString(COD_PAESE_ORIGINE);
  }

  public void setCodPaeseOrigine(String codPaeseOrigine) {
    String oldCodPaeseOrigine = getCodPaeseOrigine();
    if (equals(oldCodPaeseOrigine, codPaeseOrigine)) {
      return;
    }
    setValue(COD_PAESE_ORIGINE, codPaeseOrigine);
    firePropertyChanged("codPaeseOrigine", oldCodPaeseOrigine, codPaeseOrigine);
  }
  
  public String toString() {
    return getCodLotto();
  }
  
  public void copyTo(ObservedModel lotto) {
    ((Lotto)lotto).setId(getId());
    ((Lotto)lotto).setCodLotto(getCodLotto());
    ((Lotto)lotto).setScadenza(getScadenza());
    ((Lotto)lotto).setDisponibilita(getDisponibilita());
    ((Lotto)lotto).setDataProduzione(getDataProduzione());;
    ((Lotto)lotto).setCodPaeseOrigine(getCodPaeseOrigine());
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Lotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (getId() > 0 && ((Lotto)other).getId() > 0) {
      return getId() == ((Lotto)other).getId();
    }
    return getCodLotto().equals(((Lotto)other).getCodLotto());
  }

}
