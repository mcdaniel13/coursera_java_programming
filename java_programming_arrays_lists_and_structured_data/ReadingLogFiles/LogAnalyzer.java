import edu.duke.*;
import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public  LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        for(String s : fr.lines()) {
            LogEntry logEntryFromFile = WebLogParser.parseEntry(s);
            records.add(logEntryFromFile);
        }
    }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le : records) {
            if(!counts.keySet().contains(le.getIpAddress()))
                counts.put(le.getIpAddress(), 1);
            else
                counts.put(le.getIpAddress(), counts.get(le.getIpAddress()) + 1);
        }
        return counts;
    }

    public String mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        String mostVisitsIP = null;
        int mostVisits = 0;
        for (String s : counts.keySet()) {
            if(counts.get(s) > mostVisits) {
                mostVisits = counts.get(s);
                mostVisitsIP = s;
            }
        }
        return mostVisitsIP;
    }

    public int countUniqueIPs() {
        HashMap<String, Integer> counts = countVisitsPerIP();
        return counts.size();
    }

    public void printAllHigherThanNum(int num) {
        for(LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if(statusCode > num) {
                System.out.println("LogEntry higher than given number is " + le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            String logDate = le.getAccessTime().toString();
            String ipAddress = le.getIpAddress();
            if(logDate.contains(someday) && !uniqueIps.contains(ipAddress))
                uniqueIps.add(ipAddress);
        }
        return uniqueIps;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIps = new ArrayList<>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            String ipAddress = le.getIpAddress();
            if(statusCode >= low && statusCode <= high
                    && !uniqueIps.contains(ipAddress)) {
                uniqueIps.add(ipAddress);
                System.out.println(le);
            }
        }
        return uniqueIps.size();
    }


    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
