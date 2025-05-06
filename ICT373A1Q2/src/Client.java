/**
 * @Title: ICT 373 A1
 * @Author: Khon Min Thite
 * @Date: 2/10/2024
 * @File: Client.java
 * @Purpose: This is the main class for the project.
 * The client program is to test the functionality of the classes and methods in the project.
 * This class is used to test the functionality of the classes and methods in the project.
 * The client program should do the following: 
 * a. constructs a magazine with an array of 3-4 supplements with made-up details built in to 
 * the program (keep provision of inputs from the user using the Java Scanner class), 
 * b. constructs an array of 5-6 different customers of various types with made-up details built in to 
 * the program (keep provision of inputs from the user using the Java Scanner class), 
 * c. prints out the text of all the emails for all customers for four weeks of magazines, 
 * d. prints out the text for the end of month emails for the paying customers, 
 * e. adds a new customer to the magazine service, 
 * f. removes an existing customer from the magazine service
 * @Assumptions: 
 * 1. When asking if a user wants to create their own test data, any answer other than ‘y’ will be assumed to 
 * request to use prebuilt test data.
 * @Limitations:
 */

import java.util.ArrayList;
import java.util.Scanner;

import magazine.*;
import customer.AssociateCustomer;
import customer.Customer;
import customer.PayingCustomer;
import helper.TestDataHelper;

