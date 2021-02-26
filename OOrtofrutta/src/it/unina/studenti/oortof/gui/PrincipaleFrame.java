package it.unina.studenti.oortof.gui;


import it.unina.studenti.oortof.models.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.BevelBorder;
import java.awt.Dimension;

public class PrincipaleFrame extends JFrame {


  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          PrincipaleFrame frame = new PrincipaleFrame();
          frame.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public PrincipaleFrame() {
    DBContext context = new DBContext("jdbc:postgresql:postgres", "postgres", "Inb4Ext!");
    SQLProductDAO dao = new SQLProductDAO(context);

    //List<Bibita> bibs = dao.getBibita(null, null, null, null, null, null, null, null, null);

    //dao.createProduct(new Prodotto(1, "Ciao", 10f, true, CatProdotto.Conserva));
    Prodotto ao = new Prodotto(2, "Ciao", 1f, true, CatProdotto.Conserva);
    ArrayList<Prodotto> ass = new ArrayList<>();
    ass.add(ao);
    dao.deleteProducts(ass);
    setTitle("Ortofrutta");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800,  600);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(new BorderLayout(0, 0));
    
    JTabbedPane ilTabbedPanel = new JTabbedPane(JTabbedPane.TOP);
    contentPane.add(ilTabbedPanel, BorderLayout.CENTER);
    
    ProdottiPanel prodottiPanel = new ProdottiPanel();
    ilTabbedPanel.add(prodottiPanel);
    ilTabbedPanel.setTitleAt(0, "Prodotti");
    
    JPanel carrelloPanel = new JPanel();
    ilTabbedPanel.add(carrelloPanel);
    
    JPanel clientiPanel = new JPanel();
    ilTabbedPanel.add(clientiPanel);
    
    JPanel scontriniPanel = new JPanel();
    ilTabbedPanel.add(scontriniPanel);
    
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);
    contentPane.add(toolBar, BorderLayout.NORTH);
    
    Action rollbackAction = new AbstractAction("rollbackAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("rollback");
      }
    };
    JButton rollbackButton = new JButton("");
    rollbackButton.setFocusable(false);
    rollbackButton.setAction(rollbackAction);
    rollbackButton.setText("");
    rollbackButton.getActionMap().put("rollbackAction", rollbackAction);
    rollbackButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "rollbackAction");
    rollbackButton.setToolTipText("Annulla");
    rollbackButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/rollback.gif")));
    toolBar.add(rollbackButton);
    
    
    Action insertAction = new AbstractAction("insertAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("Insert");
      }
    };
    JButton insertButton = new JButton("");
    insertButton.setFocusable(false);
    insertButton.setAction(insertAction);
    insertButton.setText("");
    insertButton.getActionMap().put("insertAction", insertAction);
    insertButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "insertAction");
    insertButton.setToolTipText("Inserimento");
    insertButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/insert.gif")));
    toolBar.add(insertButton);
    
    Action updateAction = new AbstractAction("updateAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("update");
      }
    };
    JButton updateButton = new JButton("");
    updateButton.setFocusable(false);
    updateButton.setAction(updateAction);
    updateButton.setText("");
    updateButton.getActionMap().put("updateAction", updateAction);
    updateButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "updateAction");
    updateButton.setToolTipText("Modifica");
    updateButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/update.gif")));
    toolBar.add(updateButton);
    
    Action searchAction = new AbstractAction("searchAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("search");
      }
    };
    JButton searchButton = new JButton("");
    searchButton.setFocusable(false);
    searchButton.setAction(searchAction);
    searchButton.setText("");
    searchButton.getActionMap().put("searchAction", searchAction);
    searchButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "searchAction");
    searchButton.setToolTipText("Ricerca");
    searchButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/find.gif")));
    toolBar.add(searchButton);
    
    Action commitAction = new AbstractAction("commitAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("commit");
      }
    };
    JButton commitButton = new JButton("");
    commitButton.setFocusable(false);
    commitButton.setAction(commitAction);
    commitButton.setText("");
    commitButton.getActionMap().put("commitAction", commitAction);
    commitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "commitAction");
    commitButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/commit.gif")));
    commitButton.setToolTipText("Esegui");
    toolBar.add(commitButton);
    
    Action deleteAction = new AbstractAction("deleteAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.err.println("delete");
      }
    };
    JButton deleteButton = new JButton("");
    deleteButton.setFocusable(false);
    deleteButton.setAction(deleteAction);
    deleteButton.setText("");
    deleteButton.getActionMap().put("deleteAction", deleteAction);
    deleteButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
    deleteButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/delete.gif")));
    deleteButton.setToolTipText("Cancellazione");
    toolBar.add(deleteButton);
    
    JPanel statusBar = new JPanel();
    contentPane.add(statusBar, BorderLayout.SOUTH);
    GridBagLayout gbl_statusBar = new GridBagLayout();
    statusBar.setLayout(gbl_statusBar);
    
    JLabel counterLabel = new JLabel("");
    counterLabel.setPreferredSize(new Dimension(10, 14));
    counterLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_counterLabel = new GridBagConstraints();
    gbc_counterLabel.weightx = 1.0;
    gbc_counterLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_counterLabel.anchor = GridBagConstraints.NORTHWEST;
    gbc_counterLabel.insets = new Insets(0, 0, 0, 5);
    gbc_counterLabel.gridx = 0;
    gbc_counterLabel.gridy = 0;
    statusBar.add(counterLabel, gbc_counterLabel);
    
    JLabel messageLabel = new JLabel("");
    messageLabel.setPreferredSize(new Dimension(50, 14));
    messageLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_messageLabel = new GridBagConstraints();
    gbc_messageLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_messageLabel.weightx = 5.0;
    gbc_messageLabel.anchor = GridBagConstraints.NORTHWEST;
    gbc_messageLabel.insets = new Insets(0, 0, 0, 5);
    gbc_messageLabel.gridx = 1;
    gbc_messageLabel.gridy = 0;
    statusBar.add(messageLabel, gbc_messageLabel);
    
    JLabel statusLabel = new JLabel("");
    statusLabel.setPreferredSize(new Dimension(20, 14));
    statusLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_statusLabel = new GridBagConstraints();
    gbc_statusLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_statusLabel.weightx = 2.0;
    gbc_statusLabel.anchor = GridBagConstraints.NORTH;
    gbc_statusLabel.gridx = 2;
    gbc_statusLabel.gridy = 0;
    statusBar.add(statusLabel, gbc_statusLabel);
  }

}
