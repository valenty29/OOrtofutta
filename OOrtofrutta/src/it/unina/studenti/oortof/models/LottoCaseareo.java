package it.unina.studenti.oortof.models;

import java.util.Date;

public class LottoCaseareo extends Lotto {
  private Date dataMungitura;

  public LottoCaseareo(int id, Prodotto prodotto, String codLotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
    super(id, prodotto, codLotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
    this.dataMungitura = dataMungitura;
  }

  public Date getDataMungitura() {
    return dataMungitura;
  }
  
  public void setDataMungitura(Date dataMungitura) {
    this.dataMungitura = dataMungitura;
  }
}
