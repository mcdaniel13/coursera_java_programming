import edu.duke.FileResource;

import java.util.Scanner;

public class CaesarBreaker {

    public int[] countLetters (String message) {
        int[] counts = new int[26];
        String messageLower = message.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char c : messageLower.toCharArray()) {
            if(Character.isAlphabetic(c))
                counts[alphabet.indexOf(c)]++;
        }

        return counts;
    }

    public int indexOfMax(int[] values) {
        int maxIndex = 0;
        int maxVal = values[0];
        for (int i = 1; i < values.length; ++i) {
            if(maxVal < values[i]) {
                maxIndex = i;
                maxVal = values[i];
            }
        }

        return maxIndex;
    }

    public int getKey(String message) {
        int[]counts = countLetters(message);
        int maxIndex = indexOfMax(counts);
        int descriptedKey = maxIndex - 4;

        if (maxIndex < 4)
            descriptedKey = 26 - (4 - maxIndex);

        System.out.println("key = " + descriptedKey);

        return descriptedKey;
    }

    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }

        return sb.toString();
    }

    public String decrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder(message);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key)+
                alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key)+
                alphabetLower.substring(0,key);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            Boolean isLowerCase = Character.isLowerCase(currChar);
            if(isLowerCase)
                idx = alphabetLower.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(isLowerCase)
                    newChar = shiftedAlphabetLower.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }

    public String combineTwoStrings(String odd, String even) {
        StringBuilder decryptedMsgBuilder = new StringBuilder(odd + even);

        for (int i = 0; i < odd.length(); i ++) {
            decryptedMsgBuilder.setCharAt(2 * i, odd.charAt(i));
        }

        for (int i = 0; i < even.length(); i ++) {
            decryptedMsgBuilder.setCharAt(2 * i + 1, even.charAt(i));
        }

        return decryptedMsgBuilder.toString();
    }

    public String decryptTwoKeys(String encrypted) {
        String oddEncrypted = halfOfString(encrypted, 0);
        String evenEncrypted = halfOfString(encrypted, 1);

        //System.out.println("oddEncrypted : " + oddEncrypted);
        //System.out.println("evenEncrypted : " + evenEncrypted);
        //System.out.println("combinedEncrypted : " + combineTwoStrings(oddEncrypted, evenEncrypted));

        String oddDecrypted = decrypt(oddEncrypted, 26 - getKey(oddEncrypted));
        String evenDecrypted = decrypt(evenEncrypted, 26 - getKey(evenEncrypted));

        //System.out.println("oddDecrypted : " + oddDecrypted);
        //System.out.println("evenDecrypted : " + evenDecrypted);
        //System.out.println("combinedDecrypted : " + combineTwoStrings(oddDecrypted, evenDecrypted));

        return combineTwoStrings(oddDecrypted, evenDecrypted);
    }

    public void testDecryptTwoKeys() {
//        Scanner S = new Scanner(System.in);
//        String message = S.nextLine();
        FileResource fr = new FileResource();
        String message = fr.asString();
        //System.out.println("OriginalEncryptedMessage : " + message);
        String decryptedMessage = decryptTwoKeys(message);
        System.out.println("FinaldecryptedMessage : " + decryptedMessage);
    }
    public static void main(String arg[]) {
        CaesarBreaker obj = new CaesarBreaker();
        obj.testDecryptTwoKeys();
    }
}
