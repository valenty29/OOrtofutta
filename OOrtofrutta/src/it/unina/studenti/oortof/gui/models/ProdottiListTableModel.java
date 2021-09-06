package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.entities.ObservedList;
import it.unina.studenti.oortof.models.entities.prodotti.Prodotto;
import it.unina.studenti.oortof.models.entities.prodotti.ProdottoCommon;

@SuppressWarnings("serial")
public class ProdottiListTableModel extends AbstractTableModel {
  
  private ObservedList<Prodotto> prodotti;

  public ProdottiListTableModel(ObservedList<Prodotto> prodotti) {
    this.prodotti = prodotti;
    prodotti.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        fireTableDataChanged();
      }
    });
  };
  
  @Override
  public int getRowCount() {
    return prodotti.size();
  }

  @Override
  public int getColumnCount() {
    return 5;
  }

  @Override
  public String getColumnName(int index) {
    switch (index) {
    case 0: return "Codice";
    case 1: return "Nome"; 
    case 2: return "Tipo"; 
    case 3: return "Prezzo"; 
    case 4: return "Sfuso"; 
    }
    return null;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ProdottoCommon pc = prodotti.get(rowIndex).getProdottoCommon();
    switch (columnIndex) {
      case 0: return toString(pc.getId());
      case 1: return toString(pc.getNome());
      case 2: return getTipoString(pc);
      case 3: return toString(pc.getPrezzo());
      case 4: return pc.isSfuso() ? "X" : "";
    }
    return null;
  }
  
  private static String toString(Object o) {
    return o != null ? o.toString() : "";
  }
  
  private static String getTipoString(ProdottoCommon pc) {
    if (pc.isAltro()) {
      return "Altro";
    }
    else if (pc.isBibita()) {
      return "Bibita";
    }
    else if (pc.isCarnePesce()) {
      return "Carne/Pesce";
    }
    else if (pc.isConserva()) {
      return "Conserva";
    }
    else if (pc.isFarinaceo()) {
      return "Farinaceo";
    }
    else if (pc.isFruttaVerdura()) {
      return "Frutta/Verdura";
    }
    else if (pc.isProdottoCaseario()) {
      return "Prodotto Caseario";
    }
    else if (pc.isUovo()) {
      return "Uova";
    }
    return "";
  }
  

}
