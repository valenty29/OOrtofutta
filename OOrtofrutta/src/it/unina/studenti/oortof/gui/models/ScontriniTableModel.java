package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.dao.ListHelpers;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.Scontrino;

public class ScontriniTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Data", "Totale"};
	private ObservedList<Scontrino> scontrini = new ObservedList<Scontrino>("scontrini");

	//private Scontrino scontrino;
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return scontrini.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Scontrino currentScontrino = scontrini.get(rowIndex);
		String value = "";
		switch (columnIndex) {
			case 0: value = currentScontrino.getDataOrario() != null ? currentScontrino.getDataOrario().toString() : "";
			break;
			case 1: value = currentScontrino.getTotale() != null ? String.format("%.2f", currentScontrino.getTotale()) : "";
			break;
		}
		
		return value;
	}
	
	@Override 
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public void setModel(ObservedList<Scontrino> scontrinoList) {
	    this.scontrini = scontrinoList;
	    
	    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
	      @Override
	      public void propertyChange(PropertyChangeEvent evt) {
	        dataChanged();
	      }
	    };
	    scontrinoList.addPropertyChangeListener(dataModelListener);
	  }
	
	private void dataChanged() {
		fireTableDataChanged();
	}
	

}
