package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.GridLayout;

public class ProdottiPanel extends JPanel {
  private JTextField nomeTextField;
  private JTextField codiceProdottoTextField;
  private JTextField prezzoTextField;
  private JTextField stagionatureTextField;
  private JTextField stabilimentoTextField;
  private JTextField tipoLatteTextField;
  private JTextField tipoFarinaTextField;
  private JTextField textField;
  private JTextField gradazioneAlcolicaTextField;

  /**
   * Create the panel.
   */
  public ProdottiPanel() {
    setLayout(new BorderLayout(0, 0));
    
    JPanel ricercaPanel = new JPanel();
    add(ricercaPanel, BorderLayout.NORTH);
    GridBagLayout gbl_ricercaPanel = new GridBagLayout();
    gbl_ricercaPanel.columnWidths = new int[]{0, 147, 0, 0, 0, 0};
    gbl_ricercaPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
    gbl_ricercaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_ricercaPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    ricercaPanel.setLayout(gbl_ricercaPanel);
    
    JLabel nomeLabel = new JLabel("Nome");
    GridBagConstraints gbc_nomeLabel = new GridBagConstraints();
    gbc_nomeLabel.anchor = GridBagConstraints.EAST;
    gbc_nomeLabel.insets = new Insets(0, 0, 5, 5);
    gbc_nomeLabel.gridx = 0;
    gbc_nomeLabel.gridy = 0;
    ricercaPanel.add(nomeLabel, gbc_nomeLabel);
    
    nomeTextField = new JTextField();
    GridBagConstraints gbc_nomeTextField = new GridBagConstraints();
    gbc_nomeTextField.insets = new Insets(0, 0, 5, 5);
    gbc_nomeTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_nomeTextField.gridx = 1;
    gbc_nomeTextField.gridy = 0;
    ricercaPanel.add(nomeTextField, gbc_nomeTextField);
    nomeTextField.setColumns(10);
    
    JCheckBox fruttaVerduraCheckbox = new JCheckBox("Frutta e Verdura");
    GridBagConstraints gbc_fruttaVerduraCheckbox = new GridBagConstraints();
    gbc_fruttaVerduraCheckbox.anchor = GridBagConstraints.WEST;
    gbc_fruttaVerduraCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_fruttaVerduraCheckbox.gridx = 3;
    gbc_fruttaVerduraCheckbox.gridy = 0;
    ricercaPanel.add(fruttaVerduraCheckbox, gbc_fruttaVerduraCheckbox);
    
    JCheckBox carnePesceCheckbox = new JCheckBox("Carne e pesce");
    GridBagConstraints gbc_carnePesceCheckbox = new GridBagConstraints();
    gbc_carnePesceCheckbox.anchor = GridBagConstraints.WEST;
    gbc_carnePesceCheckbox.insets = new Insets(0, 0, 5, 0);
    gbc_carnePesceCheckbox.gridx = 4;
    gbc_carnePesceCheckbox.gridy = 0;
    ricercaPanel.add(carnePesceCheckbox, gbc_carnePesceCheckbox);
    
    JLabel codiceProdottoLabel = new JLabel("Codice Prodotto");
    GridBagConstraints gbc_codiceProdottoLabel = new GridBagConstraints();
    gbc_codiceProdottoLabel.anchor = GridBagConstraints.EAST;
    gbc_codiceProdottoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_codiceProdottoLabel.gridx = 0;
    gbc_codiceProdottoLabel.gridy = 1;
    ricercaPanel.add(codiceProdottoLabel, gbc_codiceProdottoLabel);
    
    codiceProdottoTextField = new JTextField();
    GridBagConstraints gbc_codiceProdottoTextField = new GridBagConstraints();
    gbc_codiceProdottoTextField.insets = new Insets(0, 0, 5, 5);
    gbc_codiceProdottoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_codiceProdottoTextField.gridx = 1;
    gbc_codiceProdottoTextField.gridy = 1;
    ricercaPanel.add(codiceProdottoTextField, gbc_codiceProdottoTextField);
    codiceProdottoTextField.setColumns(10);
    
    JCheckBox prodottiCaseariCheckbox = new JCheckBox("Prodotti casearei");
    GridBagConstraints gbc_prodottiCaseariCheckbox = new GridBagConstraints();
    gbc_prodottiCaseariCheckbox.anchor = GridBagConstraints.WEST;
    gbc_prodottiCaseariCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_prodottiCaseariCheckbox.gridx = 3;
    gbc_prodottiCaseariCheckbox.gridy = 1;
    ricercaPanel.add(prodottiCaseariCheckbox, gbc_prodottiCaseariCheckbox);
    
    JCheckBox bibiteCheckbox = new JCheckBox("Bibite");
    GridBagConstraints gbc_bibiteCheckbox = new GridBagConstraints();
    gbc_bibiteCheckbox.anchor = GridBagConstraints.WEST;
    gbc_bibiteCheckbox.insets = new Insets(0, 0, 5, 0);
    gbc_bibiteCheckbox.gridx = 4;
    gbc_bibiteCheckbox.gridy = 1;
    ricercaPanel.add(bibiteCheckbox, gbc_bibiteCheckbox);
    
    JLabel prezzoLabel = new JLabel("Prezzo");
    GridBagConstraints gbc_prezzoLabel = new GridBagConstraints();
    gbc_prezzoLabel.anchor = GridBagConstraints.EAST;
    gbc_prezzoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_prezzoLabel.gridx = 0;
    gbc_prezzoLabel.gridy = 2;
    ricercaPanel.add(prezzoLabel, gbc_prezzoLabel);
    
    prezzoTextField = new JTextField();
    GridBagConstraints gbc_prezzoTextField = new GridBagConstraints();
    gbc_prezzoTextField.insets = new Insets(0, 0, 5, 5);
    gbc_prezzoTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_prezzoTextField.gridx = 1;
    gbc_prezzoTextField.gridy = 2;
    ricercaPanel.add(prezzoTextField, gbc_prezzoTextField);
    prezzoTextField.setColumns(10);
    
    JCheckBox farinaceiCheckbox = new JCheckBox("Farinacei");
    GridBagConstraints gbc_farinaceiCheckbox = new GridBagConstraints();
    gbc_farinaceiCheckbox.anchor = GridBagConstraints.WEST;
    gbc_farinaceiCheckbox.insets = new Insets(0, 0, 5, 5);
    gbc_farinaceiCheckbox.gridx = 3;
    gbc_farinaceiCheckbox.gridy = 2;
    ricercaPanel.add(farinaceiCheckbox, gbc_farinaceiCheckbox);
    
    JCheckBox conserveCheckbox = new JCheckBox("Conserve");
    GridBagConstraints gbc_conserveCheckbox = new GridBagConstraints();
    gbc_conserveCheckbox.anchor = GridBagConstraints.WEST;
    gbc_conserveCheckbox.insets = new Insets(0, 0, 5, 0);
    gbc_conserveCheckbox.gridx = 4;
    gbc_conserveCheckbox.gridy = 2;
    ricercaPanel.add(conserveCheckbox, gbc_conserveCheckbox);
    
    JCheckBox sfusoCheckBox = new JCheckBox("Sfuso");
    GridBagConstraints gbc_sfusoCheckBox = new GridBagConstraints();
    gbc_sfusoCheckBox.anchor = GridBagConstraints.WEST;
    gbc_sfusoCheckBox.insets = new Insets(0, 0, 0, 5);
    gbc_sfusoCheckBox.gridx = 1;
    gbc_sfusoCheckBox.gridy = 3;
    ricercaPanel.add(sfusoCheckBox, gbc_sfusoCheckBox);
    
    JCheckBox uovaCheckbox = new JCheckBox("Uova");
    GridBagConstraints gbc_uovaCheckbox = new GridBagConstraints();
    gbc_uovaCheckbox.anchor = GridBagConstraints.WEST;
    gbc_uovaCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_uovaCheckbox.gridx = 3;
    gbc_uovaCheckbox.gridy = 3;
    ricercaPanel.add(uovaCheckbox, gbc_uovaCheckbox);
    
    JCheckBox altriTipoCheckbox = new JCheckBox("Altri tipi");
    GridBagConstraints gbc_altriTipoCheckbox = new GridBagConstraints();
    gbc_altriTipoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_altriTipoCheckbox.gridx = 4;
    gbc_altriTipoCheckbox.gridy = 3;
    ricercaPanel.add(altriTipoCheckbox, gbc_altriTipoCheckbox);
    
    JTabbedPane caratteristicheSpecifichePanel = new JTabbedPane(JTabbedPane.TOP);
    add(caratteristicheSpecifichePanel, BorderLayout.CENTER);
    
    JPanel fruttaVerduraPanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Frutta e Verdura", null, fruttaVerduraPanel, null);
    GridBagLayout gbl_fruttaVerduraPanel = new GridBagLayout();
    gbl_fruttaVerduraPanel.columnWidths = new int[]{0, 0, 0};
    gbl_fruttaVerduraPanel.rowHeights = new int[]{0, 0, 0};
    gbl_fruttaVerduraPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    gbl_fruttaVerduraPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    fruttaVerduraPanel.setLayout(gbl_fruttaVerduraPanel);
    
    JRadioButton fruttaRadioButton = new JRadioButton("Frutta");
    GridBagConstraints gbc_fruttaRadioButton = new GridBagConstraints();
    gbc_fruttaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_fruttaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_fruttaRadioButton.gridx = 0;
    gbc_fruttaRadioButton.gridy = 0;
    fruttaVerduraPanel.add(fruttaRadioButton, gbc_fruttaRadioButton);
    
    JCheckBox surgelatoFruttaVerduraCheckbox = new JCheckBox("Surgelato");
    GridBagConstraints gbc_surgelatoFruttaVerduraCheckbox = new GridBagConstraints();
    gbc_surgelatoFruttaVerduraCheckbox.anchor = GridBagConstraints.WEST;
    gbc_surgelatoFruttaVerduraCheckbox.insets = new Insets(0, 0, 5, 0);
    gbc_surgelatoFruttaVerduraCheckbox.gridx = 1;
    gbc_surgelatoFruttaVerduraCheckbox.gridy = 0;
    fruttaVerduraPanel.add(surgelatoFruttaVerduraCheckbox, gbc_surgelatoFruttaVerduraCheckbox);
    
    JRadioButton verduraRadioButton = new JRadioButton("Verdura");
    GridBagConstraints gbc_verduraRadioButton = new GridBagConstraints();
    gbc_verduraRadioButton.anchor = GridBagConstraints.WEST;
    gbc_verduraRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_verduraRadioButton.gridx = 0;
    gbc_verduraRadioButton.gridy = 1;
    fruttaVerduraPanel.add(verduraRadioButton, gbc_verduraRadioButton);
    
    JCheckBox biologicoCheckbox = new JCheckBox("Biologico");
    GridBagConstraints gbc_biologicoCheckbox = new GridBagConstraints();
    gbc_biologicoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_biologicoCheckbox.gridx = 1;
    gbc_biologicoCheckbox.gridy = 1;
    fruttaVerduraPanel.add(biologicoCheckbox, gbc_biologicoCheckbox);
    
    JPanel prodottiCaseariPanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Prodotti caseari", null, prodottiCaseariPanel, null);
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
    GridBagConstraints gbc_tipoLatteTextField = new GridBagConstraints();
    gbc_tipoLatteTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_tipoLatteTextField.gridx = 1;
    gbc_tipoLatteTextField.gridy = 2;
    prodottiCaseariPanel.add(tipoLatteTextField, gbc_tipoLatteTextField);
    tipoLatteTextField.setColumns(10);
    
    JPanel farinaceiPanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Farinacei", null, farinaceiPanel, null);
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
    GridBagConstraints gbc_tipoFarinaTextField = new GridBagConstraints();
    gbc_tipoFarinaTextField.insets = new Insets(0, 0, 5, 5);
    gbc_tipoFarinaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_tipoFarinaTextField.gridx = 1;
    gbc_tipoFarinaTextField.gridy = 0;
    farinaceiPanel.add(tipoFarinaTextField, gbc_tipoFarinaTextField);
    tipoFarinaTextField.setColumns(10);
    
    JCheckBox senzaGlutineCheckbox = new JCheckBox("Senza glutine");
    GridBagConstraints gbc_senzaGlutineCheckbox = new GridBagConstraints();
    gbc_senzaGlutineCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_senzaGlutineCheckbox.gridx = 0;
    gbc_senzaGlutineCheckbox.gridy = 1;
    farinaceiPanel.add(senzaGlutineCheckbox, gbc_senzaGlutineCheckbox);
    
    JCheckBox frescoCheckbox = new JCheckBox("Fresco");
    GridBagConstraints gbc_frescoCheckbox = new GridBagConstraints();
    gbc_frescoCheckbox.anchor = GridBagConstraints.WEST;
    gbc_frescoCheckbox.insets = new Insets(0, 0, 0, 5);
    gbc_frescoCheckbox.gridx = 1;
    gbc_frescoCheckbox.gridy = 1;
    farinaceiPanel.add(frescoCheckbox, gbc_frescoCheckbox);
    
    JCheckBox surgelatoFarinaceiCheckbox = new JCheckBox("Surgelato");
    GridBagConstraints gbc_surgelatoFarinaceiCheckbox = new GridBagConstraints();
    gbc_surgelatoFarinaceiCheckbox.gridx = 2;
    gbc_surgelatoFarinaceiCheckbox.gridy = 1;
    farinaceiPanel.add(surgelatoFarinaceiCheckbox, gbc_surgelatoFarinaceiCheckbox);
    
    JPanel uovaPanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Uova", null, uovaPanel, null);
    GridBagLayout gbl_uovaPanel = new GridBagLayout();
    gbl_uovaPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
    gbl_uovaPanel.rowHeights = new int[]{0, 0, 0};
    gbl_uovaPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_uovaPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    uovaPanel.setLayout(gbl_uovaPanel);
    
    JLabel tipoDiAllevamentoLabel = new JLabel("Tipo di allevamento");
    GridBagConstraints gbc_tipoDiAllevamentoLabel = new GridBagConstraints();
    gbc_tipoDiAllevamentoLabel.insets = new Insets(0, 0, 5, 5);
    gbc_tipoDiAllevamentoLabel.gridx = 0;
    gbc_tipoDiAllevamentoLabel.gridy = 0;
    uovaPanel.add(tipoDiAllevamentoLabel, gbc_tipoDiAllevamentoLabel);
    
    JRadioButton tipo0RadioButton = new JRadioButton("0");
    GridBagConstraints gbc_tipo0RadioButton = new GridBagConstraints();
    gbc_tipo0RadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo0RadioButton.gridx = 1;
    gbc_tipo0RadioButton.gridy = 0;
    uovaPanel.add(tipo0RadioButton, gbc_tipo0RadioButton);
    
    JRadioButton tipo1RadioButton = new JRadioButton("1");
    GridBagConstraints gbc_tipo1RadioButton = new GridBagConstraints();
    gbc_tipo1RadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo1RadioButton.gridx = 2;
    gbc_tipo1RadioButton.gridy = 0;
    uovaPanel.add(tipo1RadioButton, gbc_tipo1RadioButton);
    
    JRadioButton tipo2NewRadioButton = new JRadioButton("2");
    GridBagConstraints gbc_tipo2NewRadioButton = new GridBagConstraints();
    gbc_tipo2NewRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_tipo2NewRadioButton.gridx = 3;
    gbc_tipo2NewRadioButton.gridy = 0;
    uovaPanel.add(tipo2NewRadioButton, gbc_tipo2NewRadioButton);
    
    JRadioButton tipo3RadioButton = new JRadioButton("3");
    GridBagConstraints gbc_tipo3RadioButton = new GridBagConstraints();
    gbc_tipo3RadioButton.insets = new Insets(0, 0, 5, 0);
    gbc_tipo3RadioButton.gridx = 4;
    gbc_tipo3RadioButton.gridy = 0;
    uovaPanel.add(tipo3RadioButton, gbc_tipo3RadioButton);
    
    JLabel categoriaDiPesoLabel = new JLabel("Categoria di peso");
    GridBagConstraints gbc_categoriaDiPesoLabel = new GridBagConstraints();
    gbc_categoriaDiPesoLabel.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaDiPesoLabel.gridx = 0;
    gbc_categoriaDiPesoLabel.gridy = 1;
    uovaPanel.add(categoriaDiPesoLabel, gbc_categoriaDiPesoLabel);
    
    JRadioButton categoriaSRadioButton = new JRadioButton("S");
    GridBagConstraints gbc_categoriaSRadioButton = new GridBagConstraints();
    gbc_categoriaSRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaSRadioButton.gridx = 1;
    gbc_categoriaSRadioButton.gridy = 1;
    uovaPanel.add(categoriaSRadioButton, gbc_categoriaSRadioButton);
    
    JRadioButton categoriaMRadioButton = new JRadioButton("M");
    GridBagConstraints gbc_categoriaMRadioButton = new GridBagConstraints();
    gbc_categoriaMRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaMRadioButton.gridx = 2;
    gbc_categoriaMRadioButton.gridy = 1;
    uovaPanel.add(categoriaMRadioButton, gbc_categoriaMRadioButton);
    
    JRadioButton categoriaLRadioButton = new JRadioButton("L");
    GridBagConstraints gbc_categoriaLRadioButton = new GridBagConstraints();
    gbc_categoriaLRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_categoriaLRadioButton.gridx = 3;
    gbc_categoriaLRadioButton.gridy = 1;
    uovaPanel.add(categoriaLRadioButton, gbc_categoriaLRadioButton);
    
    JRadioButton categoriaXLRadioButton = new JRadioButton("XL");
    GridBagConstraints gbc_categoriaXLRadioButton = new GridBagConstraints();
    gbc_categoriaXLRadioButton.gridx = 4;
    gbc_categoriaXLRadioButton.gridy = 1;
    uovaPanel.add(categoriaXLRadioButton, gbc_categoriaXLRadioButton);
    
    JPanel carnePescePanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Carne e pesce", null, carnePescePanel, null);
    GridBagLayout gbl_carnePescePanel = new GridBagLayout();
    gbl_carnePescePanel.columnWidths = new int[]{0, 0, 137, 0};
    gbl_carnePescePanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_carnePescePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_carnePescePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    carnePescePanel.setLayout(gbl_carnePescePanel);
    
    JRadioButton carneRadioButton = new JRadioButton("Carne");
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
    
    textField = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 0);
    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField.gridx = 2;
    gbc_textField.gridy = 0;
    carnePescePanel.add(textField, gbc_textField);
    textField.setColumns(10);
    
