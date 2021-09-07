package it.unina.studenti.oortof.controllers;

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
