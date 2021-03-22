package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProdottoCommon extends ObservedModel implements PropertyChangeListener {

  public static final int ID = 0;                // Integer
  public static final int ALTRO = 1;             // Boolean
  public static final int BIBITA = 2;            // Boolean
  public static final int CARNE_PESCE = 3;       // Boolean
  public static final int CONSERVA = 4;          // Boolean
  public static final int FARINACEO = 5;         // Boolean
  public static final int FRUTTA_VERDURA = 6;    // Boolean
  public static final int PRODOTTO_CASEARIO = 7; // Boolean
  public static final int UOVO = 8;              // Boolean
  public static final int NOME = 9;              // String
  public static final int PREZZO = 10;           // Float
  public static final int SFUSO = 11;            // Boolean
  public static final int LOTTI = 12;            // Lotto[]
  
  public ProdottoCommon() {
    attributes = new Object[13];
    attributes[LOTTI] = new ObservedList<Lotto>("lotti");
  }
  
  protected ProdottoCommon(Integer id, String nome, Float prezzo, Boolean sfuso) {
    this();
    setValue(ID, id);
    setValue(ALTRO, Boolean.FALSE);
    setValue(BIBITA, Boolean.FALSE);
    setValue(CARNE_PESCE, Boolean.FALSE);
    setValue(CONSERVA, Boolean.FALSE);
    setValue(FARINACEO, Boolean.FALSE);
    setValue(FRUTTA_VERDURA, Boolean.FALSE);
    setValue(PRODOTTO_CASEARIO, Boolean.FALSE);
    setValue(UOVO, Boolean.FALSE);
    setValue(NOME, nome);
    setValue(PREZZO, prezzo);
    setValue(SFUSO, sfuso);
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
  
  public Boolean getAltro() {
    return getBoolean(ALTRO);
  }

  public boolean isAltro() {
    return isBoolean(ALTRO);
  }

  public void setAltro(Boolean altro) {
    Boolean oldAltro = getAltro();
    if (equals(oldAltro, altro)) {
      return;
    }
    setValue(ALTRO, altro);
    firePropertyChange("altro", oldAltro, altro);
  }

  public Boolean getBibita() {
    return getBoolean(BIBITA);
  }

  public boolean isBibita() {
    return isBoolean(BIBITA);
  }

  public void setBibita(Boolean bibita) {
    Boolean oldBibita = getBibita();
    if (equals(oldBibita, bibita)) {
      return;
    }
    setValue(BIBITA, bibita);
    firePropertyChange("bibita", oldBibita, bibita);
  }

  public Boolean getCarnePesce() {
    return getBoolean(CARNE_PESCE);
  }

  public boolean isCarnePesce() {
    return isBoolean(CARNE_PESCE);
  }

  public void setCarnePesce(Boolean carnePesce) {
    Boolean oldCarnePesce = getCarnePesce();
    if (equals(oldCarnePesce, carnePesce)) {
      return;
    }
    setValue(CARNE_PESCE, carnePesce);
    firePropertyChange("carnePesce", oldCarnePesce, carnePesce);
  }

  public Boolean getConserva() {
    return getBoolean(CONSERVA);
  }

  public boolean isConserva() {
    return isBoolean(CONSERVA);
  }

  public void setConserva(Boolean conserva) {
    Boolean oldConserva = getConserva();
    if (equals(oldConserva, conserva)) {
      return;
    }
    setValue(CONSERVA, conserva);
    firePropertyChange("conserva", oldConserva, conserva);
  }

  public Boolean getFarinaceo() {
    return getBoolean(FARINACEO);
  }

  public boolean isFarinaceo() {
    return isBoolean(FARINACEO);
  }

  public void setFarinaceo(Boolean farinaceo) {
    Boolean oldFarinaceo = getFarinaceo();
    if (equals(oldFarinaceo, farinaceo)) {
      return;
    }
    setValue(FARINACEO, farinaceo);
    firePropertyChange("farinaceo", oldFarinaceo, farinaceo);
  }

  public Boolean getFruttaVerdura() {
    return getBoolean(FRUTTA_VERDURA);
  }

  public boolean isFruttaVerdura() {
    return isBoolean(FRUTTA_VERDURA);
  }

  public void setFruttaVerdura(Boolean fruttaVerdura) {
    Boolean oldFruttaVerdura = getFruttaVerdura();
    if (equals(oldFruttaVerdura, fruttaVerdura)) {
      return;
    }
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    firePropertyChange("fruttaVerdura", oldFruttaVerdura, fruttaVerdura);
  }

  public Boolean getProdottoCaseario() {
    return getBoolean(PRODOTTO_CASEARIO);
  }

  public boolean isProdottoCaseario() {
    return isBoolean(PRODOTTO_CASEARIO);
  }

  public void setProdottoCaseario(Boolean prodottoCaseario) {
    Boolean oldProdottoCaseario = getProdottoCaseario();
    if (equals(oldProdottoCaseario, prodottoCaseario)) {
      return;
    }
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    firePropertyChange("prodottoCaseario", oldProdottoCaseario, prodottoCaseario);
  }

  public Boolean getUovo() {
    return getBoolean(UOVO);
  }

  public boolean isUovo() {
    return isBoolean(UOVO);
  }

  public void setUovo(Boolean uovo) {
    Boolean oldUovo = getUovo();
    if (equals(oldUovo, uovo)) {
      return;
    }
    setValue(UOVO, uovo);
    firePropertyChange("uovo", oldUovo, uovo);
  }

  public String getNome() {
    return getString(NOME);
  }

  public void setNome(String nome) {
    String oldNome = getNome();
    if (equals(oldNome, nome)) {
      return;
    }
    setValue(NOME, nome);
    firePropertyChange("nome", oldNome, nome);
  }

  public Float getPrezzo() {
    return getFloat(PREZZO);
  }

  public void setPrezzo(Float prezzo) {
    Float oldPrezzo = getPrezzo();
    if (equals(oldPrezzo, prezzo)) {
      return;
    }
    setValue(PREZZO, prezzo);
    firePropertyChange("prezzo", oldPrezzo, prezzo);
  }

  public boolean isSfuso() {
    return isBoolean(SFUSO);
  }

  public Boolean getSfuso() {
    return getBoolean(SFUSO);
  }

  public void setSfuso(Boolean sfuso) {
    Boolean oldSfuso = getSfuso();
    if (equals(oldSfuso, sfuso)) {
      return;
    }
    setValue(SFUSO, sfuso);
    firePropertyChange("sfuso", oldSfuso, sfuso);
  }

  @SuppressWarnings("unchecked")
  public ObservedList<Lotto> getLotti() {
    return (ObservedList<Lotto>)attributes[LOTTI];
  }

  @SuppressWarnings("unchecked")
  public void setLotti(ObservedList<Lotto> lotti) {
    if (lotti == attributes[LOTTI]) {
      return;
    }
    ObservedList<Lotto> oldLotti = (ObservedList<Lotto>)attributes[LOTTI];
    oldLotti.removePropertyChangeListener(this);
    attributes[LOTTI] = lotti;
    lotti.addPropertyChangeListener(this);
    firePropertyChange("lotti", oldLotti, lotti);
  }
  
  @SuppressWarnings("unchecked")
  public void addLotto(Lotto lotto) {
    ((ObservedList<Lotto>)attributes[LOTTI]).add(lotto);
  }

  @SuppressWarnings("unchecked")
  public void addLotto(int index, Lotto lotto) {
    ((ObservedList<Lotto>)attributes[LOTTI]).add(index, lotto);
  }
  
  @SuppressWarnings("unchecked")
  public Lotto getLottoAt(int index) {
    return ((ObservedList<Lotto>)attributes[LOTTI]).get(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeLotto(int index) {
    ((ObservedList<Lotto>)attributes[LOTTI]).remove(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeLotto(Lotto lotto) {
    ((ObservedList<Lotto>)attributes[LOTTI]).remove(lotto);
  }
  
  @SuppressWarnings("unchecked")
  public void clearLotti() {
    ((ObservedList<Lotto>)attributes[LOTTI]).clear();
  }
  
  @SuppressWarnings("unchecked")
  public int getLottiSize() {
    return ((ObservedList<Lotto>)attributes[LOTTI]).size();
  }
  
  @SuppressWarnings("unchecked")
  public void copyTo(ObservedModel prodottoCommon) {
    ((ProdottoCommon)prodottoCommon).setId(getId());
    ((ProdottoCommon)prodottoCommon).setAltro(getAltro());
    ((ProdottoCommon)prodottoCommon).setBibita(getBibita());
    ((ProdottoCommon)prodottoCommon).setCarnePesce(getCarnePesce());
    ((ProdottoCommon)prodottoCommon).setConserva(getConserva());
    ((ProdottoCommon)prodottoCommon).setFarinaceo(getFarinaceo());
    ((ProdottoCommon)prodottoCommon).setFruttaVerdura(getFruttaVerdura());
    ((ProdottoCommon)prodottoCommon).setProdottoCaseario(getProdottoCaseario());
    ((ProdottoCommon)prodottoCommon).setUovo(getUovo());
    ((ProdottoCommon)prodottoCommon).setNome(getNome());
    ((ProdottoCommon)prodottoCommon).setPrezzo(getPrezzo());
    ((ProdottoCommon)prodottoCommon).setSfuso(getSfuso());
    ((ObservedList<Lotto>)attributes[LOTTI]).copyTo(((ProdottoCommon)prodottoCommon).getLotti());
  }
  
  public void clear() {
    setId(null);
    setAltro(null);
    setBibita(null);
    setCarnePesce(null);
    setConserva(null);
    setFarinaceo(null);
    setFruttaVerdura(null);
    setProdottoCaseario(null);
    setUovo(null);
    setNome(null);
    setPrezzo(null);
    setSfuso(null);
    clearLotti();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

}