    JRadioButton pesceRadioButton = new JRadioButton("Pesce");
    GridBagConstraints gbc_pesceRadioButton = new GridBagConstraints();
    gbc_pesceRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_pesceRadioButton.gridx = 0;
    gbc_pesceRadioButton.gridy = 1;
    carnePescePanel.add(pesceRadioButton, gbc_pesceRadioButton);
    
    JCheckBox selvagginaCheckBox = new JCheckBox("Selvaggina");
    GridBagConstraints gbc_selvagginaCheckBox = new GridBagConstraints();
    gbc_selvagginaCheckBox.anchor = GridBagConstraints.WEST;
    gbc_selvagginaCheckBox.insets = new Insets(0, 0, 5, 5);
    gbc_selvagginaCheckBox.gridx = 1;
    gbc_selvagginaCheckBox.gridy = 1;
    carnePescePanel.add(selvagginaCheckBox, gbc_selvagginaCheckBox);
    
    JRadioButton salumiRadioButton = new JRadioButton("Salumi");
    GridBagConstraints gbc_salumiRadioButton = new GridBagConstraints();
    gbc_salumiRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_salumiRadioButton.gridx = 0;
    gbc_salumiRadioButton.gridy = 2;
    carnePescePanel.add(salumiRadioButton, gbc_salumiRadioButton);
    
