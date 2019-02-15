/* author: Moongee Cho
   date: 02/16/2019
 */


import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts, String[] stringByLength) {
        for(String s : resource.words()) {
            String sLower = s.toLowerCase();
            int wordLength = sLower.length();

            if(!Character.isLetter(sLower.charAt(s.length() - 1))) {
                wordLength--;
                s = s.substring(0, s.length() - 1);
            }

            if(wordLength >= counts.length)
                wordLength = counts.length - 1;

            counts[wordLength]++;

            if(stringByLength[wordLength] == null)
                stringByLength[wordLength] = s;
            else
                stringByLength[wordLength] = stringByLength[wordLength] + " " + s;
        }
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

    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int counts[] = new int[31];
        String stringByLength[] = new String[31];
        countWordLengths(fr, counts, stringByLength);
        for(int i = 0; i < counts.length; ++i) {
            if(counts[i] != 0)
                System.out.println(counts[i] + " word of length " + i + " : " + stringByLength[i]);
        }
        int maxIndex = indexOfMax(counts);
        System.out.println("The most common word length is " +  maxIndex
                                    + " and it appears " + counts[maxIndex]);
    }

    public static void main(String[] args) {
        WordLengths obj = new WordLengths();
        obj.testCountWordLengths();
    }
}
