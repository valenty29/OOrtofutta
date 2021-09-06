package it.unina.studenti.oortof.models.entities.prodotti;

import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoConservazione;

public class Conserva extends Prodotto {


  
  public Conserva(int id, String nome, float prezzo, boolean sfuso, TipoConservazione tipoConservazione) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(CONSERVA_INDEX, new ConservaSpecifico(tipoConservazione));
    prodottoCommon.setValue(ProdottoCommon.CONSERVA, Boolean.TRUE);
  }
  
}
