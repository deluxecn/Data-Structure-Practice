import java.util.Comparator;

/**
 * IgnoreCase class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 */
public class IgnoreCase implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o1.getWord().compareToIgnoreCase(o2.getWord());
    }

}
