import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Similarity {
    /**
     * Map that store each words and its frequency.
     */
    private Map<String, BigInteger> map;

    /**
     * Total lines in the document.
     */
    private int lines = 0;

    /**
     * Total valid words in the document.
     */
    private BigInteger words = BigInteger.valueOf(0);

    /**
     * Constructor with the String value.
     * @param string the input string.
     */
    public Similarity(String string) {
        map = new HashMap<String, BigInteger>();
        if (string == null || string.length() == 0) {
            return;
        }
        String str = string.toLowerCase().trim();
        String[] arr = str.split("\\W");
        for (String s : arr) {
            if (isWord(s)) {
                if (map.containsKey(s)) {
                    map.put(s, map.get(s).add(BigInteger.valueOf(1)));
                } else {
                    map.put(s, BigInteger.valueOf(1));
                }
                words = words.add(BigInteger.valueOf(1));
            }
        }
        lines++;
    }
    /**
     * Constructor with the file.
     * @param file input file
     */
    public Similarity(File file) {
        map = new HashMap<String, BigInteger>();
        if (file == null) {
            return;
        }
        String line = null;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String str = line.toLowerCase().trim();
                String[] arr = str.split("\\W");
                for (String s : arr) {
                    if (isWord(s)) {
                        if (map.containsKey(s)) {
                            map.put(s, map.get(s).add(BigInteger.valueOf(1)));
                        } else {
                            map.put(s, BigInteger.valueOf(1));
                        }
                        words = words.add(BigInteger.valueOf(1));
                    }
                }
                lines++;
            }
            br.close();
        } catch (Exception ex) {
        }
    }
    /**
     * Get the number of lines.
     * @return the number of lines.
     */
    public int numOfLines() {
        return lines;
    }
    /**
     * Get the number of words.
     * @return the number of words.
     */
    public BigInteger numOfWords() {
        return words;
    }
    /**
     * Get the number of unique words.
     * @return the number of unique words.
     */
    public int numOfWordsNoDups() {
        return map.size();
    }
    /**
     * Calculate the Euclidean norm of the text.
     * @return the Euclidean norm of the text.
     */
    public double euclideanNorm() {
        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger v : map.values()) {
            sum = sum.add(v.pow(2));
        }
        return Math.sqrt(sum.doubleValue());
    }
    /**
     * Calculate the Euclidean norm of the text in map2.
     * @param map2 the other text.
     * @return the Euclidean norm of the text in map2.
     */
    public double euclideanNorm(Map<String, BigInteger> map2) {
        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger v : map2.values()) {
            sum = sum.add(v.pow(2));
        }
        return Math.sqrt(sum.doubleValue());
    }
    /**
     * Calculate the dot-product of words frequency in map and map2.
     * @param map2 the other text.
     * @return the dot-product of words frequency in map and map2.
     */
    public double dotProduct(Map<String, BigInteger> map2) {
        if (map2 == null || map2.size() == 0 || map.size() == 0) {
            return 0;
        }
        BigInteger ret = BigInteger.valueOf(0);
        for (String word : map.keySet()) {
            if (map2.containsKey(word)) {
                ret = ret.add(map.get(word).multiply(map2.get(word)));
            }
        }
        return ret.doubleValue();
    }
    /**
     * Calculate the cosine similarity of the two text.
     * @param map2 the other text.
     * @return the cosine similarity of the two text.
     */
    public double distance(Map<String, BigInteger> map2) {
        if (map2 == null || map2.size() == 0 || map.size() == 0) {
            return Math.PI / 2;
        }
        double freq1 = euclideanNorm(), freq2 = euclideanNorm(map2);
        double product = dotProduct(map2);

        if ((product / freq1 / freq2) > 1) {
            return 0;
        }

        return Math.acos(product / freq1 / freq2);
    }
    /**
     * Get the map.
     * @return map.
     */
    public Map<String, BigInteger> getMap() {
        return new HashMap<String, BigInteger>(map);
    }
    /**
     * Check if an input is a valid word.
     * @param input input word.
     * @return Whether it is valid.
     */
    private boolean isWord(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!(input.charAt(i) >= 'a' && input.charAt(i) <= 'z')) {
                return false;
            }
        }
        return true;
    }

}
