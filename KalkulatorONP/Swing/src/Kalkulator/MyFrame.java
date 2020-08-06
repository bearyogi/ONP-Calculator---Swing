package Kalkulator;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.TextField;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JDialog;
import Kalkulator.ButtonPanel;
import javax.swing.JButton;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Font;  
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import Kalkulator.ButtonPanel;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.*;

public  class MyFrame extends JFrame {
	static TextPanel textPanel = new TextPanel();
	 ButtonPanel buttonPanel = new ButtonPanel();
	 static ONP calc = new ONP();
	static boolean czyWynik = false;
	public MyFrame() {
		super("Kalkulator ONP");
		
		BorderLayout border = new BorderLayout();
		setLayout(border);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400, 400);
		
		setLocation(500, 200);
		
		 textPanel.setPreferredSize(new Dimension(40000,100));
		 add(textPanel);
		 add(buttonPanel);
		 border.addLayoutComponent(textPanel, border.NORTH);
		 border.addLayoutComponent(buttonPanel, border.CENTER);
		 
	}
	
	static public void addToText(String text) {
		textPanel.setString(text);
	}
	static public void clear() {
		textPanel.clear();
        textPanel.updateUI();
	}
	static public void clearAll() {
		textPanel.clearAll();
        textPanel.updateUI();
	}
	static public String getText() {
		return textPanel.getTekst();
	}
	static public int check(String equation) {
		return calc.isCorrect(equation);
	}
	static public double calculate(String equation) {
		return calc.calculate(textPanel,equation);
	}
	static public String toONP(String equation) {
		return calc.toONP(equation);
	}
	static public void popUp(String text) {
		JOptionPane.showMessageDialog(textPanel,text);
	}
	static public void change() {
		if(czyWynik == true)
			czyWynik = false;
		else
			czyWynik = true;
	}
	static public boolean getBool() {
		return czyWynik;
	}
}

 class MyButton extends JButton implements ActionListener {
private String znak;
	public MyButton(String nazwa,String znak,Color kolor) {
		super(nazwa);
		this.znak = znak;
		//setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		addActionListener(this);
		setBackground(kolor);
 		setOpaque(true);
		 Font nowy = new Font(getFont().getName(),getFont().getStyle(),16);
		setFont(nowy);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ButtonPanel.hmm)
		{
			MyFrame.clear();
		}
		else if(e.getSource() == ButtonPanel.clearall)
		{
			MyFrame.clearAll();
		}
		else if(e.getSource() == ButtonPanel.enter)
		{
			 if(MyFrame.getBool() == true) {
				MyFrame.clearAll();
				MyFrame.change();
			}
		else if(MyFrame.check(MyFrame.getText()) != 0){
					String text = "";
					int tmp = MyFrame.check(MyFrame.getText());
					if(tmp == 1)
						text = "Wyra¿enie nie mo¿e byæ puste!";
					if(tmp == 2)
						text = "Dwa znaki dzia³añ! (np. //, **)";
					if(tmp == 3)
						text = "Dwa znaki dzia³añ! (np. */, %*)";
					if(tmp == 4)
						text = "z³y pierwszy znak!";
					if(tmp == 5)
						text = "Z³y ostatni znak!";
					if(tmp == 6)
						text = "Ró¿na liczba nawiasów!";
					MyFrame.popUp(text);
				}
				
				else {
					MyFrame.addToText(znak);
					String equation = MyFrame.getText(); 
					MyFrame.clearAll();
					String onpResult = MyFrame.toONP(equation);	
					double result = MyFrame.calculate(onpResult);
					MyFrame.addToText("Wyra¿enie zamienione na ONP: " + onpResult + "\n");
					MyFrame.addToText("Wynik : " + String.valueOf(result));
					MyFrame.change();
				}
				
				
				
		
			
		}
		else
		{
			if(MyFrame.getBool() == true) {
				MyFrame.clearAll();
				MyFrame.change();
			}
			MyFrame.addToText(znak);
		}
	
	}
}

