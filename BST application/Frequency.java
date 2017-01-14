import java.util.Comparator;

/**
 * Frequency class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 */
public class Frequency implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o2.getFrequency() - o1.getFrequency();
    }

}
