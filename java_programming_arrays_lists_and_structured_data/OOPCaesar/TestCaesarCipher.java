
import edu.duke.FileResource;

public class TestCaesarCipher {
    private int[] countLetters (String message) {
        int[] counts = new int[26];
        String messageLower = message.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (char c : messageLower.toCharArray()) {
            if(Character.isAlphabetic(c))
                counts[alphabet.indexOf(c)]++;
        }

        return counts;
    }

    private int indexOfMax(int[] values) {
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

    public void breakCaesarCipher(String input, CaesarCipher encrypted) {
        int[] counts = countLetters(input);
        int maxIndex = indexOfMax(counts);
        String decrypted = encrypted.decrypt(input, maxIndex);
        System.out.println("decrypted : " + decrypted);
    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher obj = new CaesarCipher(15);
        String encrypted = obj.encrypt(input);
        System.out.println("encrypted : " + encrypted);

        breakCaesarCipher(encrypted, obj);
    }

    public static void main(String arg[]) {
        TestCaesarCipher obj = new TestCaesarCipher();
        obj.simpleTests();
    }
}