    JCheckBox confezionatoCheckBox = new JCheckBox("Confezionato");
    GridBagConstraints gbc_confezionatoCheckBox = new GridBagConstraints();
    gbc_confezionatoCheckBox.anchor = GridBagConstraints.WEST;
    gbc_confezionatoCheckBox.insets = new Insets(0, 0, 0, 5);
    gbc_confezionatoCheckBox.gridx = 1;
    gbc_confezionatoCheckBox.gridy = 2;
    carnePescePanel.add(confezionatoCheckBox, gbc_confezionatoCheckBox);
    
    JPanel bibitePanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Bibite", null, bibitePanel, null);
    GridBagLayout gbl_bibitePanel = new GridBagLayout();
    gbl_bibitePanel.columnWidths = new int[]{0, 99, 0, 0, 0};
    gbl_bibitePanel.rowHeights = new int[]{0, 0, 0, 0};
    gbl_bibitePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_bibitePanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    bibitePanel.setLayout(gbl_bibitePanel);
    
    JRadioButton acquaRadioButton = new JRadioButton("Acqua");
    GridBagConstraints gbc_acquaRadioButton = new GridBagConstraints();
    gbc_acquaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_acquaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_acquaRadioButton.gridx = 0;
    gbc_acquaRadioButton.gridy = 0;
    bibitePanel.add(acquaRadioButton, gbc_acquaRadioButton);
    
