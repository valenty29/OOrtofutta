package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Prodotto extends ObservedModel implements PropertyChangeListener {

  public static final int ALTRO_INDEX = 0;
  public static final int BIBITA_INDEX = 1;
  public static final int CARNE_PESCE_INDEX = 2;
  public static final int CONSERVA_INDEX = 3;
  public static final int FARINACEO_INDEX = 4;
  public static final int FRUTTA_VERDURA_INDEX = 5;
  public static final int PRODOTTO_CASEARIO_INDEX = 6;
  public static final int UOVO_INDEX = 7;

  protected ProdottoCommon prodottoCommon;
  protected ProdottoSpecifico[] prodottiSpecifici;

  protected Prodotto() {
    prodottoCommon = new ProdottoCommon();
  }
  
  protected Prodotto(int id, String nome, float prezzo, boolean sfuso) {
    prodottoCommon = new ProdottoCommon(id, nome, prezzo, sfuso);
  }
  
  public void setProdottoCommon(ProdottoCommon prodottoCommon) {
    this.prodottoCommon = prodottoCommon;
  }
  
  public ProdottoCommon getProdottoCommon() {
    return prodottoCommon;
  }

  public void setProdottoSpecifico(ProdottoSpecifico prodottoSpecifico) {
    if (prodottoSpecifico instanceof AltroSpecifico) {
      prodottiSpecifici[ALTRO_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof BibitaSpecifico) {
      prodottiSpecifici[BIBITA_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof CarnePesceSpecifico) {
      prodottiSpecifici[CARNE_PESCE_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof ConservaSpecifico) {
      prodottiSpecifici[CONSERVA_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof FarinaceoSpecifico) {
      prodottiSpecifici[FARINACEO_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof FruttaVerduraSpecifico) {
      prodottiSpecifici[FRUTTA_VERDURA_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof ProdottoCasearioSpecifico) {
      prodottiSpecifici[PRODOTTO_CASEARIO_INDEX] = prodottoSpecifico;
    }
    else if (prodottoSpecifico instanceof UovoSpecifico) {
      prodottiSpecifici[UOVO_INDEX] = prodottoSpecifico;
    }
  }

  
  public ProdottoSpecifico getProdottoSpecifico() {
    for (ProdottoSpecifico prodottoSpecifico : prodottiSpecifici) {
      if (prodottoSpecifico != null) {
        return prodottoSpecifico;
      }
    }
    return null;
  }


  public String toString() {
    return prodottoCommon.getNome();
  }
  
  public void copyTo(Prodotto prodotto) {
    getProdottoCommon().copyTo(prodotto.getProdottoCommon());
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
}
