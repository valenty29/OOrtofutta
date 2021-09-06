package it.unina.studenti.oortof.controllers;

import it.unina.studenti.oortof.dao.ClienteDAO;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.gui.CarrelloPanel;
import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationInfo;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.ObservedModel;
import it.unina.studenti.oortof.models.exception.DatabaseException;

public class CarrelloController implements Controller {
  private Carrello carrello;
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  public CarrelloController() {
    clienteDAO = new SQLClienteDAO();
  }
  @Override
  public void rollback() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
  }

  @Override
  public void insert() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
    
  }

  @Override
  public void update() {
    throw new RuntimeException("Update non permesso per il Carrello");
    
  }

  @Override
  public void search() {
    throw new RuntimeException("Search non permesso per il Carrello");
    
  }

  @Override
  public void commit() {
    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);

    if (cliente.getId() != null) {
      try {
        int idScontrino = clienteDAO.createScontrino(cliente, carrello.getLotti());
        ApplicationInfo.getInstance().setMessage(String.format("Acquisto contabilizzato: generato scontrino %d di importo %.2f", idScontrino, CarrelloPanel.calcolaImporto(carrello)), ApplicationInfo.LEVEL_LOG);
        carrello.clear();
        ApplicationCounter.getInstance().reset();
      }
      catch (DatabaseException de) {
        ApplicationInfo.getInstance().setMessage("Si e' verificato un errore imprevisto nel confermare l\'acquisto", ApplicationInfo.LEVEL_LOG);
      }
    }
    else {
      ApplicationInfo.getInstance().setMessage("Selezionare un cliente prima di proseguire con l\'acquisto", ApplicationInfo.LEVEL_ERROR);
    }

  }

  @Override
  public void delete() {
    carrello.clear();
  }

  public void setModel(Carrello carrello, Cliente cliente) {

    this.carrello = carrello;
    this.cliente = cliente;
  }

  @Override
  public void listToDetail() {
    //Non è previsto un listToDetail, si deve solo aggiornare il counter
  }

  @Override
  public void preDelete() {
    System.out.println("preDelete dal Controller");
    
  }

  
  
}
