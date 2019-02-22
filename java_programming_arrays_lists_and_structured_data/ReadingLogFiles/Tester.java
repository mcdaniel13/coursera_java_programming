
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        String filename = "short-test_log";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        logAnalyzer.printAll();
    }

    public void testUniqueIP() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        System.out.println("Number of unique ip addresses is "
                            + logAnalyzer.countUniqueIPs());

    }

    public void testAllHigherThanNum() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        logAnalyzer.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        ArrayList<String> uniqueIps = logAnalyzer.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("Number of unique IPs visited on day is " + uniqueIps.size());
        for (String s : uniqueIps) {
            System.out.println("Unique IP visited on day is " + s);
        }
    }

    public void testUniqueIPsInRange() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        System.out.println("Number of unique ip addresses in range is "
                            + logAnalyzer.countUniqueIPsInRange(400, 499));
    }

    public void testCounts() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(counts);
    }

    public void testMostNumberVisitsByIP() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println("Most number visited by IP is "
                        + logAnalyzer.mostNumberVisitsByIP(counts));
        System.out.println("And IP Addresses are " + logAnalyzer.iPsMostVisits(counts));
    }

    public void testIPsForDays() {
        String filename = "weblog2_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        HashMap<String, ArrayList<String>> ipsByDate = logAnalyzer.iPsForDays();
        for (String s : ipsByDate.keySet()) {
            System.out.println(s + " : " + ipsByDate.get(s));
        }
        System.out.println("Date of most IP visits is "
                            + logAnalyzer.dayWithMostIPVisits(ipsByDate));
        System.out.println("IPs with most visits on day is " + logAnalyzer.iPsWithMostVisitsOnDay(ipsByDate, "Sep 29"));
    }

    public static void main(String[] args) {
        Tester obj = new Tester();
//        obj.testLogAnalyzer();
//        obj.testUniqueIP();
//       obj.testAllHigherThanNum();
//        obj.testUniqueIPVisitsOnDay();
//        obj.testUniqueIPsInRange();
//        obj.testCounts();
//        obj.testMostNumberVisitsByIP();
        obj.testIPsForDays();
    }
}
