
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
        String filename = "short-test_log";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        System.out.println("Number of unique ip addresses is "
                            + logAnalyzer.countUniqueIPs());

    }

    public void testAllHigherThanNum() {
        String filename = "weblog1_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        logAnalyzer.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay() {
        String filename = "weblog1_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        ArrayList<String> uniqueIps = logAnalyzer.uniqueIPVisitsOnDay("Mar 24");
        System.out.println("Number of unique IPs visited on day is " + uniqueIps.size());
        for (String s : uniqueIps) {
            System.out.println("Unique IP visited on day is " + s);
        }
    }

    public void testUniqueIPsInRange() {
        String filename = "weblog1_log.txt";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        System.out.println("Number of unique ip addresses in range is "
                            + logAnalyzer.countUniqueIPsInRange(300, 399));
    }

    public void testCounts() {
        String filename = "short-test_log";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println(counts);
    }

    public void testMostNumberVisitsByIP() {
        String filename = "weblog3-short_log";
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(filename);
        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIP();
        System.out.println("Most number visited IP Address is "
                        + logAnalyzer.mostNumberVisitsByIP(counts));
    }

    public static void main(String[] args) {
        Tester obj = new Tester();
//        obj.testLogAnalyzer();
//        obj.testUniqueIP();
//        obj.testAllHigherThanNum();
//        obj.testUniqueIPVisitsOnDay();
//        obj.testUniqueIPsInRange();
//        obj.testCounts();
        obj.testMostNumberVisitsByIP();
    }
}
