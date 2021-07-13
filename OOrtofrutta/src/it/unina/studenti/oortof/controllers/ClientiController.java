package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;
import java.util.List;

import it.unina.studenti.oortof.dao.DBContext;
import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.Acquisto;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.ObservedModel;
import it.unina.studenti.oortof.models.Prodotto;
import it.unina.studenti.oortof.models.Scontrino;

public class ClientiController implements Controller {
	private Cliente cliente;
	private ObservedList<Cliente> listCliente;
	private Cliente oldCliente = new Cliente();
	private SQLClienteDAO sqlClienteDAO;
	
	public ClientiController() {
		sqlClienteDAO = new SQLClienteDAO();
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
	    Scontrino scontrino = new Scontrino();
	    scontrino.addAcquisto(new Acquisto());
	    cliente.addScontrino(scontrino);
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_SEARCH);
	  }

	  @Override
	  public void commit() {
		switch (ApplicationStatus.getInstance().getStatus()) {
			case ApplicationStatus.STATUS_INSERT: {
				try {
					Cliente newCliente = sqlClienteDAO.createCliente(cliente);
					listCliente.add(newCliente);
					newCliente.copyTo(cliente);
					
				} catch(Exception e) {
					System.out.println(1);
				}
				
				break;
			}
				
			case ApplicationStatus.STATUS_UPDATE: {
				try {
					sqlClienteDAO.updateCliente(oldCliente, cliente);
					listCliente.remove(cliente);
					listCliente.add(cliente);
				} catch (Exception e) {
					
				}
				
				break;
			}	
				
			case ApplicationStatus.STATUS_SEARCH: {
				try {
					ObservedList<Cliente> clienti = sqlClienteDAO.getClienti(cliente);
					listCliente.clear();




					clienti.copyTo(listCliente);
				} catch (Exception e) {
					
				}
				
				break;
			}
			
		}
		
	    ApplicationStatus.getInstance().setStatus(ApplicationStatus.STATUS_NAVIGATION);
	  }

	  @Override
	  public void delete() {
		  try {
			  sqlClienteDAO.deleteCliente(cliente);
			  listCliente.remove(cliente);
			  cliente.clear();
		  } catch(Exception e) {
			  
		  }
		  ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
	  }

	  @Override
	  public void setModel(ObservedModel observedModel, ObservedList observedList) {
		  listCliente = observedList;
		  cliente = (Cliente)observedModel;
	  }

	  public void setModel(ObservedModel observedModel) {

	  }

    @Override
    public void listToDetail() {
      // TODO Auto-generated method stub

    }

    @Override
    public void preDelete() {
      // TODO Auto-generated method stub
      
    }

}
