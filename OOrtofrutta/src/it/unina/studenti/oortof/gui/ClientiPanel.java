package it.unina.studenti.oortof.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class ClientiPanel extends JPanel {
  private JTextField cfTextField;
  private JTextField nomeTextField;
  private JTextField cognomeTextField;
  private JTextField dataNascitaTextField;
  private JTextField eMailTextField;
  private JTextField totalePuntiTextField;
  private JTable scontriniTable;
  private JTable acquistiTable;
  public ClientiPanel() {
    GridBagLayout gridBagLayout = new GridBagLayout();
    setLayout(gridBagLayout);
    
    JPanel infoClientePanel = new JPanel();
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
    gbl_infoClientePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
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
    
    JRadioButton mRadioButton = new JRadioButton("M");
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
    
    JRadioButton fRadioButton = new JRadioButton(" F");
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
    gbc_dataNascitaLabel.insets = new Insets(0, 5, 0, 5);
    gbc_dataNascitaLabel.anchor = GridBagConstraints.EAST;
    gbc_dataNascitaLabel.gridx = 0;
    gbc_dataNascitaLabel.gridy = 3;
    infoClientePanel.add(dataNascitaLabel, gbc_dataNascitaLabel);
    
    dataNascitaTextField = new JTextField();
    dataNascitaTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_dataNascitaTextField = new GridBagConstraints();
    gbc_dataNascitaTextField.anchor = GridBagConstraints.WEST;
    gbc_dataNascitaTextField.insets = new Insets(0, 0, 0, 5);
    gbc_dataNascitaTextField.gridx = 1;
    gbc_dataNascitaTextField.gridy = 3;
    infoClientePanel.add(dataNascitaTextField, gbc_dataNascitaTextField);
    dataNascitaTextField.setColumns(20);
    
    JLabel lblNewLabel = new JLabel("Totale punti");
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
    gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
    gbc_lblNewLabel.gridx = 3;
    gbc_lblNewLabel.gridy = 3;
    infoClientePanel.add(lblNewLabel, gbc_lblNewLabel);
    
    totalePuntiTextField = new JTextField();
    totalePuntiTextField.setMinimumSize(new Dimension(150, 20));
    GridBagConstraints gbc_totalePuntiTextField = new GridBagConstraints();
    gbc_totalePuntiTextField.anchor = GridBagConstraints.WEST;
    gbc_totalePuntiTextField.weightx = 0.1;
    gbc_totalePuntiTextField.gridx = 4;
    gbc_totalePuntiTextField.gridy = 3;
    infoClientePanel.add(totalePuntiTextField, gbc_totalePuntiTextField);
    totalePuntiTextField.setColumns(10);
    
    JPanel scontriniPanel = new JPanel();
    scontriniPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Scontrini", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_scontriniPanel = new GridBagConstraints();
    gbc_scontriniPanel.weightx = 1.0;
    gbc_scontriniPanel.weighty = 1.0;
    gbc_scontriniPanel.insets = new Insets(0, 0, 5, 0);
    gbc_scontriniPanel.fill = GridBagConstraints.BOTH;
    gbc_scontriniPanel.gridx = 0;
    gbc_scontriniPanel.gridy = 1;
    add(scontriniPanel, gbc_scontriniPanel);
    GridBagLayout gbl_scontriniPanel = new GridBagLayout();
    scontriniPanel.setLayout(gbl_scontriniPanel);
    
    scontriniTable = new JTable();
    GridBagConstraints gbc_scontriniTable = new GridBagConstraints();
    gbc_scontriniTable.anchor = GridBagConstraints.NORTHWEST;
    gbc_scontriniTable.gridx = 1;
    gbc_scontriniTable.gridy = 0;
    scontriniPanel.add(scontriniTable, gbc_scontriniTable);
    
    JPanel acquistiPanel = new JPanel();
    acquistiPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Acquisti", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_acquistiPanel = new GridBagConstraints();
    gbc_acquistiPanel.weightx = 1.0;
    gbc_acquistiPanel.weighty = 1.0;
    gbc_acquistiPanel.fill = GridBagConstraints.BOTH;
    gbc_acquistiPanel.gridx = 0;
    gbc_acquistiPanel.gridy = 2;
    add(acquistiPanel, gbc_acquistiPanel);
    
    acquistiTable = new JTable();
    acquistiPanel.add(acquistiTable);

  }
}
