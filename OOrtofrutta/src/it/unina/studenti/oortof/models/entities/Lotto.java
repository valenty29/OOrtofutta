package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import it.unina.studenti.oortof.models.entities.prodotti.ProdottoCommon;


public class Lotto extends ObservedModel implements PropertyChangeListener {
  
  public static final int ID = 0;                 //Integer
  public static final int COD_LOTTO = 1;          //String
  public static final int SCADENZA = 2;           //Date
  public static final int DISPONIBILITA = 3;      //Float
  public static final int DATA_PRODUZIONE = 4;    //Date
  public static final int COD_PAESE_ORIGINE = 5;  //String
  public static final int DATA_MUNGITURA = 6;  //Date
  public static final int PRODOTTO_COMMON = 7;


  
  public Lotto() {
    attributes = new Object[8];
  }

  public Lotto(Integer id, ProdottoCommon prodottoCommon, String codLotto, Date scadenza, Float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
    this();
    setValue(ID, id);
    setValue(PRODOTTO_COMMON, prodottoCommon);
    setValue(COD_LOTTO, codLotto);
    setValue(SCADENZA, scadenza);
    setValue(DISPONIBILITA, disponibilita);
    setValue(DATA_PRODUZIONE, dataProduzione);
    setValue(COD_PAESE_ORIGINE, codPaeseOrigine);
    setValue(DATA_MUNGITURA, dataMungitura);

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

  public ProdottoCommon getProdottoCommon() {
    return (ProdottoCommon)attributes[PRODOTTO_COMMON];
  }

  public void setProdottoCommon(ProdottoCommon prodottoCommon) {
    ProdottoCommon oldProdotto = getProdottoCommon();
    if (equals(oldProdotto, prodottoCommon)) {
      return;
    }
    setValue(PRODOTTO_COMMON, prodottoCommon);
    firePropertyChange("idProdotto", oldProdotto, prodottoCommon);
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
    firePropertyChange("colLotto", oldCodLotto, codLotto);
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
    firePropertyChange("scadenza", oldScadenza, scadenza);
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
    firePropertyChange("disponibilita", oldDisponibilita, disponibilita);
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
    firePropertyChange("dataProduzione", oldDataProduzione, dataProduzione);
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
    firePropertyChange("codPaeseOrigine", oldCodPaeseOrigine, codPaeseOrigine);
  }
  
  public String toString() {
    return getCodLotto();
  }
  
  public Date getDataMungitura() {
    return getDate(DATA_MUNGITURA);
  }
  
  public void setDataMungitura(Date dataMungitura) {
    Date oldDataMungitura = getDataMungitura();
    if (equals(oldDataMungitura, dataMungitura)) {
      return;
    }
    setValue(DATA_MUNGITURA, dataMungitura);
    firePropertyChange("dataMungitura", oldDataMungitura, dataMungitura);
  }

  public void copyTo(ObservedModel lotto) {
    ((Lotto)lotto).setId(getId());
    ((Lotto)lotto).setCodLotto(getCodLotto());
    ((Lotto)lotto).setScadenza(getScadenza());
    ((Lotto)lotto).setDisponibilita(getDisponibilita());
    ((Lotto)lotto).setDataProduzione(getDataProduzione());;
    ((Lotto)lotto).setCodPaeseOrigine(getCodPaeseOrigine());
    ((Lotto)lotto).setDataMungitura(getDataMungitura());
    ((Lotto)lotto).setProdottoCommon(getProdottoCommon());
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Lotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    try {
      if (getId() > 0 && ((Lotto)other).getId() > 0) {
        return getId() == ((Lotto)other).getId();
      }
    } catch (Exception ex) {
      return false;
    }

    return getCodLotto().equals(((Lotto)other).getCodLotto());
  }

  public void clear(){
    setId(null);
    setCodLotto(null);
    setProdottoCommon(null);
    setDataMungitura(null);
    setDataProduzione(null);
    setCodPaeseOrigine(null);
    setDisponibilita(null);
    setScadenza(null);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

}
