package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;

import it.unina.studenti.oortof.dao.SQLProductDAO;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Lotto;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.Prodotto;

public class ProdottiController implements Controller<Prodotto> {
  
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
    prodotti.add(prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitUpdate() {
    sqlProductDao.updateProducts(oldProdotto, prodotto);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }
  
  void commitSearch() {
    ObservedList<Prodotto> ritorno = sqlProductDao.getProduct(prodotto);
    ritorno.copyTo(prodotti);
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
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

  @Override
  public void setModel(Prodotto prodotto, ObservedList<Prodotto> prodotti) {
    this.prodotto = prodotto;
    this.prodotti = prodotti;
  }



  @Override
  public void listToDetail() {
    int index = ApplicationCounter.getInstance().getCounter();
    if (index > 0) {
      prodotti.get(index - 1).copyTo(prodotto);
    }
    else {
      prodotto.clear();
    }
  }

}
