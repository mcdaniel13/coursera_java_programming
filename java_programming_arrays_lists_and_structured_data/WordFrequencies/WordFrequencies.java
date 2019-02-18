import edu.duke.FileResource;

import java.util.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    private WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();

        for (String s : fr.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index != -1) {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);
            } else {
                myWords.add(s);
                myFreqs.add(1);
            }
        }
    }

    private int findIndexOfMax() {
        int maxCount = 0;
        int maxIndex = 0;
        for (int i : myFreqs) {
            if(maxCount < i) {
                maxCount = i;
                maxIndex = myFreqs.indexOf(i);
            }
        }
        return maxIndex;
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (String s : myWords) {
            int index = myWords.indexOf(s);
            System.out.println(myFreqs.get(index) + " " + myWords.get(index));
        }
        System.out.println("The word that occurs most often and its count are: "
                + myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
    }

    public static void main(String[] arg) {
        WordFrequencies obj = new WordFrequencies();
        obj.tester();
    }
}