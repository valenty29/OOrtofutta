package it.unina.studenti.oortof.models;

import java.util.Date;


public class LottoCaseario extends Lotto {
  
  public static final int DATA_MUNGITURA = 6;  //Date
  
  
  public LottoCaseario() {
    super();
  }

  public LottoCaseario(int id, String codLotto, Date scadenza, Float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
    super(id, codLotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
    setValue(DATA_MUNGITURA, dataMungitura);
  }

  public Date getDataMungitura() {
    return getDate(DATA_MUNGITURA);
  }
  
  public void setDataMungitura(Date dataMungitura) {
    Date oldDataMungitura = getDataMungitura();
    if (equals(oldDataMungitura, dataMungitura)) {
      return;
    }
    setValue(DATA_MUNGITURA, dataMungitura);
    firePropertyChanged("dataMungitura", oldDataMungitura, dataMungitura);
  }
  
//  public void copyTo(Lotto lotto) {
//    super.copyTo(lotto);
//    ((LottoCaseario)lotto).dataMungitura = dataMungitura;
//  }
}
