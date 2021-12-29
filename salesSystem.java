/** 
 * @ (#) Roelofs005pa3.java
 * @author Logan Roelofs
 * @version 1.00 11/26/2021
 *  PROGRAM PURPOSE: Creatre a program that accepts 
 * a customer's orders for hammocks. The customer
 * gets a discount based on the order total (size).
 * A sales receipt will be generated.
 */
/**
 * The purpose of the main plan:Customer will be asked 
 * for a hammock choice based on small, medium or large; 
 * the quantity or hammock size; and, the color. 
 * An itemTotal is calculated for a selection, followed 
 * by a subtotal. Based on the subtotal a discount is derived, 
 * then the subtotal is re-calculated with the discount. 
 * The sales tax is based on the discounted subtotal.A total 
 * for the sale is finalized and the sales receipt is printed.
 */


//importing librays
import java.util.Scanner;
import java.util.Calendar;
import java.util.ArrayList; //https://www.w3schools.com/java/java_arraylist.asp
import java.io.File; //https://www.w3schools.com/java/java_files.asp
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;

// begin public class salesSystem
public class salesSystem {

    // defined var For PA3
    static Hammock hammockSales[];
    static Scanner input = new Scanner(System.in);
    static Calendar dateTime = Calendar.getInstance();
    static ArrayList < String > hammockReport = new ArrayList < String > ();
    static String fileName;
    static char cont;
    static double subtotal;
    static double discount;

    //define method salesSystem
    public salesSystem() {

    }

    //define method start
    public static void start() throws IOException {

        //define method
        processSales();
        cont = 'y';

        if (cont == 'y') {
            writeHammockRecords();
            checkInputFile();
        }
    }

    //define method processSales
    public static void processSales() {

        //define var for processSales
        double itemTotal = 0;
        double discSubtotal;
        double tax;
        double total;
        String salesReceipt = "";

        //define method
        System.out.println("How many separate hammock sales will be made?");
        //take user input and set input equal to itemTotal catch to make sure it is a number
        //the second timke that this catch statment is ran the program will not set the value of item total correctly
        try {
            itemTotal = input.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid integer! Re-enter the number of separate hammock sales to be made: ");
            input.nextLine();
            processSales();
        }
        //validate that the user input is greater than 0
        if (itemTotal <= 0) {
            System.out.println("Invalid integer! Re-enter the number of separate hammock sales to be made: ");
            input.nextLine();
            processSales();
        }
        //create an array of the name hammockSales of the size of the itemTotal
        hammockSales = new Hammock[(int) itemTotal];
        //Use a for loop to create a Hammock at each array location. 
        for (int i = 0; i < hammockSales.length; i++) {
            hammockSales[i] = new Hammock();
            hammockSales[i].setHammockChoice("1");
            hammockSales[i].setColorChoice();
            hammockSales[i].setHammockDescPrice();
            hammockSales[i].setQuantity();
            hammockSales[i].setHammockColor();
        }
        //print out valuse of the array
        /**for (int i = 0; i < hammockSales.length; i++){
            System.out.println("Hammock getHammockChoice" + (i+1) + ": " + hammockSales[i].getHammockChoice());
            System.out.println("Hammock getColorChoice" + (i+1) + ": " + hammockSales[i].getColorChoice());
            System.out.println("Hammock getHammockDesc" + (i+1) + ": " + hammockSales[i].getHammockDesc());
            System.out.println("Hammock getHammockColor " + (i+1) + ": " + hammockSales[i].getHammockColor());
            System.out.println("Hammock getPrice " + (i+1) + ": " + hammockSales[i].getPrice());
            System.out.println("Hammock getQuantity " + (i+1) + ": " + hammockSales[i].getQuantity());
        } */

        //Call calcItemTotal() for the hammock at the index and assign the return value to itemTotal.
        for (int i = 0; i < hammockSales.length; i++) {
            itemTotal = hammockSales[i].calcItemTotal();
            subtotal = subtotal + itemTotal;
            salesReceipt = String.format("%n%-24s %c %-13s %5s %,7d %4s %s%,14.2f",
                hammockSales[i].getHammockDesc(), '-', hammockSales[i].getHammockColor(), " ",
                hammockSales[i].getQuantity(), " ", "$",
                itemTotal);
            //add salesReceipt to the hammockReport array
            hammockReport.add(salesReceipt);
        }

        // print out hammockReport array to the console
        /**for (int i = 0; i < hammockReport.size(); i++){
            System.out.println(hammockReport.get(i));
        }*/
        // call method determineDiscount()
        determineDiscount();
        //Accumulate the subtotal with the discount into discSubtotal.
        discSubtotal = subtotal - discount;
        //calculate the sales tax
        tax = discSubtotal * 0.0825;
        //calculate the total
        total = discSubtotal + tax;
        //print sales receipt
        System.out.printf("%n%nSALES RECEIPT" +
            "%n%nLAZY HAZY DAYS, INC." +
            "%nHuebner Oaks Mall" +
            "%nSan Antonio, TX" +
            "%n%nDate: %tD" +
            "%nTime: %tr%n", dateTime, dateTime);
        //Use an enhanced for loop to print each item in the hammockReport array.
        for (String s: hammockReport) {
            System.out.println(s);
        }
        //Print the subtotal, discount, tax and total lines.
        System.out.printf("%s" +
            "%n%n%52s %-6s $%,14.2f" +
            "%n%52s %-7s %,14.2f" +
            "%n%52s %-7s %,14.2f" +
            "%n%n%52s %-6s $%,14.2f",
            salesReceipt, "SUBTOTAL:", " ",
            subtotal, "DISCOUNT:", " ",
            discount, "TAX @ 8.250%:",
            " ", tax, "TOTAL:", " ", total);


        // end method
    }

