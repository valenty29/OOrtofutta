package it.unina.studenti.oortof.gui;

import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.gui.models.CarrelloTableModel;
import it.unina.studenti.oortof.gui.models.LottiTableModel;
import it.unina.studenti.oortof.models.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class CarrelloPanel extends JPanel {
  private JTable carrelloTable;
  private JScrollPane scrollPane;
  private JButton confermaButton;
  private JButton cancellaButton;
  private Carrello carrello;
  private Cliente cliente;
  private JPanel southCarrelloPanel;
  private SQLClienteDAO sqlClienteDAO;
  private JPanel northCarrelloPanel;
  private JLabel nomeCognomeLabel;
  private JLabel importoLabel;

  public CarrelloPanel() {
    sqlClienteDAO = new SQLClienteDAO();
    scrollPane = new JScrollPane();
    southCarrelloPanel = new JPanel();
    southCarrelloPanel.setLayout(new BoxLayout(southCarrelloPanel, BoxLayout.X_AXIS));
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

    confermaButton = new JButton();
    confermaButton.setText("Conferma acquisti");

    confermaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (cliente.getId() != null) {
          try {
            int idScontrino = sqlClienteDAO.createScontrino(cliente, carrello.getLotti());
            ApplicationInfo.getInstance().setMessage(String.format("Acquisto contabilizzato: generato scontrino %d di importo %.2f", idScontrino, calcolaImporto()), ApplicationInfo.LEVEL_ERROR);
            carrello.clear();
          } catch (DatabaseException de) {
            ApplicationInfo.getInstance().setMessage("Si è verificato un errore imprevisto nel confermare l\'acquisto", ApplicationInfo.LEVEL_LOG);
          }
        } else {
          ApplicationInfo.getInstance().setMessage("Selezionare un cliente prima di proseguire con l\'acquisto", ApplicationInfo.LEVEL_ERROR);
        }

      }
    });

    importoLabel = new JLabel("Importo:");

    cancellaButton = new JButton();
    cancellaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        carrello.clear();
      }
    });
    cancellaButton.setText("Cancella acquisti");
    southCarrelloPanel.add(confermaButton);
    southCarrelloPanel.add(cancellaButton);
    southCarrelloPanel.add(importoLabel);
    carrelloTable.setModel(new CarrelloTableModel());
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

  void impostaImportoLabel(){
    float importo = calcolaImporto();
    importoLabel.setText(String.format("Importo: %.2f", importo));
  }

  float calcolaImporto() {
    float importo = 0f;
    for ( Lotto lotto: carrello.getLotti()) {
      importo += lotto.getDisponibilita() * (lotto.getProdottoCommon().getPrezzo());
    }
    return importo;

  }
}