    JRadioButton fermentatiRadioButton = new JRadioButton("Fermentati");
    GridBagConstraints gbc_fermentatiRadioButton = new GridBagConstraints();
    gbc_fermentatiRadioButton.anchor = GridBagConstraints.WEST;
    gbc_fermentatiRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_fermentatiRadioButton.gridx = 1;
    gbc_fermentatiRadioButton.gridy = 0;
    bibitePanel.add(fermentatiRadioButton, gbc_fermentatiRadioButton);
    
    JLabel gradazioneAlcolicaLabel = new JLabel("Gradazione alcolica");
    GridBagConstraints gbc_gradazioneAlcolicaLabel = new GridBagConstraints();
    gbc_gradazioneAlcolicaLabel.anchor = GridBagConstraints.EAST;
    gbc_gradazioneAlcolicaLabel.insets = new Insets(0, 0, 5, 5);
    gbc_gradazioneAlcolicaLabel.gridx = 2;
    gbc_gradazioneAlcolicaLabel.gridy = 0;
    bibitePanel.add(gradazioneAlcolicaLabel, gbc_gradazioneAlcolicaLabel);
    
    gradazioneAlcolicaTextField = new JTextField();
    GridBagConstraints gbc_gradazioneAlcolicaTextField = new GridBagConstraints();
    gbc_gradazioneAlcolicaTextField.insets = new Insets(0, 0, 5, 0);
    gbc_gradazioneAlcolicaTextField.fill = GridBagConstraints.HORIZONTAL;
    gbc_gradazioneAlcolicaTextField.gridx = 3;
    gbc_gradazioneAlcolicaTextField.gridy = 0;
    bibitePanel.add(gradazioneAlcolicaTextField, gbc_gradazioneAlcolicaTextField);
    gradazioneAlcolicaTextField.setColumns(10);
    
