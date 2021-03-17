package it.unina.studenti.oortof.models;


public class CarnePesce extends Prodotto {
  
  private CarnePesceSpecifico carnePesceSpecifico;
  
   public CarnePesce() {
     carnePesceSpecifico = new CarnePesceSpecifico();
  }

  public CarnePesce(Integer id, String nome, Float prezzo, Boolean sfuso, CatProdotto catProdotto, TipoCarnePesce tipoCP, Boolean daAllevamento, String animale, Boolean confezionato) {
    super(id, nome, prezzo, sfuso, catProdotto);
    carnePesceSpecifico = new CarnePesceSpecifico(tipoCP, daAllevamento, animale, confezionato);
  }
  
  public void setCarnePesceSpecifico(CarnePesceSpecifico carnePesceSpecifico) {
    CarnePesceSpecifico oldCarnePesceSpecifico = this.carnePesceSpecifico;
    this.carnePesceSpecifico = carnePesceSpecifico;
    firePropertyChanged("carnePesceSpecifico", oldCarnePesceSpecifico, carnePesceSpecifico);
  }
  
  public CarnePesceSpecifico getCarnePesceSpecifico() {
    return carnePesceSpecifico;
  }
  
  public void copyTo(CarnePesce carnePesce) {
    super.copyTo(carnePesce);
    carnePesceSpecifico.copyTo(carnePesce.getCarnePesceSpecifico());
  }
}
