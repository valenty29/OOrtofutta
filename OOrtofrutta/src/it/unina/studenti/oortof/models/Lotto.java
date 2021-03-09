package it.unina.studenti.oortof.models;

import java.util.Date;


public class Lotto extends ObservedModel {
  private int id;
  private String codLotto;
  private Date scadenza;
  private float disponibilita;
  private Date dataProduzione;
  private String codPaeseOrigine;
  
  public Lotto() {
  }

  public Lotto(int id, String codLotto, Date scadenza, float disponibilita, Date dataProduzione, String codPaeseOrigine) {
    this.id = id;
    this.codLotto = codLotto;
    this.scadenza = scadenza;
    this.disponibilita = disponibilita;
    this.dataProduzione = dataProduzione;
    this.codPaeseOrigine = codPaeseOrigine;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    int oldId = this.id;
    this.id = id;
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCodLotto() {
    return codLotto;
  }

  public void setCodLotto(String codLotto) {
    String oldCodLotto = this.codLotto;
    this.codLotto = codLotto;
    firePropertyChanged("colLotto", oldCodLotto, codLotto);
  }

  public Date getScadenza() {
    return scadenza;
  }

  public void setScadenza(Date scadenza) {
    Date oldScadenza = this.scadenza;
    this.scadenza = scadenza;
    firePropertyChanged("scadenza", oldScadenza, scadenza);
  }

  public float getDisponibilita() {
    return disponibilita;
  }

  public void setDisponibilita(float disponibilita) {
    float oldDisponibilita = this.disponibilita;
    this.disponibilita = disponibilita;
    firePropertyChanged("disponibilita", oldDisponibilita, disponibilita);
  }

  public Date getDataProduzione() {
    return dataProduzione;
  }

  public void setDataProduzione(Date dataProduzione) {
    Date oldDataProduzione = this.dataProduzione;
    this.dataProduzione = dataProduzione;
    firePropertyChanged("dataProduzione", oldDataProduzione, dataProduzione);
  }

  public String getCodPaeseOrigine() {
    return codPaeseOrigine;
  }

  public void setCodPaeseOrigine(String codPaeseOrigine) {
    String oldCodPaeseOrigine = this.codPaeseOrigine;
    this.codPaeseOrigine = codPaeseOrigine;
    firePropertyChanged("codPaeseOrigine", oldCodPaeseOrigine, codPaeseOrigine);
  }
  
  public String toString() {
    return codLotto;
  }
  
  public void copyTo(Lotto lotto) {
    lotto.id = id;
    lotto.codLotto = codLotto;
    lotto.scadenza = scadenza;
    lotto.disponibilita = disponibilita;
    lotto.dataProduzione = dataProduzione;
    lotto.codPaeseOrigine = codPaeseOrigine;
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Lotto)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (id > 0 && ((Lotto)other).id > 0) {
      return id == ((Lotto)other).id;
    }
    return codLotto.equals(((Lotto)other).codLotto);
  }

}
