package it.unina.studenti.oortof.models;

public class RaccoltaPunti extends ObservedModel{
  
  public static final int ID = 0;                //Integer
  public static final int ALTRO = 1;             //Integer 
  public static final int BIBITA = 2;            //Integer 
  public static final int CARNE_PESCE = 3;       //Integer 
  public static final int CONSERVA = 4;          //Integer 
  public static final int FARINACEO = 5;         //Integer 
  public static final int FRUTTA_VERDURA = 6;    //Integer 
  public static final int PRODOTTO_CASEARIO = 7; //Integer
  public static final int UOVO = 8;              //Integer
  
  public RaccoltaPunti() {
    attributes = new Object[9];
  }

  public RaccoltaPunti(Integer id, Integer altro, Integer bibita, Integer carnePesce, Integer conserva, Integer farinaceo, Integer fruttaVerdura, Integer prodottoCaseario, Integer uovo) {
    this();
    setValue(ID, id);
    setValue(ALTRO, altro);
    setValue(BIBITA, bibita);
    setValue(CARNE_PESCE, carnePesce);
    setValue(CONSERVA, conserva);
    setValue(FARINACEO, farinaceo);
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    setValue(UOVO, uovo);
  }

  public Integer getId() {
    return getInteger(ID);
  }

  public void setId(Integer id) {
    Integer oldId = getId();
    setValue(ID, id);
    firePropertyChanged("id", oldId, getId());
  }

  public Integer getFruttaVerdura() {
    return getInteger(FRUTTA_VERDURA);
  }

  public void setFruttaVerdura(Integer fruttaVerdura) {
    Integer oldFruttaVerdura = getFruttaVerdura();
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    firePropertyChanged("fruttaVerdura", oldFruttaVerdura, getFruttaVerdura());
  }

  public Integer getProdottoCaseario() {
    return getInteger(PRODOTTO_CASEARIO);
  }

  public void setProdottoCaseario(Integer prodottoCaseario) {
    Integer oldProdottoCaseario = getProdottoCaseario();
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    firePropertyChanged("prodottoCaseario", oldProdottoCaseario, getProdottoCaseario());
  }

  public Integer getFarinaceo() {
    return getInteger(FARINACEO);
  }

  public void setFarinaceo(Integer farinaceo) {
    Integer oldFarinaceo = getFarinaceo();
    setValue(FARINACEO, farinaceo);
    firePropertyChanged("farinaceo", oldFarinaceo, getFarinaceo());
  }

  public Integer getUovo() {
    return getInteger(UOVO);
  }

  public void setUovo(Integer uovo) {
    Integer oldUovo = getUovo();
    setValue(UOVO, uovo);
    firePropertyChanged("uovo", oldUovo, getUovo());
  }

  public Integer getCarnePesce() {
    return getInteger(CARNE_PESCE);
  }

  public void setCarnePesce(Integer carnePesce) {
    Integer oldCarnePesce = getCarnePesce();
    setValue(CARNE_PESCE, carnePesce);
    firePropertyChanged("carnePesce", oldCarnePesce, getCarnePesce());
  }

  public Integer getBibita() {
    return getInteger(BIBITA);
  }

  public void setBibita(Integer bibita) {
    Integer oldBibita = getBibita();
    setValue(BIBITA, bibita);
    firePropertyChanged("bibita", oldBibita, getBibita());
  }

  public Integer getConserva() {
    return getInteger(CONSERVA);
  }

  public void setConserva(Integer conserva) {
    Integer oldConserva = getConserva();
    setValue(CONSERVA, conserva);
    firePropertyChanged("conserva", oldConserva, getConserva());
  }

  public Integer getAltro() {
    return getInteger(ALTRO);
  }

  public void setAltro(Integer altro) {
    Integer oldAltro = getAltro();
    setValue(ALTRO, altro);
    firePropertyChanged("altro", oldAltro, getAltro());
  }

  public Integer getTotale() {
    return getFruttaVerdura() + getProdottoCaseario() + getFarinaceo() + getAltro() + getCarnePesce() + getBibita() + getConserva() + getAltro();
  }
}
