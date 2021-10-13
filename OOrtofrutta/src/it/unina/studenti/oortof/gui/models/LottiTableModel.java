package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.application.ApplicationInfo;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Lotto;
import it.unina.studenti.oortof.models.entities.ObservedList;

@SuppressWarnings("serial")
public class LottiTableModel extends AbstractTableModel {
  
  private ObservedList<Lotto> lotti;

  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  private static int[] tableIndexToModelIndex = new int[] {Lotto.COD_LOTTO, Lotto.SCADENZA, Lotto.DISPONIBILITA, Lotto.DATA_PRODUZIONE, Lotto.COD_PAESE_ORIGINE, Lotto.DATA_MUNGITURA};


  public void setList(ObservedList<Lotto> lotti) {
    this.lotti = lotti;
    lotti.addPropertyChangeListener(new PropertyChangeListener() {
      
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        fireTableDataChanged();
      }
    });
  }
  
  @Override
  public int getRowCount() {
    return lotti != null ? lotti.size() : 0;
  }

  @Override
  public int getColumnCount() {
    return 6;
  }
  
  @Override
  public Class<?> getColumnClass(int index) {
    return String.class;
  }
  
  @Override
  public String getColumnName(int index) {
    switch (index) {
    case 0: return "Codice";
    case 1: return "Scadenza"; 
    case 2: return "Disponibilita'"; 
    case 3: return "Prodotto il"; 
    case 4: return "Origine"; 
    case 5: return "Mungitura";
    }
    return null;
  }
  
  public boolean isCellEditable(int rowIndex, int columnIndex) {

    if (ApplicationStatus.getInstance().isNavigation() || ApplicationStatus.getInstance().isSearch()) {
      return false;
    }

    if (rowIndex != (getRowCount() - 1)) {
      return false;
    }
    
    if (columnEditable[columnIndex] != true) {
      return false;
    }
    
    return true;
  }

  boolean[] columnEditable = {true, true, true, true, true, false};
  
  public void setColumnEditable(int columnIndex, boolean editable) {
    columnEditable[columnIndex] = editable;
  }
  
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Lotto lotto = lotti.get(rowIndex);
    switch (columnIndex) {
      case 0: return toString(lotto.getCodLotto());
      case 1: return dateToString(lotto.getScadenza());
      case 2: return toString(lotto.getDisponibilita());
      case 3: return dateToString(lotto.getDataProduzione());
      case 4: return toString(lotto.getCodPaeseOrigine());
      case 5: return dateToString(lotto.getDataMungitura());
    }
    return null;
  }

  public Lotto getSelectedLotto(int index)
  {
    return lotti.get(index);
  }

  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    if (columnIndex == 1 || columnIndex == 3 ||columnIndex == 5) {
      try {
        aValue = sdf.parse((String)aValue);
      }
      catch (Exception ex) {
        aValue = "";
      }
    }
    if (columnIndex == 2) {
      try {
        aValue = Math.abs(Float.parseFloat((String)aValue));
      }
      catch (Exception e) {
        ApplicationInfo.getInstance().setMessage("Quantità non valida", 0);
      }
    }
    lotti.get(rowIndex).setValue(tableIndexToModelIndex[columnIndex], aValue);
  }

  private static String dateToString(Date d) {
    return d != null ? sdf.format(d) : "";
  }

  private static String toString(Object o) {
    return o != null ? o.toString() : "";
  }
}
