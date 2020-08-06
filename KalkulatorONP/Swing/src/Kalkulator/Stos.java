package Kalkulator;
import java.util.LinkedList;

public class Stos<T> {
	
   private LinkedList<T> lista = new LinkedList<>();
   
   public void push(T liczba) {
     lista.addFirst(liczba);
   }
   public T top() {
      return lista.getFirst();
   }
   public T pop() {
      return  lista.removeFirst();
   }
   public int getSize() {
	   return lista.size();
   }
   public T get(int i) {
	   return lista.get(i);
   }
   public String toString() {
	   String tmp = "";
		   for(int i = 0;i<lista.size();i++)
			   tmp += lista.get(i) + " ";
		   return tmp;
   }
}