package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.ObservedModel;

public interface Controller {
  void rollback();
  void insert();
  void update();
  void search();
  void commit();
  void delete();
  void listToDetail();
  public void preDelete();
}
