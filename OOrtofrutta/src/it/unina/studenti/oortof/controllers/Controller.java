package it.unina.studenti.oortof.controllers;

import java.util.List;

import it.unina.studenti.oortof.models.ObservableList;
import it.unina.studenti.oortof.models.ObservedModel;

public interface Controller<T> {
  public void rollback();
  public void insert();
  public void update();
  public void search();
  public void commit();
  public void delete();
  public void setModel(ObservedModel observedModel, ObservableList observableList);
}
