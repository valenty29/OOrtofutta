package it.unina.studenti.oortof.models.entities.prodotti;

import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoCarnePesce;

public class CarnePesce extends Prodotto {


  public CarnePesce() {
    super();
    replaceProdottoSpecifico(CARNE_PESCE_INDEX, new CarnePesceSpecifico());
    prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }

  public CarnePesce(int id, String nome, float prezzo, boolean sfuso, TipoCarnePesce tipoCP, boolean daAllevamento, String animale, boolean confezionato) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(CARNE_PESCE_INDEX, new CarnePesceSpecifico(tipoCP, daAllevamento, animale, confezionato));
    prodottoCommon.setValue(ProdottoCommon.CARNE_PESCE, Boolean.TRUE);
  }
}
