package it.unina.studenti.oortof.models;


public class CarnePesce extends Prodotto {
  
   public CarnePesce() {
     super();
     prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }

  public CarnePesce(int id, String nome, float prezzo, boolean sfuso, TipoCarnePesce tipoCP, boolean daAllevamento, String animale, boolean confezionato) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(CARNE_PESCE_INDEX, new CarnePesceSpecifico(tipoCP, daAllevamento, animale, confezionato));
    prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }
}
