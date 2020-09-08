
package tipcalc;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.text.NumberFormat; //For 1 decimal place on floats
/**
 *
 * @author Jacob Hinkebein
 */
public class TipCalc {    
    //global vars
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        //local vars
        double taxRate, billAmt, tip15, tip18, tip20, taxAmt, custTip, custTipAmt;
        String choice;
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        
        System.out.println("Welcome to the Tip Calculator");
        
        do {
        taxRate = getValue("Please enter the sales tax rate (7.25% = 0.075): ");
            if (taxRate > .25) {
                System.out.println("Tax rate out of bounds: 0 to .25 (25%) only.");
            }  
        } while (taxRate > .25);
        
        do {
            custTip = getValue("Enter a custom tip if you'd like or zero (25% = .25): ");
            if (custTip > 1) {
                System.out.println("Limit on custom tip is 100% (=1.0)");
            }
        } while (custTip > 1);
        
        sc.nextLine();
        
        System.out.print("Do you have a bill amount? (Y/N): ");
        choice = sc.nextLine();
        //System.out.println("Your answer was: "+ choice);
        while (choice.toUpperCase().startsWith("Y")) {
            billAmt = getValue("Please enter the food and beverage total: ");
            
            taxAmt = billAmt * taxRate;
            tip15 = billAmt * .15;
            tip18 = billAmt * .18;
            tip20 = billAmt * .20;
            custTipAmt = billAmt * custTip;
            
            System.out.println("Tipping 15% on a bill of " + 
                    curr.format(billAmt) + " you will pay " + curr.format(billAmt + taxAmt + tip15) +
            " which includes tax of " + curr.format(taxAmt) + " and a tip of " + curr.format(tip15));
            
            System.out.println("Tipping 18% on a bill of " + 
                    curr.format(billAmt) + " you will pay " + curr.format(billAmt + taxAmt + tip18) +
            " which includes tax of " + curr.format(taxAmt) + " and a tip of " + curr.format(tip18));
            
            System.out.println("Tipping 20% on a bill of " + 
                    curr.format(billAmt) + " you will pay " + curr.format(billAmt + taxAmt + tip20) +
            " which includes tax of " + curr.format(taxAmt) + " and a tip of " + curr.format(tip20));
            
            if (custTip > 0) {
                //calculate and display result for custom tip amt
                DecimalFormat df = new DecimalFormat("#.#"); //Create DecimalFormat Object. Constructor uses #.# for 1 decimal point
                //could also do df.setMaximumFractionDigits(1) but that's a lot of extra typing
                System.out.println("Tipping " + df.format(custTip*100) + "% on a bill of " +
                        curr.format(billAmt) + " you will pay " + curr.format(billAmt + taxAmt + custTip) + 
                        " which includes a tax of " + curr.format(taxAmt) + " and a tip of " +
                        curr.format(custTipAmt));
                
            }
            
            sc.nextLine();
            System.out.println("Do you have another bill amount? (Y/N): ");
            choice = sc.nextLine();
        }
        
        System.out.println("Thanks for using the tip calculator");
    }
    
    public static double getValue(String prompt){
        double v = -1;
        do{
            try {
            System.out.print(prompt);
            v = sc.nextDouble();
            if (v < 0) {
                System.out.println("Please enter a non-negative value.");
            }
            } catch (Exception e){
                System.out.println("Illegal input: non-numeric.");
                v = -1;
                sc.nextLine();
            }
            
        } while (v < 0);
        return v;
    }
    
}
