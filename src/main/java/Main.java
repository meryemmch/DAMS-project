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
            String customerFilePath = "resources/customers.csv";
            String orderFilePath = "resources/order.csv";
            String productFilePath = "resources/products.csv";
            String outputCustomerFilePath = "resources/outputcustomer.csv"; // File path for storing processed customer data
            String outputOrderFilePath = "resources/outputorder.csv";
            String outputProductFilePath = "resources/outputproduct.csv";
            // Start the user authentication menu
            userAuthenticationMenu(customerFilePath, orderFilePath, productFilePath, outputCustomerFilePath,outputOrderFilePath,outputProductFilePath);
            dataManagementMenu(customerFilePath, orderFilePath, productFilePath,outputOrderFilePath, outputProductFilePath,outputCustomerFilePath);
           
        }
    
        // User authentication menu (sign-up, log-in, or exit)
    private static void userAuthenticationMenu(String customerFilePath, String orderFilePath, String productFilePath,String outputOrderFilePath, String outputCustomerFilePath,String outputProductFilePath) {
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                System.out.println("1. Sign Up");
                System.out.println("2. Log In");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
    
                
                switch (choice) {
                    case 1:
                        // Sign up process
                        UserManager userManager = new UserManager();
                        userManager.signup();
                        break;
                    case 2:
                        // Log in process
                        userManager = new UserManager();
                        if (userManager.login()) {
                            System.out.println("Login successful!");
                            dataManagementMenu(customerFilePath, orderFilePath, productFilePath, outputOrderFilePath,  outputCustomerFilePath, outputProductFilePath);
                        } else {
                            System.out.println("Login failed. Try again.");
                        }
                        break;
                    case 3:
                        // Exit the program
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
private static void processAndStoreCustomerData(String inputFilePath, String outputCustomerFilePath) {
            CustomerDataManager customerManaging = new CustomerDataManager();
        
            // Step 1: Read the customer data
            List<Customer> customerData = customerManaging.readData(inputFilePath);
            System.out.println("Data read from file: " + customerData.size() + " customers");
        
            // Step 2: Remove duplicates
            customerManaging.removeDuplicates(customerData); // Modify in place
            System.out.println("After removing duplicates: " + customerData.size() + " customers");
        
            // Step 3: Remove rows with missing data
            customerData = customerManaging.removeRowsWithMissingData(customerData); // Returns new list
            System.out.println("After removing rows with missing data: " + customerData.size() + " customers");
        
            // Step 4: Get a random sample (returns a sublist)
            customerData = customerManaging.getRandomSample(customerData, 200); // Make sure the sample is returned
            System.out.println("After sampling: " + customerData.size() + " customers");
        
            // Step 5: Analyze the data
            customerManaging.analyzeData(customerData);
        
            // Step 6: Store the processed data
            customerManaging.storeData(customerData, outputCustomerFilePath);
        }
        

    // Method to process and store order data
private static void processAndStoreOrderData(String inputFilePath, String outputOrderFilePath) {
        OrderDataManager orderManaging = new OrderDataManager();
        List<Order> orderData = orderManaging.readData(inputFilePath);
        orderManaging.removeDuplicates(orderData); // Remove duplicates
        orderData = orderManaging.removeRowsWithMissingData(orderData); // Remove invalid data
        orderData = orderManaging.getRandomSample(orderData,200); 
        orderManaging.analyzeData(orderData); // Analyze the data
        orderManaging.storeData(orderData, outputOrderFilePath); // Store the processed data
    }

    // Method to process and store product data
private static void processAndStoreProductData(String inputFilePath, String outputProductFilePath) {
        ProductDataManager productManaging = new ProductDataManager();
        List<Product> productData = productManaging.readData(inputFilePath);
        productManaging.removeDuplicates(productData); // Remove duplicates
        productData = productManaging.removeRowsWithMissingData(productData); // Remove invalid data
        productData=productManaging.getRandomSample(productData,200) ;
        productManaging.analyzeData(productData); // Analyze the data
        productManaging.storeData(productData, outputProductFilePath); // Store the processed data
    }
    
        // Data management menu
    private static void dataManagementMenu(String customerFilePath, String orderFilePath, String productFilePath,String outputOrderFilePath, String outputCustomerFilePath,String outputProductFilePath) {
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                System.out.println("\nData Management Menu:");
                System.out.println("1. Manage Customer Data");
                System.out.println("2. Manage Product Data");
                System.out.println("3. Manage Order Data");
                System.out.println("4. Manage All Data");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
    
                switch (choice) {
                    case 1:
                        processAndStoreCustomerData(customerFilePath, outputCustomerFilePath);
                        break;
                    case 2:
                        processAndStoreProductData(productFilePath, outputProductFilePath);
                        break;
                    case 3:
                        processAndStoreOrderData(orderFilePath, outputOrderFilePath);
                        break;
                    case 4:
                        processAndStoreCustomerData(customerFilePath, outputCustomerFilePath);
                        processAndStoreProductData(productFilePath, outputProductFilePath);
                        processAndStoreOrderData(orderFilePath, outputOrderFilePath);
                        break;
                    case 5:
                        System.out.println("Exiting Data Management Menu...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    
}

