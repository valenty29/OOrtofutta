package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;
import java.util.List;

import it.unina.studenti.oortof.dao.DBContext;
import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ObservableList;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;

public class ClientiController implements Controller {
	private Cliente cliente;
	private List<Cliente> listCliente;
	private Cliente oldCliente = new Cliente();
	private SQLClienteDAO sqlClienteDAO;
	
	public ClientiController(DBContext dbContext) {
		sqlClienteDAO = new SQLClienteDAO(dbContext);
	}
	
	@Override
	  public void rollback() {
	    oldCliente.copyTo(cliente);
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
	  }

	  @Override
	  public void insert() {
		cliente.copyTo(oldCliente);
		cliente.clear();
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_INSERT);
	  }

	  @Override
	  public void update() {
		cliente.copyTo(oldCliente);
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_UPDATE);
	  }

	  @Override
	  public void search() {
	    cliente.copyTo(oldCliente);
	    cliente.clear();
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
	  }

	  @Override
	  public void commit() {
		List<Cliente> clienti = sqlClienteDAO.getClienti(cliente);
		ListHelpers.makeCopy(clienti, listCliente);
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION
	    );
	  }

	  @Override
	  public void delete() {
	  }

	  @Override
	  public void setModel(ObservedModel observedModel, ObservableList observedList) {
		  listCliente = (List)observedList;
		  cliente = (Cliente)observedModel;
	  }

}
