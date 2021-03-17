package it.unina.studenti.oortof.models;


public class CarnePesce extends Prodotto {
  
   public CarnePesce() {
     super();
     setProdottoSpecifico(new CarnePesceSpecifico());
     prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }

  public CarnePesce(int id, String nome, float prezzo, boolean sfuso, TipoCarnePesce tipoCP, boolean daAllevamento, String animale, boolean confezionato) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new CarnePesceSpecifico(tipoCP, daAllevamento, animale, confezionato));
    prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }
  
  public void setCarnePesceSpecifico(CarnePesceSpecifico carnePesceSpecifico) {
    CarnePesceSpecifico oldCarnePesceSpecifico = (CarnePesceSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(carnePesceSpecifico);
    firePropertyChanged("carnePesceSpecifico", oldCarnePesceSpecifico, carnePesceSpecifico);
  }
  
  public CarnePesceSpecifico getCarnePesceSpecifico() {
    return (CarnePesceSpecifico)getProdottoSpecifico();
  }
  
  public void copyTo(CarnePesce carnePesce) {
    super.copyTo(carnePesce);
    getProdottoSpecifico().copyTo(carnePesce.getCarnePesceSpecifico());
  }
}
