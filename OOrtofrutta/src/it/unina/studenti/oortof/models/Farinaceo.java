package it.unina.studenti.oortof.models;

public class Farinaceo extends Prodotto {
  
  private FarinaceoSpecifico farinaceoSpecifico;
  
  public Farinaceo() {
    farinaceoSpecifico = new FarinaceoSpecifico();
  }

  public Farinaceo(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, boolean glutine, String tipoFarina, boolean fresco, boolean surgelato) {
    super(id, nome, prezzo, sfuso, catProdotto);
    farinaceoSpecifico = new FarinaceoSpecifico(glutine, tipoFarina, fresco, surgelato);
  }
  
  public void setFarinaceoSpecifico(FarinaceoSpecifico farinaceoSpecifico) {
    FarinaceoSpecifico oldFarinaceoSpecifico = this.farinaceoSpecifico;
    this.farinaceoSpecifico = farinaceoSpecifico;
    firePropertyChanged("farinaceoSpecifico", oldFarinaceoSpecifico, farinaceoSpecifico);
  }
  
  public FarinaceoSpecifico getFarinaceoSpecifico() {
    return farinaceoSpecifico;
  }

  public void copyTo(Farinaceo farinaceo) {
    super.copyTo(farinaceo);
    farinaceoSpecifico.copyTo(farinaceo.getFarinaceoSpecifico());
  }

}
