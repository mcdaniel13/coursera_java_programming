import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;


    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna) {
        codonMap.clear();
        for (int i = 0; i < (dna.length() - start) / 3; ++i) {
            String checkDNA = dna.substring(start + i * 3, start + i * 3 + 3);
            if(codonMap.containsKey(checkDNA))
                codonMap.put(checkDNA, codonMap.get(checkDNA) + 1);
            else
                codonMap.put(checkDNA, 1);
        }
    }

    private String getMostCommonCodon() {
        int maxCount = 0;
        String maxCountCodon = null;
        for (String s : codonMap.keySet()) {
            if (maxCount < codonMap.get(s)) {
                maxCount = codonMap.get(s);
                maxCountCodon = s;
            }
        }

        return maxCountCodon;
    }

    private void printCodonCounts(int start, int end) {
        for (String s : codonMap.keySet()) {
            if(codonMap.get(s) >= start && codonMap.get(s) <= end) {
                System.out.println(s + " " + codonMap.get(s));
            }
        }
    }

    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();

        int start = 1;
        int end = 5;

        buildCodonMap(0, dna);

        System.out.println("Reading frame starting with 0 results in "
                            + codonMap.size() + " unique codons");
        System.out.println("and most common codon is " + getMostCommonCodon()
                            + " with count " + codonMap.get(getMostCommonCodon()));
        System.out.println("Counts of codons between " + start + " and "
                            + end + "inclusive are: ");
        printCodonCounts(start, end);
    }

    public static void main(String[] arg) {
        CodonCount obj = new CodonCount();
        obj.tester();
    }
}
