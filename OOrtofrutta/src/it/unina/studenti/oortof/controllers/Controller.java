package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.models.ObservedModel;

public interface Controller {
  public void rollback();
  public void insert();
  public void update();
  public void search();
  public void commit();
  public void delete();
  public void setModel(ObservedModel observedModel);
}
