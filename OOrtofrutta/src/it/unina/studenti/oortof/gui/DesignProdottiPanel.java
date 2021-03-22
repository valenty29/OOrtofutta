package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;

public abstract class DesignProdottiPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  protected JTextField nomeTextField;
  protected JFormattedTextField codiceProdottoTextField;
  protected JFormattedTextField prezzoTextField;
  protected JTextField stagionatureTextField;
  protected JTextField stabilimentoTextField;
  protected JTextField tipoLatteTextField;
  protected JTextField tipoFarinaTextField;
  protected JTextField animaleTextField;
  protected JTextField gradazioneAlcolicaTextField;
  protected JCheckBox sfusoCheckBox = new JCheckBox("Sfuso");
  protected JTabbedPane caratteristicheSpecificheTabbed = new JTabbedPane(JTabbedPane.TOP);
  protected JRadioButton sottovuotoRadioButton = new JRadioButton("Sottovuoto");
  protected JRadioButton sottolioRadioButton = new JRadioButton("Sott'olio");
  protected JRadioButton tipo0RadioButton = new JRadioButton("0");
  protected JRadioButton tipo1RadioButton = new JRadioButton("1");
  protected JRadioButton tipo2RadioButton = new JRadioButton("2");
  protected JRadioButton tipo3RadioButton = new JRadioButton("3");
  protected JRadioButton inZuccheriRadioButton = new JRadioButton("In zuccheri");
  protected JRadioButton conserveAltroTipoRadioButton = new JRadioButton("Altro tipo di conservazione");
  protected JRadioButton sottacetoRadioButton = new JRadioButton("Sott'aceto");
  protected JRadioButton sottosaleRadioButton = new JRadioButton("Sotto sale");
  protected JRadioButton sottoSpiritoRadioButton = new JRadioButton("Sotto spirito");
  protected JRadioButton fruttaRadioButton = new JRadioButton("Frutta");
  protected JCheckBox surgelatoFruttaVerduraCheckbox = new JCheckBox("Surgelato");
  protected JRadioButton verduraRadioButton = new JRadioButton("Verdura");
  protected JCheckBox biologicoCheckbox = new JCheckBox("Biologico");
  protected JCheckBox senzaGlutineCheckbox = new JCheckBox("Senza glutine");
  protected JCheckBox frescoCheckbox = new JCheckBox("Fresco");
  protected JCheckBox surgelatoFarinaceiCheckbox = new JCheckBox("Surgelato");
  protected JCheckBox confezionatoCheckBox = new JCheckBox("Confezionato");
  protected JRadioButton acquaRadioButton = new JRadioButton("Acqua");
  protected JRadioButton fermentatiRadioButton = new JRadioButton("Fermentati");
  protected JRadioButton succhiDiFruttaRadioButton = new JRadioButton("Succhi di frutta");
  protected JRadioButton liquoriRadioButton = new JRadioButton("Liquori");
  protected JCheckBox frizzanteCheckBox = new JCheckBox("Frizzante");
  protected JRadioButton softDrinkRadioButton = new JRadioButton("Soft Drink");
  protected JRadioButton bibitaAltroRadioButton = new JRadioButton("Altro tipo");
  protected JRadioButton categoriaSRadioButton = new JRadioButton("S");
  protected JRadioButton categoriaMRadioButton = new JRadioButton("M");
  protected JRadioButton categoriaLRadioButton = new JRadioButton("L");
  protected JRadioButton categoriaXLRadioButton = new JRadioButton("XL");
  protected JRadioButton carneRadioButton = new JRadioButton("Carne");
  protected JRadioButton pesceRadioButton = new JRadioButton("Pesce");
  protected JTable lottiTable;

  protected JCheckBox fruttaVerduraCheckbox = new JCheckBox("Frutta e Verdura");
  protected JCheckBox carnePesceCheckbox = new JCheckBox("Carne e pesce");
  protected JCheckBox prodottiCaseariCheckbox = new JCheckBox("Prodotti casearei");
  protected JCheckBox bibiteCheckbox = new JCheckBox("Bibite");
  protected JCheckBox farinaceiCheckbox = new JCheckBox("Farinacei");
  protected JCheckBox conserveCheckbox = new JCheckBox("Conserve");
  protected JCheckBox uovaCheckbox = new JCheckBox("Uova");
  protected JCheckBox altriTipoCheckbox = new JCheckBox("Altri tipi");
  protected JCheckBox daAllevamentoCheckBox = new JCheckBox("Da Allevamento");
  protected JLabel lblNewLabel = new JLabel("Codice Allevamento");
  protected JTextField codiceAllevamentoTextField = new JTextField();

  protected ButtonGroup fruttaVerduraBG = new ButtonGroup();
  protected ButtonGroup conserveBG = new ButtonGroup();
  protected ButtonGroup categoriaPesoBG = new ButtonGroup();
  protected ButtonGroup tipoAllevamentoBG = new ButtonGroup();
  protected ButtonGroup carnePesceBG = new ButtonGroup();
  protected ButtonGroup bibitaBG = new ButtonGroup();
  
  
  /**
   * Create the panel.
   */
  public DesignProdottiPanel() {

    codiceAllevamentoTextField.setColumns(10);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{680, 0};
    gridBagLayout.rowHeights = new int[]{128, 157, 200, 0};
    gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    setLayout(gridBagLayout);
    
    JPanel commonPanel = new JPanel();
    commonPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informazioni comuni", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_commonPanel = new GridBagConstraints();
    gbc_commonPanel.ipady = 10;
    gbc_commonPanel.weightx = 1.0;
    gbc_commonPanel.weighty = 1.0;
    gbc_commonPanel.fill = GridBagConstraints.BOTH;
    gbc_commonPanel.insets = new Insets(0, 0, 5, 0);
    gbc_commonPanel.gridx = 0;
    gbc_commonPanel.gridy = 0;
    add(commonPanel, gbc_commonPanel);
    GridBagLayout gbl_commonPanel = new GridBagLayout();
    gbl_commonPanel.columnWidths = new int[]{0, 147, 0, 0, 0, 0, 0};
    gbl_commonPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
    gbl_commonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_commonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    commonPanel.setLayout(gbl_commonPanel);
    
    JLabel nomeLabel = new JLabel("Nome");
    GridBagConstraints gbc_nomeLabel = new GridBagConstraints();
    gbc_nomeLabel.anchor = GridBagConstraints.EAST;
    gbc_nomeLabel.insets = new Insets(0, 0, 5, 5);
    gbc_nomeLabel.gridx = 0;
    gbc_nomeLabel.gridy = 0;
    commonPanel.add(nomeLabel, gbc_nomeLabel);
    
    nomeTextField = new JTextField();
    nomeTextField.setEnabled(false);
    nomeTextField.setBackground(SystemColor.control);
    GridBagConstraints gbc_nomeTextField = new GridBagConstraints();
    gbc_nomeTextField.insets = new Insets(0, 0, 5, 5);
    gbc_nomeTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_nomeTextField.gridx = 1;
    gbc_nomeTextField.gridy = 0;
    commonPanel.add(nomeTextField, gbc_nomeTextField);
    nomeTextField.setColumns(10);
    
    fruttaVerduraCheckbox.setEnabled(false);
    GridBagConstraints gbc_fruttaVerduraCheckbox = new GridBagConstraints();
    gbc_fruttaVerduraCheckbox.anchor = GridBagConstraints.WEST;
    gbc_fruttaVerduraCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_fruttaVerduraCheckbox.gridx = 3;
    gbc_fruttaVerduraCheckbox.gridy = 0;
    commonPanel.add(fruttaVerduraCheckbox, gbc_fruttaVerduraCheckbox);
    
    conserveCheckbox.setEnabled(false);
    GridBagConstraints gbc_conserveCheckbox = new GridBagConstraints();
    gbc_conserveCheckbox.anchor = GridBagConstraints.WEST;
    gbc_conserveCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_conserveCheckbox.gridx = 4;
    gbc_conserveCheckbox.gridy = 0;
    commonPanel.add(conserveCheckbox, gbc_conserveCheckbox);
    
    JLabel codiceProdottoLabel = new JLabel("Codice Prodotto");
    GridBagConstraints gbc_codiceProdottoLabel = new GridBagConstraints();
    gbc_codiceProdottoLabel.anchor = GridBagConstraints.EAST;
    gbc_codiceProdottoLabel.insets = new Insets(0, 5, 5, 5);
    gbc_codiceProdottoLabel.gridx = 0;
    gbc_codiceProdottoLabel.gridy = 1;
    commonPanel.add(codiceProdottoLabel, gbc_codiceProdottoLabel);
    
    codiceProdottoTextField = new JFormattedTextField();
    codiceProdottoTextField.setEnabled(false);
    codiceProdottoTextField.setBackground(SystemColor.control);
    GridBagConstraints gbc_codiceProdottoTextField = new GridBagConstraints();
    gbc_codiceProdottoTextField.insets = new Insets(0, 0, 5, 5);
    gbc_codiceProdottoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_codiceProdottoTextField.gridx = 1;
    gbc_codiceProdottoTextField.gridy = 1;
    commonPanel.add(codiceProdottoTextField, gbc_codiceProdottoTextField);
    codiceProdottoTextField.setColumns(10);
    
    prodottiCaseariCheckbox.setEnabled(false);
    GridBagConstraints gbc_prodottiCaseariCheckbox = new GridBagConstraints();
    gbc_prodottiCaseariCheckbox.anchor = GridBagConstraints.WEST;
    gbc_prodottiCaseariCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_prodottiCaseariCheckbox.gridx = 3;
    gbc_prodottiCaseariCheckbox.gridy = 1;
    commonPanel.add(prodottiCaseariCheckbox, gbc_prodottiCaseariCheckbox);
    
    farinaceiCheckbox.setEnabled(false);
    GridBagConstraints gbc_farinaceiCheckbox = new GridBagConstraints();
    gbc_farinaceiCheckbox.anchor = GridBagConstraints.WEST;
    gbc_farinaceiCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_farinaceiCheckbox.gridx = 4;
    gbc_farinaceiCheckbox.gridy = 1;
    commonPanel.add(farinaceiCheckbox, gbc_farinaceiCheckbox);
    
    JLabel prezzoLabel = new JLabel("Prezzo");
    GridBagConstraints gbc_prezzoLabel = new GridBagConstraints();
    gbc_prezzoLabel.anchor = GridBagConstraints.EAST;
    gbc_prezzoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_prezzoLabel.gridx = 0;
    gbc_prezzoLabel.gridy = 2;
    commonPanel.add(prezzoLabel, gbc_prezzoLabel);
    
    prezzoTextField = new JFormattedTextField();
    prezzoTextField.setEnabled(false);
    prezzoTextField.setBackground(SystemColor.control);
    GridBagConstraints gbc_prezzoTextField = new GridBagConstraints();
    gbc_prezzoTextField.insets = new Insets(0, 0, 5, 5);
    gbc_prezzoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_prezzoTextField.gridx = 1;
    gbc_prezzoTextField.gridy = 2;
    commonPanel.add(prezzoTextField, gbc_prezzoTextField);
    prezzoTextField.setColumns(10);
    
    uovaCheckbox.setEnabled(false);
    GridBagConstraints gbc_uovaCheckbox = new GridBagConstraints();
    gbc_uovaCheckbox.anchor = GridBagConstraints.WEST;
    gbc_uovaCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_uovaCheckbox.gridx = 3;
    gbc_uovaCheckbox.gridy = 2;
    commonPanel.add(uovaCheckbox, gbc_uovaCheckbox);
    
    carnePesceCheckbox.setEnabled(false);
    GridBagConstraints gbc_carnePesceCheckbox = new GridBagConstraints();
    gbc_carnePesceCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_carnePesceCheckbox.anchor = GridBagConstraints.WEST;
    gbc_carnePesceCheckbox.gridx = 4;
    gbc_carnePesceCheckbox.gridy = 2;
    commonPanel.add(carnePesceCheckbox, gbc_carnePesceCheckbox);
    
    sfusoCheckBox.setEnabled(false);
    sfusoCheckBox.setBackground(SystemColor.control);
    GridBagConstraints gbc_sfusoCheckBox = new GridBagConstraints();
    gbc_sfusoCheckBox.anchor = GridBagConstraints.WEST;
    gbc_sfusoCheckBox.insets = new Insets(0, 0, 0, 5);
    gbc_sfusoCheckBox.gridx = 1;
    gbc_sfusoCheckBox.gridy = 3;
    commonPanel.add(sfusoCheckBox, gbc_sfusoCheckBox);
    
    bibiteCheckbox.setEnabled(false);
    GridBagConstraints gbc_bibiteCheckbox = new GridBagConstraints();
    gbc_bibiteCheckbox.anchor = GridBagConstraints.WEST;
    gbc_bibiteCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_bibiteCheckbox.gridx = 3;
    gbc_bibiteCheckbox.gridy = 3;
    commonPanel.add(bibiteCheckbox, gbc_bibiteCheckbox);
    
    altriTipoCheckbox.setEnabled(false);
    GridBagConstraints gbc_altriTipoCheckbox = new GridBagConstraints();
    gbc_altriTipoCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_altriTipoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_altriTipoCheckbox.gridx = 4;
    gbc_altriTipoCheckbox.gridy = 3;
    commonPanel.add(altriTipoCheckbox, gbc_altriTipoCheckbox);
    
    caratteristicheSpecificheTabbed.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informazioni Specifiche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    caratteristicheSpecificheTabbed.setVisible(false);
    GridBagConstraints gbc_caratteristicheSpecifichePanel = new GridBagConstraints();
    gbc_caratteristicheSpecifichePanel.weightx = 1.0;
    gbc_caratteristicheSpecifichePanel.weighty = 1.0;
    gbc_caratteristicheSpecifichePanel.fill = GridBagConstraints.BOTH;
    gbc_caratteristicheSpecifichePanel.insets = new Insets(0, 0, 5, 0);
    gbc_caratteristicheSpecifichePanel.gridx = 0;
    gbc_caratteristicheSpecifichePanel.gridy = 1;
    add(caratteristicheSpecificheTabbed, gbc_caratteristicheSpecifichePanel);
    
    JPanel fruttaVerduraPanel = new JPanel();
    fruttaVerduraPanel.setEnabled(false);
    caratteristicheSpecificheTabbed.addTab("Frutta e Verdura", null, fruttaVerduraPanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(0, false);
    GridBagLayout gbl_fruttaVerduraPanel = new GridBagLayout();
    gbl_fruttaVerduraPanel.columnWidths = new int[]{0, 0, 0};
    gbl_fruttaVerduraPanel.rowHeights = new int[]{0, 0, 0};
    gbl_fruttaVerduraPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    gbl_fruttaVerduraPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    fruttaVerduraPanel.setLayout(gbl_fruttaVerduraPanel);
    
    fruttaRadioButton.setEnabled(false);
    GridBagConstraints gbc_fruttaRadioButton = new GridBagConstraints();
    gbc_fruttaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_fruttaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_fruttaRadioButton.gridx = 0;
    gbc_fruttaRadioButton.gridy = 0;
    fruttaVerduraPanel.add(fruttaRadioButton, gbc_fruttaRadioButton);
    
    surgelatoFruttaVerduraCheckbox.setEnabled(false);
    GridBagConstraints gbc_surgelatoFruttaVerduraCheckbox = new GridBagConstraints();
    gbc_surgelatoFruttaVerduraCheckbox.anchor = GridBagConstraints.WEST;
    gbc_surgelatoFruttaVerduraCheckbox.insets = new Insets(0, 0, 5, 0);
    gbc_surgelatoFruttaVerduraCheckbox.gridx = 1;
    gbc_surgelatoFruttaVerduraCheckbox.gridy = 0;
    fruttaVerduraPanel.add(surgelatoFruttaVerduraCheckbox, gbc_surgelatoFruttaVerduraCheckbox);
    
    verduraRadioButton.setEnabled(false);
    GridBagConstraints gbc_verduraRadioButton = new GridBagConstraints();
    gbc_verduraRadioButton.anchor = GridBagConstraints.WEST;
    gbc_verduraRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_verduraRadioButton.gridx = 0;
    gbc_verduraRadioButton.gridy = 1;
    fruttaVerduraPanel.add(verduraRadioButton, gbc_verduraRadioButton);
    
    biologicoCheckbox.setEnabled(false);
    GridBagConstraints gbc_biologicoCheckbox = new GridBagConstraints();
    gbc_biologicoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_biologicoCheckbox.gridx = 1;
    gbc_biologicoCheckbox.gridy = 1;
    fruttaVerduraPanel.add(biologicoCheckbox, gbc_biologicoCheckbox);
    
    JPanel conservePanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Conserve", null, conservePanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(1, false);
    GridBagLayout gbl_conservePanel = new GridBagLayout();
    gbl_conservePanel.columnWidths = new int[]{0, 0, 0, 0, 0};
    gbl_conservePanel.rowHeights = new int[]{0, 0, 0};
    gbl_conservePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_conservePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    conservePanel.setLayout(gbl_conservePanel);
    
    sottovuotoRadioButton.setEnabled(false);
    GridBagConstraints gbc_sottovuotoRadioButton = new GridBagConstraints();
    gbc_sottovuotoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottovuotoRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_sottovuotoRadioButton.gridx = 0;
    gbc_sottovuotoRadioButton.gridy = 0;
    conservePanel.add(sottovuotoRadioButton, gbc_sottovuotoRadioButton);
    
    sottolioRadioButton.setEnabled(false);
    GridBagConstraints gbc_sottolioRadioButton = new GridBagConstraints();
    gbc_sottolioRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottolioRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_sottolioRadioButton.gridx = 1;
    gbc_sottolioRadioButton.gridy = 0;
    conservePanel.add(sottolioRadioButton, gbc_sottolioRadioButton);
    

    inZuccheriRadioButton.setEnabled(false);
    GridBagConstraints gbc_inZuccheriRadioButton = new GridBagConstraints();
    gbc_inZuccheriRadioButton.anchor = GridBagConstraints.WEST;
    gbc_inZuccheriRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_inZuccheriRadioButton.gridx = 2;
    gbc_inZuccheriRadioButton.gridy = 0;
    conservePanel.add(inZuccheriRadioButton, gbc_inZuccheriRadioButton);
    
    conserveAltroTipoRadioButton.setEnabled(false);
    GridBagConstraints gbc_conserveAltroTipoRadioButton = new GridBagConstraints();
    gbc_conserveAltroTipoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_conserveAltroTipoRadioButton.insets = new Insets(0, 0, 5, 0);
    gbc_conserveAltroTipoRadioButton.gridx = 3;
    gbc_conserveAltroTipoRadioButton.gridy = 0;
    conservePanel.add(conserveAltroTipoRadioButton, gbc_conserveAltroTipoRadioButton);
    
    sottacetoRadioButton.setEnabled(false);
    GridBagConstraints gbc_sottacetoRadioButton = new GridBagConstraints();
    gbc_sottacetoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottacetoRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottacetoRadioButton.gridx = 0;
    gbc_sottacetoRadioButton.gridy = 1;
    conservePanel.add(sottacetoRadioButton, gbc_sottacetoRadioButton);
    
    sottosaleRadioButton.setEnabled(false);
    GridBagConstraints gbc_sottosaleRadioButton = new GridBagConstraints();
    gbc_sottosaleRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottosaleRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottosaleRadioButton.gridx = 1;
    gbc_sottosaleRadioButton.gridy = 1;
    conservePanel.add(sottosaleRadioButton, gbc_sottosaleRadioButton);
    
    sottoSpiritoRadioButton.setEnabled(false);
    GridBagConstraints gbc_sottoSpiritoRadioButton = new GridBagConstraints();
    gbc_sottoSpiritoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottoSpiritoRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottoSpiritoRadioButton.gridx = 2;
    gbc_sottoSpiritoRadioButton.gridy = 1;
    conservePanel.add(sottoSpiritoRadioButton, gbc_sottoSpiritoRadioButton);
    
    JPanel prodottiCaseariPanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Prodotti caseari", null, prodottiCaseariPanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(2, false);
    GridBagLayout gbl_prodottiCaseariPanel = new GridBagLayout();
    gbl_prodottiCaseariPanel.columnWidths = new int[]{0, 204, 0};
    gbl_prodottiCaseariPanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_prodottiCaseariPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    gbl_prodottiCaseariPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    prodottiCaseariPanel.setLayout(gbl_prodottiCaseariPanel);
    
    JLabel stagionaturaLabel = new JLabel("Stagionatura");
    GridBagConstraints gbc_stagionaturaLabel = new GridBagConstraints();
    gbc_stagionaturaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_stagionaturaLabel.anchor = GridBagConstraints.EAST;
    gbc_stagionaturaLabel.gridx = 0;
    gbc_stagionaturaLabel.gridy = 0;
    prodottiCaseariPanel.add(stagionaturaLabel, gbc_stagionaturaLabel);
    
    stagionatureTextField = new JTextField();
    stagionatureTextField.setEnabled(false);
    GridBagConstraints gbc_stagionatureTextField = new GridBagConstraints();
    gbc_stagionatureTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_stagionatureTextField.insets = new Insets(0, 0, 5, 0);
    gbc_stagionatureTextField.gridx = 1;
    gbc_stagionatureTextField.gridy = 0;
    prodottiCaseariPanel.add(stagionatureTextField, gbc_stagionatureTextField);
    stagionatureTextField.setColumns(10);
    
    JLabel stabilimentoLabel = new JLabel("Stabilimento");
    GridBagConstraints gbc_stabilimentoLabel = new GridBagConstraints();
    gbc_stabilimentoLabel.anchor = GridBagConstraints.EAST;
    gbc_stabilimentoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_stabilimentoLabel.gridx = 0;
    gbc_stabilimentoLabel.gridy = 1;
    prodottiCaseariPanel.add(stabilimentoLabel, gbc_stabilimentoLabel);
    
    stabilimentoTextField = new JTextField();
    stabilimentoTextField.setEnabled(false);
    GridBagConstraints gbc_stabilimentoTextField = new GridBagConstraints();
    gbc_stabilimentoTextField.insets = new Insets(0, 0, 5, 0);
    gbc_stabilimentoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_stabilimentoTextField.gridx = 1;
    gbc_stabilimentoTextField.gridy = 1;
    prodottiCaseariPanel.add(stabilimentoTextField, gbc_stabilimentoTextField);
    stabilimentoTextField.setColumns(10);
    
    JLabel tipoLatteLabel = new JLabel("Tipo di latte");
    GridBagConstraints gbc_tipoLatteLabel = new GridBagConstraints();
    gbc_tipoLatteLabel.anchor = GridBagConstraints.EAST;
    gbc_tipoLatteLabel.insets = new Insets(0, 0, 0, 5);
    gbc_tipoLatteLabel.gridx = 0;
    gbc_tipoLatteLabel.gridy = 2;
    prodottiCaseariPanel.add(tipoLatteLabel, gbc_tipoLatteLabel);
    
    tipoLatteTextField = new JTextField();
    tipoLatteTextField.setEnabled(false);
    GridBagConstraints gbc_tipoLatteTextField = new GridBagConstraints();
    gbc_tipoLatteTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_tipoLatteTextField.gridx = 1;
    gbc_tipoLatteTextField.gridy = 2;
    prodottiCaseariPanel.add(tipoLatteTextField, gbc_tipoLatteTextField);
    tipoLatteTextField.setColumns(10);
    
    JPanel farinaceiPanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Farinacei", null, farinaceiPanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(3, false);
    GridBagLayout gbl_farinaceiPanel = new GridBagLayout();
    gbl_farinaceiPanel.columnWidths = new int[]{0, 0, 0, 0};
    gbl_farinaceiPanel.rowHeights = new int[]{0, 0, 0};
    gbl_farinaceiPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_farinaceiPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    farinaceiPanel.setLayout(gbl_farinaceiPanel);
    
    JLabel tipoFarinaLabel = new JLabel("Tipo di farina");
    GridBagConstraints gbc_tipoFarinaLabel = new GridBagConstraints();
    gbc_tipoFarinaLabel.anchor = GridBagConstraints.EAST;
    gbc_tipoFarinaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_tipoFarinaLabel.gridx = 0;
    gbc_tipoFarinaLabel.gridy = 0;
    farinaceiPanel.add(tipoFarinaLabel, gbc_tipoFarinaLabel);
    
    tipoFarinaTextField = new JTextField();
    tipoFarinaTextField.setEnabled(false);
    GridBagConstraints gbc_tipoFarinaTextField = new GridBagConstraints();
    gbc_tipoFarinaTextField.insets = new Insets(0, 0, 5, 5);
    gbc_tipoFarinaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_tipoFarinaTextField.gridx = 1;
    gbc_tipoFarinaTextField.gridy = 0;
    farinaceiPanel.add(tipoFarinaTextField, gbc_tipoFarinaTextField);
    tipoFarinaTextField.setColumns(10);
    
    senzaGlutineCheckbox.setEnabled(false);
    GridBagConstraints gbc_senzaGlutineCheckbox = new GridBagConstraints();
    gbc_senzaGlutineCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_senzaGlutineCheckbox.gridx = 0;
    gbc_senzaGlutineCheckbox.gridy = 1;
    farinaceiPanel.add(senzaGlutineCheckbox, gbc_senzaGlutineCheckbox);
    
    frescoCheckbox.setEnabled(false);
    GridBagConstraints gbc_frescoCheckbox = new GridBagConstraints();
    gbc_frescoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_frescoCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_frescoCheckbox.gridx = 1;
    gbc_frescoCheckbox.gridy = 1;
    farinaceiPanel.add(frescoCheckbox, gbc_frescoCheckbox);
    
    surgelatoFarinaceiCheckbox.setEnabled(false);
    GridBagConstraints gbc_surgelatoFarinaceiCheckbox = new GridBagConstraints();
    gbc_surgelatoFarinaceiCheckbox.gridx = 2;
    gbc_surgelatoFarinaceiCheckbox.gridy = 1;
    farinaceiPanel.add(surgelatoFarinaceiCheckbox, gbc_surgelatoFarinaceiCheckbox);
    
    JPanel uovaPanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Uova", null, uovaPanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(4, false);
    GridBagLayout gbl_uovaPanel = new GridBagLayout();
    gbl_uovaPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
    gbl_uovaPanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_uovaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_uovaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    uovaPanel.setLayout(gbl_uovaPanel);
    
    JLabel tipoDiAllevamentoLabel = new JLabel("Tipo di allevamento");
    GridBagConstraints gbc_tipoDiAllevamentoLabel = new GridBagConstraints();
    gbc_tipoDiAllevamentoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_tipoDiAllevamentoLabel.gridx = 0;
    gbc_tipoDiAllevamentoLabel.gridy = 0;
    uovaPanel.add(tipoDiAllevamentoLabel, gbc_tipoDiAllevamentoLabel);
    
    tipo0RadioButton.setEnabled(false);
    GridBagConstraints gbc_tipo0RadioButton = new GridBagConstraints();
    gbc_tipo0RadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo0RadioButton.gridx = 1;
    gbc_tipo0RadioButton.gridy = 0;
    uovaPanel.add(tipo0RadioButton, gbc_tipo0RadioButton);
    
    tipo1RadioButton.setEnabled(false);
    GridBagConstraints gbc_tipo1RadioButton = new GridBagConstraints();
    gbc_tipo1RadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo1RadioButton.gridx = 2;
    gbc_tipo1RadioButton.gridy = 0;
    uovaPanel.add(tipo1RadioButton, gbc_tipo1RadioButton);
    
    tipo2RadioButton.setEnabled(false);
    GridBagConstraints gbc_tipo2RadioButton = new GridBagConstraints();
    gbc_tipo2RadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo2RadioButton.gridx = 3;
    gbc_tipo2RadioButton.gridy = 0;
    uovaPanel.add(tipo2RadioButton, gbc_tipo2RadioButton);
    
    tipo3RadioButton.setEnabled(false);
    GridBagConstraints gbc_tipo3RadioButton = new GridBagConstraints();
    gbc_tipo3RadioButton.insets = new Insets(0, 0, 5, 0);
    gbc_tipo3RadioButton.gridx = 4;
    gbc_tipo3RadioButton.gridy = 0;
    uovaPanel.add(tipo3RadioButton, gbc_tipo3RadioButton);
    
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel.gridx = 0;
    gbc_lblNewLabel.gridy = 1;
    uovaPanel.add(lblNewLabel, gbc_lblNewLabel);
    
    GridBagConstraints gbc_codiceAllevamentoTextField = new GridBagConstraints();
    gbc_codiceAllevamentoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_codiceAllevamentoTextField.gridwidth = 4;
    gbc_codiceAllevamentoTextField.insets = new Insets(0, 0, 5, 5);
    gbc_codiceAllevamentoTextField.gridx = 1;
    gbc_codiceAllevamentoTextField.gridy = 1;
    uovaPanel.add(codiceAllevamentoTextField, gbc_codiceAllevamentoTextField);
    
    JLabel categoriaDiPesoLabel = new JLabel("Categoria di peso");
    GridBagConstraints gbc_categoriaDiPesoLabel = new GridBagConstraints();
    gbc_categoriaDiPesoLabel.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaDiPesoLabel.gridx = 0;
    gbc_categoriaDiPesoLabel.gridy = 2;
    uovaPanel.add(categoriaDiPesoLabel, gbc_categoriaDiPesoLabel);
    
    categoriaSRadioButton.setEnabled(false);
    GridBagConstraints gbc_categoriaSRadioButton = new GridBagConstraints();
    gbc_categoriaSRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaSRadioButton.gridx = 1;
    gbc_categoriaSRadioButton.gridy = 2;
    uovaPanel.add(categoriaSRadioButton, gbc_categoriaSRadioButton);
    
    categoriaMRadioButton.setEnabled(false);
    GridBagConstraints gbc_categoriaMRadioButton = new GridBagConstraints();
    gbc_categoriaMRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaMRadioButton.gridx = 2;
    gbc_categoriaMRadioButton.gridy = 2;
    uovaPanel.add(categoriaMRadioButton, gbc_categoriaMRadioButton);
    
    categoriaLRadioButton.setEnabled(false);
    GridBagConstraints gbc_categoriaLRadioButton = new GridBagConstraints();
    gbc_categoriaLRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaLRadioButton.gridx = 3;
    gbc_categoriaLRadioButton.gridy = 2;
    uovaPanel.add(categoriaLRadioButton, gbc_categoriaLRadioButton);
    
    categoriaXLRadioButton.setEnabled(false);
    GridBagConstraints gbc_categoriaXLRadioButton = new GridBagConstraints();
    gbc_categoriaXLRadioButton.gridx = 4;
    gbc_categoriaXLRadioButton.gridy = 2;
    uovaPanel.add(categoriaXLRadioButton, gbc_categoriaXLRadioButton);
    
    JPanel carnePescePanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Carne e pesce", null, carnePescePanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(5, false);
    GridBagLayout gbl_carnePescePanel = new GridBagLayout();
    gbl_carnePescePanel.columnWidths = new int[]{0, 0, 137, 0};
    gbl_carnePescePanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_carnePescePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_carnePescePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    carnePescePanel.setLayout(gbl_carnePescePanel);
    
    carneRadioButton.setEnabled(false);
    GridBagConstraints gbc_carneRadioButton = new GridBagConstraints();
    gbc_carneRadioButton.anchor = GridBagConstraints.WEST;
    gbc_carneRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_carneRadioButton.gridx = 0;
    gbc_carneRadioButton.gridy = 0;
    carnePescePanel.add(carneRadioButton, gbc_carneRadioButton);
    
    JLabel animaleLabel = new JLabel("Animale");
    GridBagConstraints gbc_animaleLabel = new GridBagConstraints();
    gbc_animaleLabel.anchor = GridBagConstraints.EAST;
    gbc_animaleLabel.insets = new Insets(0, 0, 5, 5);
    gbc_animaleLabel.gridx = 1;
    gbc_animaleLabel.gridy = 0;
    carnePescePanel.add(animaleLabel, gbc_animaleLabel);
    
    animaleTextField = new JTextField();
    animaleTextField.setEnabled(false);
    GridBagConstraints gbc_animaleTextField = new GridBagConstraints();
    gbc_animaleTextField.insets = new Insets(0, 0, 5, 0);
    gbc_animaleTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_animaleTextField.gridx = 2;
    gbc_animaleTextField.gridy = 0;
    carnePescePanel.add(animaleTextField, gbc_animaleTextField);
    animaleTextField.setColumns(10);
    
    pesceRadioButton.setEnabled(false);
    GridBagConstraints gbc_pesceRadioButton = new GridBagConstraints();
    gbc_pesceRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_pesceRadioButton.gridx = 0;
    gbc_pesceRadioButton.gridy = 1;
    carnePescePanel.add(pesceRadioButton, gbc_pesceRadioButton);
    
    daAllevamentoCheckBox.setEnabled(false);
    GridBagConstraints gbc_daAllevamentoCheckBox = new GridBagConstraints();
    gbc_daAllevamentoCheckBox.anchor = GridBagConstraints.WEST;
    gbc_daAllevamentoCheckBox.insets = new Insets(0, 0, 5, 5);
    gbc_daAllevamentoCheckBox.gridx = 1;
    gbc_daAllevamentoCheckBox.gridy = 1;
    carnePescePanel.add(daAllevamentoCheckBox, gbc_daAllevamentoCheckBox);
    
    confezionatoCheckBox.setEnabled(false);
    GridBagConstraints gbc_confezionatoCheckBox = new GridBagConstraints();
    gbc_confezionatoCheckBox.insets = new Insets(0, 0, 5, 0);
    gbc_confezionatoCheckBox.anchor = GridBagConstraints.WEST;
    gbc_confezionatoCheckBox.gridx = 2;
    gbc_confezionatoCheckBox.gridy = 1;
    carnePescePanel.add(confezionatoCheckBox, gbc_confezionatoCheckBox);
    
    JPanel bibitePanel = new JPanel();
    caratteristicheSpecificheTabbed.addTab("Bibite", null, bibitePanel, null);
    caratteristicheSpecificheTabbed.setEnabledAt(6, false);
    GridBagLayout gbl_bibitePanel = new GridBagLayout();
    gbl_bibitePanel.columnWidths = new int[]{0, 99, 0, 0, 0};
    gbl_bibitePanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_bibitePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_bibitePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    bibitePanel.setLayout(gbl_bibitePanel);
    
    acquaRadioButton.setEnabled(false);
    GridBagConstraints gbc_acquaRadioButton = new GridBagConstraints();
    gbc_acquaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_acquaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_acquaRadioButton.gridx = 0;
    gbc_acquaRadioButton.gridy = 0;
    bibitePanel.add(acquaRadioButton, gbc_acquaRadioButton);
    
    fermentatiRadioButton.setEnabled(false);
    GridBagConstraints gbc_fermentatiRadioButton = new GridBagConstraints();
    gbc_fermentatiRadioButton.anchor = GridBagConstraints.WEST;
    gbc_fermentatiRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_fermentatiRadioButton.gridx = 1;
    gbc_fermentatiRadioButton.gridy = 0;
    bibitePanel.add(fermentatiRadioButton, gbc_fermentatiRadioButton);
    
    JLabel gradazioneAlcolicaLabel = new JLabel("Gradazione alcolica");
    gradazioneAlcolicaLabel.setEnabled(false);
    GridBagConstraints gbc_gradazioneAlcolicaLabel = new GridBagConstraints();
    gbc_gradazioneAlcolicaLabel.anchor = GridBagConstraints.EAST;
    gbc_gradazioneAlcolicaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_gradazioneAlcolicaLabel.gridx = 2;
    gbc_gradazioneAlcolicaLabel.gridy = 0;
    bibitePanel.add(gradazioneAlcolicaLabel, gbc_gradazioneAlcolicaLabel);
    
    gradazioneAlcolicaTextField = new JTextField();
    gradazioneAlcolicaTextField.setEnabled(false);
    GridBagConstraints gbc_gradazioneAlcolicaTextField = new GridBagConstraints();
    gbc_gradazioneAlcolicaTextField.insets = new Insets(0, 0, 5, 0);
    gbc_gradazioneAlcolicaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_gradazioneAlcolicaTextField.gridx = 3;
    gbc_gradazioneAlcolicaTextField.gridy = 0;
    bibitePanel.add(gradazioneAlcolicaTextField, gbc_gradazioneAlcolicaTextField);
    gradazioneAlcolicaTextField.setColumns(10);
    
    succhiDiFruttaRadioButton.setEnabled(false);
    GridBagConstraints gbc_succhiDiFruttaRadioButton = new GridBagConstraints();
    gbc_succhiDiFruttaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_succhiDiFruttaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_succhiDiFruttaRadioButton.gridx = 0;
    gbc_succhiDiFruttaRadioButton.gridy = 1;
    bibitePanel.add(succhiDiFruttaRadioButton, gbc_succhiDiFruttaRadioButton);
    
    liquoriRadioButton.setEnabled(false);
    GridBagConstraints gbc_liquoriRadioButton = new GridBagConstraints();
    gbc_liquoriRadioButton.anchor = GridBagConstraints.WEST;
    gbc_liquoriRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_liquoriRadioButton.gridx = 1;
    gbc_liquoriRadioButton.gridy = 1;
    bibitePanel.add(liquoriRadioButton, gbc_liquoriRadioButton);
    
    frizzanteCheckBox.setEnabled(false);
    GridBagConstraints gbc_frizzanteCheckBox = new GridBagConstraints();
    gbc_frizzanteCheckBox.anchor = GridBagConstraints.WEST;
    gbc_frizzanteCheckBox.insets = new Insets(0, 0, 5, 5);
    gbc_frizzanteCheckBox.gridx = 2;
    gbc_frizzanteCheckBox.gridy = 1;
    bibitePanel.add(frizzanteCheckBox, gbc_frizzanteCheckBox);
    
    softDrinkRadioButton.setEnabled(false);
    GridBagConstraints gbc_softDrinkRadioButton = new GridBagConstraints();
    gbc_softDrinkRadioButton.anchor = GridBagConstraints.WEST;
    gbc_softDrinkRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_softDrinkRadioButton.gridx = 0;
    gbc_softDrinkRadioButton.gridy = 2;
    bibitePanel.add(softDrinkRadioButton, gbc_softDrinkRadioButton);
    
    bibitaAltroRadioButton.setEnabled(false);
    GridBagConstraints gbc_bibitaAltroRadioButton = new GridBagConstraints();
    gbc_bibitaAltroRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_bibitaAltroRadioButton.anchor = GridBagConstraints.WEST;
    gbc_bibitaAltroRadioButton.gridx = 1;
    gbc_bibitaAltroRadioButton.gridy = 2;
    bibitePanel.add(bibitaAltroRadioButton, gbc_bibitaAltroRadioButton);
    
    JPanel lottiPanel = new JPanel();
    lottiPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lotti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    GridBagConstraints gbc_lottiPanel = new GridBagConstraints();
    gbc_lottiPanel.weightx = 1.0;
    gbc_lottiPanel.weighty = 1.0;
    gbc_lottiPanel.fill = GridBagConstraints.BOTH;
    gbc_lottiPanel.gridx = 0;
    gbc_lottiPanel.gridy = 2;
    add(lottiPanel, gbc_lottiPanel);
    lottiPanel.setLayout(new BorderLayout(0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    lottiPanel.add(scrollPane);
    
    lottiTable = new JTable();
    lottiTable.setBackground(SystemColor.control);
    lottiTable.getTableHeader().setOpaque(true);
    lottiTable.getTableHeader().setBackground(SystemColor.control);
    scrollPane.setViewportView(lottiTable);
    
    fruttaVerduraBG.add(fruttaRadioButton);
    fruttaVerduraBG.add(verduraRadioButton);
    
    conserveBG.add(sottovuotoRadioButton);
    conserveBG.add(sottolioRadioButton);
    conserveBG.add(inZuccheriRadioButton);
    conserveBG.add(conserveAltroTipoRadioButton);
    conserveBG.add(sottacetoRadioButton);
    conserveBG.add(sottosaleRadioButton);
    conserveBG.add(sottoSpiritoRadioButton);
    
    tipoAllevamentoBG.add(tipo0RadioButton);
    tipoAllevamentoBG.add(tipo1RadioButton);
    tipoAllevamentoBG.add(tipo2RadioButton);
    tipoAllevamentoBG.add(tipo3RadioButton);
    
    categoriaPesoBG.add(categoriaSRadioButton);
    categoriaPesoBG.add(categoriaMRadioButton);
    categoriaPesoBG.add(categoriaLRadioButton);
    categoriaPesoBG.add(categoriaXLRadioButton);
    
    carnePesceBG.add(carneRadioButton);
    carnePesceBG.add(pesceRadioButton);
    
    bibitaBG.add(acquaRadioButton);
    bibitaBG.add(fermentatiRadioButton);
    bibitaBG.add(succhiDiFruttaRadioButton);
    bibitaBG.add(liquoriRadioButton);
    bibitaBG.add(softDrinkRadioButton);
    bibitaBG.add(bibitaAltroRadioButton);
    
  }

}
