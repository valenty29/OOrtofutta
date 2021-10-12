package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.gui.models.CarrelloTableModel;
import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.Lotto;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  private JScrollPane scrollPane;
  private JPanel northCarrelloPanel;
  private JLabel nomeCognomeLabel;
  private JLabel importoLabel;
  private Carrello carrello;
  private Cliente cliente;

  public CarrelloPanel() {

    scrollPane = new JScrollPane();
    JPanel southCarrelloPanel = new JPanel();
    setLayout(new BorderLayout(0, 0));
    add(scrollPane, BorderLayout.CENTER);

    northCarrelloPanel = new JPanel();
    add(northCarrelloPanel, BorderLayout.NORTH);

    nomeCognomeLabel = new JLabel("Seleziona un Cliente");
    northCarrelloPanel.add(nomeCognomeLabel);
    add(southCarrelloPanel, BorderLayout.SOUTH);

    carrelloTable = new JTable();
    carrelloTable.setBorder(new LineBorder(new Color(0, 0, 0)));

    scrollPane.setViewportView(carrelloTable);
    GridBagLayout gbl_southCarrelloPanel = new GridBagLayout();
    gbl_southCarrelloPanel.columnWidths = new int[] {119, 113, 43, 0};
    gbl_southCarrelloPanel.rowHeights = new int[] {23, 0};
    gbl_southCarrelloPanel.columnWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
    gbl_southCarrelloPanel.rowWeights = new double[] {0.0, Double.MIN_VALUE};
    southCarrelloPanel.setLayout(gbl_southCarrelloPanel);

    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });

    carrelloTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent event) {
        if (ApplicationCounter.getInstance().getCounter() != (carrelloTable.getSelectedRow() + 1)) {
          if (event.getFirstIndex() == ApplicationCounter.getInstance().getCounter() - 1 && carrelloTable.getSelectedRow() == -1) {
            ApplicationCounter.getInstance().setCounter(ApplicationCounter.getInstance().getCounter());
          }
          else {
            ApplicationCounter.getInstance().setCounter(carrelloTable.getSelectedRow() + 1);
          }

          ApplicationCounter.getInstance().setLimit(carrelloTable.getRowCount());
        }
      }
    });

    importoLabel = new JLabel("Importo:          ");
    importoLabel.setBorder(new LineBorder(Color.GRAY));
    importoLabel.setBackground(Color.WHITE);
    importoLabel.setForeground(Color.BLACK);
    importoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    GridBagConstraints gbc_importoLabel = new GridBagConstraints();
    gbc_importoLabel.anchor = GridBagConstraints.EAST;
    gbc_importoLabel.gridx = 2;
    gbc_importoLabel.gridy = 0;
    southCarrelloPanel.add(importoLabel, gbc_importoLabel);
    carrelloTable.setModel(new CarrelloTableModel());

    CarrelloTableModel carrelloModel = (CarrelloTableModel)carrelloTable.getModel();
    carrelloTable.addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        if (carrelloTable.getSelectedRow() == -1) {
          return;
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && ApplicationStatus.getInstance().isNavigation()) {
          Lotto lotto = carrelloModel.getSelectedLotto(carrelloTable.getSelectedRow());
          carrello.remove(lotto);
        }
      }
    });

  }

  public void setModel(Carrello carrello, Cliente cliente) {
    this.carrello = carrello;
    this.cliente = cliente;
    PropertyChangeListener dataModelListener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        dataModelChanged(evt);
      }
    };
    ((CarrelloTableModel)carrelloTable.getModel()).setList(carrello.getLotti());
    carrello.addPropertyChangeListener(dataModelListener);
    cliente.addPropertyChangeListener(dataModelListener);
  }

  public static float calcolaImporto(Carrello carrello) {
    float importo = 0f;
    for (Lotto lotto : carrello.getLotti()) {
      importo += lotto.getDisponibilita() * (lotto.getProdottoCommon().getPrezzo());
    }
    return importo;

  }

  private void dataModelChanged(PropertyChangeEvent evt) {
    modelToView(evt);
  }

  private void modelToView(PropertyChangeEvent evt) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        modelToViewCore(evt);
      }
    });
  }

  private void modelToViewCore(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("listaCarrello")) {
      impostaImportoLabel();
    }
    nomeCognomeLabel.setText(cliente.getNome() != null && cliente.getCognome() != null ? cliente.getNome() + " " + cliente.getCognome() : "Selezionare un Cliente");
  }

  private void impostaImportoLabel() {
    float importo = CarrelloPanel.calcolaImporto(carrello);
    if (importo == 0) {
      importoLabel.setText("Importo:          ");
    }
    else {
      importoLabel.setText(String.format("Importo: %.2f", importo));
    }
  }

  @SuppressWarnings("static-access")
  private void applicationStatusChanged(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("status")) {
      switch (ApplicationStatus.getInstance().getStatus()) {
      case ApplicationStatus.STATUS_NAVIGATION:
        navigation();
        break;
      case ApplicationStatus.STATUS_INSERT:
        insert();
        break;
      }
    }
    else if (evt.getPropertyName().equals("action")) {
      if (evt.getNewValue().equals(ApplicationStatus.ACTION_PRE_DELETE) && ((JTabbedPane)this.getParent()).getSelectedIndex() == 1) {
        int response = JOptionPane.showConfirmDialog(this, "Si conferma la cancellazione dell intero carrello?", "Conferma Cancellazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.OK_OPTION) {
          ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_DELETE);
        }
        else {
          ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_NONE);
        }
      }
    }
    else if (evt.getPropertyName().equals("activeTab")) {
      if ((ApplicationStatus.getInstance().getActiveTab() == ApplicationStatus.getInstance().TAB_CARRELLO) && carrelloTable.getSelectedRow() > -1) {
        carrelloTable.removeRowSelectionInterval(0, carrelloTable.getRowCount()-1);
      }
    }
  }

  private void navigation() {
    
  }

  private void insert() {

  }

}
