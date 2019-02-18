import edu.duke.*;
import java.util.*;

public class CharacterInPlay {
    private ArrayList<String> myCharNames;
    private ArrayList<Integer> myFreqs;

    private CharacterInPlay() {
        myCharNames = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void update(String person) {
        int index = myCharNames.indexOf(person);
        if (index != -1) {
            int freq = myFreqs.get(index);
            myFreqs.set(index, freq + 1);
        } else {
            myCharNames.add(person);
            myFreqs.add(1);
        }
    }

    private void findAllCharacters() {
        myCharNames.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();
        for(String s : fr.lines()) {
            int index = s.indexOf('.');
            if(index != -1) {
                update(s.substring(0, index));
            }
        }
    }

    private void charactersWithNumParts(int num1, int num2) {
        for (String s : myCharNames) {
            int index = myCharNames.indexOf(s);
            if(myFreqs.get(index) >= num1 && myFreqs.get(index) <= num2) {
                System.out.println(myCharNames.get(index) + " " + myFreqs.get(index));
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
        findAllCharacters();
        charactersWithNumParts(10, 15);
        System.out.println("The name that speaks most often and its count are: "
                + myCharNames.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
    }

    public static void main(String[] args) {
        CharacterInPlay obj = new CharacterInPlay();
        obj.tester();
    }
}
