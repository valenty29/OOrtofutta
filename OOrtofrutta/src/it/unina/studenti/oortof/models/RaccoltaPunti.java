package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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

  public RaccoltaPunti(){
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
    return (Integer)attributes[ID];
  }

  public void setId(int id) {
    if (equals(id, attributes[ID])) {
      return;
    }
    Integer oldId = (Integer)attributes[ID];
    attributes[ID] = id;
    firePropertyChanged("id", oldId, id);
  }

  public Float getFruttaVerdura() {
    return (Float)attributes[FRUTTA_VERDURA];
  }

  public void setFruttaVerdura(int fruttaVerdura) {
    if (equals(fruttaVerdura, attributes[FRUTTA_VERDURA])) {
      return;
    }
    Float oldFruttaVerdura = (Float)attributes[FRUTTA_VERDURA];
    attributes[FRUTTA_VERDURA] = fruttaVerdura;
    firePropertyChanged("fruttaVerdura",oldFruttaVerdura, fruttaVerdura);
  }

  public Float getProdottoCaseario() {
    return (Float)attributes[PRODOTTO_CASEARIO];
  }

  public void setProdottoCaseario(int prodottoCaseario) {
    if (equals(prodottoCaseario, attributes[PRODOTTO_CASEARIO])) {
      return;
    }
    Float oldProdottoCaseario = (Float)attributes[PRODOTTO_CASEARIO];
    attributes[PRODOTTO_CASEARIO] = prodottoCaseario;
    firePropertyChanged("prodottoCaseario", oldProdottoCaseario, prodottoCaseario);
  }

  public Float getFarinaceo() {
    return (Float)attributes[FARINACEO];
  }

  public void setFarinaceo(int farinaceo) {
    if (equals(farinaceo, attributes[FARINACEO])) {
      return;
    }
    Float oldFarinaceo = (Float)attributes[FARINACEO];
    attributes[FARINACEO] = farinaceo;
    firePropertyChanged("farinaceo", oldFarinaceo, farinaceo);
  }

  public Float getUovo() {
    return (Float)attributes[UOVO];
  }

  public void setUovo(int uovo) {
    if (equals(uovo, attributes[UOVO])) {
      return;
    }
    Float oldUovo = (Float)attributes[UOVO];
    attributes[UOVO] = uovo;
    firePropertyChanged("uovo", oldUovo, uovo);
  }

  public Float getCarnePesce() { return (Float)attributes[CARNE_PESCE];
  }

  public void setCarnePesce(int carnePesce) {
    if (equals(carnePesce, attributes[CARNE_PESCE])) {
      return;
    }
    Float oldCarnePesce = (Float)attributes[CARNE_PESCE];
    attributes[CARNE_PESCE] = carnePesce;
    firePropertyChanged("carnePesce", oldCarnePesce, carnePesce);
  }

  public Float getBibita() {
    return (Float)attributes[BIBITA];
  }

  public void setBibita(int bibita) {
    if (equals(bibita, attributes[BIBITA])) {
      return;
    }
    Float oldBibita = (Float)attributes[BIBITA];
    attributes[BIBITA] = bibita;
    firePropertyChanged("bibita", oldBibita, bibita);
  }

  public Float getConserva() {
    return (Float)attributes[CONSERVA];
  }

  public void setConserva(int conserva) {
    if (equals(conserva, attributes[CONSERVA])) {
      return;
    }
    Float oldConserva = (Float)attributes[CONSERVA];
    attributes[CONSERVA] = conserva;
    firePropertyChanged("conserva", oldConserva, conserva);
  }

  public Float getAltro() {
    return (Float)attributes[ALTRO];
  }

  public void setAltro(int altro) {
    if (equals(altro, attributes[ALTRO])) {
      return;
    }
    Float oldAltro = (Float)attributes[ALTRO];
    attributes[ALTRO] = altro;
    firePropertyChanged("altro", oldAltro, altro);
  }
  
  public Float getTotale() {
    return (Float)attributes[FRUTTA_VERDURA] + (Float)attributes[PRODOTTO_CASEARIO] + (Float)attributes[FARINACEO] + (Float)attributes[UOVO] + (Float)attributes[CARNE_PESCE] + (Float)attributes[BIBITA] + (Float)attributes[CONSERVA] + (Float)attributes[ALTRO];
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
}


