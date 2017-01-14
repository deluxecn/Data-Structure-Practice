import java.util.HashSet;
import java.util.Set;

/**
 * Word class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 */
public class Word implements Comparable<Word> {
    /**
     * String value.
     */
    private String word;
    /**
     * Indices set.
     */
    private Set<Integer> index;
    /**
     * Frequency of the word.
     */
    private int frequency;

    /**
     * Constructor.
     * @param w input String
     */
    public Word(String w) {
        word = w;
        index = new HashSet<Integer>();
        frequency = 1;
    }

    /**
     * Get the String value of the word.
     * @return String value of the word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the index set.
     * @return index set.
     */
    public Set<Integer> getIndex() {
        return new HashSet<Integer>(index);
    }

    /**
     * Get the frequency value.
     * @return frequency value.
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * Set the String value.
     * @param w input String value.
     */
    public void setWord(String w) {
        word = w;
    }

    /**
     * Set the frequency.
     * @param freq input value.
     */
    public void setFrequency(int freq) {
        frequency = freq;
    }

    /**
     * Add a line number to index set.
     * @param line input value.
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word).append(" ").append(frequency).append(" ")
                .append(index.toString());
        return sb.toString();
    }

    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.getWord());
    }

}
