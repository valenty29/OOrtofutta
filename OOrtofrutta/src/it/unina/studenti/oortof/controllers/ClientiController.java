package it.unina.studenti.oortof.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unina.studenti.oortof.dao.DBContext;
import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.models.*;

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
				} catch (DatabaseException de) {
					ApplicationInfo.getInstance().setMessage(de.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
					listCliente.remove(listCliente.indexOf(cliente));
				}
				break;
			}
				
			case ApplicationStatus.STATUS_UPDATE: {
				try {
					Cliente updatedCliente = sqlClienteDAO.updateCliente(oldCliente, cliente);
					listCliente.set(listCliente.indexOf(cliente), updatedCliente);

				} catch (DatabaseException de) {
					ApplicationInfo.getInstance().setMessage(de.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
					listCliente.set(listCliente.indexOf(cliente), oldCliente);
				} catch (ValidationException ve) {

				}
				
				break;
			}	
				
			case ApplicationStatus.STATUS_SEARCH: {
				try {
					ObservedList<Cliente> clienti = sqlClienteDAO.getClienti(cliente);
					listCliente.clear();
					clienti.copyTo(listCliente);
					System.out.println();
				}
				catch (ValidationException ve) {
					ApplicationInfo.getInstance().setMessage(ve.toString(), ApplicationInfo.LEVEL_ERROR);
				}
				catch (Exception e) {
					
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
		int index = ApplicationCounter.getInstance().getCounter();
		if (index > 0 && index < listCliente.size() + 1) {
			listCliente.get(index - 1).copyTo(cliente);
		}
		else {
			cliente.clear();
		}
    }

    @Override
    public void preDelete() {
      // TODO Auto-generated method stub
      
    }

}
