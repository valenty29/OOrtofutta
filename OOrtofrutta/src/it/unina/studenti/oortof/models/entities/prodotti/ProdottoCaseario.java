package it.unina.studenti.oortof.models;


public class ProdottoCaseario extends Prodotto {
  
  public ProdottoCaseario() {
    super();
    replaceProdottoSpecifico(PRODOTTO_CASEARIO_INDEX, new ProdottoCasearioSpecifico());
    prodottoCommon.setValue(ProdottoCommon.PRODOTTO_CASEARIO, Boolean.TRUE);
  }
  
  public ProdottoCaseario(int id, String nome, float prezzo, boolean sfuso, String tipoLatte,  int stagionatura) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(PRODOTTO_CASEARIO_INDEX, new ProdottoCasearioSpecifico(tipoLatte, stagionatura));
    prodottoCommon.setValue(ProdottoCommon.PRODOTTO_CASEARIO, Boolean.TRUE);
  }
}
