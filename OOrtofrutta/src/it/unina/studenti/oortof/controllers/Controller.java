package it.unina.studenti.oortof.controllers;

public interface Controller {
  public void rollback();
  public void insert();
  public void update();
  public void search();
  public void commit();
  public void delete();
}
