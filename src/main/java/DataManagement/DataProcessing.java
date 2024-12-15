package src.main.java.DataManagement;

import java.util.*;
public interface DataProcessing<T> {
    void removeDuplicates(List<T> data);
    boolean isValid(T item);
    List<T> removeRowsWithMissingData(List<T> data);
    List<T> getRandomSample(List<T> data, int numRows) ;

}
