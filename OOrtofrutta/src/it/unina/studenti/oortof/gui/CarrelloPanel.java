package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.dao.ClienteDAO;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.gui.models.CarrelloTableModel;
import it.unina.studenti.oortof.models.application.ApplicationCounter;
import it.unina.studenti.oortof.models.application.ApplicationInfo;
import it.unina.studenti.oortof.models.application.ApplicationStatus;
import it.unina.studenti.oortof.models.entities.Carrello;
import it.unina.studenti.oortof.models.entities.Cliente;
import it.unina.studenti.oortof.models.entities.Lotto;
import it.unina.studenti.oortof.models.exception.DatabaseException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  private JScrollPane scrollPane;
  private JButton confermaButton;
  private JButton cancellaButton;
  private Carrello carrello;
  private Cliente cliente;
  private ClienteDAO clienteDAO;
  private JPanel northCarrelloPanel;
  private JLabel nomeCognomeLabel;
  private JLabel importoLabel;

  public CarrelloPanel() {
    clienteDAO = new SQLClienteDAO();
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

    cancellaButton = new JButton();
    cancellaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        carrello.clear();
      }
    });
    
    carrelloTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent event) {
        if (ApplicationCounter.getInstance().getCounter() != (carrelloTable.getSelectedRow() + 1)) {
          if (event.getFirstIndex() == ApplicationCounter.getInstance().getCounter() - 1 && carrelloTable.getSelectedRow() == -1) {
            ApplicationCounter.getInstance().setCounter(ApplicationCounter.getInstance().getCounter());
          } else {
            ApplicationCounter.getInstance().setCounter(carrelloTable.getSelectedRow() + 1);
          }

          ApplicationCounter.getInstance().setLimit(carrelloTable.getRowCount());
        }
      }
    });

    confermaButton = new JButton();
    confermaButton.setText("Conferma acquisti");
    confermaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (cliente.getId() != null) {
          try {
            int idScontrino = clienteDAO.createScontrino(cliente, carrello.getLotti());
            ApplicationInfo.getInstance().setMessage(String.format("Acquisto contabilizzato: generato scontrino %d di importo %.2f", idScontrino, calcolaImporto()), ApplicationInfo.LEVEL_LOG);
            carrello.clear();
          }
          catch (DatabaseException de) {
            ApplicationInfo.getInstance().setMessage("Si e' verificato un errore imprevisto nel confermare l\'acquisto", ApplicationInfo.LEVEL_LOG);
          }
        }
        else {
          ApplicationInfo.getInstance().setMessage("Selezionare un cliente prima di proseguire con l\'acquisto", ApplicationInfo.LEVEL_ERROR);
        }

      }
    });
    GridBagConstraints gbc_confermaButton = new GridBagConstraints();
    gbc_confermaButton.anchor = GridBagConstraints.WEST;
    gbc_confermaButton.insets = new Insets(0, 0, 0, 5);
    gbc_confermaButton.gridx = 0;
    gbc_confermaButton.gridy = 0;
    southCarrelloPanel.add(confermaButton, gbc_confermaButton);
    cancellaButton.setText("Cancella acquisti");
    GridBagConstraints gbc_cancellaButton = new GridBagConstraints();
    gbc_cancellaButton.anchor = GridBagConstraints.WEST;
    gbc_cancellaButton.insets = new Insets(0, 0, 0, 5);
    gbc_cancellaButton.gridx = 1;
    gbc_cancellaButton.gridy = 0;
    southCarrelloPanel.add(cancellaButton, gbc_cancellaButton);

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
        if (e.getKeyCode() == KeyEvent.VK_DELETE && ApplicationStatus.getInstance().isNavigation()) {
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

  void dataModelChanged(PropertyChangeEvent evt) {
    modelToView(evt);
  }

  void modelToView(PropertyChangeEvent evt) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        modelToViewCore(evt);
      }
    });
  }

  void modelToViewCore(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("listaCarrello")) {
      impostaImportoLabel();
    }
    nomeCognomeLabel.setText(cliente.getNome() != null && cliente.getCognome() != null ? cliente.getNome() + " " + cliente.getCognome() : "Selezionare un Cliente");
  }

  void impostaImportoLabel() {
    float importo = calcolaImporto();
    if (importo == 0) {
      importoLabel.setText("Importo:          ");
    }
    else {
      importoLabel.setText(String.format("Importo: %.2f", importo));
    }
  }

  float calcolaImporto() {
    float importo = 0f;
    for (Lotto lotto : carrello.getLotti()) {
      importo += lotto.getDisponibilita() * (lotto.getProdottoCommon().getPrezzo());
    }
    return importo;

  }
}
