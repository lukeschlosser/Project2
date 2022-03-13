package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final RestAccountSvcs restAccSvcs = new RestAccountSvcs();
    private final RestTransferSvcs restTransferSvcs = new RestTransferSvcs();
    private final RestUserSvcs restUserSvcs = new RestUserSvcs();

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }


    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }


    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }


    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }


    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            printMMAddOn();
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }


	private void viewCurrentBalance() {                                      //current balance method still shows as null
        displayAccountMenu();
        System.out.println("Welcome to Your Tenmo Account " + displayUser() + ", id:" + userAsIntFromLong());
        System.out.println();
        System.out.println("Your current account balance is: $" + restAccSvcs.getBalance(currentUser));

	}


	private void viewTransferHistory() {                             //TODO given userId return list of transfer history
        displayTransferMenu();
        System.out.println("Your Transfer History: " + restTransferSvcs.getTransferHistory(userAsIntFromLong()));
    }


	private void viewPendingRequests() {                        // TODO Auto-generated method stub getTransferStatusByUserId
        displayTransferMenu();
        System.out.println("Your pending requests: ");
	}


	private void sendBucks() {
        displayTransferMenu();
        System.out.println("Who would you like to send TE Bucks " + displayUser() + "?");
        System.out.println("Here are the available users: ");
        System.out.println();
        displayListUsernameAndId();
        System.out.println();
        consoleService.menuForSendBucks();
	}


// BONUS
	private void requestBucks() {
        displayTransferMenu();
        System.out.println("Who would you like to request TE Bucks from " + displayUser() + "?");
        System.out.println("Here are the available users?");
        System.out.println();
        displayListUsernameAndId();
        System.out.println();
        System.out.println();
        System.out.println("************ಠ╭╮ಠ*************");
        System.out.println("This feature is not implemented yet.");
        System.out.println("*Please check back in the future!*");
	}

    private int userAsIntFromLong(){
        int user = currentUser.getUser().getId();
        int i = user;
        return i;
    }

    private void spacer(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void printMMAddOn() {
        spacer();
        System.out.println("You are logged in as Tenmo User");
        System.out.println("********" + displayUser() + " id: " + currentUser.getUser().getId() + "********");
        System.out.println("*********welcome*back*********");
        System.out.println();
    }

    private String displayUser() {
    return currentUser.getUser().getUsername();
    }

    private void displayAccountMenu(){
        spacer();
        System.out.println("******ACCOUNT*MENU******");
        System.out.println();
    }

    private void displayTransferMenu(){
        spacer();
        System.out.println("******TRANSFER*MENU******");
        System.out.println();
    }

    public void displayListUsernameAndId() {

        for (User user : restUserSvcs.listUsernameAndId(currentUser)) {
            System.out.println("username: " + user.getUsername() + ", account id:" + user.getId());
        }
    }



}
