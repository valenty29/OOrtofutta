package it.unina.studenti.oortof.models;

public class CarnePesceSpecifico extends ProdottoSpecifico{
  
  public static final int TIPO_CARNE_PESCE = 0; //TipoCarnePesce
  public static final int DA_ALLEVAMENTO = 1;   //Boolean
  public static final int ANIMALE = 2;          //String
  public static final int CONFEZIONATO = 3;     //Boolean
  
  public CarnePesceSpecifico() {
    attributes = new Object[4];
  }
  
  public CarnePesceSpecifico(TipoCarnePesce tipoCarnePesce, boolean daAllevamento, String animale, boolean confezionato) {
    this();
    setValue(TIPO_CARNE_PESCE, tipoCarnePesce);
    setValue(DA_ALLEVAMENTO, daAllevamento);
    setValue(ANIMALE, animale);
    setValue(CONFEZIONATO, confezionato);
  }


  public TipoCarnePesce getTipoCarnePesce() {
    try {
      return TipoCarnePesce.valueOf(getString(TIPO_CARNE_PESCE));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setTipoCarnePesce(TipoCarnePesce tipoCarnePesce) {
    TipoCarnePesce oldTipoCarnePesce = getTipoCarnePesce();
    setValue(TIPO_CARNE_PESCE, tipoCarnePesce);
    firePropertyChanged("tipoCarnePesce", oldTipoCarnePesce, tipoCarnePesce);
  }

  public boolean isDaAllevamento() {
    return getBoolean(DA_ALLEVAMENTO);
  }

  public void setDaAllevamento(boolean daAllevamento) {
    boolean oldDaAllevamento = isDaAllevamento();
    setValue(DA_ALLEVAMENTO, daAllevamento);
    firePropertyChanged("daAllevamento", oldDaAllevamento, daAllevamento);
  }

  public String getAnimale() {
    return getString(ANIMALE);
  }

  public void setAnimale(String animale) {
    String oldAnimale = getAnimale();
    setValue(ANIMALE, animale);
    firePropertyChanged("animale", oldAnimale, animale);
  }

  public boolean isConfezionato() {
    return getBoolean(CONFEZIONATO);
  }

  public void setConfezionato(boolean confezionato) {
    boolean oldConfezionato = isConfezionato();
    setValue(CONFEZIONATO, confezionato);
    firePropertyChanged("confezionato", oldConfezionato, confezionato);
  }
  
  public void copyTo(ProdottoSpecifico carnePesceSpecifico) {
    ((CarnePesceSpecifico)carnePesceSpecifico).setTipoCarnePesce(getTipoCarnePesce());
    ((CarnePesceSpecifico)carnePesceSpecifico).setDaAllevamento(isDaAllevamento());
    ((CarnePesceSpecifico)carnePesceSpecifico).setAnimale(getAnimale());
    ((CarnePesceSpecifico)carnePesceSpecifico).setConfezionato(isConfezionato());
  }
}