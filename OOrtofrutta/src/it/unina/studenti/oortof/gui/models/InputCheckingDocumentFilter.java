package it.unina.studenti.oortof.gui.models;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import it.unina.studenti.oortof.models.application.ApplicationInfo;

import java.util.regex.Matcher;

public class InputCheckingDocumentFilter extends DocumentFilter {
  private JTextField textField;
  private InputCheckRule checkRule;

  public InputCheckingDocumentFilter(JTextField textField, InputCheckRule checkRule) {
    this.textField = textField;
    this.checkRule = checkRule;
  }

  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
    super.remove(fb, offset, length);
  }

  public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {

    if (str == null) {
      super.insertString(fb, offs, str, a);
      return;
    }

    if (str.length() == 0) {
      return;
    }

    Matcher matcher = checkRule.pattern.matcher(str);
    if (matcher.matches()) {
      ApplicationInfo.getInstance().setMessage(checkRule.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
    }
    else {
      super.insertString(fb, offs, str, a);
    }
  }

  public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
    if (str == null) {
      super.replace(fb, offs, length, null, a);
      return;
    }

    if (str.length() == 0) {
      return;
    }

    Matcher matcher = checkRule.pattern.matcher(str);
    if (matcher.matches()) {
      ApplicationInfo.getInstance().setMessage(checkRule.getErrorMessage(), ApplicationInfo.LEVEL_ERROR);
    }
    else {
      super.replace(fb, offs, length, str, a);
    }

  }
}
