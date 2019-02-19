import java.io.*;
import java.util.*;
import edu.duke.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordInFilenameMap;


    public WordsInFiles() {
        wordInFilenameMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String s : fr.words()) {
            //s = s.toLowerCase();
            //s = s.replaceAll("[^a-zA-Z0-9]", "");
            if(wordInFilenameMap.containsKey(s)) {
                ArrayList<String> getList = wordInFilenameMap.get(s);
                if(!getList.contains(fileName)) {
                    getList.add(fileName);
                    wordInFilenameMap.put(s, getList);
                }
            } else {
                ArrayList<String> wordList = new ArrayList<>();
                wordList.add(fileName);
                wordInFilenameMap.put(s, wordList);
            }
        }
    }

    private void buildWordFileMap() {
        wordInFilenameMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    private int maxNumber() {
        int maxNum = 0;
        for(String s : wordInFilenameMap.keySet()) {
            if (maxNum < wordInFilenameMap.get(s).size()) {
                maxNum = wordInFilenameMap.get(s).size();
            }
        }
        return maxNum;
    }

    private void printFilesIn(String word) {
        ArrayList<String> wordList = wordInFilenameMap.get(word);
        String fileNameList = wordList.toString();
        System.out.println(word + " appears in the files: " + fileNameList);
    }

    public void tester() {
        buildWordFileMap();

        int maxNumber = maxNumber();
        int maxNumberofWords = 0;
        ArrayList<String> maxNumberWordList = new ArrayList<>();
        for (String s : wordInFilenameMap.keySet()) {
            if(wordInFilenameMap.get(s).size() == maxNumber) {
                maxNumberWordList.add(s);
                maxNumberofWords++;
            }
        }
        System.out.println("The greatest number of files a word appears in is " + maxNumberofWords
                + ", and there are such words: " + maxNumberWordList.toString());

        for (String s : maxNumberWordList) {
            printFilesIn(s);
        }
    }

    public static void main(String[] args) {
        WordsInFiles obj = new WordsInFiles();
        obj.tester();
    }
}
