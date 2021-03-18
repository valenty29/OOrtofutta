package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends ObservedModel implements PropertyChangeListener{
  
  public static final int ID = 0;            //Integer
  public static final int CF = 1;            //String
  public static final int NOME = 2;          //String
  public static final int COGNOME = 3;       //String
  public static final int DATA_NASCITA = 4;  //Date
  public static final int LUOGO_NASCITA = 5; //String
  public static final int GENERE = 6;        //Genere
  public static final int EMAIL = 7;         //String
  public static final int TOTALE_PUNTI = 8;  //String
  public static final int SCONTRINI = 9;     //String
  
  private Cliente() {
    attributes = new Object[10];
  }
  
  public Cliente(int id, String cf, String nome, String cognome, Date dataNascita, String luogoNascita, Genere genere, String email, Integer totalePunti, List<Scontrino> scontrini) {
    this();
    setValue(ID, id);
    setValue(CF, cf);
    setValue(NOME, nome);
    setValue(COGNOME, cognome);
    setValue(DATA_NASCITA, dataNascita);
    setValue(LUOGO_NASCITA, luogoNascita);
    setValue(GENERE, genere);
    setValue(EMAIL, email);
    setValue(TOTALE_PUNTI, totalePunti);
    setValue(SCONTRINI, scontrini);
  }

  public Integer getId() {
    return getInteger(ID);
  }
  
  public void setId(Integer id) {
    Integer oldId = getId();
    setValue(ID, id);
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCF() {
    return getString(CF);
  }

  public void setCF(String cf) {
    String oldCF = getCF();
    setValue(CF, cf);
    firePropertyChanged("cf", oldCF, cf);    
  }

  public String getNome() {
    return getString(NOME);
  }

  public void setNome(String nome) {
    String oldNome = getNome();
    setValue(NOME, nome);
    firePropertyChanged("nome", oldNome, nome);
  }

  public String getCognome() {
    return getString(COGNOME);
  }

  public void setCognome(String cognome) {
    String oldCognome = getCognome();
    setValue(COGNOME,cognome);
    firePropertyChanged("cognome", oldCognome, cognome);
  }

//  public Date getDataNascita() {
//    return dataNascita;
//  }
//
//  public void setDataNascita(Date dataNascita) {
//    Date oldDataNascita = this.dataNascita;
//    this.dataNascita = dataNascita;
//    firePropertyChanged("dataNascita", oldDataNascita, dataNascita);
//  }

  public String getLuogoNascita() {
    return getString(LUOGO_NASCITA);
  }

  public void setLuogoNascita(String luogoNascita) {
    String oldLuogoNascita = getLuogoNascita();
    setValue(LUOGO_NASCITA, luogoNascita);
    firePropertyChanged("luogoNascita", oldLuogoNascita, luogoNascita);
  }

  public Genere getGenere() {
    return Genere.valueOf(getString(Cliente.GENERE));
  }

  public void setGenere(Genere genere) {
    Genere oldGenere = getGenere();
    setValue(GENERE, genere);
    firePropertyChanged("genere", oldGenere, genere);
  }

  public String getEmail() {
    return getString(EMAIL);
  }

  public void setEmail(String email) {
    String oldEmail = getEmail();
    setValue(EMAIL, email);
    firePropertyChanged("email", oldEmail, email);
  }

  public Integer getTotalePunti() {
    return getInteger(TOTALE_PUNTI);
  }

  public void setTotalePunti(Integer totalePunti) {
    Integer oldTotalePunti = getTotalePunti();
    setValue(TOTALE_PUNTI, totalePunti);
    firePropertyChanged("totalePunti", oldTotalePunti, totalePunti);
  }
  
  public List<Scontrino> getScontrini() {
    return (List<Scontrino>)attributes[SCONTRINI];
  }

  public void setScontrini(List<Scontrino> scontrini) {
    if (scontrini == attributes[SCONTRINI]) {
      return;
    }
    for (Scontrino scontrino : scontrini) {
      scontrino.addPropertyChangeListener(this);
    }
    List<Scontrino> oldScontrini = (List<Scontrino>)attributes[SCONTRINI];
    attributes[SCONTRINI] = scontrini;
    firePropertyChanged("scontrini", oldScontrini, scontrini);
  }
  
  public void addLotto(Scontrino scontrino) {
    ((List<Scontrino>)attributes[SCONTRINI]).add(scontrino);
    firePropertyChanged("scontrini", null, scontrino);
    scontrino.addPropertyChangeListener(this);
  }

  public void addLotto(int index, Scontrino scontrino) {
    ((List<Scontrino>)attributes[SCONTRINI]).add(index, scontrino);
    firePropertyChanged("scontrini", index, scontrino);
    scontrino.addPropertyChangeListener(this);
  }
  
  public Scontrino getScontrinoAt(int index) {
    return ((List<Scontrino>)attributes[SCONTRINI]).get(index);
  }
  
  public void removeScontrino(int index) {
    Scontrino removedScontrino = ((List<Scontrino>)attributes[SCONTRINI]).remove(index);
    removedScontrino.removePropertyChangeListener(this);
    firePropertyChanged("scontrini", removedScontrino, index);
  }
  
  public void removeScontrino(Scontrino scontrino) {
    int toRemoveIndex = ((List<Scontrino>)attributes[SCONTRINI]).indexOf(scontrino);
    if (toRemoveIndex >= 0) {
      removeScontrino(toRemoveIndex);
    }
  }
  
  public void clearScontrini() {
    if (((List<Scontrino>)attributes[SCONTRINI]).size() == 0) {
      return;
    }
    List<Scontrino> oldScontrini = new ArrayList<Scontrino>(((List<Scontrino>)attributes[SCONTRINI]));
    ((List<Scontrino>)attributes[SCONTRINI]).clear();
    firePropertyChanged("scontrini", oldScontrini, ((List<Scontrino>)attributes[SCONTRINI]));
  }
  
  public int getScontriniSize() {
    return ((List<Scontrino>)attributes[SCONTRINI]).size();
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    sb.append(getCognome());
    sb.append(" ");
    sb.append(getNome());
    return sb.toString();
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Cliente)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if (getId() > 0 && ((Cliente)other).getId() > 0) {
      return getId() == ((Cliente)other).getId();
    }
    return getCF().equals(((Cliente)other).getCF());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
}

