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
import java.util.Scanner;

public class Hammock {
    int hammock = 0;
    private int quantity = 0;
    private int color = 0;
    private String hammockDesc = "";
    private String colorSelected = "";
    private double price = 0.0;
    private boolean repeat = false;
    private Scanner input = new Scanner(System.in);

    public Hammock() {}

    public Hammock(int hammock, int color, int quantity) {
        setHammockChoice(hammock);
        setColorChoice(color);
        setQuantity(quantity);
    }

    public final void setHammockChoice(int hammock) {
        this.hammock = hammock;
    }

    public final void setColorChoice(int color) {
        this.color = color;
    }

    public final void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setHammockChoice(String hammockNo) {
        do {
            do {
                System.out.printf("%nLAZY HAZY DAYS, INC. - Hammock %s" +
                    "%n%nOur beautiful cotton hammocks sport a " +
                    "traditional look and are very comfortable.  " +
                    "%n%n1.  Small - 48 in. x 11 ft. - Good for 1 " +
                    "person\t\t\t\t$100.00" +
                    "%n2.  Large - 55 in. x 13 ft. - Good for 2 " +
                    "people\t\t\t\t$140.00" +
                    "%n3.  Deluxe - 60 in. x 13 ft. - Good for 2 or " +
                    "more people\t\t$175.00" +
                    "%n%nEnter your choice:  ", hammockNo);

                validateNumber(!input.hasNextInt());
            } while (repeat);

            hammock = input.nextInt();
            input.nextLine();

            if (hammock < 1 || hammock > 3) {
                System.out.printf("%nYou entered an invalid hammock choice!  " +
                    "Try again!%n");
            }

        } while (hammock < 1 || hammock > 3);

    }

    public void setHammockDescPrice() {
        switch (hammock) {
            case 1:
                hammockDesc = "Small - 48 in. x 11 ft.";
                price = 100.00;
                break;
            case 2:
                hammockDesc = "Large - 55 in. x 13 ft.";
                price = 140.00;
                break;
            case 3:
                hammockDesc = "Deluxe - 60 in. x 13 ft.";
                price = 175.00;
        }
    }

    public void setColorChoice() {
        do {
            do {
                System.out.printf("%n1.  Crimson Red" +
                    "%n2.  Emerald Green" +
                    "%n3.  Indigo Blue" +
                    "%n4.  Natural" +
                    "%n5.  Purple Haze" +
                    "%n%nEnter your choice of colors:  ");

                validateNumber(!input.hasNextInt());

            } while (repeat);

            color = input.nextInt();
            input.nextLine();

            if (color < 1 || color > 5) {
                System.out.printf("%nYou entered an invalid color choice.  " +
                    "Try again!%n");

            }
        } while (color < 1 || color > 5);
    }

    public void setHammockColor() {
        switch (color) {
            case 1:
                colorSelected = "Crimson Red";
                break;
            case 2:
                colorSelected = "Emerald Green";
                break;
            case 3:
                colorSelected = "Indigo Blue";
                break;
            case 4:
                colorSelected = "Natural";
                break;
            case 5:
                colorSelected = "Purple Haze";

        }

    }


    public void setQuantity() {
        do {
            System.out.printf("%nEnter the quantity:  ");

            validateNumber(!input.hasNextInt());
        } while (repeat);

        quantity = input.nextInt();
    }

    public double calcItemTotal() {
        return quantity * price;
    }


    public int getHammockChoice() {
        return hammock;
    }

    public int getColorChoice() {
        return color;
    }

    public String getHammockDesc() {
        return hammockDesc;
    }

    public String getHammockColor() {
        return colorSelected;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void validateNumber(boolean repeat) {
        if (repeat) {
            input.next();
            System.out.printf("%nRe-enter a valid integer or " +
                "floating-point value:  ");
        }

    }

}