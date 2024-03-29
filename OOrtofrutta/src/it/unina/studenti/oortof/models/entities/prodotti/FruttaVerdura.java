package it.unina.studenti.oortof.models.entities.prodotti;

import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoFruttaVerdura;

public class FruttaVerdura extends Prodotto {
  
  public FruttaVerdura() {
    super();
    replaceProdottoSpecifico(FRUTTA_VERDURA_INDEX, new FruttaVerduraSpecifico());
    prodottoCommon.setValue(ProdottoCommon.FRUTTA_VERDURA, Boolean.TRUE);
  }

  public FruttaVerdura(int id, String nome, float prezzo, boolean sfuso, TipoFruttaVerdura tipoFruttaVerdura, boolean bio, boolean surgelato) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(FRUTTA_VERDURA_INDEX, new FruttaVerduraSpecifico(tipoFruttaVerdura, bio, surgelato));
    prodottoCommon.setValue(ProdottoCommon.FRUTTA_VERDURA, Boolean.TRUE);
  }
}
