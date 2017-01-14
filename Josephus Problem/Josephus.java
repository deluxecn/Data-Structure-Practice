import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Solve Josephus problem with different data structures and different
 * algorithms and compare running times
 *
 * @author Luxiao Ding
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO your implementation here
        if (size < 1 || rotation <= 0) {
            throw new RuntimeException();
        }
        Deque<Integer> dq = new ArrayDeque<Integer>();
        for (int i = 1; i <= size; i++) {
            dq.add(i);
        }
        int rot = 0;
        while (dq.size() > 1) {
            if (rotation > dq.size()) {
                rot = rotation % dq.size();
                if (rot == 0) {
                    rot = dq.size();
                }
            } else {
                rot = rotation;
            }
            for (int i = 0; i < rot - 1; i++) {
                dq.offer(dq.poll());
            }
            dq.poll();
            // System.out.println("poll: " + dq.poll());
        }
        return dq.peek();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO your implementation here
        if (size < 1 || rotation <= 0) {
            throw new RuntimeException();
        }
        Deque<Integer> dq = new LinkedList<Integer>();
        for (int i = 1; i <= size; i++) {
            dq.add(i);
        }
        int rot = 0;
        while (dq.size() > 1) {
            if (rotation > dq.size()) {
                rot = rotation % dq.size();
                if (rot == 0) {
                    rot = dq.size();
                }
            } else {
                rot = rotation;
            }
            for (int i = 0; i < rot - 1; i++) {
                dq.offer(dq.poll());
            }
            dq.poll();
            // System.out.println("poll: " + dq.poll());
        }
        return dq.peek();
    }

    /**
     * Uses LinkedList class to find the survivor's position.
     * However, do NOT use the LinkedList as Queue/Deque
     * Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be executed in the circle
     *
     * Note: Think carefully about this method!!
     * When in doubt, please visit one of the office hours!!
     *
     * @param size Number of people in the circle that is bigger than 0
     * @param rotation Elimination order in the circle. The value has to be greater than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO your implementation here
        if (size < 1 || rotation <= 0) {
            throw new RuntimeException();
        }
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }
        int a = 0, rot = 0;
        while (list.size() > 1) {
            if (rotation > list.size()) {
                rot = rotation % list.size();
                if (rot == 0) {
                    rot = list.size();
                }
            } else {
                rot = rotation;
            }
            a = (a + rot - 1) % list.size();
            list.remove(a);
            if (a == list.size()) {
                a = 0;
            }
        }
        return list.get(0);
    }

}
