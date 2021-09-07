package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Acquisto;
import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.Scontrino;

public class AcquistiTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Quantita'", "Importo pagato", "Id lotto", "Nome prodotto", "Tipologia prodotto"};
	private ObservedList<Scontrino> scontrini = new ObservedList<Scontrino>("scontrini");
	private List<Acquisto> acquisti = new ArrayList<>();
	private int index = -1;
	@Override
	public int getRowCount() {
		return acquisti.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Acquisto currentAcquisto = acquisti.get(rowIndex);
		String value = "";
		switch (columnIndex) {
			case 0: value = currentAcquisto.getQuantita() != null ? Float.toString(currentAcquisto.getQuantita()) : "";
			break;
			case 1: value = currentAcquisto.getPrezzo()!= null ? Float.toString(currentAcquisto.getPrezzo()) : "";
			break;
			case 2: value = currentAcquisto.getLotto() != null && currentAcquisto.getLotto().getId() != null ? Integer.toString(currentAcquisto.getLotto().getId()) : "";
			break;
			case 3: value = currentAcquisto.getNomeProdotto();
			break;
			case 4: value = currentAcquisto.getTipoProdotto();
		}
		
		return value;
	}
	
	  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    if (rowIndex == 0 && (columnIndex == 0 || columnIndex == 3)) {
	      if (columnIndex == 0) {
	    	  try {
	    		  this.scontrini.get(0).getAcquisti().get(0).setValue(Acquisto.PREZZO,(String)aValue);
	    	  } catch( Exception e) {
	    		  throw new RuntimeException(e);
	    	  }
	      } else {
	    	  this.scontrini.get(0).getAcquisti().get(0).setTipoProdotto((String)aValue);
	      }
	    }
	    ;
	  }
	
	@Override 
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
	    return false;
	  }

	public void isSearching(boolean searching) {
		if (searching) {
			
			this.acquisti.clear();
			this.acquisti.add(new Acquisto());
			dataChanged();
		} else {
			if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_ROLLBACK) {
				if (index != -1 && index < scontrini.size() + 1) {
					this.acquisti = scontrini.get(index).getAcquisti();
				} else {
					this.acquisti.clear();
				}

				dataChanged();
			} else if (ApplicationStatus.getInstance().getAction() == ApplicationStatus.ACTION_COMMIT){
				if (acquisti.size() > 0) {
					@SuppressWarnings("unused")
          Acquisto acq = this.acquisti.get(0);
					dataChanged();
				}
			}
		}
	}
	
	public void setIndex(int index) {
		if (index < scontrini.size()) {

			if (index != -1 && index < scontrini.size() + 1) {
				this.acquisti = scontrini.get(index).getAcquisti();
			} else {
				this.acquisti.clear();
			}
			this.index = index;
			dataChanged();
		}
		
	}
	
	public void setModel(ObservedList<Scontrino> scontrini) {
		this.scontrini = scontrini;

		
		scontrini.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				acquisti.clear();
				
				dataChanged();
				
			}
		});
		acquisti.clear();
		dataChanged();
	}
	
	private void dataChanged() {
		fireTableDataChanged();
	}

}
