package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;

public interface Controller<T extends ObservedModel> {
  public void rollback();
  public void insert();
  public void update();
  public void search();
  public void commit();
  public void delete();
  public void setModel(T observedModel, ObservedList<T> observedList);
  public void setModel(T observedModel);
  public void listToDetail();
}
