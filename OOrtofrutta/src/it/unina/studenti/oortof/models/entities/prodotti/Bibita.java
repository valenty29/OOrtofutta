package it.unina.studenti.oortof.models.entities.prodotti;

import it.unina.studenti.oortof.models.entities.prodotti.enumeration.TipoBibita;

public class Bibita extends Prodotto {
  
  public Bibita() {
    super();
    replaceProdottoSpecifico(BIBITA_INDEX, new BibitaSpecifico());
    prodottoCommon.setValue(ProdottoCommon.BIBITA, Boolean.TRUE);
  }

  public Bibita(Integer id, String nome, Float prezzo, Boolean sfuso, Float gradazioneAlcolica, Boolean frizzante, TipoBibita tipoBibita) {
    super(id, nome, prezzo, sfuso);
    replaceProdottoSpecifico(BIBITA_INDEX, new BibitaSpecifico(gradazioneAlcolica, frizzante, tipoBibita));
    prodottoCommon.setValue(ProdottoCommon.BIBITA, Boolean.TRUE);
  }
}
