package src.main.java;

import java.util.List;
import java.util.Scanner;
import src.main.java.model.Customer;
import src.main.java.model.Order;
import src.main.java.model.Product;
import src.main.java.DataManagement.*;
import src.main.java.UserManager.*;


public class Main {
    public static void main(String[] args) {

        // Define file paths for the data files
        String customerFilePath = "resources/customer.csv";
        String orderFilePath = "resources/order.csv";
        String productFilePath = "resources/product.csv";
        String outputFilePath = "resources/output.csv";  // File path for storing processed data

        // Process and store customer data
        processAndStoreCustomerData(customerFilePath, outputFilePath);

        // Process and store order data
        processAndStoreOrderData(orderFilePath, outputFilePath);

        // Process and store product data
        processAndStoreProductData(productFilePath, outputFilePath);

        // Start the user authentication menu
        userAuthenticationMenu();
    }

    // Method to process and store customer data
    private static void processAndStoreCustomerData(String inputFilePath, String outputFilePath) {
        CustomerDataManager customerManager = new CustomerDataManager();
        List<Customer> customerData = customerManager.readData(inputFilePath);
        customerManager.removeDuplicates(customerData);  // Remove duplicates
        customerData = customerManager.removeRowsWithMissingData(customerData);  // Remove invalid data
        customerManager.analyzeData(customerData);  // Analyze the data
        customerManager.storeData(customerData, outputFilePath);  // Store the processed data
    }

    // Method to process and store order data
    private static void processAndStoreOrderData(String inputFilePath, String outputFilePath) {
        OrderDataManager orderManager = new OrderDataManager();
        List<Order> orderData = orderManager.readData(inputFilePath);
        orderManager.removeDuplicates(orderData);  // Remove duplicates
        orderData = orderManager.removeRowsWithMissingData(orderData);  // Remove invalid data
        orderManager.analyzeData(orderData);  // Analyze the data
        orderManager.storeData(orderData, outputFilePath);  // Store the processed data
    }

    // Method to process and store product data
    private static void processAndStoreProductData(String inputFilePath, String outputFilePath) {
        ProductDataManager productManager = new ProductDataManager();
        List<Product> productData = productManager.readData(inputFilePath);
        productManager.removeDuplicates(productData);  // Remove duplicates
        productData = productManager.removeRowsWithMissingData(productData);  // Remove invalid data
        productManager.analyzeData(productData);  // Analyze the data
        productManager.storeData(productData, outputFilePath);  // Store the processed data
    }

    // User authentication menu (sign-up, log-in, or exit)
    private static void userAuthenticationMenu() {
        Scanner scanner = new Scanner(System.in);

        // Display menu for the user
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Sign up process
                    UserManager userManager = new UserManager();
                    userManager.signup();  // Call signup method
                    break;

                case 2:
                    // Log in process
                    userManager = new UserManager();
                    if (userManager.login()) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Login failed. Try again.");
                    }
                    break;

                case 3:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;  // End the program

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

