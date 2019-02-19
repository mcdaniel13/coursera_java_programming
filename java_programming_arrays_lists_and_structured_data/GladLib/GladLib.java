import edu.duke.*;
import java.util.*;

public class GladLib {
    private HashMap<String, ArrayList<String>> myMap;

    private ArrayList<String> seenList;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        myMap = new HashMap<>();
        seenList = new ArrayList<>();
        String[] labels = {"country", "noun", "animal",
                            "adjective", "name", "color",
                            "timeframe", "verb", "fruit"};
        for (String s : labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));

        while(true) {
            if (seenList.contains(sub)) {
                sub = getSubstitute(w.substring(first + 1, last));
            } else {
                seenList.add(sub);
                break;
            }
        }

        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap() {
        int totalWords = 0;
        for (String s : myMap.keySet()) {
            totalWords += myMap.get(s).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered() {
        int totalWords = 0;
        ArrayList<String> categoryList = new ArrayList<>();
        FileResource fr = new FileResource("data/madtemplate2.txt");
        for(String s : fr.words()) {
            if(s.contains("<")) {
                String categoryName = s.substring(s.indexOf("<") + 1, s.indexOf(">"));

                if (categoryName.equals("number"))
                    continue;

                if (!categoryList.contains(categoryName))
                    categoryList.add(categoryName);
            }
        }
        for (String s : categoryList) {
            totalWords += myMap.get(s).size();
        }
        return totalWords;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Number of total words in map is " + totalWordsInMap());
        System.out.println("Number of total words considered is " + totalWordsConsidered());
    }

    public static void main(String[] args) {
        GladLib obj = new GladLib();
        obj.makeStory();
    }
}
