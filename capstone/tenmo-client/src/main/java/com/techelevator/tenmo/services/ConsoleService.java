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
    RestUserSvcs restUserSvcs;

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
    public void menuForSendBucks() {
        String verifySelection;
        int selectAccountTo = -1;
        int selectAccountFrom;
        BigDecimal userInputTransferAmount;

    while(selectAccountTo != 0){
        selectAccountTo = promptForInt("Please enter the userId of who you would like to send the TE Bucks: ");
        selectAccountFrom = currentUser.getUser().getId();

        System.out.println();
        if(sbSelectPoint1(selectAccountTo, selectAccountFrom)){

            userInputTransferAmount = promptForBigDecimal("Please enter the amount of TE Bucks you would like to send: ");

            if(sbSelectPoint2(userInputTransferAmount, selectAccountTo)){

            verifySelection = promptForString("If this is correct enter 'yes' to cancel this action enter 'no': ");
            System.out.println();
                if(sbSelectPoint3(verifySelection)){

                    //transferMethod();
                }
            }
        }
    }
    }

    public boolean checkAccountToIsInList(int selectAccountTo) {
        boolean check = false;

        for (User user : restUserSvcs.listUsernameAndId(currentUser)){
            if(user.getId() == selectAccountTo) {
                check = true;
            }
        } return check;
    }

    public boolean sbSelectPoint1(int selectAccountTo, int selectAccountFrom){
        boolean result = false;
        if(selectAccountTo == selectAccountFrom) {
            System.out.println("You cannot send TE Bucks to yourself.");
        } else if (!checkAccountToIsInList(selectAccountTo)) {
            System.out.println("Your selection is not a valid user");
        }    else {
            System.out.println("You have selected username: " + ", id: " + selectAccountTo + " as the recipient for this transaction."); //TODO add username
            System.out.println();
            result = true;
        }   return result;
    }

    public boolean sbSelectPoint2(BigDecimal userInputTransferAmount, int selectAccountTo){
        boolean result = false;
        if (account.getBalance(currentUser).compareTo(userInputTransferAmount) < 0) {
            System.out.println("Insufficient funds. Please try a smaller amount.");
        } else if (account.getBalance(currentUser).compareTo(userInputTransferAmount) > 0) {
                    result = true;
                    System.out.println();
                    System.out.println("You would like to send " + userInputTransferAmount + " to the user with ID = " + selectAccountTo + ".");
                    System.out.println();
            } return result;
    }


    public boolean sbSelectPoint3(String verifySelection){              //TODO add catch System.out.println("Sorry that prompt is not recognized. Please try again.");
        boolean result = false;

        if (verifySelection.equalsIgnoreCase("no")) {
            System.out.println("Your send Bucks request has been canceled.");
        } else if (verifySelection.equalsIgnoreCase("yes")) {
            System.out.println("Your send Bucks request has been accepted.");
            result = true;
        } return result;
    }



    }



