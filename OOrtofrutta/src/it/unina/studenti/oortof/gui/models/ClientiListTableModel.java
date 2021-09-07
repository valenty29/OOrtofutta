package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.ObservedList;

@SuppressWarnings("serial")
public class ClientiListTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"Nome", "Cognome", "CF", "eMail", "Data di nascita", "Luogo di nascita", "Genere"};
	private ObservedList<Cliente> clienteList = new ObservedList<Cliente>("clienti");
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	@Override
	public int getRowCount() {
		return clienteList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
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
			case 4: value = formatter.format(cliente.getDataNascita());
			break;
			case 5: value = cliente.getLuogoNascita().toString();
			break;
			case 6: value = cliente.getGenere().toString();
			break;
		}
		
		return value;
	}
	
	@Override 
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public void setModel(ObservedList<Cliente> clienteList) {
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