    JRadioButton succhiDiFruttaRadioButton = new JRadioButton("Succhi di frutta");
    GridBagConstraints gbc_succhiDiFruttaRadioButton = new GridBagConstraints();
    gbc_succhiDiFruttaRadioButton.anchor = GridBagConstraints.WEST;
    gbc_succhiDiFruttaRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_succhiDiFruttaRadioButton.gridx = 0;
    gbc_succhiDiFruttaRadioButton.gridy = 1;
    bibitePanel.add(succhiDiFruttaRadioButton, gbc_succhiDiFruttaRadioButton);
    
    JRadioButton liquoriRadioButton = new JRadioButton("Liquori");
    GridBagConstraints gbc_liquoriRadioButton = new GridBagConstraints();
    gbc_liquoriRadioButton.anchor = GridBagConstraints.WEST;
    gbc_liquoriRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_liquoriRadioButton.gridx = 1;
    gbc_liquoriRadioButton.gridy = 1;
    bibitePanel.add(liquoriRadioButton, gbc_liquoriRadioButton);
    
    JCheckBox frizzanteCheckBox = new JCheckBox("Frizzante");
    GridBagConstraints gbc_frizzanteCheckBox = new GridBagConstraints();
    gbc_frizzanteCheckBox.anchor = GridBagConstraints.WEST;
    gbc_frizzanteCheckBox.insets = new Insets(0, 0, 5, 5);
    gbc_frizzanteCheckBox.gridx = 2;
    gbc_frizzanteCheckBox.gridy = 1;
    bibitePanel.add(frizzanteCheckBox, gbc_frizzanteCheckBox);
    
