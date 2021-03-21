package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public abstract class DesignClientiPanel extends JPanel {
  protected JPanel infoClientePanel;
  protected JPanel puntiPanel;
	
  protected JTextField cfTextField;
  protected JTextField nomeTextField;
  protected JTextField cognomeTextField;
  protected JTextField dataNascitaTextField;
  protected JTextField eMailTextField;
  protected JTextField luogoNascitaTextField;
  protected JTable scontriniTable;
  protected JTable acquistiTable;
  protected JRadioButton mRadioButton = new JRadioButton("M");
  protected JRadioButton fRadioButton = new JRadioButton("F");
  
  protected JTextField puntiFruttaVerduraTextField;
  protected JTextField puntiFarinaceoTextField;
  protected JTextField puntiConservaTextField;
  protected JTextField puntiProdottoCasearioTextField;
  protected JTextField puntiBibitaTextField;
  protected JTextField puntiCarnePesceTextField;
  protected JTextField puntiUovoTextField;
  protected JTextField puntiAltroTextField;
  protected JTextField puntiTotaliTextField;
  private JScrollPane scrollPane;
  
  public DesignClientiPanel() {
	  
	
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
    gridBagLayout.columnWeights = new double[]{1.0};
    setLayout(gridBagLayout);
    
    
    infoClientePanel = new JPanel();
    infoClientePanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informazioni Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_infoClientePanel = new GridBagConstraints();
    gbc_infoClientePanel.ipady = 10;
    gbc_infoClientePanel.fill = GridBagConstraints.HORIZONTAL;
    gbc_infoClientePanel.weightx = 1.0;
    gbc_infoClientePanel.insets = new Insets(0, 0, 5, 0);
    gbc_infoClientePanel.gridx = 0;
    gbc_infoClientePanel.gridy = 0;
    add(infoClientePanel, gbc_infoClientePanel);
    GridBagLayout gbl_infoClientePanel = new GridBagLayout();
    gbl_infoClientePanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0};
    gbl_infoClientePanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0};
    gbl_infoClientePanel.columnWidths = new int[]{0, 0, 0, 0, 0};
    infoClientePanel.setLayout(gbl_infoClientePanel);
    
    JLabel cfLabel = new JLabel("Codice Fiscale");
    GridBagConstraints gbc_cfLabel = new GridBagConstraints();
    gbc_cfLabel.anchor = GridBagConstraints.EAST;
    gbc_cfLabel.insets = new Insets(0, 0, 5, 5);
    gbc_cfLabel.gridx = 0;
    gbc_cfLabel.gridy = 0;
    infoClientePanel.add(cfLabel, gbc_cfLabel);
    
    cfTextField = new JTextField();
  
    cfTextField.setMinimumSize(new Dimension(150, 20));
    cfTextField.setPreferredSize(new Dimension(150, 20));
    GridBagConstraints gbc_cfTextField = new GridBagConstraints();
    gbc_cfTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_cfTextField.anchor = GridBagConstraints.WEST;
    gbc_cfTextField.insets = new Insets(0, 0, 5, 5);
    gbc_cfTextField.gridx = 1;
    gbc_cfTextField.gridy = 0;
    infoClientePanel.add(cfTextField, gbc_cfTextField);
    cfTextField.setColumns(20);
    
    
    GridBagConstraints gbc_mRadioButton = new GridBagConstraints();
    gbc_mRadioButton.anchor = GridBagConstraints.EAST;
    gbc_mRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_mRadioButton.gridx = 3;
    gbc_mRadioButton.gridy = 0;
    infoClientePanel.add(mRadioButton, gbc_mRadioButton);
    
    JLabel nomeLabel = new JLabel("Nome");
    GridBagConstraints gbc_nomeLabel = new GridBagConstraints();
    gbc_nomeLabel.anchor = GridBagConstraints.EAST;
    gbc_nomeLabel.insets = new Insets(0, 0, 5, 5);
    gbc_nomeLabel.gridx = 0;
    gbc_nomeLabel.gridy = 1;
    infoClientePanel.add(nomeLabel, gbc_nomeLabel);
    
    nomeTextField = new JTextField();
    nomeTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_nomeTextField = new GridBagConstraints();
    gbc_nomeTextField.anchor = GridBagConstraints.WEST;
    gbc_nomeTextField.insets = new Insets(0, 0, 5, 5);
    gbc_nomeTextField.gridx = 1;
    gbc_nomeTextField.gridy = 1;
    infoClientePanel.add(nomeTextField, gbc_nomeTextField);
    nomeTextField.setColumns(20);
    
    
    GridBagConstraints gbc_fRadioButton = new GridBagConstraints();
    gbc_fRadioButton.anchor = GridBagConstraints.EAST;
    gbc_fRadioButton.fill = GridBagConstraints.VERTICAL;
    gbc_fRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_fRadioButton.gridx = 3;
    gbc_fRadioButton.gridy = 1;
    infoClientePanel.add(fRadioButton, gbc_fRadioButton);
    
    JLabel cognomeLabel = new JLabel("Cognome");
    GridBagConstraints gbc_cognomeLabel = new GridBagConstraints();
    gbc_cognomeLabel.anchor = GridBagConstraints.EAST;
    gbc_cognomeLabel.insets = new Insets(0, 0, 5, 5);
    gbc_cognomeLabel.gridx = 0;
    gbc_cognomeLabel.gridy = 2;
    infoClientePanel.add(cognomeLabel, gbc_cognomeLabel);
    
    cognomeTextField = new JTextField();
    cognomeTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_cognomeTextField = new GridBagConstraints();
    gbc_cognomeTextField.anchor = GridBagConstraints.WEST;
    gbc_cognomeTextField.insets = new Insets(0, 0, 5, 5);
    gbc_cognomeTextField.gridx = 1;
    gbc_cognomeTextField.gridy = 2;
    infoClientePanel.add(cognomeTextField, gbc_cognomeTextField);
    cognomeTextField.setColumns(20);
    
    JLabel eMailLabel = new JLabel("E-Mail");
    GridBagConstraints gbc_eMailLabel = new GridBagConstraints();
    gbc_eMailLabel.anchor = GridBagConstraints.EAST;
    gbc_eMailLabel.insets = new Insets(0, 0, 5, 5);
    gbc_eMailLabel.gridx = 3;
    gbc_eMailLabel.gridy = 2;
    infoClientePanel.add(eMailLabel, gbc_eMailLabel);
    
    eMailTextField = new JTextField();
    GridBagConstraints gbc_eMailTextField = new GridBagConstraints();
    gbc_eMailTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_eMailTextField.anchor = GridBagConstraints.WEST;
    gbc_eMailTextField.insets = new Insets(0, 0, 5, 0);
    gbc_eMailTextField.gridx = 4;
    gbc_eMailTextField.gridy = 2;
    infoClientePanel.add(eMailTextField, gbc_eMailTextField);
    eMailTextField.setColumns(50);
    
    JLabel dataNascitaLabel = new JLabel("Data di nascita");
    GridBagConstraints gbc_dataNascitaLabel = new GridBagConstraints();
    gbc_dataNascitaLabel.insets = new Insets(0, 5, 5, 5);
    gbc_dataNascitaLabel.anchor = GridBagConstraints.EAST;
    gbc_dataNascitaLabel.gridx = 0;
    gbc_dataNascitaLabel.gridy = 3;
    infoClientePanel.add(dataNascitaLabel, gbc_dataNascitaLabel);
    
    dataNascitaTextField = new JTextField();
    dataNascitaTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_dataNascitaTextField = new GridBagConstraints();
    gbc_dataNascitaTextField.anchor = GridBagConstraints.WEST;
    gbc_dataNascitaTextField.insets = new Insets(0, 0, 5, 5);
    gbc_dataNascitaTextField.gridx = 1;
    gbc_dataNascitaTextField.gridy = 3;
    infoClientePanel.add(dataNascitaTextField, gbc_dataNascitaTextField);
    dataNascitaTextField.setColumns(20);
    
    JLabel luogoNascitaLabel = new JLabel("Luogo di nascita");
    GridBagConstraints gbc_luogoNascitaLabel = new GridBagConstraints();
    gbc_luogoNascitaLabel.anchor = GridBagConstraints.EAST;
    gbc_luogoNascitaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_luogoNascitaLabel.gridx = 3;
    gbc_luogoNascitaLabel.gridy = 3;
    infoClientePanel.add(luogoNascitaLabel, gbc_luogoNascitaLabel);
    
    luogoNascitaTextField = new JTextField();
    luogoNascitaTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_luogoNascitaTextField = new GridBagConstraints();
    gbc_luogoNascitaTextField.insets = new Insets(0, 0, 5, 0);
    gbc_luogoNascitaTextField.anchor = GridBagConstraints.WEST;
    gbc_luogoNascitaTextField.weightx = 0.1;
    gbc_luogoNascitaTextField.gridx = 4;
    gbc_luogoNascitaTextField.gridy = 3;
    infoClientePanel.add(luogoNascitaTextField, gbc_luogoNascitaTextField);
    luogoNascitaTextField.setColumns(10);
    
    JSeparator separator = new JSeparator();
    GridBagConstraints gbc_separator = new GridBagConstraints();
    gbc_separator.insets = new Insets(0, 0, 0, 5);
    gbc_separator.gridx = 1;
    gbc_separator.gridy = 4;
    infoClientePanel.add(separator, gbc_separator);
    
    puntiPanel = new JPanel();
    puntiPanel.setBorder(new TitledBorder(new LineBorder(new Color(0,0,0)), "Punti cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_puntiPanel = new GridBagConstraints();
    gbc_puntiPanel.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiPanel.gridx = 0;
    gbc_puntiPanel.gridy = 1;
    add(puntiPanel, gbc_puntiPanel);
    GridBagLayout gbl_puntiPanel = new GridBagLayout();
    gbl_puntiPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
    gbl_puntiPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0};
    gbl_puntiPanel.columnWidths = new int[]{0, 0, 0, 0};
    puntiPanel.setLayout(gbl_puntiPanel);
    
    JLabel puntiFruttaVerduraLabel = new JLabel("Punti Frutta Verdura");
    GridBagConstraints gbc_puntiFruttaVerduraLabel = new GridBagConstraints();
    gbc_puntiFruttaVerduraLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiFruttaVerduraLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiFruttaVerduraLabel.gridx = 0;
    gbc_puntiFruttaVerduraLabel.gridy = 0;
    puntiPanel.add(puntiFruttaVerduraLabel, gbc_puntiFruttaVerduraLabel);
    
    puntiFruttaVerduraTextField = new JTextField();
    GridBagConstraints gbc_puntiFruttaVerduraTextField = new GridBagConstraints();
    gbc_puntiFruttaVerduraTextField.insets = new Insets(0, 0, 5, 5);
    gbc_puntiFruttaVerduraTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiFruttaVerduraTextField.gridx = 1;
    gbc_puntiFruttaVerduraTextField.gridy = 0;
    puntiPanel.add(puntiFruttaVerduraTextField, gbc_puntiFruttaVerduraTextField);
    puntiFruttaVerduraTextField.setColumns(10);
    
    JLabel puntiFarinaceoLabel = new JLabel("Punti Farinaceo");
    GridBagConstraints gbc_puntiFarinaceoLabel = new GridBagConstraints();
    gbc_puntiFarinaceoLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiFarinaceoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiFarinaceoLabel.gridx = 2;
    gbc_puntiFarinaceoLabel.gridy = 0;
    puntiPanel.add(puntiFarinaceoLabel, gbc_puntiFarinaceoLabel);
    
    puntiFarinaceoTextField = new JTextField();
    GridBagConstraints gbc_puntiFarinaceoTextField = new GridBagConstraints();
    gbc_puntiFarinaceoTextField.insets = new Insets(0, 0, 5, 0);
    gbc_puntiFarinaceoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiFarinaceoTextField.gridx = 3;
    gbc_puntiFarinaceoTextField.gridy = 0;
    puntiPanel.add(puntiFarinaceoTextField, gbc_puntiFarinaceoTextField);
    puntiFarinaceoTextField.setColumns(10);
    
    JLabel puntiBibitaLabel = new JLabel("Punti Bibita");
    GridBagConstraints gbc_puntiBibitaLabel = new GridBagConstraints();
    gbc_puntiBibitaLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiBibitaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiBibitaLabel.gridx = 0;
    gbc_puntiBibitaLabel.gridy = 1;
    puntiPanel.add(puntiBibitaLabel, gbc_puntiBibitaLabel);
    
    puntiBibitaTextField = new JTextField();
    GridBagConstraints gbc_puntiBibitaTextField = new GridBagConstraints();
    gbc_puntiBibitaTextField.insets = new Insets(0, 0, 5, 5);
    gbc_puntiBibitaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiBibitaTextField.gridx = 1;
    gbc_puntiBibitaTextField.gridy = 1;
    puntiPanel.add(puntiBibitaTextField, gbc_puntiBibitaTextField);
    puntiBibitaTextField.setColumns(10);
    
    JLabel puntiCarnePesceLabel = new JLabel("Punti Carne Pesce");
    GridBagConstraints gbc_puntiCarnePesceLabel = new GridBagConstraints();
    gbc_puntiCarnePesceLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiCarnePesceLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiCarnePesceLabel.gridx = 2;
    gbc_puntiCarnePesceLabel.gridy = 1;
    puntiPanel.add(puntiCarnePesceLabel, gbc_puntiCarnePesceLabel);
    
    puntiCarnePesceTextField = new JTextField();
    GridBagConstraints gbc_puntiCarnePesceTextField = new GridBagConstraints();
    gbc_puntiCarnePesceTextField.insets = new Insets(0, 0, 5, 0);
    gbc_puntiCarnePesceTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiCarnePesceTextField.gridx = 3;
    gbc_puntiCarnePesceTextField.gridy = 1;
    puntiPanel.add(puntiCarnePesceTextField, gbc_puntiCarnePesceTextField);
    puntiCarnePesceTextField.setColumns(10);
    
    JLabel puntiProdottoCasearioLabel = new JLabel("Punti Prodotto Caseario");
    GridBagConstraints gbc_puntiProdottoCasearioLabel = new GridBagConstraints();
    gbc_puntiProdottoCasearioLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiProdottoCasearioLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiProdottoCasearioLabel.gridx = 0;
    gbc_puntiProdottoCasearioLabel.gridy = 2;
    puntiPanel.add(puntiProdottoCasearioLabel, gbc_puntiProdottoCasearioLabel);
    
    puntiProdottoCasearioTextField = new JTextField();
    GridBagConstraints gbc_puntiProdottoCasearioTextField = new GridBagConstraints();
    gbc_puntiProdottoCasearioTextField.insets = new Insets(0, 0, 5, 5);
    gbc_puntiProdottoCasearioTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiProdottoCasearioTextField.gridx = 1;
    gbc_puntiProdottoCasearioTextField.gridy = 2;
    puntiPanel.add(puntiProdottoCasearioTextField, gbc_puntiProdottoCasearioTextField);
    puntiProdottoCasearioTextField.setColumns(10);
    
    JLabel puntiUovoLabel = new JLabel("Punti Uovo");
    GridBagConstraints gbc_puntiUovoLabel = new GridBagConstraints();
    gbc_puntiUovoLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiUovoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiUovoLabel.gridx = 2;
    gbc_puntiUovoLabel.gridy = 2;
    puntiPanel.add(puntiUovoLabel, gbc_puntiUovoLabel);
    
    puntiUovoTextField = new JTextField();
    GridBagConstraints gbc_puntiUovoTextField = new GridBagConstraints();
    gbc_puntiUovoTextField.insets = new Insets(0, 0, 5, 0);
    gbc_puntiUovoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiUovoTextField.gridx = 3;
    gbc_puntiUovoTextField.gridy = 2;
    puntiPanel.add(puntiUovoTextField, gbc_puntiUovoTextField);
    puntiUovoTextField.setColumns(10);
    
    JLabel puntiConservaLabel = new JLabel("Punti Conserva");
    GridBagConstraints gbc_puntiConservaLabel = new GridBagConstraints();
    gbc_puntiConservaLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiConservaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiConservaLabel.gridx = 0;
    gbc_puntiConservaLabel.gridy = 3;
    puntiPanel.add(puntiConservaLabel, gbc_puntiConservaLabel);
    
    puntiConservaTextField = new JTextField();
    GridBagConstraints gbc_puntiConservaTextField = new GridBagConstraints();
    gbc_puntiConservaTextField.insets = new Insets(0, 0, 5, 5);
    gbc_puntiConservaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiConservaTextField.gridx = 1;
    gbc_puntiConservaTextField.gridy = 3;
    puntiPanel.add(puntiConservaTextField, gbc_puntiConservaTextField);
    puntiConservaTextField.setColumns(10);
    
    JLabel puntiAltroLabel = new JLabel("Punti Altro");
    GridBagConstraints gbc_puntiAltroLabel = new GridBagConstraints();
    gbc_puntiAltroLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiAltroLabel.insets = new Insets(0, 0, 5, 5);
    gbc_puntiAltroLabel.gridx = 2;
    gbc_puntiAltroLabel.gridy = 3;
    puntiPanel.add(puntiAltroLabel, gbc_puntiAltroLabel);
    
    puntiAltroTextField = new JTextField();
    GridBagConstraints gbc_puntiAltroTextField = new GridBagConstraints();
    gbc_puntiAltroTextField.insets = new Insets(0, 0, 5, 0);
    gbc_puntiAltroTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiAltroTextField.gridx = 3;
    gbc_puntiAltroTextField.gridy = 3;
    puntiPanel.add(puntiAltroTextField, gbc_puntiAltroTextField);
    puntiAltroTextField.setColumns(10);
    
    JLabel puntiTotaliLabel = new JLabel("Punti Totali");
    GridBagConstraints gbc_puntiTotaliLabel = new GridBagConstraints();
    gbc_puntiTotaliLabel.anchor = GridBagConstraints.EAST;
    gbc_puntiTotaliLabel.insets = new Insets(0, 0, 0, 5);
    gbc_puntiTotaliLabel.gridx = 0;
    gbc_puntiTotaliLabel.gridy = 4;
    puntiPanel.add(puntiTotaliLabel, gbc_puntiTotaliLabel);
    
    puntiTotaliTextField = new JTextField();
    GridBagConstraints gbc_puntiTotaliTextField = new GridBagConstraints();
    gbc_puntiTotaliTextField.insets = new Insets(0, 0, 0, 5);
    gbc_puntiTotaliTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_puntiTotaliTextField.gridx = 1;
    gbc_puntiTotaliTextField.gridy = 4;
    puntiPanel.add(puntiTotaliTextField, gbc_puntiTotaliTextField);
    puntiTotaliTextField.setColumns(10);
    
    JPanel scontriniPanel = new JPanel();
    scontriniPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Scontrini", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_scontriniPanel = new GridBagConstraints();
    gbc_scontriniPanel.weightx = 1.0;
    gbc_scontriniPanel.weighty = 1.0;
    gbc_scontriniPanel.insets = new Insets(0, 0, 5, 0);
    gbc_scontriniPanel.fill = GridBagConstraints.BOTH;
    gbc_scontriniPanel.gridx = 0;
    gbc_scontriniPanel.gridy = 2;
    add(scontriniPanel, gbc_scontriniPanel);
    GridBagLayout gbl_scontriniPanel = new GridBagLayout();
    gbl_scontriniPanel.rowWeights = new double[]{0.0, 1.0};
    gbl_scontriniPanel.columnWeights = new double[]{1.0, 0.0};
    scontriniPanel.setLayout(gbl_scontriniPanel);
    
    
    
    scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
    gbc_scrollPane.fill = GridBagConstraints.BOTH;
    gbc_scrollPane.gridx = 0;
    gbc_scrollPane.gridy = 1;
    scontriniPanel.add(scrollPane, gbc_scrollPane);
    
    scontriniTable = new JTable();
    scontriniTable.setBackground(SystemColor.control);
    scontriniTable.getTableHeader().setOpaque(true);
    scontriniTable.getTableHeader().setBackground(SystemColor.control);
    scrollPane.setViewportView(scontriniTable);
    
    JPanel acquistiPanel = new JPanel();
    acquistiPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Acquisti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_acquistiPanel = new GridBagConstraints();
    gbc_acquistiPanel.insets = new Insets(0, 0, 5, 0);
    gbc_acquistiPanel.weightx = 1.0;
    gbc_acquistiPanel.weighty = 1.0;
    gbc_acquistiPanel.fill = GridBagConstraints.BOTH;
    gbc_acquistiPanel.gridx = 0;
    gbc_acquistiPanel.gridy = 3;
    add(acquistiPanel, gbc_acquistiPanel);
    
    acquistiTable = new JTable();
    acquistiPanel.add(acquistiTable);
    
   

  }
}
