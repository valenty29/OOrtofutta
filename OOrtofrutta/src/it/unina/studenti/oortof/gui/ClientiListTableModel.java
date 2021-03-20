package it.unina.studenti.oortof.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ObservedList;

public class ClientiListTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Nome", "Cognome", "CF", "eMail", "Data di nascita", "Luogo di nascita", "Genere"};
	private ObservedList clienteList = new ObservedList("clienti");
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return clienteList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Cliente cliente = (Cliente)clienteList.get(rowIndex);
		String value = "";
		switch (columnIndex) {
			case 0: value = cliente.getNome();
			break;
			case 1: value = cliente.getCognome();
			break;
			case 2: value = cliente.getCF();
			break;
			case 3: value = cliente.getEmail();
			break;
			case 4: value = cliente.getDataNascita().toString();
			break;
			case 5: value = cliente.getLuogoNascita().toString();
			break;
			case 6: value = cliente.getNome();
			break;
		}
		
		return value;
	}
	
	@Override 
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public void setModel(ObservedList clienteList) {
	    this.clienteList = clienteList;
	    
	    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
	      @Override
	      public void propertyChange(PropertyChangeEvent evt) {
	        dataChanged();
	      }
	    };
	    clienteList.addPropertyChangeListener(dataModelListener);
	  }
	
	private void dataChanged() {
		fireTableDataChanged();
	}

}
