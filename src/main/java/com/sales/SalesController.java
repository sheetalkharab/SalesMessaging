package com.sales;

import com.sales.service.SalesService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class SalesController {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("1   salesMessagesFromStore1.txt");
            System.out.println("2   salesMessagesFromStore2.txt");
            System.out.println("3   salesMessagesFromStore3.txt");
            System.out.println(" choose options from above. for eg: 1 \n");

            int number;
            do {
                System.out.println("Please enter a valid option!");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number!");
                    sc.next(); // ask for correct input
                }
                number = sc.nextInt();
            } while (!(0 < number && number <= 3));

            String fileName = "salesMessagesFromStore" + number + ".txt";
            System.out.println("file chosen " + fileName);

            SalesService salesService = new SalesService();
            salesService.processSalesMessages(fileName);

            System.out.println("");
            System.out.println(" :-) Sales messages processed successfully :-) ");
        } catch (URISyntaxException e) {
            System.out.println(" Error for file selected:" + e.getMessage());
        } catch (IOException e) {
            System.out.println(" Error while process data from file: " + e.getMessage());
        }
    }
}
