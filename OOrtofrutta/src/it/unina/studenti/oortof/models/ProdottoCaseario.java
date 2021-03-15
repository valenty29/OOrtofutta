package it.unina.studenti.oortof.models;


public class ProdottoCaseario extends Prodotto {
  
  public ProdottoCaseario() {
    super();
    setProdottoSpecifico(new ProdottoCasearioSpecifico());
    prodottoCommon.setValue(ProdottoCommon.PRODOTTO_CASEARIO, Boolean.TRUE);
  }
  
  public ProdottoCaseario(int id, String nome, float prezzo, boolean sfuso, String tipoLatte, String stabilimento, int stagionatura) {
    super(id, nome, prezzo, sfuso);
    setProdottoSpecifico(new ProdottoCasearioSpecifico(tipoLatte, stabilimento, stagionatura));
    prodottoCommon.setValue(ProdottoCommon.PRODOTTO_CASEARIO, Boolean.TRUE);
  }
  
  public void setProdottoCasearioSpecifico(ProdottoCasearioSpecifico prodottoCasearioSpecifico) {
    ProdottoCasearioSpecifico oldProdottoCasearioSpecifico = (ProdottoCasearioSpecifico)getProdottoSpecifico();
    setProdottoSpecifico(prodottoCasearioSpecifico);
    firePropertyChanged("prodottoCasearioSpecifico", oldProdottoCasearioSpecifico, prodottoCasearioSpecifico);
  }
  
  public ProdottoCasearioSpecifico getProdottoCasearioSpecifico() {
    return (ProdottoCasearioSpecifico)getProdottoSpecifico();
  }

  public void copyTo(ProdottoCaseario prodottoCaseario) {
    super.copyTo(prodottoCaseario);
    getProdottoSpecifico().copyTo(prodottoCaseario.getProdottoCasearioSpecifico());
  }
}
