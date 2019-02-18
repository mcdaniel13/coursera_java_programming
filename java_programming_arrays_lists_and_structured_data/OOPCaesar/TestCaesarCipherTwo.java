import edu.duke.FileResource;

public class TestCaesarCipherTwo {
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

    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            sb.append(message.charAt(i));
        }

        return sb.toString();
    }

    public void breakCaesarCipher(String input, CaesarCipherTwo encrypted) {
        String oddEncrypted = halfOfString(input, 0);
        String evenEcrypted = halfOfString(input, 1);

        int[] oddCounts = countLetters(oddEncrypted);
        int[] evenCounts = countLetters(evenEcrypted);
        int oddMaxIndex = indexOfMax(oddCounts);
        int evenMaxIndex = indexOfMax(evenCounts);

        String decrypted = encrypted.decryptTwoKeys(input, oddMaxIndex, evenMaxIndex);
        System.out.println("decrypted : " + decrypted);

    }

    public void simpleTests() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo obj = new CaesarCipherTwo(17, 3);
        String encrypted = obj.encryptTwoKeys(input);
        System.out.println("encrypted : " + encrypted);

        breakCaesarCipher(encrypted, obj);
    }

    public static void main(String arg[]) {
        TestCaesarCipherTwo obj = new TestCaesarCipherTwo();
        obj.simpleTests();
    }
}
