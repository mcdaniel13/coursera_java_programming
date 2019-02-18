public class CaesarCipherTwo {
    private CaesarCipher odd;
    private CaesarCipher even;


    public CaesarCipherTwo(int key1, int key2) {
        odd = new CaesarCipher(key1);
        even = new CaesarCipher(key2);
    }

    public String encryptTwoKeys(String input) {
        String oddEncrypted = odd.encrypt(input);
        String evenEncrypted = even.encrypt(input);
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

    private int getKey(int maxIndex) {
        int descriptedKey = maxIndex - 4;

        if (maxIndex < 4)
            descriptedKey = 26 - (4 - maxIndex);

        return descriptedKey;
    }

    public String decryptTwoKeys(String input, int oddMaxIndex, int evenMaxIndex) {
        int key1 = 26 - getKey(oddMaxIndex);
        int key2 = 26 - getKey(evenMaxIndex);

        CaesarCipherTwo obj2 = new CaesarCipherTwo(key1, key2);
        return obj2.encryptTwoKeys(input);
    }

}
