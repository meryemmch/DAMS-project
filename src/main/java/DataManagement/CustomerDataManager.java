package src.main.java.DataManagement;

import com.opencsv.CSVWriter;
import src.main.java.model.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDataManager implements DataReading<Customer>, 
                                            DataProcessing<Customer>, 
                                            Analyzable<Customer>, 
                                            DataStoring<Customer> {

    // Reading data
    @Override
    public List<Customer> readData(String filePath) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Skip the Country field (index 6)
                Customer customer = new Customer(values[0], values[1], values[2], values[3], values[4], values[5],
                        values[7], values[8]);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Data processing
    @Override
    public void removeDuplicates(List<Customer> data) {
        System.out.println("Processing customer data...");
        List<Customer> uniqueCustomers = data.stream()
                                              .distinct()
                                              .toList();
        System.out.println("Processed " + uniqueCustomers.size() + " unique customers.");
    }

    @Override
    public boolean isValid(Customer customer) {
        return !customer.getCustomerId().isEmpty() && 
               !customer.getName().isEmpty() && 
               !customer.getEmail().isEmpty() && 
               !customer.getPhoneNumber().isEmpty() && 
               !customer.getCity().isEmpty() &&
               !customer.getState().isEmpty() &&
               !customer.getGender().isEmpty() && 
               !customer.getAgeGroup().isEmpty();
    }

    @Override
    public List<Customer> removeRowsWithMissingData(List<Customer> data) {
        return data.stream()
                   .filter(this::isValid)
                   .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getRandomSample(List<Customer> data, int numRows) {
        Collections.shuffle(data);
        return data.subList(0, Math.min(numRows, data.size()));
    }

    // Analyzing data
    @Override
    public void analyzeData(List<Customer> data) {
        System.out.println("Analyzing customer data...");
    }

    // Storing data (with Country removed)
    @Override
    public void storeData(List<Customer> data, String filePath) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Write the headers (without the "Country" column)
            String[] header = {"CustomerID", "CustomerName", "PhoneNumber", "Email", "City", "State", "Gender", "AgeGroup"};
            writer.writeNext(header);

            // Write the customer data (excluding the Country field)
            for (Customer customer : data) {
                String[] customerData = {
                    customer.getCustomerId(),          // CustomerID
                    customer.getName(),                // CustomerName
                    customer.getPhoneNumber(),         // PhoneNumber
                    customer.getEmail(),               // Email
                    customer.getCity(),                // City
                    customer.getState(),               // State
                    customer.getGender(),              // Gender
                    customer.getAgeGroup()             // AgeGroup
                };
                writer.writeNext(customerData);
            }

            System.out.println("Customer data has been stored to CSV.");
        } catch (IOException e) {
            System.err.println("Error while writing customer data to CSV: " + e.getMessage());
        }
    }
}