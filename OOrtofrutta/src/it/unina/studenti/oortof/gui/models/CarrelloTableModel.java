package it.unina.studenti.oortof.gui.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Lotto;
import it.unina.studenti.oortof.models.ObservedList;

public class CarrelloTableModel extends AbstractTableModel {

    ObservedList<Lotto> lotti;

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
            case 2: return "Disponibilit�";
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

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (ApplicationStatus.getInstance().isNavigation() || ApplicationStatus.getInstance().isSearch()) {
            return false;
        }
        if (rowIndex != (getRowCount() - 1)) {
            return false;
        }
        return true;
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
        }
        return null;
    }

    static int[] tableIndexToModelIndex = new int[] {Lotto.COD_LOTTO, Lotto.SCADENZA, Lotto.DISPONIBILITA, Lotto.DATA_PRODUZIONE, Lotto.COD_PAESE_ORIGINE, Lotto.DATA_MUNGITURA};

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
        lotti.get(rowIndex).setValue(tableIndexToModelIndex[columnIndex], aValue);
    }
}
