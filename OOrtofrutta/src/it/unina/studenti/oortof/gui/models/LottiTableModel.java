package it.unina.studenti.oortof.gui.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.Lotto;
import it.unina.studenti.oortof.models.LottoCaseario;

public class LottiTableModel extends AbstractTableModel {
  
  List<Lotto> lotti;

  public LottiTableModel() {
    lotti = new ArrayList<Lotto>();
  }
  
  public void setList(List<Lotto> lotti) {
    this.lotti = lotti;
  }
  
  @Override
  public int getRowCount() {
    return lotti.size();
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
    case 2: return "Disponibilità"; 
    case 3: return "Prodotto il"; 
    case 4: return "Origine"; 
    case 5: return "Mungitura";
    }
    return null;
  }
  
  static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  
  static String dateToString(Date d) {
    return d != null ? sdf.format(d) : "";
  }

  static String toString(Object o) {
    return o != null ? o.toString() : "";
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
      case 5: return dateToString(lotto instanceof LottoCaseario ? ((LottoCaseario)lotto).getDataMungitura() : null);
    }
    return null;
  }

}
