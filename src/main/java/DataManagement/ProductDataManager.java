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
import src.main.java.model.Product;


public class ProductDataManager implements DataReading<Product>, 
                                            DataProcessing<Product>, 
                                            Analyzable<Product>, 
                                            DataStoring<Product> {

    // Reading data
    @Override
    public List<Product> readData(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Product product = new Product(values[0], values[1], values[2], values[3], values[4], Double.parseDouble(values[5]));
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
    
    // Data processing
    @Override
    public void removeDuplicates(List<Product> data) {
        System.out.println("Processing product data...");
        // Example: Remove duplicates based on productId
        List<Product> uniqueProducts = data.stream()
                                           .distinct()
                                           .toList();
        System.out.println("Processed " + uniqueProducts.size() + " unique products.");
    }

    @Override
    public boolean isValid(Product product) {
        return !product.getproductId().isEmpty() &&
               !product.getproductName().isEmpty() &&
               !product.getcategory().isEmpty() &&
               product.getprice() > 0;
    }
    
    @Override
    public List<Product> removeRowsWithMissingData(List<Product> data) {
        return data.stream()
                   .filter(this::isValid)  // Keep only valid rows
                   .collect(Collectors.toList());
    }

    @Override
    public List<Product> getRandomSample(List<Product> data, int numRows) {
        Collections.shuffle(data);  // Shuffle the list to randomize the selection
        return data.subList(0, Math.min(numRows, data.size()));  // Return the first 'numRows' items
    }

    // Analyzing data
    @Override
    public void analyzeData(List<Product> data) {
        System.out.println("Analyzing product data...");
      
    }

   
    // Storing data
   @Override
  public void storeData(List<Product> data, String outputProductFilePath) {
    System.out.println(data);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputProductFilePath))) {
        // Write the headers
        String header = "ProductID,ProductName,Category,SubCategory,Brand,Price";
        writer.write(header);
        writer.newLine();  // New line after header

        // Write the product data
        for (Product product : data) {
            String productData = product.getproductId() + "," +
                                 product.getproductName() + "," +
                                 product.getcategory() + "," +
                                 product.getsubCategory() + "," +
                                 product.getbrand() + "," +
                                 product.getprice();
            writer.write(productData);
            writer.newLine();  // New line after each product
        }

        System.out.println("Product data has been stored to CSV.");
    } catch (IOException e) {
        System.err.println("Error while writing product data to CSV: " + e.getMessage());
    }
}
}