    JRadioButton softDrinkRadioButton = new JRadioButton("Soft Drink");
    GridBagConstraints gbc_softDrinkRadioButton = new GridBagConstraints();
    gbc_softDrinkRadioButton.anchor = GridBagConstraints.WEST;
    gbc_softDrinkRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_softDrinkRadioButton.gridx = 0;
    gbc_softDrinkRadioButton.gridy = 2;
    bibitePanel.add(softDrinkRadioButton, gbc_softDrinkRadioButton);
    
    JRadioButton bibitaAltroRadioButton = new JRadioButton("Altro tipo");
    GridBagConstraints gbc_bibitaAltroRadioButton = new GridBagConstraints();
    gbc_bibitaAltroRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_bibitaAltroRadioButton.anchor = GridBagConstraints.WEST;
    gbc_bibitaAltroRadioButton.gridx = 1;
    gbc_bibitaAltroRadioButton.gridy = 2;
    bibitePanel.add(bibitaAltroRadioButton, gbc_bibitaAltroRadioButton);
    
    JPanel conservePanel = new JPanel();
    caratteristicheSpecifichePanel.addTab("Conserve", null, conservePanel, null);
    GridBagLayout gbl_conservePanel = new GridBagLayout();
    gbl_conservePanel.columnWidths = new int[]{0, 0, 0, 0, 0};
    gbl_conservePanel.rowHeights = new int[]{0, 0, 0};
    gbl_conservePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
    gbl_conservePanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    conservePanel.setLayout(gbl_conservePanel);
    
