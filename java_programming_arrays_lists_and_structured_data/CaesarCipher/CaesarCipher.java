/* author: Moongee Cho
   date: 02/15/2019
 */

import edu.duke.*;
import java.util.*;


public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
                alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key)+
                alphabetLower.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            Boolean isLowerCase = Character.isLowerCase(currChar);
            if(isLowerCase)
                idx = alphabetLower.indexOf(currChar);
                //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                if(isLowerCase)
                    newChar = shiftedAlphabetLower.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        String oddEncrypted = encrypt(input, key1);
        String evenEncrypted = encrypt(input, key2);
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            if((i + 1) % 2 == 1) {// odd index
                char currChar = oddEncrypted.charAt(i);
                encrypted.append(currChar);
            } else {
                char currChar = evenEncrypted.charAt(i);
                encrypted.append(currChar);
            }

        }

        return encrypted.toString();
    }

    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        Scanner S = new Scanner(System.in);
        int key = S.nextInt();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26 - key);
        System.out.println(decrypted);
    }

    public void testEncryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        Scanner S = new Scanner(System.in);
        int key1 = S.nextInt();
        int key2 = S.nextInt();
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        System.out.println(decrypted);
    }

    public static void main(String[] args) {
        CaesarCipher obj = new CaesarCipher();
        //obj.testCaesar();
        obj.testEncryptTwoKeys();
    }

}

