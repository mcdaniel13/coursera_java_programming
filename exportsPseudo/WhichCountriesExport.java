/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public String countryInfo(CSVParser parser, String country) {
        boolean isFound = false;
        String finalString = "NOT FOUND";
        for(CSVRecord record: parser) {
            String countryName = record.get("Country");
            if(countryName.equals(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(countryName);
                stringBuilder.append(": ");
                stringBuilder.append(exports);
                stringBuilder.append(": ");
                stringBuilder.append(value);
                
                finalString = stringBuilder.toString();

                isFound = true;
                break;
            }
        }
        return finalString;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println("Name of a country that has " + exportItem1 + " and " + 
                                    exportItem2 + " as exporters is " + country);
            }
        }        
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println("Name of a country that has bigExporters is " + country + " " + value);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        CSVParser parser = fr.getCSVParser();
        String resultCountryInfo = countryInfo(parser, "Nauru");
        System.out.println("resultCountryInfo is " + resultCountryInfo);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        parser = fr.getCSVParser();
        int resultNumberOfExporters = numberOfExporters(parser, "cocoa");
        System.out.println("NumberOfExporters is " + resultNumberOfExporters);
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
