package it.unina.studenti.oortof.models;

public class Farinaceo extends Prodotto {
  
  public Farinaceo() {
    super();
    setProdottoSpecifico(new FarinaceoSpecifico());
    prodottoCommon.setValue(ProdottoCommon.FARINACEO, Boolean.TRUE);
  }

  public Farinaceo(int id, String nome, float prezzo, boolean sfuso, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new FarinaceoSpecifico(glutine, tipoFarina, fresco, surgelato));
    prodottoCommon.setValue(ProdottoCommon.CONSERVA, Boolean.TRUE);
  }
  
  public void setFarinaceoSpecifico(FarinaceoSpecifico farinaceoSpecifico) {
    FarinaceoSpecifico oldFarinaceoSpecifico = (FarinaceoSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(farinaceoSpecifico);
    firePropertyChanged("farinaceoSpecifico", oldFarinaceoSpecifico, farinaceoSpecifico);
  }
  
  public FarinaceoSpecifico getFarinaceoSpecifico() {
    return (FarinaceoSpecifico)getProdottoSpecifico();
  }

  public void copyTo(Farinaceo farinaceo) {
    super.copyTo(farinaceo);
    getProdottoSpecifico().copyTo(farinaceo.getFarinaceoSpecifico());
  }

}
