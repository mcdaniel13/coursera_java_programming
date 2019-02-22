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

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int mostVisits = 0;
        for (String s : counts.keySet()) {
            if(counts.get(s) > mostVisits)
                mostVisits = counts.get(s);
        }
        return mostVisits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        ArrayList<String> mostVisitsIP = new ArrayList<>();
        int mostVisits = mostNumberVisitsByIP(counts);
        for (String s : counts.keySet()) {
            if(counts.get(s) == mostVisits)
                mostVisitsIP.add(s);
        }

        return mostVisitsIP;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipsByDate = new HashMap<>();
        for (LogEntry le : records) {
            String[] dateArray = le.getAccessTime().toString().split("\\s");
            String date = dateArray[1] + " " + dateArray[2];
            if(!ipsByDate.keySet().contains(date)) {
                ArrayList<String> ipAddress = new ArrayList<>();
                ipAddress.add(le.getIpAddress());
                ipsByDate.put(date, ipAddress);
            } else {
                ArrayList<String> ipAddress = ipsByDate.get(date);
                ipAddress.add(le.getIpAddress());
                ipsByDate.put(date, ipAddress);
            }
        }
        return ipsByDate;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsByDate) {
        int mostVisits = 0;
        String mostVisitsDay = null;
        for(String s : ipsByDate.keySet()) {
            int visitsInDay = ipsByDate.get(s).size();
            if(visitsInDay > mostVisits) {
                mostVisits = visitsInDay;
                mostVisitsDay = s;
            }
        }
        return  mostVisitsDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsByDate, String date) {
       ArrayList<String> ipsInSelectedDate = ipsByDate.get(date);
       HashMap<String, Integer> counts = new HashMap<>();
       for (String s : ipsInSelectedDate) {
           if(!counts.containsKey(s))
                counts.put(s, 1);
            else
                counts.put(s, counts.get(s) + 1);
        }
        return iPsMostVisits(counts);
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
