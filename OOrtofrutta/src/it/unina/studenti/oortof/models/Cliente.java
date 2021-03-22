package it.unina.studenti.oortof.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;
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

  public Cliente() {
    attributes = new Object[11];
    setRaccoltaPunti(new RaccoltaPunti());
    attributes[SCONTRINI] = new ObservedList<Scontrino>("scontrini");
  }

  public Cliente(Integer id, String cf, String nome, String cognome, Date dataNascita, String luogoNascita, Genere genere, String email) {
    this();
    setValue(ID, id);
    setValue(CF, cf);
    setValue(NOME, nome);
    setValue(COGNOME, cognome);
    setValue(DATA_NASCITA, dataNascita);
    setValue(LUOGO_NASCITA, luogoNascita);
    setValue(GENERE, genere);
    setValue(EMAIL, email);
  }

  public Integer getId() {
    return getInteger(ID);
  }
  
  public void setId(Integer id) {
    Integer oldId = getId();
    if (equals(oldId, id)) {
      return;
    }
    setValue(ID, id);
    firePropertyChange("id", oldId, id);
  }
  
  public String getCF() {
    return getString(CF);
  }

  public void setCF(String cf) {
    String oldCF = getCF();
    if (equals(oldCF, cf)) {
      return;
    }
    setValue(CF, cf);
    firePropertyChange("cf", oldCF, cf);    
  }

  public String getNome() {
    return getString(NOME);
  }

  public void setNome(String nome) {
    String oldNome = getNome();
    if (equals(oldNome, nome)) {
      return;
    }
    setValue(NOME, nome);
    firePropertyChange("nome", oldNome, nome);
  }

  public String getCognome() {
    return (String)attributes[COGNOME];
  }

  public void setCognome(String cognome) {
    String oldCognome = getCognome();
    if(equals(oldCognome, cognome)){
      return;
    }
    setValue(COGNOME, cognome);
    firePropertyChange("cognome", oldCognome, cognome);
  }

  public Date getDataNascita() {
    return getDate(DATA_NASCITA);
  } 

  public void setDataNascita(Date dataNascita) {
    Date oldDataNascita = getDataNascita();
    if (equals(oldDataNascita, dataNascita)){
      return;
    }
    setValue(DATA_NASCITA, dataNascita);
    firePropertyChange("dataNascita", oldDataNascita, dataNascita);
  }

  public String getLuogoNascita() {
    return getString(LUOGO_NASCITA);
  }

  public void setLuogoNascita(String luogoNascita) {
    String oldLuogoNascita = getLuogoNascita();
    if (equals(oldLuogoNascita, luogoNascita)) {
      return;
    }
    setValue(LUOGO_NASCITA, luogoNascita);
    firePropertyChange("luogoNascita", oldLuogoNascita, luogoNascita);
  }

  public Genere getGenere() {
    try {
      return Genere.valueOf(getString(Cliente.GENERE));
    }
    catch (Exception e) {
      return null;
    }
  }

  public void setGenere(Genere genere) {
    Genere oldGenere = getGenere();
    if (equals(oldGenere, genere)) {
      return;
    }
    setValue(GENERE, genere);
    firePropertyChange("genere", oldGenere, genere);
  }

  public String getEmail() {
    return getString(EMAIL);
  }

  public void setEmail(String email) {
    String oldEmail = getEmail();
    if (equals(oldEmail, email)) {
      return;
    }
    setValue(EMAIL, email);
    firePropertyChange("email", oldEmail, email);
  }
  
  public RaccoltaPunti getRaccoltaPunti() {
    return (RaccoltaPunti)attributes[RACCOLTA_PUNTI];
  }

  public void setRaccoltaPunti(RaccoltaPunti raccoltaPunti) {
    RaccoltaPunti oldRaccoltaPunti = getRaccoltaPunti();
    if (equals(oldRaccoltaPunti, raccoltaPunti)) {
      return;
    }
    if (oldRaccoltaPunti != null) {
    	oldRaccoltaPunti.removePropertyChangeListener(this);
    }
    
    setValue(RACCOLTA_PUNTI, raccoltaPunti);
    raccoltaPunti.addPropertyChangeListener(this);
    firePropertyChange("raccoltaPunti", oldRaccoltaPunti, raccoltaPunti);
  }

  @SuppressWarnings("unchecked")
  public ObservedList<Scontrino> getScontrini() {
    return (ObservedList<Scontrino>)attributes[SCONTRINI];
  }

  @SuppressWarnings("unchecked")
  public void setScontrini(ObservedList<Scontrino> scontrini) {
    if (scontrini == attributes[SCONTRINI]) {
      return;
    }
    ObservedList<Scontrino> oldScontrini = (ObservedList<Scontrino>)attributes[SCONTRINI];
    oldScontrini.removePropertyChangeListener(this);
    attributes[SCONTRINI] = scontrini;
    scontrini.addPropertyChangeListener(this);
    firePropertyChange("scontrini", oldScontrini, scontrini);
  }

  
  @SuppressWarnings("unchecked")
  public void addScontrino(Scontrino scontrino) {
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).add(scontrino);
  }

  @SuppressWarnings("unchecked")
  public void addScontrino(int index, Scontrino scontrino) {
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).add(index, scontrino);
  }
  
  @SuppressWarnings("unchecked")
  public Scontrino getScontrinoAt(int index) {
    return ((ObservedList<Scontrino>)attributes[SCONTRINI]).get(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeScontrino(int index) {
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).remove(index);
  }
  
  @SuppressWarnings("unchecked")
  public void removeScontrino(Scontrino scontrino) {
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).remove(scontrino);
  }
  
  @SuppressWarnings("unchecked")
  public void clearScontrini() {
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).clear();
  }
  
  @SuppressWarnings("unchecked")
  public int getScontriniSize() {
    return ((ObservedList<Scontrino>)attributes[SCONTRINI]).size();
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
    
    if (equals(getId(), ((Cliente)other).getId())) {
      return true;
    }
    
    return equals(getCF(), ((Cliente)other).getCF());
  }

  @SuppressWarnings("unchecked")
  public void copyTo(ObservedModel cliente) {
    ((Cliente)cliente).setId(getId());
    ((Cliente)cliente).setCF(getCF());
    ((Cliente)cliente).setNome(getNome());
    ((Cliente)cliente).setCognome(getCognome());
    ((Cliente)cliente).setDataNascita(getDataNascita());
    ((Cliente)cliente).setLuogoNascita(getLuogoNascita());
    ((Cliente)cliente).setGenere(getGenere());
    ((Cliente)cliente).setEmail(getEmail());
    ((Cliente)cliente).setRaccoltaPunti(getRaccoltaPunti());
    ((ObservedList<Scontrino>)attributes[SCONTRINI]).copyTo(((Cliente)cliente).getScontrini());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    firePropertyChanged(evt);
  }
  
  public void clear() {
	  getRaccoltaPunti().clear();
	  clearScontrini();
	  setCF(null);
	  setCognome(null);
	  setDataNascita(null);
	  setEmail(null);
	  setGenere(null);
	  setNome(null);
	  setId(null);
	  setLuogoNascita(null);
  }
}

