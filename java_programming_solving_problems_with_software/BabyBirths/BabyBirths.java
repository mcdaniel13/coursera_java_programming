/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Moongee Cho
 * @version 1.0
 * @date 02/14/2019
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.*;


public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    /* Find total birth number of boys/girls
     * Find total number of boys/girls names and total
     */
    public void totalBirths (FileResource fr) {
        // 1st part - find total birth number of boys/girls
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                    totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        
        // 2nd part - find total birth number of boys/girls
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        StringBuilder sb = new StringBuilder();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String name = rec.get(0);
            String gender = rec.get(1);
            if(gender.equals("M")) {
                totalBoysNames++;
            } else {
                totalGirlsNames++;
            }
            sb.append(name + " (" + gender + ") ");
        }
        
        String nameString = sb.toString();
        System.out.println("Number of boys names is " + totalBoysNames + 
                            ", Number of girls names is " + totalGirlsNames);
        System.out.println("Total Names: " + nameString);
    }
    
    /* test for totalBirth */
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    /* find the rank of the name in the given year, the given gender */
    public int getRank(int year, String name, String gender) {
        // String fileName = "yob" + year + "short.csv";
        // FileResource fr = new FileResource(fileName);
        FileResource fr = new FileResource();
        int numBorn = 0;
        int rank = 1;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                numBorn = Integer.parseInt(rec.get(2));
                break;
            }
        }
        if(numBorn == 0) {
            return -1;
        }
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender) && numBorn <  Integer.parseInt(rec.get(2))) {
                rank++;
            }
        }
        
        return rank;
    }
    
    /* test for getRank */
    public void testGetRank() {
        Scanner S = new Scanner(System.in);
        String name = S.nextLine();
        String gender = S.nextLine();
        int result = getRank(2012, name, gender);
        System.out.println("The rank of the given name " + name + " is " + result);
    }
    
    /* find the name of the rank in the given year, the given gender */
    public String getName(int year, int rank, String gender) {
        // String fileName = "yob" + year + "short.csv";
        // FileResource fr = new FileResource(fileName);
        FileResource fr = new FileResource();
        
        int itRound = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                itRound++;
                if(itRound == rank) {
                    return rec.get(0);
                }
            }
        }
        
        return "NO NAME"; 
    }
    
    /* test for getName */
    public void testGetName() {
        Scanner S = new Scanner(System.in);
        int year = S.nextInt();
        int rank = S.nextInt();
        
        Scanner S2 = new Scanner(System.in);
        String gender = S2.nextLine();
        
        String result = getName(year, rank, gender);
        System.out.println("The name of the rank " + rank + " in " + year + " is " + result);
    }
    
    /* get the name of the same populatrity in another year */  
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String NameInNewYear = getName(year, rank, gender);
        String printedGender = "he";
        if(gender.equals("F")) {
            printedGender = "she";
        }
        System.out.println(name + " born in " + year + " would be " + NameInNewYear +
                            " if " + printedGender + " was born in " + newYear);
    }
    
    /* test for whatIsNameInYear */
    public void testWhatIsNameInYear() {
        Scanner S2 = new Scanner(System.in);
        String name = S2.nextLine();
        String gender = S2.nextLine();
        
        Scanner S = new Scanner(System.in);
        int year = S.nextInt();
        int newYear = S.nextInt();
        
        whatIsNameInYear(name, year, newYear, gender);
    }

    /* find year of highest rank */
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int minYear = 0;
        int minRank = Integer.MAX_VALUE;

        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
            int rank = 0;
            Boolean isFound = false;
            for(CSVRecord rec : fr.getCSVParser(false)) {
                if(rec.get(1).equals(gender)) {
                    rank++;
                    if(rec.get(0).equals(name)) {
                        isFound = true;
                        break;
                    }
                }
            }
            if(isFound && (minRank > rank)) {
                minRank = rank;
                minYear = year;
            } else {
                rank = -1;
            }
        }
        
        return minYear;
    }

    /* test */
    public void testYearOfHighestRank() {
        Scanner S2 = new Scanner(System.in);
        String name = S2.nextLine();
        String gender = S2.nextLine();
        
        int year = yearOfHighestRank(name, gender);
        System.out.println("Year of the highest rank is " + year);
    }

    /* get average rank */
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0;
        double fileCount = 0;
        for(File f : dr.selectedFiles()) {
            fileCount += 1.0;
            FileResource fr = new FileResource(f);
            double rank = 0.0;
            Boolean isFound = false;
            for(CSVRecord rec : fr.getCSVParser(false)) {
                if(rec.get(1).equals(gender)) {
                    rank += 1.0;
                    if(rec.get(0).equals(name)) {
                        isFound = true;
                        break;
                    }
                }
            }
            if(!isFound) {
                rank = -1;
            }
            rankSum += rank;
        }
        return rankSum / fileCount;
    }

    /* test */
    public void testGetAverageRank() {
        Scanner S2 = new Scanner(System.in);
        String name = S2.nextLine();
        String gender = S2.nextLine();
        
        double averageRank = getAverageRank(name, gender);
        System.out.println("Average rank is " + averageRank);
    }

    /* find total births higher than given name */
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        // String fileName = "yob" + year + "short.csv";
        // FileResource fr = new FileResource(fileName);
        FileResource fr = new FileResource();
        
        int sum = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                if(rec.get(0).equals(name)) {
                    break;
                } else {
                    sum += Integer.parseInt(rec.get(2));
                }
            }
        }
        
        return sum;
    }

    /* test */
    public void testGetTotalBirthsRankedHigher() {
        Scanner S2 = new Scanner(System.in);
        String name = S2.nextLine();
        String gender = S2.nextLine();
        
        Scanner S = new Scanner(System.in);
        int year = S.nextInt();

        int totalBirth = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total births ranked higher are " + totalBirth);        
    }
}