    JRadioButton sottovuotoRadioButton = new JRadioButton("Sottovuoto");
    GridBagConstraints gbc_sottovuotoRadioButton = new GridBagConstraints();
    gbc_sottovuotoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottovuotoRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_sottovuotoRadioButton.gridx = 0;
    gbc_sottovuotoRadioButton.gridy = 0;
    conservePanel.add(sottovuotoRadioButton, gbc_sottovuotoRadioButton);
    
    JRadioButton sottolioRadioButton = new JRadioButton("Sott'olio");
    GridBagConstraints gbc_sottolioRadioButton = new GridBagConstraints();
    gbc_sottolioRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottolioRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_sottolioRadioButton.gridx = 1;
    gbc_sottolioRadioButton.gridy = 0;
    conservePanel.add(sottolioRadioButton, gbc_sottolioRadioButton);
    
    JRadioButton inZuccheriRadioButton = new JRadioButton("In zuccheri");
    GridBagConstraints gbc_inZuccheriRadioButton = new GridBagConstraints();
    gbc_inZuccheriRadioButton.anchor = GridBagConstraints.WEST;
    gbc_inZuccheriRadioButton.insets = new Insets(0, 0, 5, 5);
    gbc_inZuccheriRadioButton.gridx = 2;
    gbc_inZuccheriRadioButton.gridy = 0;
    conservePanel.add(inZuccheriRadioButton, gbc_inZuccheriRadioButton);
    
    JRadioButton conserveAltroTipoRadioButton = new JRadioButton("Altro tipo di conservazione");
    GridBagConstraints gbc_conserveAltroTipoRadioButton = new GridBagConstraints();
    gbc_conserveAltroTipoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_conserveAltroTipoRadioButton.insets = new Insets(0, 0, 5, 0);
    gbc_conserveAltroTipoRadioButton.gridx = 3;
    gbc_conserveAltroTipoRadioButton.gridy = 0;
    conservePanel.add(conserveAltroTipoRadioButton, gbc_conserveAltroTipoRadioButton);
    
    JRadioButton sottacetoRadioButton = new JRadioButton("Sott'aceto");
    GridBagConstraints gbc_sottacetoRadioButton = new GridBagConstraints();
    gbc_sottacetoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottacetoRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottacetoRadioButton.gridx = 0;
    gbc_sottacetoRadioButton.gridy = 1;
    conservePanel.add(sottacetoRadioButton, gbc_sottacetoRadioButton);
    
    JRadioButton sottosaleRadioButton = new JRadioButton("Sotto sale");
    GridBagConstraints gbc_sottosaleRadioButton = new GridBagConstraints();
    gbc_sottosaleRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottosaleRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottosaleRadioButton.gridx = 1;
    gbc_sottosaleRadioButton.gridy = 1;
    conservePanel.add(sottosaleRadioButton, gbc_sottosaleRadioButton);
    
    JRadioButton sottoSpiritoRadioButton = new JRadioButton("Sotto spirito");
    GridBagConstraints gbc_sottoSpiritoRadioButton = new GridBagConstraints();
    gbc_sottoSpiritoRadioButton.anchor = GridBagConstraints.WEST;
    gbc_sottoSpiritoRadioButton.insets = new Insets(0, 0, 0, 5);
    gbc_sottoSpiritoRadioButton.gridx = 2;
    gbc_sottoSpiritoRadioButton.gridy = 1;
    conservePanel.add(sottoSpiritoRadioButton, gbc_sottoSpiritoRadioButton);
    
  }

}