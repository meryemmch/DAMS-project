package src.main.java.DataManagement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

import src.main.java.model.Order;



public class OrderDataManager implements DataReading<Order>, 
                                            DataProcessing<Order>, 
                                            Analyzable<Order>, 
                                            DataStoring<Order> {

    // Reading data
    @Override
    public List<Order> readData(String filePath) {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Order order = new Order(values[0], values[1], values[2], values[3], Integer.parseInt(values[4]),
                                        values[5], Double.parseDouble(values[6]), Double.parseDouble(values[7]),
                                        values[8], Double.parseDouble(values[9]), values[10], Integer.parseInt(values[11]));
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Data processing
    @Override
    public void removeDuplicates(List<Order> data) {
        System.out.println("Processing order data...");
        // Example: Remove duplicates based on orderId
        List<Order> uniqueOrders = data.stream()
                                       .distinct()
                                       .toList();
        System.out.println("Processed " + uniqueOrders.size() + " unique orders.");
    }

    @Override
    public boolean isValid(Order order) {
        return !order.getOrderId().isEmpty() &&
               !order.getCustomerId().isEmpty() &&
               !order.getProductId().isEmpty() &&
               order.getQuantity() > 0;
    }

    @Override
    public List<Order> removeRowsWithMissingData(List<Order> data) {
        return data.stream()
                   .filter(this::isValid)  // Keep only valid rows
                   .collect(Collectors.toList());
    }

    @Override
    public List<Order> getRandomSample(List<Order> data, int numRows) {
        Collections.shuffle(data);  // Shuffle the list to randomize the selection
        return data.subList(0, Math.min(numRows, data.size()));  // Return the first 'numRows' items
    }

    // Analyzing data
    @Override
    public void analyzeData(List<Order> data) {
        System.out.println("Analyzing order data...");
       
    }

     // Storing data
  @Override
public void storeData(List<Order> data, String outputOrderFilePath) {
    System.out.println("Number of records to write: " + data.size());
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputOrderFilePath))) {
        // Write the headers
        String header = "OrderID,CustomerID,ProductID,SellerID,Quantity,OrderDate,ShippingCost,DiscountAmount,PaymentMethod,TotalAmount,DeliveryStatus,ReviewRating";
        writer.write(header);
        writer.newLine();  // New line after header

        // Write the order data
        for (Order order : data) {
            String orderData = order.getOrderId() + "," +
                               order.getCustomerId() + "," +
                               order.getProductId() + "," +
                               order.getSellerId() + "," +
                               order.getQuantity() + "," +
                               order.getOrderDate() + "," +
                               order.getShippingCost() + "," +
                               order.getDiscountAmount() + "," +
                               order.getPaymentMethod() + "," +
                               order.getTotalAmount() + "," +
                               order.getDeliveryStatus() + "," +
                               order.getReviewRating();
            writer.write(orderData);
            writer.newLine();  // New line after each order record
        }

        System.out.println("Order data has been stored to CSV.");
    } catch (IOException e) {
        System.err.println("Error while writing order data to CSV: " + e.getMessage());
    }
}
}

