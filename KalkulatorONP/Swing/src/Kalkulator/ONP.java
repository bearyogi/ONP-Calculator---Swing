package Kalkulator;
import Operatory.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
public class ONP {
private Stos<Operators> stos;
private Stos<String> stosDouble;
String resultONP = "";
public ONP() {
	this.stos = new Stos();
	this.stosDouble = new Stos();
}

public double calculate(TextPanel textPanel,String equation) {
	double result = 0.0;
	String str = "";
	String mes = "";
	Double temp1 = 0.0;
	Double temp2 = 0.0;
	char cur = ' ';
	char next = ' ';

	for (int i = 0; i < equation.length(); i++) {
         cur = equation.charAt(i);
         if(i != equation.length() - 1) {
        	 next = equation.charAt(i+1);
         }
         else {
        	 return Double.parseDouble(stosDouble.pop());
         }
         
         
         if(charNumber(cur)) {
        	 str += cur;
        	 if (!charNumber(next) && !charDot(next)) {
                 stosDouble.push(str);
                 str = "";
             }
         }
         else if(cur == '.') {
        	 str += '.';
         }
         else if(cur == '!') {
        	 temp1 = Double.parseDouble(stosDouble.pop());
        	 if(temp1 < 0 || temp1 % 1 != 0) {
        		 mes = "Nie mo¿na uzyskaæ silni z liczby ujemnej lub u³amka!";
    			 JOptionPane.showMessageDialog(textPanel,mes);
    			 throw new IllegalArgumentException();
        	 }
        	 else {
            	 int wynik = factorial((int) temp1.doubleValue());
            	 stosDouble.push(String.valueOf(wynik));
        	 }
        	 
         }

         else if(cur != ' ') {
        	temp2 = Double.parseDouble(stosDouble.pop()) ;
        	if(stosDouble.getSize() != 0)
        	temp1 = Double.parseDouble(stosDouble.pop()) ;
         switch(cur) {
         case ('+'): {
             stosDouble.push(temp1 + temp2 + "");
             break;
         }
         case ('-'): {
        	 stosDouble.push(temp1 - temp2 + "");
             break;
         }
         case ('*'): {
        	 stosDouble.push(temp1 * temp2 + "");
             break;
         }
         case ('^'): {
        	 stosDouble.push((Math.pow(temp1, temp2)) + "");
             break;
         }
         case ('%'): {
        	 stosDouble.push(temp1 % temp2 + "");
        	 if(temp2 == 0) {
    			 mes = "Nie mo¿na uzyskaæ reszty z dzielenia przez 0!";
    			 JOptionPane.showMessageDialog(textPanel,mes);
    			 throw new IllegalArgumentException();
        	 }
             break;
         }
         case ('/'): {
        			 stosDouble.push(temp1 / temp2 + "");
        		 if(temp2 == 0) {
        			 mes = "Nie mo¿na dzieliæ przez zero!";
        			 JOptionPane.showMessageDialog(textPanel,mes);
        			 throw new IllegalArgumentException();
        			 
        		 
        	 }
        
             break;
         }
         case ('#'): {
        	if(temp1 < 0 && temp2 % 2 == 0) {
        		 mes = "Nie mo¿na uzyskaæ pierwiastka stopnia parzystego z liczby ujemnej";
    			 JOptionPane.showMessageDialog(textPanel,mes);
    			 throw new IllegalArgumentException();
        	}
        	else {
        		 stosDouble.push((Math.pow(temp1, 1 / temp2)) + "");
        	}
        	
        	 
             break;
         }
         }
	}
	}
	return result;
}


public String toONP(String equation) {
	resultONP = "";
	char cur = ' ';
	char next = ' ';
	boolean last = false;
	
	for(int i = 0;i<equation.length();i++) {
		cur = equation.charAt(i);
		
		if(i != equation.length() - 1) {
			next = equation.charAt(i+1);
		}
		else {
			last = true;
		}
		
		if(equation.charAt(i) == '(' && equation.charAt(i+1) == '-' && charNumber(equation.charAt(i+2)) )
		{
			stos.push(new LBracket());
			resultONP += '0';
			pushToStack(new Sub());
			resultONP += equation.charAt(i+2);
			i+=3;
		cur = equation.charAt(i);
		if(i != equation.length() - 1) {
			next = equation.charAt(i+1);
		}
		}
		if(charNumber(cur) || charDot(cur)) {
			resultONP += cur;
		if(last == true || !charNumber(next) && !charDot(next)) {
			resultONP += " ";
		}
		}
		else {
			switch(cur) {
			case ('('): {
               stos.push(new LBracket());
                break;
            }

            case ('+'): {
            	pushToStack(new Add());
                break;
            }

            case ('-'): {

            		pushToStack(new Sub());
            	
                break;
            }

            case (')'): {
                while (stos.getSize() > 0 && stos.top().notLBracket()) {
                	resultONP += stos.pop() + " ";
                }
                stos.pop();
                break;
            }

            case ('*'): {
            	pushToStack(new Multi());
                break;
            }

            case ('/'): {
            	pushToStack(new Div());
                break;
            }

            case ('%'): {
            	pushToStack(new Mod());
                break;
            }

            case ('#'): {
            	pushToStack(new Root());
                break;
            }

            case ('^'): {
            	pushToStack(new Pow());
                break;
            }

            case ('!'): {
            	pushToStack(new Factor());
                break;
            }
        }
			}
		}
	 while (stos.getSize() != 0) {
	        resultONP += stos.pop() + " ";
	    }
	return resultONP;
}

boolean charNumber(char c) {
	if(c >= '0' && c <= '9')
		return true;
	else
		return false;
}

boolean charDot(char c) {
	if(c == '.')
		return true;
	else
		return false;
}

void pushToStack(Operators mark) {
	while(stos.getSize() > 0 && mark.priorityCheck(stos.top())) {
		resultONP += stos.pop() + " ";
	}
	stos.push(mark);
}


int isCorrect(String equation) {
	int correct = 0;
	int nrLB = 0;
	int nrRB = 0;
	char cur = ' ';
	char next = ' ';
	if(equation.contentEquals("")) {
		return 1;
	}
	
	
	
	for(int i=0;i<equation.length() - 1;i++) {
		cur = equation.charAt(i);
		next = equation.charAt(i+1);
		
		if(charNumber(cur) == true && cur == next) {
			correct = 2;
			break;
		}
		
		if(charNumber(cur) == false && charNumber(next) == false) {
			if(next != '(') {
				if(cur != ')') {
					correct = 3;
					break;
				}
			}
		}
	}
	
	char temp = equation.charAt(0);
	if(charNumber(temp) == false) {
		if(temp == '(' || temp == '-') {
			correct = 0;
			
		}
		else
			correct = 4;
	}
	
	temp = equation.charAt(equation.length() - 1);
	if(charNumber(temp) == false) {
		if(temp == ')' || temp == '!') {
			correct = 0;
			
		}
		else
			correct = 5;
	}
	
	for (int i = 0; i < equation.length(); i++) {
        if (equation.charAt(i) == '(')
            nrLB++;
        if (equation.charAt(i) == ')')
            nrRB++;
    }

    if (nrLB != nrRB)
        correct = 6;

    return correct;

}

int factorial(int mark) {
	int result = 1;
	for(int i=1; i <= mark; i++) {
		result *= i;
	}
	return result;
}
}