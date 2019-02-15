/* author: Moongee Cho
   date: 02/15/2019
 */

import edu.duke.*;
import java.util.*;

public class WordPlay {
    // check the character is vowel or not
    public Boolean isVowel(char ch) {
        String vowelUpper = "AEIOU";
        String vowelLower = vowelUpper.toLowerCase();

        boolean isUpper = Character.isUpperCase(ch);

        int index = vowelUpper.indexOf(ch);
        if(!isUpper)
            index = vowelLower.indexOf(ch);

        if(index != -1)
            return true;
        else
            return false;
    }

    // check the vowels from the given phrase, and replace to ch
    public String replaceVowels(String phrase, char ch) {
        StringBuilder changed = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); ++i) {
            char currChar = phrase.charAt(i);
            boolean isVowel = isVowel(currChar);
            if(isVowel)
                changed.setCharAt(i, ch);
        }

        return changed.toString();
    }

    // test for replaceVewels
    public void testReplaceVowel() {
        Scanner S = new Scanner(System.in);
        String phrase = S.nextLine();
        String changed = replaceVowels(phrase, '*');
        System.out.println(phrase);
        System.out.println(changed);
    }

    // check the ch from the given phrase, and replace to + or *
    public String emphasize(String phrase, char ch) {
        StringBuilder changed = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); ++i) {
            char currChar = phrase.charAt(i);
            if(currChar == ch || currChar == Character.toUpperCase(ch)) {
                if ((i + 1) % 2 == 0)  // even number location
                    changed.setCharAt(i, '+');
                else  //odd number location
                    changed.setCharAt(i, '*');
            }
        }

        return changed.toString();
    }

    // test for emphasize
    public void testEmphasize() {
        Scanner S = new Scanner(System.in);
        String phrase = S.nextLine();
        char ch = S.next().charAt(0);
        String changed = emphasize(phrase, ch);
        System.out.println(phrase);
        System.out.println(changed);
    }

//    public static void main(String[] args) {
//        WordPlay obj = new WordPlay();
//        obj.testReplaceVowel();
//        obj.testEmphasize();
//    }
}
