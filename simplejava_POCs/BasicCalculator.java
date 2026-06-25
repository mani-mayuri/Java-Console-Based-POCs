package simplejava_POCs;
//WAP to perform arithmetic operations based on user input..using do while and switch case
import java.util.Scanner;

public class BasicCalculator {

	public static void main(String[] args) {
		Scanner scr=new Scanner(System.in);
		String yrn;
		do {
			System.out.println("Enter a number: ");
			int a=scr.nextInt();
			System.out.println("Enter another number: ");
			int b=scr.nextInt();
			System.out.println("Choose Operation: ");
			String symb=scr.next();
			double result=0;
			switch(symb) {
			case "+" -> result=a+b;
			case "-" -> result=a-b;
			case "*" -> result=a*b;
			case "/" ->{
			    if(b!=0)
			        result=(double)a/b;
			    else
			        System.out.println("Cannot divide by zero.");
			}
			case "%" -> {
			    if(b!=0)
			        result=a%b;
			    else
			        System.out.println("Cannot perform modulus with zero.");
			}
			default -> System.out.println("Invalid Symbol");
			}
			System.out.println("The result is: "+result);
			System.out.println("Do you want to continue..?Enter(Yes/No)");
			yrn=scr.next();
		}while (yrn.equalsIgnoreCase("Yes"));
        System.out.println("Calculator Closed...");
        scr.close();
	}

}
