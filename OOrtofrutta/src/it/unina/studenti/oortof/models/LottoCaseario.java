package it.unina.studenti.oortof.models;

import java.util.Date;


public class LottoCaseario extends Lotto {
  private Date dataMungitura;
  
  public LottoCaseario() {
  }

  public LottoCaseario(int id, String codLotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
    super(id, codLotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
    this.dataMungitura = dataMungitura;
  }

  public Date getDataMungitura() {
    return dataMungitura;
  }
  
  public void setDataMungitura(Date dataMungitura) {
    Date oldDataMungitura = this.dataMungitura;
    this.dataMungitura = dataMungitura;
    firePropertyChanged("dataMungitura", oldDataMungitura, dataMungitura);
  }
  
  public void copyTo(Lotto lotto) {
    super.copyTo(lotto);
    ((LottoCaseario)lotto).dataMungitura = dataMungitura;
  }
}
