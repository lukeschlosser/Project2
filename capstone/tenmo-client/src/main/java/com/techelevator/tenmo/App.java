package com.techelevator.tenmo;

import com.techelevator.tenmo.model.AuthenticatedUser;
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

	private void viewCurrentBalance() {
        //takes the current user and uses their userId to get a current balance
        Long user = currentUser.getUser().getId();
        int i = user.intValue();
        System.out.println("Your current account balance is: " + restAccSvcs.getBalance(i)); //current balance method still shows as null


	}

	private void viewTransferHistory() {
        //takes the current user and uses their userId to get a transfer history
        Long user = currentUser.getUser().getId();
        int i = user.intValue();
        System.out.println("Your Transfer History: " + restTransferSvcs.getTransferHistory(i));
    }

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
        System.out.println("Your pending requests: "); //TODO need to implement a getTransferStatusByUserId I think?
	}

	private void sendBucks() {

        //list of users and their associated userId
        System.out.println(restUserSvcs.listUsernameAndId());    //TODO
                                                                 // this method isn't connected to server yet and there might be
                                                                 // a better way to list them this is just a start because we need to
                                                                 // show a list of the users and their id's so the person knows what to
                                                                 // type for the next prompts below

        Long user = currentUser.getUser().getId(); //the below code doesn't quite do what I want but it's a start.
        int i = user.intValue();
        System.out.println();
        String sendingBucksTo = consoleService.promptForString("Please enter the userId of who you would like to send the TE Bucks: ");
        System.out.println(sendingBucksTo);
        BigDecimal bigDecimalFromUser = consoleService.promptForBigDecimal("Please enter the amount of TE Bucks you would like to send: ");
        System.out.println(bigDecimalFromUser);

	}

// BONUS
	private void requestBucks() {

        System.out.println("This feature is not implemented yet. Please check back in the future!");
	}

}
