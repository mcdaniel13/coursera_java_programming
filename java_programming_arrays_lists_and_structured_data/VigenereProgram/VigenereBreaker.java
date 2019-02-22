import java.io.*;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; ++i) {
            String slice = sliceString(encrypted, i, klength);
            key[i] =  cc.getKey(slice);
        }
//        System.out.println(Arrays.toString(key));
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionarySet = new HashSet<>();
        for (String s : fr.lines()) {
            dictionarySet.add(s.toLowerCase());
        }
        return dictionarySet;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        String words[] = message.toLowerCase().split("\\W+");
        for(String s : words) {
            if(dictionary.contains(s)) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxCount = 0;
        int keyLength = 0;
        char commonChar = mostCommonCharIn(dictionary);
        String finalDecryptedMessage = null;
        for (int i = 1; i <= 100; ++i) {
            int key[] = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String decryptedMessage = vc.decrypt(encrypted);
            int currCount = countWords(decryptedMessage, dictionary);
            if(currCount > maxCount) {
                maxCount = currCount;
                keyLength = i;
                finalDecryptedMessage = decryptedMessage;
            }
        }
        System.out.println("Key length is " + keyLength + " where valid words are " + maxCount);
        return finalDecryptedMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> alphabetMap = new HashMap<>();
        for(String s : dictionary) {
            for(Character c : s.toCharArray()) {
                if(!alphabetMap.containsKey(c))
                    alphabetMap.put(c, 1);
                else
                    alphabetMap.put(c, alphabetMap.get(c) + 1);
            }
        }

        int maxCount = 0;
        char mostCommonChar = 0;
        for(Character c : alphabetMap.keySet()) {
            int currCharCount = alphabetMap.get(c);
            if(currCharCount > maxCount) {
                maxCount = currCharCount;
                mostCommonChar = c;
            }
        }

        System.out.println("Most common character is " + mostCommonChar);
        return mostCommonChar;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> language) {
        int maxCount = 0;
        String finaldecrypted = null;
        String finalLanguage = null;
        for(String s : language.keySet()) {
            System.out.println("Language is " + s);
            HashSet<String> words = language.get(s);
            String decrypted = breakForLanguage(encrypted, words);
            int currCount = countWords(decrypted, words);
            if(maxCount < currCount) {
                maxCount = currCount;
                finaldecrypted = decrypted;
                finalLanguage = s;
            }
        }
        System.out.println("Final Language is " + finalLanguage);
        return finaldecrypted;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource("data/secretmessage3.txt");
        String encrypted = fr.asString();

        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> language = new HashMap<>();
        for(File f : dr.selectedFiles()) {
            FileResource frDic = new FileResource(f);
            String languageName = f.getName();
            language.put(languageName, readDictionary(frDic));
        }
        String decrypted = breakForAllLangs(encrypted, language);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        VigenereBreaker obj = new VigenereBreaker();
        obj.breakVigenere();
    }
}
