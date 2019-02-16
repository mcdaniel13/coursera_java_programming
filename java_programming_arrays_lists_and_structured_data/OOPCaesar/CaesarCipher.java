
public class CaesarCipher {
    private String alphabet;
    private String alphabetLower;
    private String shiftedAlphabet;
    private String shiftedAlphabetLower;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        alphabetLower = alphabet.toLowerCase();
        shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < sb.length(); i++) {
            char currChar = sb.charAt(i);
            int idx = alphabet.indexOf(currChar);
            Boolean isLowerCase = Character.isLowerCase(currChar);
            if(isLowerCase)
                idx = alphabetLower.indexOf(currChar);
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(isLowerCase)
                    newChar = shiftedAlphabetLower.charAt(idx);
                sb.setCharAt(i, newChar);
            }
        }

        return sb.toString();
    }

    private int getKey(int maxIndex) {
        int descriptedKey = maxIndex - 4;

        if (maxIndex < 4)
            descriptedKey = 26 - (4 - maxIndex);

        System.out.println("key = " + descriptedKey);

        return descriptedKey;
    }

    public String decrypt(String input, int maxIndex) {
        int key = 26 - getKey(maxIndex);
        CaesarCipher obj = new CaesarCipher(key);
        return obj.encrypt(input);
    }
}
