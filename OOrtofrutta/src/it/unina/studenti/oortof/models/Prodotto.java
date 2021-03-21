package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;


public class Prodotto extends ObservedModel implements PropertyChangeListener {

  public static final int ALTRO_INDEX = 0;
  public static final int BIBITA_INDEX = 1;
  public static final int CARNE_PESCE_INDEX = 2;
  public static final int CONSERVA_INDEX = 3;
  public static final int FARINACEO_INDEX = 4;
  public static final int FRUTTA_VERDURA_INDEX = 5;
  public static final int PRODOTTO_CASEARIO_INDEX = 6;
  public static final int UOVO_INDEX = 7;

  public static final int PRODOTTO_COMMON = 0;        // Float
  public static final int PRODOTTI_SPECIFICI = 1;     // Boolean

  protected ProdottoCommon prodottoCommon = new ProdottoCommon();
  protected ObservedList<ProdottoSpecifico> prodottiSpecifici = new ObservedList<ProdottoSpecifico>("prodottiSpecifici");

  public Prodotto() {
    attributes = new Object[2];
    attributes[PRODOTTO_COMMON] = prodottoCommon;
    attributes[PRODOTTI_SPECIFICI] = prodottiSpecifici;
    prodottiSpecifici.add(new AltroSpecifico());
    prodottiSpecifici.add(new BibitaSpecifico());
    prodottiSpecifici.add(new CarnePesceSpecifico());
    prodottiSpecifici.add(new ConservaSpecifico());
    prodottiSpecifici.add(new FarinaceoSpecifico());
    prodottiSpecifici.add(new FruttaVerduraSpecifico());
    prodottiSpecifici.add(new ProdottoCasearioSpecifico());
    prodottiSpecifici.add(new UovoSpecifico());
    prodottoCommon.addPropertyChangeListener(this);
    prodottiSpecifici.get(ALTRO_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(BIBITA_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(CARNE_PESCE_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(CONSERVA_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(FARINACEO_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(FRUTTA_VERDURA_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(PRODOTTO_CASEARIO_INDEX).addPropertyChangeListener(this);
    prodottiSpecifici.get(UOVO_INDEX).addPropertyChangeListener(this);
  }
  
  protected Prodotto(Integer id, String nome, Float prezzo, Boolean sfuso) {
    attributes = new Object[2];
    attributes[PRODOTTO_COMMON] = prodottoCommon;
    attributes[PRODOTTI_SPECIFICI] = prodottiSpecifici;
    prodottiSpecifici.add(null);   // Altro
    prodottiSpecifici.add(null);   // Bibita
    prodottiSpecifici.add(null);   // CarnePesce
    prodottiSpecifici.add(null);   // Conserva
    prodottiSpecifici.add(null);   // Farinaceo
    prodottiSpecifici.add(null);   // FruttaVerdura
    prodottiSpecifici.add(null);   // ProdottoCaseario
    prodottiSpecifici.add(null);   // Uovo
    prodottoCommon.setId(id);
    prodottoCommon.setNome(nome);
    prodottoCommon.setPrezzo(prezzo);
    prodottoCommon.setSfuso(sfuso);
    prodottoCommon.addPropertyChangeListener(this);
  }
  
  public ProdottoCommon getProdottoCommon() {
    return prodottoCommon;
  }

  public ProdottoSpecifico getProdottoSpecifico() {
    for (ProdottoSpecifico prodottoSpecifico : prodottiSpecifici) {
      if (prodottoSpecifico != null) {
        return prodottoSpecifico;
      }
    }
    return null;
  }
  
  public AltroSpecifico getAltroSpecifico() {
    return (AltroSpecifico)getProdottoSpecificoAt(ALTRO_INDEX);
  }
  
  public BibitaSpecifico getBibitaSpecifico() {
    return (BibitaSpecifico)getProdottoSpecificoAt(BIBITA_INDEX);
  }
  
  public CarnePesceSpecifico getCarnePesceSpecifico() {
    return (CarnePesceSpecifico)getProdottoSpecificoAt(CARNE_PESCE_INDEX);
  }
  
  public ConservaSpecifico getConservaSpecifico() {
    return (ConservaSpecifico)getProdottoSpecificoAt(CONSERVA_INDEX);
  }
  
  public FarinaceoSpecifico getFarinaceoSpecifico() {
    return (FarinaceoSpecifico)getProdottoSpecificoAt(FARINACEO_INDEX);
  }
  
  public FruttaVerduraSpecifico getFruttaVerduraSpecifico() {
    return (FruttaVerduraSpecifico)getProdottoSpecificoAt(FRUTTA_VERDURA_INDEX);
  }
  
  public ProdottoCasearioSpecifico getProdottoCasearioSpecifico() {
    return (ProdottoCasearioSpecifico)getProdottoSpecificoAt(PRODOTTO_CASEARIO_INDEX);
  }
  
  public UovoSpecifico getUovoSpecifico() {
    return (UovoSpecifico)getProdottoSpecificoAt(UOVO_INDEX);
  }
  
  public ProdottoSpecifico getProdottoSpecificoAt(int index) {
    return prodottiSpecifici.get(index);
  }
  
  public ObservedList<ProdottoSpecifico> getProdottiSpecifici() {
    return (ObservedList<ProdottoSpecifico>)attributes[PRODOTTI_SPECIFICI];
  }

  public void replaceProdottoSpecifico(int index, ProdottoSpecifico prodottoSpecifico) {
    if (((ObservedList<ProdottoSpecifico>)attributes[PRODOTTI_SPECIFICI]).get(index) == prodottoSpecifico) {
      return;
    }
    ProdottoSpecifico old = ((ObservedList<ProdottoSpecifico>)attributes[PRODOTTI_SPECIFICI]).get(index);
    if (old != null) {
      old.removePropertyChangeListener(this);
    }
    ((ObservedList<ProdottoSpecifico>)attributes[PRODOTTI_SPECIFICI]).set(index, prodottoSpecifico);
    firePropertyChanged("prodottiSpecifici", index, prodottoSpecifico);
    prodottoSpecifico.addPropertyChangeListener(this);
  }

  public String toString() {
    return prodottoCommon.getNome();
  }
  
  public void clear() {
    prodottoCommon.clear();
    for (ProdottoSpecifico ps : prodottiSpecifici) {
      if (ps != null) {
        ps.clear();
      }
    }
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Prodotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (prodottoCommon.getId() > 0 && ((Prodotto)other).prodottoCommon.getId() > 0) {
      return prodottoCommon.getId() == ((Prodotto)other).prodottoCommon.getId();
    }
    return prodottoCommon.getNome().equals(((Prodotto)other).prodottoCommon.getNome());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
  
  ProdottoSpecifico newByIndex(int index) {
    switch (index) {
    case ALTRO_INDEX: return new AltroSpecifico();
    case BIBITA_INDEX: return new BibitaSpecifico();
    case CARNE_PESCE_INDEX: return new CarnePesceSpecifico();
    case CONSERVA_INDEX: return new ConservaSpecifico();
    case FARINACEO_INDEX: return new FarinaceoSpecifico();
    case FRUTTA_VERDURA_INDEX: return new FruttaVerduraSpecifico();
    case PRODOTTO_CASEARIO_INDEX: return new ProdottoCasearioSpecifico();
    case UOVO_INDEX: return new UovoSpecifico();       
    }
    return null;
  }

  @Override
  public void copyTo(ObservedModel prodotto) {
    prodottoCommon.copyTo(((Prodotto)prodotto).getProdottoCommon());
    boolean subclass1 = getClass().getSuperclass().equals(Prodotto.class);
    boolean subclass2 = prodotto.getClass().getSuperclass().equals(Prodotto.class);
    if (subclass1 == subclass2) {
      prodottiSpecifici.copyTo(((Prodotto)prodotto).getProdottiSpecifici());
    }
    else {
      for (int i = ALTRO_INDEX; i <= UOVO_INDEX; i++) {
        ProdottoSpecifico ps1 = getProdottoSpecificoAt(i);
        ProdottoSpecifico ps2 = ((Prodotto)prodotto).getProdottoSpecificoAt(i);
        if (subclass1) {
          if (ps1 == null) {
            ps2.clear();
          }
          else {
            ps1.copyTo(ps2);
          }
        }
        else {
          if (ps2 != null) {
            ps1.copyTo(ps2);
          }
        }
      }
    }
  }
}
