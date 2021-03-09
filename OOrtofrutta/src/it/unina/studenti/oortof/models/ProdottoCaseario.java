package it.unina.studenti.oortof.models;


public class ProdottoCaseario extends Prodotto {
  
  ProdottoCasearioSpecifico prodottoCasearioSpecifico;
  
  public ProdottoCaseario() {
    prodottoCasearioSpecifico = new ProdottoCasearioSpecifico();
  }
  
  public ProdottoCaseario(int id, String nome, float prezzo, boolean sfuso, CatProdotto catProdotto, String tipoLatte, String stabilimento, int stagionatura) {
    super(id, nome, prezzo, sfuso, catProdotto);
    prodottoCasearioSpecifico = new ProdottoCasearioSpecifico(tipoLatte, stabilimento, stagionatura);
  }
  
  public void setProdottoCasearioSpecifico(ProdottoCasearioSpecifico prodottoCasearioSpecifico) {
    ProdottoCasearioSpecifico oldProdottoCasearioSpecifico = this.prodottoCasearioSpecifico;
    this.prodottoCasearioSpecifico = prodottoCasearioSpecifico;
    firePropertyChanged("prodottoCasearioSpecifico", oldProdottoCasearioSpecifico, prodottoCasearioSpecifico);
  }
  
  public ProdottoCasearioSpecifico getProdottoCasearioSpecifico() {
    return prodottoCasearioSpecifico;
  }

  public void copyTo(ProdottoCaseario prodottoCaseario) {
    super.copyTo(prodottoCaseario);
    prodottoCasearioSpecifico.copyTo(prodottoCaseario.getProdottoCasearioSpecifico());
  }
}
