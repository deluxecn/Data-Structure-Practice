import java.util.Comparator;

/**
 * AlphaFreq class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 */
public class AlphaFreq implements Comparator<Word> {
    @Override
    public int compare(Word o1, Word o2) {
        int ret = o1.getWord().compareTo(o2.getWord());
        if (ret != 0) {
            return ret;
        } else {
            return o1.getFrequency() - o2.getFrequency();
        }
    }
}
