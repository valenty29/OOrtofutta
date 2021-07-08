package it.unina.studenti.oortof.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.unina.studenti.oortof.controllers.ApplicationController;
import it.unina.studenti.oortof.dao.DBContext;
import it.unina.studenti.oortof.dao.SQLClienteDAO;
import it.unina.studenti.oortof.dao.SQLProductDAO;
import it.unina.studenti.oortof.models.ApplicationCounter;
import it.unina.studenti.oortof.models.ApplicationStatus;
import it.unina.studenti.oortof.models.Bibita;
import it.unina.studenti.oortof.models.Cliente;
import it.unina.studenti.oortof.models.Lotto;
import it.unina.studenti.oortof.models.ObservedList;
import it.unina.studenti.oortof.models.Prodotto;
import it.unina.studenti.oortof.models.TipoBibita;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;

public class PrincipaleFrame extends JFrame {


  private static final long serialVersionUID = 1L;
  
  JPanel principalePanel;

  JToolBar toolBar = new JToolBar();
  JButton rollbackButton = new JButton("");
  JButton insertButton = new JButton("");
  JButton updateButton = new JButton("");
  JButton searchButton = new JButton("");
  JButton commitButton = new JButton("");
  JButton deleteButton = new JButton("");

  JTabbedPane ilTabbedPanel = new JTabbedPane(JTabbedPane.TOP);
  ProdottiTabbed prodottiTabbed = new ProdottiTabbed();
  CarrelloPanel carrelloPanel = new CarrelloPanel();
  ClientiTabbed clientiTabbed = new ClientiTabbed();

  JPanel statusBar = new JPanel();
  JLabel counterLabel = new JLabel("");
  JLabel messageLabel = new JLabel("");
  JLabel statusLabel = new JLabel("NAVIGAZIONE");

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    ApplicationController.getInstance();
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
          PrincipaleFrame frame = new PrincipaleFrame();
          frame.setVisible(true);
          Prodotto p = new Prodotto();
          ObservedList prodottoList = new ObservedList("prodotti");
          ((ProdottiPanel)frame.prodottiTabbed.getComponent(0)).setModel(p);
          ((ProdottiListPanel)frame.prodottiTabbed.getComponent(1)).setModel(prodottoList);
          ApplicationController.getInstance().getSubController(0).setModel(p, prodottoList);
          
          Cliente c = new Cliente();
          ObservedList<Cliente> clienteList = new ObservedList<Cliente>("clienteList");
          ((ClientiPanel)frame.clientiTabbed.getComponent(0)).setModel(c);
          ((ClientiListPanel)frame.clientiTabbed.getComponent(1)).setModel(c, clienteList);
          ApplicationController.getInstance().getSubController(2).setModel(c, clienteList);
          
          
          
