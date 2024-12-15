package src.main.java.DataManagement;

import java.util.List;

public interface DataReading<T> {
    List<T> readData(String filePath);  // Method to read data from a file
}
