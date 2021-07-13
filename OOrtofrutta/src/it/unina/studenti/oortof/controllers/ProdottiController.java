package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;

import it.unina.studenti.oortof.dao.SQLProductDAO;
import it.unina.studenti.oortof.models.*;

public class ProdottiController implements Controller<Prodotto> {

  private Carrello carrello;
  private Prodotto oldProdotto = new Prodotto();
  private Prodotto prodotto;
  private ObservedList<Prodotto> prodotti;
  
  private SQLProductDAO sqlProductDao;
  
  public ProdottiController() {
	  sqlProductDao = new SQLProductDAO();
  }
  
  @Override
  public void rollback() {
    oldProdotto.copyTo(prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void insert() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    Lotto lotto = new Lotto();
    prodotto.getProdottoCommon().addLotto(lotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
  }

  @Override
  public void update() {
    prodotto.copyTo(oldProdotto);
    Lotto lotto = new Lotto();
    prodotto.getProdottoCommon().addLotto(lotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_UPDATE);
  }

  @Override
  public void search() {
    prodotto.copyTo(oldProdotto);
    prodotto.clear();
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
  }

  void commitInsert() {
    sqlProductDao.createProduct(prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitUpdate() {
    sqlProductDao.updateProducts(oldProdotto, prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitSearch() {
    try {
      Prodotto prodottoDAO = new Prodotto();
      prodotto.copyTo(prodottoDAO);
      ObservedList<Prodotto> ritorno = sqlProductDao.getProduct(prodottoDAO);
      ritorno.copyTo(prodotti);
      ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void commit() {
    switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.STATUS_INSERT: commitInsert(); break;
      case ApplicationStatus.STATUS_UPDATE: commitUpdate(); break;
      case ApplicationStatus.STATUS_SEARCH: commitSearch(); break;
    }
  }
  
  @Override
  public void preDelete() {
  }

  

  @Override
  public void delete() {
    ArrayList<Prodotto> toDelete = new ArrayList<>();
    toDelete.add(prodotto);
    sqlProductDao.deleteProducts(toDelete);
    prodotti.remove(prodotto);
  }

  public void setModel(Prodotto prodotto, ObservedList<Prodotto> prodotti, Carrello carrello) {
    this.carrello = carrello;
    this.prodotto = prodotto;
    this.prodotti = prodotti;
  }

  @Override
  public void setModel(Prodotto prodotto) {

  }

  @Override
  public void setModel(Prodotto prodotto, ObservedList<Prodotto> prodotti) {

  }

  @Override
  public void listToDetail() {
    int index = ApplicationCounter.getInstance().getCounter();
    if (index > 0) {
      prodotti.get(index - 1).copyTo(prodotto);
      prodotto.getProdottoCommon().getLotti().forEach(lotto -> {
        carrello.getLotti().stream().filter(lotto1 -> {
          System.out.println("AOOO");
          if (lotto1.getId().equals(lotto.getId())) {
            lotto.setDisponibilita(lotto.getDisponibilita() - lotto1.getDisponibilita());
            return true;
          }
          return false;
        }).findFirst();
      });
    }
    else {
      prodotto.clear();
    }
  }

}