          new Thread() {
            public void run() {
              try {
System.err.println("THREAD START");
                Thread.sleep(3000);
System.err.println("BINGO");






                System.err.println();

                new Thread() {
                  public void run() {
                    try {


                      Thread.sleep(5000);





                    }
                    catch (Exception e) {
                      e.printStackTrace();
                    }
                  }
                }.start();

                System.out.println("a");
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            }
          }.start();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public PrincipaleFrame() {
    
    setTitle("Ortofrutta");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800,  600);
    principalePanel = new JPanel();
    principalePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(principalePanel);
    principalePanel.setLayout(new BorderLayout(0, 0));
    
    principalePanel.add(ilTabbedPanel, BorderLayout.CENTER);
    
    ilTabbedPanel.add(prodottiTabbed);
    ilTabbedPanel.setTitleAt(0, "Prodotti");

    ilTabbedPanel.add(carrelloPanel);
    ilTabbedPanel.setTitleAt(1, "Carrello");
    
    ilTabbedPanel.add(clientiTabbed);
    ilTabbedPanel.setTitleAt(2, "Clienti");
    
    ilTabbedPanel.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        ApplicationStatus.getInstance().setActiveTab(ilTabbedPanel.getSelectedIndex());
      }
    });
    
    toolBar.setFloatable(false);
    principalePanel.add(toolBar, BorderLayout.NORTH);
    
    Action rollbackAction = new AbstractAction("rollbackAction") {
      private static final long serialVersionUID = 1L;
      @Override
      public void actionPerformed(ActionEvent e) {
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_ROLLBACK);
      }
    };
    rollbackButton.setFocusable(false);
    rollbackButton.setAction(rollbackAction);
    rollbackButton.setText("");
    rollbackButton.getActionMap().put("rollbackAction", rollbackAction);
    rollbackButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "rollbackAction");
    rollbackButton.setToolTipText("Annulla");
    rollbackButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/rollback.gif")));
    toolBar.add(rollbackButton);
    rollbackButton.setEnabled(false);
    
    
    Action insertAction = new AbstractAction("insertAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_INSERT);
      }
    };
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
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_UPDATE);
      }
    };
    updateButton.setFocusable(false);
    updateButton.setAction(updateAction);
    updateButton.setText("");
    updateButton.getActionMap().put("updateAction", updateAction);
    updateButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "updateAction");
    updateButton.setToolTipText("Modifica");
    updateButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/update.gif")));
    toolBar.add(updateButton);
    updateButton.setEnabled(false);
    
    Action searchAction = new AbstractAction("searchAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_SEARCH);
      }
    };
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
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_COMMIT);
      }
    };
    commitButton.setFocusable(false);
    commitButton.setAction(commitAction);
    commitButton.setText("");
    commitButton.getActionMap().put("commitAction", commitAction);
    commitButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "commitAction");
    commitButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/commit.gif")));
    commitButton.setToolTipText("Esegui");
    toolBar.add(commitButton);
    commitButton.setEnabled(false);
    
    Action deleteAction = new AbstractAction("deleteAction") {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        ApplicationStatus.getInstance().setAction(ApplicationStatus.ACTION_DELETE);
      }
    };
    deleteButton.setFocusable(false);
    deleteButton.setAction(deleteAction);
    deleteButton.setText("");
    deleteButton.getActionMap().put("deleteAction", deleteAction);
    deleteButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");
    deleteButton.setIcon(new ImageIcon(PrincipaleFrame.class.getResource("/it/unina/studenti/oortof/gui/resources/images/delete.gif")));
    deleteButton.setToolTipText("Cancellazione");
    toolBar.add(deleteButton);
    deleteButton.setEnabled(false);
    
    principalePanel.add(statusBar, BorderLayout.SOUTH);
    GridBagLayout gbl_statusBar = new GridBagLayout();
    statusBar.setLayout(gbl_statusBar);
    
    counterLabel.setPreferredSize(new Dimension(10, 22));
    counterLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_counterLabel = new GridBagConstraints();
    gbc_counterLabel.weightx = 1.0;
    gbc_counterLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_counterLabel.anchor = GridBagConstraints.NORTHWEST;
    gbc_counterLabel.insets = new Insets(0, 0, 0, 5);
    gbc_counterLabel.gridx = 0;
    gbc_counterLabel.gridy = 0;
    statusBar.add(counterLabel, gbc_counterLabel);
    
    messageLabel.setPreferredSize(new Dimension(50, 22));
    messageLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_messageLabel = new GridBagConstraints();
    gbc_messageLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_messageLabel.weightx = 5.0;
    gbc_messageLabel.anchor = GridBagConstraints.NORTHWEST;
    gbc_messageLabel.insets = new Insets(0, 0, 0, 5);
    gbc_messageLabel.gridx = 1;
    gbc_messageLabel.gridy = 0;
    statusBar.add(messageLabel, gbc_messageLabel);
    statusLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setOpaque(true);
    
    statusLabel.setPreferredSize(new Dimension(20, 22));
    statusLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    GridBagConstraints gbc_statusLabel = new GridBagConstraints();
    gbc_statusLabel.fill = GridBagConstraints.HORIZONTAL;
    gbc_statusLabel.weightx = 2.0;
    gbc_statusLabel.anchor = GridBagConstraints.NORTH;
    gbc_statusLabel.gridx = 2;
    gbc_statusLabel.gridy = 0;
    statusBar.add(statusLabel, gbc_statusLabel);
    
    ApplicationStatus.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationStatusChanged(evt);
      }
    });
    ApplicationCounter.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        applicationCounterChanged(evt);
      }
    });
  }
  
  void applicationCounterChanged(PropertyChangeEvent evt) {
    counterLabel.setText(ApplicationCounter.getInstance().toString());
  }
  
  void applicationStatusChanged(PropertyChangeEvent evt) {
    switch (ApplicationStatus.getInstance().getStatus()) {
    case ApplicationStatus.STATUS_NAVIGATION: navigation(); break;
    case ApplicationStatus.STATUS_INSERT: insert(); break;
    case ApplicationStatus.STATUS_UPDATE: update(); break;
    case ApplicationStatus.STATUS_SEARCH: search(); break;
    }
  }
  
  void navigation() {
    int selectedItem = ApplicationCounter.getInstance().getCounter();
    rollbackButton.setEnabled(false);
    insertButton.setEnabled(true);
    updateButton.setEnabled(selectedItem >= 0);
    searchButton.setEnabled(true);
    commitButton.setEnabled(false);
    deleteButton.setEnabled(selectedItem >= 0);
    statusLabel.setText("NAVIGAZIONE");
    statusLabel.setBackground(SystemColor.control);
    for (int i = 0; i < ilTabbedPanel.getTabCount(); i++) {
      ilTabbedPanel.setEnabledAt(i, true);
    }
  }
  
  void insert() {
    rollbackButton.setEnabled(true);
    insertButton.setEnabled(false);
    updateButton.setEnabled(false);
    searchButton.setEnabled(false);
    commitButton.setEnabled(true);
    deleteButton.setEnabled(false);
    statusLabel.setText("INSERIMENTO");
    statusLabel.setBackground(SystemColor.white);
    for (int i = 0; i < ilTabbedPanel.getTabCount(); i++) {
      ilTabbedPanel.setEnabledAt(i, i == ilTabbedPanel.getSelectedIndex());
    }
  }

  void update() {
    rollbackButton.setEnabled(true);
    insertButton.setEnabled(false);
    updateButton.setEnabled(false);
    searchButton.setEnabled(false);
    commitButton.setEnabled(true);
    deleteButton.setEnabled(false);
    statusLabel.setText("MODIFICA");
    statusLabel.setBackground(SystemColor.cyan);
    for (int i = 0; i < ilTabbedPanel.getTabCount(); i++) {
      ilTabbedPanel.setEnabledAt(i, i == ilTabbedPanel.getSelectedIndex());
    }
  }
  
  void search() {
    rollbackButton.setEnabled(true);
    insertButton.setEnabled(false);
    updateButton.setEnabled(false);
    searchButton.setEnabled(false);
    commitButton.setEnabled(true);
    deleteButton.setEnabled(false);
    statusLabel.setText("RICERCA");
    statusLabel.setBackground(SystemColor.yellow);
    for (int i = 0; i < ilTabbedPanel.getTabCount(); i++) {
      ilTabbedPanel.setEnabledAt(i, i == ilTabbedPanel.getSelectedIndex());
    }
  }
}
