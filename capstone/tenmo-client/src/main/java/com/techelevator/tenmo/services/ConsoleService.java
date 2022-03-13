package com.techelevator.tenmo.services;

import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import java.math.BigDecimal;
import java.util.Scanner;


public class ConsoleService {

    AuthenticatedUser currentUser = new AuthenticatedUser();
    User user = new User();
    RestAccountSvcs account = new RestAccountSvcs();

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }


    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }


    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();


    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }


    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void displaySelecTransRecip(){
        String selectedId = scanner.nextLine();

        System.out.println();
        System.out.println("You have selected username:" + selectedId + " as the recipient for this transaction.");
        System.out.println();
    }
    public void promptForSendBucks() {
        String transferSelection;
        String userInputIdSelection;
        BigDecimal userInputTransferAmount;
        userInputIdSelection = promptForString("Please enter the userId of who you would like to send the TE Bucks: ");
        System.out.println();
        // if(userInputIdSelection.equals(currentUser.getUser().getId())){
        //    System.out.println("You cannot send TE Bucks to yourself.");
        // } else if (userInputIdSelection.equals(user.getId())) {
        System.out.println("You have selected username:" + userInputIdSelection + " as the recipient for this transaction.");
        System.out.println();
        userInputTransferAmount = promptForBigDecimal("Please enter the amount of TE Bucks you would like to send: ");
//        if(account.getBalance(currentUser.getUser().getId()) < userInputTransferAmount){
//            System.out.println("Insufficient funds. Please try a smaller amount.");
//        }else{ System.out.println();
            System.out.println("You would like to send " + userInputTransferAmount + " to the user with ID = " + userInputIdSelection + ".");
            System.out.println("If this is correct enter 'yes' to cancel this action enter 'cancel': ");
            System.out.println();
            transferSelection = scanner.nextLine();
            if (transferSelection.equalsIgnoreCase("yes")) {
            //code for doing a transfer
            } else if (transferSelection.equalsIgnoreCase("cancel")) {
                System.out.println("Your send Bucks request has been canceled.");
            } else {
                System.out.println("Sorry that prompt is not recognized. Please try again."); //TODO take user back to "If this is correct enter 'yes...
        }
    }
//
//        try {
//            transferSelection = Integer.parseInt(scanner.nextLine());
//            if (transferSelection ==  userid && transferSelection != currentUser.getId){
//
//            }
//        } catch (NumberFormatException e) {
//            transferSelection = -1;
//        }
//        return transferSelection;
//    }




    }



