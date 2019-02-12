
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
        }        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public void testColdestInDay () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF"));
    }
    
    public File fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File resultFile = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                resultFile = f;
            } else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                    resultFile = f;
                }
            }
        }
        
        return resultFile;
    }
    
    public void testFileWithColdestTemperature() {
        File fileWithColdestTemperature = fileWithColdestTemperature();
        String fileAbsolutePath = fileWithColdestTemperature.getAbsolutePath();
        String fileName = fileWithColdestTemperature.getName();        
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource(fileAbsolutePath);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");        
        for(CSVRecord record: fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                String currentRowStr = currentRow.get("Humidity");
                String lowestRowStr = lowestSoFar.get("Humidity");               
                int currentTemp = 100;
                int lowestTemp = 100;
                if(!currentRowStr.contains("N/A")) {
                    currentTemp = Integer.parseInt(currentRowStr);                
                }
                 if(!currentRowStr.contains("N/A")) {
                    lowestTemp = Integer.parseInt(lowestRowStr);                
                }
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < lowestTemp) {
                    //If so update largestSoFar to currentRow
                    lowestSoFar = currentRow;
                }
            }
        }        //The largestSoFar is the answer
        return lowestSoFar;        
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            } else {
                String currentRowStr = currentRow.get("Humidity");
                String lowestRowStr = lowestSoFar.get("Humidity");               
                int currentTemp = 100;
                int lowestTemp = 100;
                if(!currentRowStr.contains("N/A")) {
                    currentTemp = Integer.parseInt(currentRowStr);                
                }
                 if(!currentRowStr.contains("N/A")) {
                    lowestTemp = Integer.parseInt(lowestRowStr);                
                }
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentTemp < lowestTemp) {
                    //If so update largestSoFar to currentRow
                    lowestSoFar = currentRow;
                }
            }
        } 
        return lowestSoFar;
    }
    
    public void testlowestHumidityInManyFiles () {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
                   " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        int size = 0;
        double tempTotal = 0;
        for(CSVRecord record: parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            tempTotal += temp;
            size++;
        }
        return (double) tempTotal/ (double) size;
    }
    
    public void testaverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int size = 0;
        double tempTotal = 0.0;
        for(CSVRecord record: parser) {
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity >= value) {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                tempTotal += temp;
                size++;
            }
        }
        double averageTemp = (double) tempTotal / (double) size;
        if(averageTemp > 0) {
            return averageTemp;
        } else {
            return 0;
        }
    }
    
    public void testaverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(averageTemp != 0) {
            System.out.println("Average temperature in file is " + averageTemp);
        } else {
            System.out.println("No temperatures with that humidity");
        }
    }
}    