    //define method determineDiscount
    public static void determineDiscount() {

        if (subtotal >= 100000) {
            discount = subtotal * 0.035;
        } else {
            if (subtotal >= 50000) {
                discount = subtotal * 0.03;
            } else {
                if (subtotal >= 10000) {
                    discount = subtotal * 0.025;
                } else {
                    if (subtotal >= 5000) {
                        discount = subtotal * 0.02;
                    } else {
                        discount = subtotal * 0;
                    }
                }
            }
        }
    }

    //define method writeHammockRecords
    public static void writeHammockRecords() throws FileNotFoundException {

        //define var for processSales
        String record;
        Scanner keyboard = new Scanner(System.in);
        //define method
        System.out.print(" \n Enter the file name for the hammock sales records (WARNING: This will erase a pre-existing file!): ");
        record = keyboard.nextLine();


        PrintWriter outputFile = new PrintWriter(record);
        //print to outputFile the hammockReport array
        for (int i = 0; i < hammockReport.size(); i++) {
            outputFile.println(hammockReport.get(i));
        }
        outputFile.close();

        //Print the message: Data written to the Xxxxxxxxxxx file where the x’s is the name of the file.
        System.out.println("Data written to the " + record + " file.");
    }

    //define method checkInputFile
    public static void checkInputFile() throws FileNotFoundException {
        //define var for processSales
        Scanner keyboard = new Scanner(System.in);
        System.out.print("\n Enter the name for the hammock records file: ");
        String filename = keyboard.nextLine();
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        while (inputFile.hasNext()) {
            // Read the next name.
            String friendName = inputFile.nextLine();

            // Display the last name read.
            System.out.println(friendName);
        }
        inputFile.close();


    }
    // end public class salesSystem
}


/**
 *How many separate hammock sales will be made?
0
Invalid integer! Re-enter the number of separate hammock sales to be made: 
How many separate hammock sales will be made?
4

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.  

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00   
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00   
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00   

Enter your choice:  d 

Re-enter a valid integer or floating-point value:  1

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  2

Enter the quantity:  3

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  4

You entered an invalid hammock choice!  Try again!

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  5

You entered an invalid hammock choice!  Try again!

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  2

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  1

Enter the quantity:  3

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  4

You entered an invalid hammock choice!  Try again!

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  2

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  1

Enter the quantity:  3

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  3

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  1

Enter the quantity:  f 

Re-enter a valid integer or floating-point value:  3


SALES RECEIPT

LAZY HAZY DAYS, INC.
Huebner Oaks Mall
San Antonio, TX

Date: 11/28/21
Time: 08:25:38 PM

Small - 48 in. x 11 ft.  - Emerald Green             3      $        300.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Deluxe - 60 in. x 13 ft. - Crimson Red               3      $        525.00

Deluxe - 60 in. x 13 ft. - Crimson Red               3      $        525.00

                                           SUBTOTAL:        $      1,665.00
                                           DISCOUNT:                   0.00
                                       TAX @ 8.250%:                 137.36

                                              TOTAL:        $      1,802.36

SALES RECEIPT

LAZY HAZY DAYS, INC.
Huebner Oaks Mall
San Antonio, TX

Date: 11/28/21
Time: 08:25:38 PM

Small - 48 in. x 11 ft.  - Emerald Green             3      $        300.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Deluxe - 60 in. x 13 ft. - Crimson Red               3      $        525.00


                                           SUBTOTAL:        $      1,665.00
                                           DISCOUNT:                   0.00
                                       TAX @ 8.250%:                 137.36

                                              TOTAL:        $      1,802.36
 Enter the file name for the hammock sales records (WARNING: This will erase a pre-existing file!): 1
Data written to the 1 file.

 Enter the name for the hammock records file: 1

Small - 48 in. x 11 ft.  - Emerald Green             3      $        300.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Large - 55 in. x 13 ft.  - Crimson Red               3      $        420.00

Deluxe - 60 in. x 13 ft. - Crimson Red               3      $        525.00
 */






/**
  How many separate hammock sales will be made?
2

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  3

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  2

Enter the quantity:  1000

LAZY HAZY DAYS, INC. - Hammock 1

Our beautiful cotton hammocks sport a traditional look and are very comfortable.

1.  Small - 48 in. x 11 ft. - Good for 1 person                         $100.00
2.  Large - 55 in. x 13 ft. - Good for 2 people                         $140.00
3.  Deluxe - 60 in. x 13 ft. - Good for 2 or more people                $175.00

Enter your choice:  2

1.  Crimson Red
2.  Emerald Green
3.  Indigo Blue
4.  Natural
5.  Purple Haze

Enter your choice of colors:  1

Enter the quantity:  153


SALES RECEIPT

LAZY HAZY DAYS, INC.
Huebner Oaks Mall
San Antonio, TX

Date: 11/28/21
Time: 08:29:26 PM

Deluxe - 60 in. x 13 ft. - Emerald Green         1,000      $    175,000.00

Large - 55 in. x 13 ft.  - Crimson Red             153      $     21,420.00

Large - 55 in. x 13 ft.  - Crimson Red             153      $     21,420.00

                                           SUBTOTAL:        $    196,420.00
                                           DISCOUNT:               6,874.70
                                       TAX @ 8.250%:              15,637.49

                                              TOTAL:        $    205,182.79
 Enter the file name for the hammock sales records (WARNING: This will erase a pre-existing file!): 1
Data written to the 1 file.

 Enter the name for the hammock records file: 1

Deluxe - 60 in. x 13 ft. - Emerald Green         1,000      $    175,000.00

Large - 55 in. x 13 ft.  - Crimson Red             153      $     21,420.00
*/