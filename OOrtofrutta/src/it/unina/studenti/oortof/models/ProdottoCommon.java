package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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
    attributes[LOTTI] = new ArrayList<Lotto>();
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
    return (Integer)attributes[ID];
  }
  
  public void setId(Integer id) {
    if (equals(id, attributes[ID])) {
      return;
    }
    Integer oldId = (Integer)attributes[ID];
    attributes[ID] = id;
    firePropertyChanged("id", oldId, id);
  }
  
  public Boolean getAltro() {
    return getBoolean(ALTRO);
  }

  public boolean isAltro() {
    return isBoolean(ALTRO);
  }

  public void setAltro(Boolean altro) {
    if (equals(altro, attributes[ALTRO])) {
      return;
    }
    Boolean oldAltro = getAltro();
    setValue(ALTRO, altro);
    firePropertyChanged("altro", oldAltro, altro);
  }

  public Boolean getBibita() {
    return getBoolean(BIBITA);
  }

  public boolean isBibita() {
    return isBoolean(BIBITA);
  }

  public void setBibita(Boolean bibita) {
    if (equals(bibita, attributes[BIBITA])) {
      return;
    }
    Boolean oldBibita = getBibita();
    setValue(BIBITA, bibita);
    firePropertyChanged("bibita", oldBibita, bibita);
  }

  public Boolean getCarnePesce() {
    return getBoolean(CARNE_PESCE);
  }

  public boolean isCarnePesce() {
    return isBoolean(CARNE_PESCE);
  }

  public void setCarnePesce(Boolean carnePesce) {
    if (equals(carnePesce, attributes[CARNE_PESCE])) {
      return;
    }
    Boolean oldCarnePesce = getCarnePesce();
    setValue(CARNE_PESCE, carnePesce);
    firePropertyChanged("carnePesce", oldCarnePesce, carnePesce);
  }

  public Boolean getConserva() {
    return getBoolean(CONSERVA);
  }

  public boolean isConserva() {
    return isBoolean(CONSERVA);
  }

  public void setConserva(Boolean conserva) {
    if (equals(conserva, attributes[CONSERVA])) {
      return;
    }
    Boolean oldConserva = getConserva();
    setValue(CONSERVA, conserva);
    firePropertyChanged("conserva", oldConserva, conserva);
  }

  public Boolean getFarinaceo() {
    return getBoolean(FARINACEO);
  }

  public boolean isFarinaceo() {
    return isBoolean(FARINACEO);
  }

  public void setFarinaceo(Boolean farinaceo) {
    if (equals(farinaceo, attributes[FARINACEO])) {
      return;
    }
    Boolean oldFarinaceo = getFarinaceo();
    setValue(FARINACEO, farinaceo);
    firePropertyChanged("farinaceo", oldFarinaceo, farinaceo);
  }

  public Boolean getFruttaVerdura() {
    return getBoolean(FRUTTA_VERDURA);
  }

  public boolean isFruttaVerdura() {
    return isBoolean(FRUTTA_VERDURA);
  }

  public void setFruttaVerdura(Boolean fruttaVerdura) {
    if (equals(fruttaVerdura, attributes[FRUTTA_VERDURA])) {
      return;
    }
    Boolean oldFruttaVerdura = getFruttaVerdura();
    setValue(FRUTTA_VERDURA, fruttaVerdura);
    firePropertyChanged("fruttaVerdura", oldFruttaVerdura, fruttaVerdura);
  }

  public Boolean getProdottoCaseario() {
    return getBoolean(PRODOTTO_CASEARIO);
  }

  public boolean isProdottoCaseario() {
    return isBoolean(PRODOTTO_CASEARIO);
  }

  public void setProdottoCaseario(Boolean prodottoCaseario) {
    if (equals(prodottoCaseario, attributes[PRODOTTO_CASEARIO])) {
      return;
    }
    Boolean oldProdottoCaseario = getProdottoCaseario();
    setValue(PRODOTTO_CASEARIO, prodottoCaseario);
    firePropertyChanged("prodottoCaseario", oldProdottoCaseario, prodottoCaseario);
  }

  public Boolean getUovo() {
    return getBoolean(UOVO);
  }

  public boolean isUovo() {
    return isBoolean(UOVO);
  }

  public void setUovo(Boolean uovo) {
    if (equals(uovo, attributes[UOVO])) {
      return;
    }
    Boolean oldUovo = getUovo();
    setValue(UOVO, uovo);
    firePropertyChanged("uovo", oldUovo, uovo);
  }

  public String getNome() {
    return (String)attributes[NOME];
  }

  public void setNome(String nome) {
    String oldNome = (String)attributes[NOME];
    setValue(NOME, nome);
    firePropertyChanged("nome", oldNome, nome);
  }

  public Float getPrezzo() {
    return getFloat(PREZZO);
  }

  public void setPrezzo(Float prezzo) {
    Float oldPrezzo = getPrezzo();
    setValue(PREZZO, prezzo);
    firePropertyChanged("prezzo", oldPrezzo, prezzo);
  }

  public boolean isSfuso() {
    return isBoolean(SFUSO);
  }

  public Boolean getSfuso() {
    return getBoolean(SFUSO);
  }

  public void setSfuso(Boolean sfuso) {
    Boolean oldSfuso = getSfuso();
    setValue(SFUSO, sfuso);
    firePropertyChanged("sfuso", oldSfuso, sfuso);
  }

  public List<Lotto> getLotti() {
    return (List<Lotto>)attributes[LOTTI];
  }

  public void setLotti(List<Lotto> lotti) {
    if (lotti == attributes[LOTTI]) {
      return;
    }
    for (Lotto lotto : lotti) {
      lotto.addPropertyChangeListener(this);
    }
    List<Lotto> oldLotti = (List<Lotto>)attributes[LOTTI];
    attributes[LOTTI] = lotti;
    firePropertyChanged("lotto", oldLotti, lotti);
  }
  
  public void addLotto(Lotto lotto) {
    ((List<Lotto>)attributes[LOTTI]).add(lotto);
    firePropertyChanged("lotto", null, lotto);
    lotto.addPropertyChangeListener(this);
  }

  public void addLotto(int index, Lotto lotto) {
    ((List<Lotto>)attributes[LOTTI]).add(index, lotto);
    firePropertyChanged("lotto", index, lotto);
    lotto.addPropertyChangeListener(this);
  }
  
  public Lotto getLottoAt(int index) {
    return ((List<Lotto>)attributes[LOTTI]).get(index);
  }
  
  public void removeLotto(int index) {
    Lotto removedLotto = ((List<Lotto>)attributes[LOTTI]).remove(index);
    removedLotto.removePropertyChangeListener(this);
    firePropertyChanged("lotto", removedLotto, index);
  }
  
  public void removeLotto(Lotto lotto) {
    int toRemoveIndex = ((List<Lotto>)attributes[LOTTI]).indexOf(lotto);
    if (toRemoveIndex >= 0) {
      removeLotto(toRemoveIndex);
      lotto.removePropertyChangeListener(this);
    }
  }
  
  public void clearLotti() {
    if (((List<Lotto>)attributes[LOTTI]).size() == 0) {
      return;
    }
    List<Lotto> oldLotti = new ArrayList<Lotto>(((List<Lotto>)attributes[LOTTI]));
    ((List<Lotto>)attributes[LOTTI]).clear();
    firePropertyChanged("lotto", oldLotti, ((List<Lotto>)attributes[LOTTI]));
  }
  
  public int getLottiSize() {
    return ((List<Lotto>)attributes[LOTTI]).size();
  }
  
  public void copyTo(ProdottoCommon prodottoCommon) {
    prodottoCommon.setId(getId());
    prodottoCommon.setNome(getNome());
    prodottoCommon.setPrezzo(getPrezzo());
    prodottoCommon.setSfuso(getSfuso());
    prodottoCommon.getLotti().clear();
    for (Lotto lotto : ((List<Lotto>)attributes[LOTTI])) {
      Lotto newLotto = lotto instanceof LottoCaseario ? new LottoCaseario() : new Lotto();
      lotto.copyTo(newLotto);
      ((List<Lotto>)attributes[LOTTI]).add(newLotto);
    }
  }
  
  public void clear() {
    setId(null);
    setAltro(Boolean.FALSE);
    setBibita(Boolean.FALSE);
    setCarnePesce(Boolean.FALSE);
    setConserva(Boolean.FALSE);
    setFarinaceo(Boolean.FALSE);
    setFruttaVerdura(Boolean.FALSE);
    setProdottoCaseario(Boolean.FALSE);
    setUovo(Boolean.FALSE);
    setNome(null);
    setPrezzo(null);
    setSfuso(Boolean.FALSE);
    clearLotti();
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

}
