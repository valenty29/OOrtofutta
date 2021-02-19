package it.unina.studenti.oortof.models;

public class RaccoltaPunti {
  private int id;
  private Cliente cliente;
  private int fruttaVerdura;
  private int prodottoCaseario;
  private int farinaceo;
  private int uovo;
  private int carnePesce;
  private int bibita;
  private int conserva;
  private int altro;
  
  public RaccoltaPunti(int id, Cliente cliente, int fruttaVerdura, int prodottoCaseario, int farinaceo, int uovo, int carnePesce, int bibita, int conserva, int altro) {
    this.id = id;
    this.cliente = cliente;
    this.fruttaVerdura = fruttaVerdura;
    this.prodottoCaseario = prodottoCaseario;
    this.farinaceo = farinaceo;
    this.uovo = uovo;
    this.carnePesce = carnePesce;
    this.bibita = bibita;
    this.conserva = conserva;
    this.altro = altro;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public int getFruttaVerdura() {
    return fruttaVerdura;
  }

  public void setFruttaVerdura(int fruttaVerdura) {
    this.fruttaVerdura = fruttaVerdura;
  }

  public int getProdottoCaseario() {
    return prodottoCaseario;
  }

  public void setProdottoCaseario(int prodottoCaseario) {
    this.prodottoCaseario = prodottoCaseario;
  }

  public int getFarinaceo() {
    return farinaceo;
  }

  public void setFarinaceo(int farinaceo) {
    this.farinaceo = farinaceo;
  }

  public int getUovo() {
    return uovo;
  }

  public void setUovo(int uovo) {
    this.uovo = uovo;
  }

  public int getCarnePesce() {
    return carnePesce;
  }

  public void setCarnePesce(int carnePesce) {
    this.carnePesce = carnePesce;
  }

  public int getBibita() {
    return bibita;
  }

  public void setBibita(int bibita) {
    this.bibita = bibita;
  }

  public int getConserva() {
    return conserva;
  }

  public void setConserva(int conserva) {
    this.conserva = conserva;
  }

  public int getAltro() {
    return altro;
  }

  public void setAltro(int altro) {
    this.altro = altro;
  }
  
  public int getTotale() {
    return fruttaVerdura + prodottoCaseario + farinaceo + uovo + carnePesce + bibita + conserva + altro;          
  }
}


