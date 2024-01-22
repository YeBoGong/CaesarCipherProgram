/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecaesarcipher;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author harry
 */
public class SimpleCaesarCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Caesar Cipher project");
        System.out.println("Created by Ye Bo Gong");

        boolean exit = false;

        while (!exit) {
            Menu();
            String userOption = sc.nextLine();
            if (userOption.length() == 1) {
                if (userOption.equals("e")) {
                    //ask user for the phrase to encode 
                    System.out.print("Phrase to encode: ");
                    String message = sc.nextLine();
                    //checking message isn't empty 
                    if (notEmpty(message)) {
                        System.out.print("How much would you like to shift right by?: ");
                        String shift = sc.nextLine();
                        //if result is an int between 0 and 25
                        if (validInt(shift)) {
                            System.out.println(crpyt(message, Integer.parseInt(shift)));

                        } else {
                            //prompt user
                            System.out.println("Please enter an int between 0 and 25");
                        }
                    } else {
                        //prompt user
                        System.out.println("Please enter a valid message");
                    }

                } else if (userOption.equals("d")) {
                    //ask user for the phrase to encode 
                    System.out.print("Phrase to decode: ");
                    String message = sc.nextLine();
                    //checking message isn't empty 
                    if (notEmpty(message)) {
                        System.out.print("What was the message shift by?: ");
                        String shift = sc.nextLine();
                        //if result is an int between 0 and 25
                        if (validInt(shift)) {
                            System.out.println(crpyt(message, Integer.parseInt(shift) * -1));

                        } else {
                            //prompt user
                            System.out.println("Please enter an int between 0 and 25");
                        }
                    } else {
                        //prompt user
                        System.out.println("Please enter a valid message");
                    }

                } else if (userOption.equals("q")) {
                       System.out.println("Exitting the Caesar Cipher program");
                       exit = true;
                    }
            }
        }
    }

    public static void Menu() {

        System.out.print("Encode (e) Decode (d) Quit (q): ");
    }

    public static boolean validInt(String shiftInt) {
        try {
            int dataInt = Integer.parseInt(shiftInt);
            if (dataInt < 0 || dataInt > 26) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean notEmpty(String enteredString) {
        String trimmedString = enteredString.trim();
        if (trimmedString.equals("")) {
            return false;
        }
        return true;
    }

    public static String crpyt(String message, int shift) {
        String encMessage = "";
        for (int i = 0; i < message.length(); i++) {
            //original char 
            int originalChar = message.charAt(i);
            //encoded character
            char encodedChar = (char) originalChar;
            //the original char is a letter
            if ((originalChar >= 65 && originalChar <= 90) || (originalChar >= 97 && originalChar <= 122)) {
                //shift the encoded character by the number entered
                encodedChar = (char) (originalChar + shift);
                //this is a long comparison list, so I will go through it one by one
                //if the encoded character ends up not being a letter
                //or the encoded character ends up being lowercase when the original character was uppercase
                //or vice versa
                if ((encodedChar >= 91 && encodedChar <= 96) || (encodedChar > 122) || (encodedChar < 65) || (originalChar <= 90 && encodedChar > 96) || (originalChar >= 97 && encodedChar < 91)) {
                    //if the message is being encoded, loop around back by subtracting
                    if (shift >= 0) {
                        encodedChar -= 26;
                        //if the message is decoded, loop around back by adding
                    } else {
                        encodedChar += 26;
                    }
                }
            }
            encMessage = encMessage + encodedChar;

        }
        return encMessage;
    }

}
