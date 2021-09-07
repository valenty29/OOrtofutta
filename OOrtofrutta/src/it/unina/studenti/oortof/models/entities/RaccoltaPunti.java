package it.unina.studenti.oortof.models.entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RaccoltaPunti extends ObservedModel implements PropertyChangeListener {


  public static final int ID = 0;
  public static final int FRUTTA_VERDURA = 1;
  public static final int PRODOTTO_CASEARIO = 2;
  public static final int FARINACEO = 3;
  public static final int UOVO = 4;
  public static final int CARNE_PESCE = 5;
  public static final int BIBITA = 6;
  public static final int CONSERVA = 7;
  public static final int ALTRO = 8;

  public RaccoltaPunti() {
    attributes = new Object[9];
  }

  public RaccoltaPunti(Integer id, Float fruttaVerdura, Float prodottoCaseario, Float farinaceo, Float uovo, Float carnePesce, Float bibita, Float conserva, Float altro) {
    this();
    setValue(ID, id);
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    setValue(FARINACEO, farinaceo);
    setValue(UOVO, uovo);
    setValue(CARNE_PESCE, carnePesce);
    setValue(BIBITA, bibita);
    setValue(CONSERVA, conserva);
    setValue(ALTRO, altro);
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

  public Float getFruttaVerdura() {
    return getFloat(FRUTTA_VERDURA);
  }

  public void setFruttaVerdura(Float fruttaVerdura) {
    Float oldFruttaVerdura = getFruttaVerdura();
    if (equals(oldFruttaVerdura, fruttaVerdura)) {
      return;
    }
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    firePropertyChange("fruttaVerdura",oldFruttaVerdura, fruttaVerdura);
  }

  public Float getProdottoCaseario() {
    return getFloat(PRODOTTO_CASEARIO);
  }

  public void setProdottoCaseario(Float prodottoCaseario) {
    Float oldProdottoCaseario = getProdottoCaseario();
    if (equals(oldProdottoCaseario, prodottoCaseario)) {
      return;
    }
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    firePropertyChange("prodottoCaseario", oldProdottoCaseario, prodottoCaseario);
  }

  public Float getFarinaceo() {
    return getFloat(FARINACEO);
  }

  public void setFarinaceo(Float farinaceo) {
    Float oldFarinaceo = getFarinaceo();
    if (equals(oldFarinaceo, farinaceo)) {
      return;
    }
    setValue(FARINACEO, farinaceo);
    firePropertyChange("farinaceo", oldFarinaceo, farinaceo);
  }

  public Float getUovo() {
    return getFloat(UOVO);
  }

  public void setUovo(Float uovo) {
    Float oldUovo = getUovo();
    if (equals(oldUovo, uovo)) {
      return;
    }
    setValue(UOVO, uovo);
    firePropertyChange("uovo", oldUovo, uovo);
  }

  public Float getCarnePesce() { 
    return getFloat(CARNE_PESCE);
  }

  public void setCarnePesce(Float carnePesce) {
    Float oldCarnePesce = getCarnePesce();
    if (equals(oldCarnePesce, carnePesce)) {
      return;
    }
    setValue(CARNE_PESCE, carnePesce);
    firePropertyChange("carnePesce", oldCarnePesce, carnePesce);
  }

  public Float getBibita() {
    return getFloat(BIBITA);
  }

  public void setBibita(Float bibita) {
    Float oldBibita = getBibita();
    if (equals(oldBibita, bibita)) {
      return;
    }
    setValue(BIBITA, bibita);
    firePropertyChange("bibita", oldBibita, bibita);
  }

  public Float getConserva() {
    return getFloat(CONSERVA);
  }

  public void setConserva(Float conserva) {
    Float oldConserva = getConserva();
    if (equals(oldConserva, conserva)) {
      return;
    }
    setValue(CONSERVA, conserva);
    firePropertyChange("conserva", oldConserva, conserva);
  }

  public Float getAltro() {
    return getFloat(ALTRO);
  }

  public void setAltro(Float altro) {
    Float oldAltro = getAltro();
    if (equals(oldAltro, altro)) {
      return;
    }
    setValue(ALTRO, altro);
    firePropertyChange("altro", oldAltro, altro);
  }
  
  public Float getTotale() {
    return getFloat(FRUTTA_VERDURA) + getFloat(PRODOTTO_CASEARIO) + getFloat(FARINACEO) + getFloat(UOVO) + getFloat(CARNE_PESCE) + getFloat(BIBITA) + getFloat(CONSERVA) + getFloat(ALTRO);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

  @Override
  public void copyTo(ObservedModel raccoltaPunti) {
    ((RaccoltaPunti)raccoltaPunti).setId(getId());
    ((RaccoltaPunti)raccoltaPunti).setFruttaVerdura(getFruttaVerdura());
    ((RaccoltaPunti)raccoltaPunti).setProdottoCaseario(getProdottoCaseario());
    ((RaccoltaPunti)raccoltaPunti).setFarinaceo(getFarinaceo());
    ((RaccoltaPunti)raccoltaPunti).setUovo(getUovo());
    ((RaccoltaPunti)raccoltaPunti).setCarnePesce(getCarnePesce());
    ((RaccoltaPunti)raccoltaPunti).setBibita(getBibita());
    ((RaccoltaPunti)raccoltaPunti).setConserva(getConserva());
    ((RaccoltaPunti)raccoltaPunti).setAltro(getAltro());
  }
  
  public void clear() {
    setId(null);
    setFruttaVerdura(null);
    setProdottoCaseario(null);
    setFarinaceo(null);
    setUovo(null);
    setCarnePesce(null);
    setBibita(null);
    setConserva(null);
    setAltro(null);
  }
}