public class Client {
    public static void main(String[] args) {
        System.out.println(displayStudentDetails());
        try (// initialize scanner
                Scanner scanner = new Scanner(System.in)) {
            String response;
            /**
             * a. construct a magazine with an array of 3-4 supplements with made-up details
             * built in to the program (keep provision of inputs from the user using the
             * Java Scanner class)
             * Ask user if they want to create their own test magazine or use prebuilt ones
             * Ask user if they want to create their own set of supplements or use prebuilt
             * ones
             */
            Magazine magazine;
            System.out.println("Do you want to create your own test magazine? If not, pre-built test data are available. (y/n)");
            OUTER:
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }
                // Proceed based on response
                switch (response) {
                    case "y" -> {
                        magazine = TestDataHelper.createTestMagazine(scanner);
                        break OUTER;
                    }
                    case "n" -> {
                        magazine = TestDataHelper.createPreBuiltMagazine();
                        break OUTER;
                    }
                    default -> System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            ArrayList<Supplement> supplements = new ArrayList<>();
            System.out.println("Do you want to create your own test supplements? If not, pre-built test data are available. (y/n)");
            OUTER_1:
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }
                // Proceed based on response
                switch (response) {
                    case "y" -> {
                        System.err.print("You will need to create at least 5 supplements to use pre-built customer data.\n");
                        supplements = TestDataHelper.createTestSupplements(scanner);
                        break OUTER_1;
                    }
                    case "n" -> {
                        supplements = TestDataHelper.createPreBuiltSupplements();
                        break OUTER_1;
                    }
                    default -> System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            /**
             * b. construct an array of 5-6 different customers of various types with
             * made-up details built in to the program (keep provision of inputs from the
             * user using the Java Scanner class)
             * Ask the user if they want to create their own test customers or use prebuilt
             * ones
             */
            ArrayList<Customer> customers = new ArrayList<>();
            System.out.println("Do you want to create your own test customers? If not, pre-built test data are available. (y/n)");
            OUTER_2:
            while (true) {
                response = scanner.nextLine().toLowerCase().trim();
                if (response.isEmpty()) {
                    System.out.println("Blank or empty inputs are not allowed. Please enter 'y' or 'n'.");
                    continue;
                }
                switch (response) {
                    case "y" -> {
                        while (true) {
                            customers = TestDataHelper.createTestCustomers(supplements, scanner);
                            if (customers == null) {
                                System.out.println("Invalid input. Please try again.");
                            } else {
                                break;
                            }
                        }   break OUTER_2;
                    }
                    case "n" -> {
                        customers = TestDataHelper.createPreBuiltCustomers(supplements);
                        break OUTER_2;
                    }
                    default -> {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }
            }

            /**
             * Display the menu for the user to select which function they want to execute
             * Ask the user for which function c, d, e, or f they want to execute until the
             * user select to exit program
             */
            while (true) {
                System.out.println("Select one of the functions to execute: ");
                System.out.println("c. print out the text of all the emails for all customers for four weeks of magazines");
                System.out.println("d. print out the text for the end of month emails for the paying customers");
                System.out.println("e. add a new customer to the magazine service");
                System.out.println("f. remove an existing customer from the magazine service");
                System.out.println("exit. Exit program");
                System.out.println();
                response = scanner.nextLine();

                switch (response) {
                    case "c" -> {
                        /**
                         * c. print out the text of all the emails for all customers for four weeks of
                         * magazines
                         * Each week, each customer gets an email telling them their magazine is ready
                         * to look at and listing the supplements that they currently subscribe to.
                         */
                        if (customers == null || magazine == null) {
                            System.out.println("Magazine or customers data is missing.");
                            break;
                        }
                        for (int i = 0; i < 4; i++) {
                            System.out.println("Week " + (i + 1) + " emails:\n");
                            for (Customer customer : customers) {
                                System.out.println(customer.getWeeklyEmail());
                            }
                        }
                    }
                    case "d" -> {
                        /**
                         * d. print out the text for the end of month emails for the paying customers
                         * Each month, each paying customer gets an email telling them how much is being
                         * charged to their card or account for the month and itemizing the cost by
                         * supplements for them and any of their associate customers.
                         */
                        if (customers == null || magazine == null) {
                            System.out.println("Magazine or customers data is missing.");
                            break;
                        }
                        for (Customer customer : customers) {
                            if (customer instanceof PayingCustomer) {
                                System.out.println(customer.getMonthlyEmail(magazine));
                            }
                        }
                    }
                    case "e" -> {
                        /**
                         * e. add a new customer to the magazine service
                         */
                        if (customers == null || supplements == null) {
                            System.out.println("Supplements data is missing.");
                            break;
                        }
                        Customer newCustomer = TestDataHelper.createTestCustomer(customers, supplements, scanner);
                        // If newCustomer is an associate customer, add it to the list of associate
                        // customer for the paying customer that is linked to the new associate customer
                        if (newCustomer != null) {
                            if (newCustomer instanceof AssociateCustomer associateCustomer) {
                                PayingCustomer payingCustomer = (PayingCustomer) associateCustomer.getPayingCustomer();
                                if (payingCustomer != null) {
                                    payingCustomer.addAssociateCustomer(associateCustomer);
                                }
                            }
                            customers.add(newCustomer);
                        }
                        break;
                    }
                    case "f" -> {
                        /**
                         * f. remove an existing customer from the magazine service
                         */
                        if (customers == null) {
                            System.out.println("Customers data is missing.");
                            break;
                        }
                        // Display the list of customers
                        for (int i = 0; i < customers.size(); i++) {
                            System.out.println(i + 1 + ". " + customers.get(i).getName());
                        }
                        while (true) {
                            System.out.println("Enter the index of the customer to remove: ");
                            String input = scanner.nextLine().trim();
                            // Check if input is empty
                            if (input.isEmpty()) {
                                System.out
                                        .println("Blank or empty inputs are not allowed. Please enter a valid index.");
                                continue;
                            }
                            // Try to parse the input as an integer
                            try {
                                int index = Integer.parseInt(input);
                                if (index < 1 || index > customers.size()) {
                                    System.out.println("Invalid index");
                                } else {
                                    Customer customer = customers.get(index - 1);
                                    customers = TestDataHelper.removeCustomer(customers, customer);
                                    break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("A valid index expected, but got: " + input);
                            }
                        }
                    }
                    case "exit" -> System.exit(0);
                    default -> System.out.println("Invalid input");
                }
            }
        }
    }

    static String displayStudentDetails() {
        StringBuilder studentDetails = new StringBuilder();
        studentDetails.append("Name: Khon Min Thite\n");
        studentDetails.append("Student ID: 35141021\n");
        studentDetails.append("Mode of Enrolment: Kaplan Singapore\n");
        studentDetails.append("Tutor Name: \n");
        studentDetails.append("Tutorial Day and Time: \n");
        return studentDetails.toString();
    }
}