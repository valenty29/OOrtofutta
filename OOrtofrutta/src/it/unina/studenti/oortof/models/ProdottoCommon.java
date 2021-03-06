package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ProdottoCommon extends ObservedModel implements PropertyChangeListener {

  private int id;
  private String nome;
  private float prezzo;
  private boolean sfuso;
  private CatProdotto catProdotto;
  private List<Lotto> lotti = new ArrayList<Lotto>();


  public ProdottoCommon() {
  }
  
  protected ProdottoCommon(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto) {
    this.id = id;
    this.nome = nome;
    this.prezzo = prezzo;
    this.sfuso = sfuso;
    this.catProdotto = catProdotto;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    int oldId = this.id;
    this.id = id;
    firePropertyChanged("id", oldId, id);
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    String oldNome = this.nome;
    this.nome = nome;
    firePropertyChanged("nome", oldNome, nome);
  }

  public float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(float prezzo) {
    float oldPrezzo = this.prezzo;
    this.prezzo = prezzo;
    firePropertyChanged("prezzo", oldPrezzo, prezzo);
  }

  public boolean isSfuso() {
    return sfuso;
  }

  public void setSfuso(boolean sfuso) {
    if (this.sfuso == sfuso) {
      return;
    }
    boolean oldSfuso = this.sfuso;
    this.sfuso = sfuso;
    firePropertyChanged("sfuso", oldSfuso, sfuso);
  }

  public CatProdotto getCatProdotto() {
    return catProdotto;
  }

  public void setCatProdotto(CatProdotto catProdotto) {
    CatProdotto oldCatProdotto = this.catProdotto;
    this.catProdotto = catProdotto;
    firePropertyChanged("catProdotto", oldCatProdotto, catProdotto);
  }

  public List<Lotto> getLotti() {
    return lotti;
  }

  public void setLotti(List<Lotto> lotti) {
    if (lotti == this.lotti) {
      return;
    }
    for(Lotto lotto : lotti) {
      lotto.addPropertyChangeListener(this);
    }
    List<Lotto> oldLotti = this.lotti;
    this.lotti = lotti;
    firePropertyChanged("lotto", oldLotti, lotti);
  }
  
  public void addLotto(Lotto lotto) {
    lotti.add(lotto);
    firePropertyChanged("lotto", null, lotto);
    lotto.addPropertyChangeListener(this);
  }

  public void addLotto(int index, Lotto lotto) {
    lotti.add(index, lotto);
    firePropertyChanged("lotto", index, lotto);
    lotto.addPropertyChangeListener(this);
  }
  
  public Lotto getLottoAt(int index) {
    return lotti.get(index);
  }
  
  public void removeLotto(int index) {
    Lotto removedLotto = lotti.remove(index);
    removedLotto.removePropertyChangeListener(this);
    firePropertyChanged("lotto", removedLotto, index);
  }
  
  public void removeLotto(Lotto lotto) {
    int toRemoveIndex = lotti.indexOf(lotto);
    if (toRemoveIndex >= 0) {
      removeLotto(toRemoveIndex);
    }
  }
  
  public void clearLotti() {
    List<Lotto> oldLotti = new ArrayList<Lotto>(lotti);
    lotti.clear();
    firePropertyChanged("lotto", oldLotti, lotti);
  }
  
  public int getLottiSize() {
    return lotti.size();
  }
  
  public void copyTo(ProdottoCommon prodottoCommon) {
    prodottoCommon.setId(id);
    prodottoCommon.setNome(nome);
    prodottoCommon.setPrezzo(prezzo);
    prodottoCommon.setSfuso(sfuso);
    prodottoCommon.setCatProdotto(catProdotto);
    prodottoCommon.getLotti().clear();
    for (Lotto lotto : lotti) {
      Lotto newLotto = lotto instanceof LottoCaseario ? new LottoCaseario() : new Lotto();
      lotto.copyTo(newLotto);
      lotti.add(newLotto);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }

}
