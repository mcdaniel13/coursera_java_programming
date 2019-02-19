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

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
