package it.unina.studenti.oortof.models;

import java.util.Date;


public class LottoCaseario extends Lotto {
  
  public static final int DATA_MUNGITURA = 6;  //Date
  
  
  public LottoCaseario() {
    super();
    Object[] superAttributes = attributes;
    attributes = new Object[7];
    System.arraycopy(superAttributes, 0, attributes, 0, 6);
  }

  public LottoCaseario(int id, String codLotto, Date scadenza, Float disponibilita, Date dataProduzione, String codPaeseOrigine, Date dataMungitura) {
    super(id, codLotto, scadenza, disponibilita, dataProduzione, codPaeseOrigine);
    Object[] superAttributes = attributes;
    attributes = new Object[7];
    System.arraycopy(superAttributes, 0, attributes, 0, 6);
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
  
  public void copyTo(Lotto lotto) {
    super.copyTo(lotto);
    ((LottoCaseario)lotto).setDataMungitura(getDataMungitura());
  }
}
