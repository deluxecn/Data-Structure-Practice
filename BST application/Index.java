import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Index class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 */
public class Index {

    /**
     * Build index with a file.
     * @param fileName String value of the filename
     * @return BST object
     */
    public BST<Word> buildIndex(String fileName) {
        return buildIndex(fileName, null);
    }

    /**
     * Build index with a file and specified comparator.
     * @param fileName String value of the filename
     * @param comparator specified comparator
     * @return BST object
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        BST<Word> bst = new BST<Word>(comparator);
        if (fileName == null) {
            return bst;
        }
        Scanner sc = null;
        String line = null;
        int lines = 0;
        List<Word> list = new ArrayList<Word>();
        try {
            sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                lines++;
                String[] words = line.split("\\W");
                for (String w : words) {
                    if (isWord(w)) {
                        if (comparator instanceof IgnoreCase) {
                            w = w.toLowerCase();
                        }
                        Word newWord = new Word(w);
                        boolean found = false;
                        for (Word i : list) {
                            if (i.compareTo(newWord) == 0) {
                                i.addToIndex(lines);
                                i.setFrequency(i.getFrequency() + 1);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            newWord.addToIndex(lines);
                            list.add(newWord);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        for (Word word : list) {
            bst.insert(word);
        }
        return bst;
    }

    /**
     * Build index by ArrayList and comparator.
     * @param list input ArrayList
     * @param comparator specified comparator
     * @return BST object
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        BST<Word> bst = new BST<Word>(comparator);
        if (list == null) {
            return bst;
        }
        for (Word w : list) {
            bst.insert(w);
        }
        return bst;
    }

    /**
     * Sort the words by alpha.
     * @param tree input BST object
     * @return sorted ArrayList
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<Word>();
        if (tree == null) {
            return list;
        }
        for (Word w : tree) {
            list.add(w);
        }
        Collections.sort(list, new Comparator<Word>() {
            @Override
            public int compare(Word w1, Word w2) {
                return w1.compareTo(w2);
            }
        });
        return list;
    }

    /**
     * Sort the words by frequency.
     * @param tree input BST object
     * @return sorted ArrayList
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<Word>();
        if (tree == null) {
            return list;
        }
        for (Word w : tree) {
            list.add(w);
        }
        Collections.sort(list, new Frequency());
        return list;
    }

    /**
     * Sort the words by highest frequency.
     * @param tree input BST object
     * @return sorted ArrayList
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> list = new ArrayList<Word>();
        if (tree == null) {
            return list;
        }
        int max = 1;
        for (Word w : tree) {
            if (w.getFrequency() > max) {
                max = w.getFrequency();
            }
        }

        for (Word w : tree) {
            if (w.getFrequency() == max) {
                list.add(w);
            }
        }
        return list;
    }

    /**
     * Check if a String is a valid word.
     * @param input input Sting value
     * @return boolean value of whether it's a valid word.
     */
    private boolean isWord(String input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isLetter(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
