package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.dao.ClienteDAO;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.ObservedModel;

public class CarrelloController implements Controller {
  private Carrello carrello;
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  public CarrelloController() {
    clienteDAO = new SQLClienteDAO();
  }
  @Override
  public void rollback() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void insert() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void search() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void commit() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setModel(ObservedModel observedModel, ObservedList observedList) {
    // TODO Auto-generated method stub

    
  }
  public void setModel(ObservedModel observedModel) {
    // TODO Auto-generated method stub


  }

  public void setModel(Carrello carrello, Cliente cliente) {

    this.carrello = carrello;
    this.cliente = cliente;
  }

  @Override
  public void listToDetail() {
    // TODO Auto-generated method stub

  }

  @Override
  public void preDelete() {
    // TODO Auto-generated method stub
    
  }

  
  
}
