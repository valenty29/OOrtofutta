package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;
import java.util.List;

import it.unina.studenti.oortof.dao.DBContext;
import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;

public class ClientiController implements Controller {
	private Cliente cliente;
	private ObservedList listCliente;
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
		switch (ApplicationStatus.getInstance().getStatus()) {
			case ApplicationStatus.STATUS_INSERT: {
				cliente = sqlClienteDAO.createCliente(cliente);
				break;
			}
				
			case ApplicationStatus.STATUS_UPDATE: {
				sqlClienteDAO.updateCliente(oldCliente, cliente);
				break;
			}	
				
			case ApplicationStatus.STATUS_SEARCH: {
				ObservedList<Cliente> clienti = sqlClienteDAO.getClienti(cliente);
				listCliente.clear();
				clienti.copyTo(listCliente);
				break;
			}
			
		}
		ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
	  }

	  @Override
	  public void delete() {
	  }

	  @Override
	  public void setModel(ObservedModel observedModel, ObservedList observedList) {
		  listCliente = observedList;
		  cliente = (Cliente)observedModel;
	  }

}
