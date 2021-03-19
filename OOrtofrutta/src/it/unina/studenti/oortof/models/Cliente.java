package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cliente extends ObservedModel implements PropertyChangeListener{


  public static final int ID = 0;   // Float
  public static final int CF = 1;             // Boolean
  public static final int NOME = 2;           // TipoBibita
  public static final int COGNOME = 3;
  public static final int DATA_NASCITA = 4;
  public static final int LUOGO_NASCITA = 5;
  public static final int GENERE = 6;
  public static final int EMAIL = 7;
  public static final int RACCOLTA_PUNTI = 9;
  public static final int SCONTRINI = 10;

  public Cliente(){
    attributes = new Object[11];
    attributes[SCONTRINI] = new ArrayList<Scontrino>();
  }

  public Cliente(Integer id, String cf, String nome, String cognome, LocalDate dataNascita, String luogoNascita, Genere genere, String email, RaccoltaPunti raccoltaPunti) {
    this();
    setValue(ID, id);
    setValue(CF, cf);
    setValue(NOME, nome);
    setValue(COGNOME, cognome);
    setValue(DATA_NASCITA, dataNascita);
    setValue(LUOGO_NASCITA, luogoNascita);
    setValue(GENERE, genere);
    setValue(EMAIL, email);
    setValue(RACCOLTA_PUNTI, raccoltaPunti);
  }

  public RaccoltaPunti getRaccoltaPunti() {
    return (RaccoltaPunti)attributes[RACCOLTA_PUNTI];
  }

  public void setRaccoltaPunti(RaccoltaPunti raccoltaPunti) {

    if (equals(raccoltaPunti, attributes[RACCOLTA_PUNTI])) {
      return;
    }
    RaccoltaPunti oldRaccoltaPunti = (RaccoltaPunti)attributes[RACCOLTA_PUNTI];
    attributes[RACCOLTA_PUNTI] = raccoltaPunti;
    firePropertyChanged("raccoltaPunti", oldRaccoltaPunti, raccoltaPunti);
  }

  public Integer getId() {
    return getInteger(ID);
  }
  
  public void setId(int id) {
    if (equals(id, attributes[ID])) {
      return;
    }
    Integer oldId = (Integer)attributes[ID];
    setValue(ID, id);
    firePropertyChanged("id", oldId, id);
  }
  
  public String getCF() {
    return (String)attributes[CF];
  }

  public void setCF(String cf) {
   if(equals(cf, attributes[CF])){
     return;
   }
   String oldCF = (String)attributes[CF];
   setValue(CF, cf);
   firePropertyChanged("cf", oldCF, cf);
  }

  public String getNome() {
    return (String)attributes[NOME];
  }

  public void setNome(String nome) {
    if(equals(nome, attributes[NOME])){
      return;
    }
    String oldNome = (String)attributes[NOME];
    setValue(NOME, nome);
    firePropertyChanged("nome", oldNome, nome);
  }

  public String getCognome() {
    return (String)attributes[COGNOME];
  }

  public void setCognome(String cognome) {
    if(equals(cognome, attributes[COGNOME])){
      return;
    }
    String oldCognome = (String)attributes[COGNOME];
    setValue(COGNOME, cognome);
    firePropertyChanged("cognome", oldCognome, cognome);
  }

  public LocalDate getDataNascita() {
    return LocalDate.parse((String)attributes[DATA_NASCITA]);
  }

  public void setDataNascita(LocalDate dataNascita) {
    if(equals(dataNascita, attributes[DATA_NASCITA])){
      return;
    }
    LocalDate oldData = (LocalDate)attributes[DATA_NASCITA];
    setValue(DATA_NASCITA, dataNascita);
    firePropertyChanged("dataNascita", oldData, dataNascita);
  }

  public String getLuogoNascita() {
    return (String)attributes[LUOGO_NASCITA];
  }

  public void setLuogoNascita(String luogoNascita) {
    if(equals(luogoNascita, attributes[LUOGO_NASCITA])){
      return;
    }
    String oldLuogoNascita = (String)attributes[LUOGO_NASCITA];
    attributes[LUOGO_NASCITA] = luogoNascita;
    firePropertyChanged("luogoNascita", oldLuogoNascita, luogoNascita);
  }

  public Genere getGenere() {
    return Genere.valueOf(getString(GENERE));
  }

  public void setGenere(Genere genere) {
    if(equals(genere, attributes[GENERE])){
      return;
    }
    Genere oldGenere = (Genere)attributes[GENERE];
    setValue(GENERE, genere);
    firePropertyChanged("genere", oldGenere, genere);
  }

  public String getEmail() {
    return (String)attributes[EMAIL];
  }

  public void setEmail(String email) {
    if(equals(email, attributes[EMAIL])){
      return;
    }
    String oldEmail = (String)attributes[EMAIL];
    attributes[EMAIL] = email;
    firePropertyChanged("email", oldEmail, email);
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
    List<Lotto> oldScontrini  = (List<Lotto>)attributes[SCONTRINI];
    attributes[SCONTRINI] = scontrini;
    firePropertyChanged("scontrini", oldScontrini, scontrini);
  }
  
  public void addScontrino(Scontrino scontrino) {
    ( (List<Scontrino>)attributes[SCONTRINI]).add(scontrino);
    firePropertyChanged("scontrino", null, scontrino);
    scontrino.addPropertyChangeListener(this);
  }

  public void addScontrino(int index, Scontrino scontrino) {
    ( (List<Scontrino>)attributes[SCONTRINI]).add(index, scontrino);
    firePropertyChanged("scontrino", index, scontrino);
    scontrino.addPropertyChangeListener(this);
  }
  
  public Scontrino getScontrinoAt(int index) {
    return ((List<Scontrino>)attributes[SCONTRINI]).get(index);
  }
  
  public void removeScontrino(int index) {
    Scontrino removedScontrino = ((List<Scontrino>)attributes[SCONTRINI]).remove(index);
    removedScontrino.removePropertyChangeListener(this);
    firePropertyChanged("scontrino", removedScontrino, index);
  }
  
  public void removeScontrino(Scontrino scontrino) {
    int toRemoveIndex = ((List<Scontrino>)attributes[SCONTRINI]).indexOf(scontrino);
    if (toRemoveIndex >= 0) {
      removeScontrino(toRemoveIndex);
    }
  }
  public int getScontriniSize() {
    return ((List<Scontrino>)attributes[SCONTRINI]).size();
  }
  
  public String toString() {
    return attributes[COGNOME] + " " + attributes[NOME];
  }
  
  public boolean equals(Object other) {
    if (!(other instanceof Cliente)) {
      return false;
    }
    if (this == other) {
      return true;
    }
    if ( (Integer)attributes[ID] > 0 &&  (Integer)((Cliente)other).attributes[ID] > 0) {
      return (Integer)attributes[ID] == (Integer)((Cliente)other).attributes[ID];
    }

    return attributes[CF].equals(((Cliente)other).attributes[CF] );
  }

  public void copyTo(Cliente cliente) {
    cliente.setId(getId());
    cliente.setCF(getCF());
    cliente.setNome(getNome());
    cliente.setCognome(getCognome());
    cliente.setDataNascita(getDataNascita());
    cliente.setLuogoNascita(getLuogoNascita());
    cliente.setGenere(getGenere());
    cliente.setEmail(getEmail());
    cliente.setRaccoltaPunti(getRaccoltaPunti());
    cliente.getScontrini().clear();
    for (Scontrino scontrino: ((List<Scontrino>)attributes[SCONTRINI])) {
      Scontrino newScontrino = new Scontrino();
      scontrino.copyTo(newScontrino);
      ((List<Scontrino>)attributes[SCONTRINI]).add(newScontrino);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
}

