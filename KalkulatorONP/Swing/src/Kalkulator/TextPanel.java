package Kalkulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{


	static private JTextArea text;
	public TextPanel() {
		text = new JTextArea(6,29);
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		Font f = text.getFont();
		Font font = new Font(f.getFontName(),f.getStyle(),f.getSize()+4);
		text.setFont(font);
		add(text);
		
	}

	static public void setString(String tekst) {
		text.append(tekst);
	}
	static public void clear() {
		text.replaceRange(null,text.getText().length()-1,text.getText().length());
		//text.setText(null);
	}
	static public void clearAll() {
		text.replaceRange(null,0,text.getText().length());
	}
	static public String getTekst() {
		return text.getText();
	}
}