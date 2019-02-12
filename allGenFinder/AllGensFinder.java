import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author Moongee Cho
 * @version 02/07/2019
 */
public class AllGensFinder {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while(currIndex != -1) {
            if((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int where) {
        String dnaUpper = dna.toUpperCase();
        int startIndex = dnaUpper.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dnaUpper, startIndex, "TAA");
        int tagIndex = findStopCodon(dnaUpper, startIndex, "TAG");
        int tgaIndex = findStopCodon(dnaUpper, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        if(minIndex + 3 > dnaUpper.length()) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            };
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
        }
        
        return geneList;
    }
    
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()) {
            System.out.println(g);
        }
    }
    
    public int howMany(String stringa, String stringb) {
        int startIndex = stringb.indexOf(stringa);
        int count = 0;
        while (startIndex != -1) {
            count++;
            startIndex = stringb.indexOf(stringa, startIndex + stringa.length());
        }
        
        return count;
    }
    
    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            };
            count++;
            startIndex = dna.indexOf(currentGene, startIndex) + 
                         currentGene.length();
        }
        return count;
    }
    
    public float cgRatio(String dna) {
        String dnaUpper = dna.toUpperCase();
        int cgCount = 0;
        
        for(char c: dnaUpper.toCharArray()) {
            if(c == 'C' || c== 'G') {
                cgCount++;
            }
        }
        
        return (float) cgCount / dna.length();
    }
    
    public int countCTG(String dna) {
        String dnaUpper = dna.toUpperCase();
        int currIndex = 0;
        int ctgCount = 0;
        while(true) {
            currIndex = dna.indexOf("CTG", currIndex);
            if(currIndex == -1) {
                break;
            } else {
                ctgCount++;
                currIndex = currIndex + 3;
            }
        }
        
        return ctgCount;
    }
    
    public void processGenes(StorageResource sr) {
        System.out.println("========== LONG_STRING ==========");        
        int countLongString = 0;
        for(String str: sr.data()) {
            if(str.length() > 60) {
                System.out.println(str);
                countLongString++;
            }
        }
        System.out.println("Number of Strings Longer than 60 is " 
                            + countLongString);
                            
        System.out.println("========== CG_RATIO ==========");
        int countCgRatio = 0;
        for(String str: sr.data()) {
            if(cgRatio(str) > 0.35) {
                System.out.println(str);
                countCgRatio++;
            }
        }
        System.out.println("Number of CgRatio higher than 0.35 is " 
                            + countCgRatio);
        
        System.out.println("========== LONGEST STR ==========");
        int maxStrLength = 0;
        String maxString = "";
        for(String str: sr.data()) {
            if(str.length() > maxStrLength) {
                maxStrLength = str.length();
                maxString = str;
            }
        }
        System.out.println("Longest String is " + maxString + " and its Length is " + maxString.length());
        
    }
    
    public void testOnProcessGenes() {
        URLResource fr = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String source = fr.asString();
        System.out.println("========== COUNT CTG ==========");
        System.out.println("Total CTG Codons are " + countCTG(source));
        StorageResource genes = getAllGenes(source);
        int countGenes = 0;
        for (String g: genes.data()) {
            System.out.println(g);
            countGenes++;
        }
        System.out.println("Number of total genes is " + countGenes);
        processGenes(genes);
    }    
}


