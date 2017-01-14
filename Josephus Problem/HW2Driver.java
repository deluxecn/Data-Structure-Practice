/**
 * Solve Josephus Problem
 */
public class HW2Driver {

    /**
     * A simple test program.
     * @param args arguments
     */
    public static void main(String[] args) {
        // the size and rotation values to be changed for testing
        int size = 10000;
        int rotation = 69;

        Josephus game = new Josephus();
        Stopwatch timer1 = new Stopwatch();
        System.out.println("Survivor's position: " + game.playWithAD(size, rotation));
        System.out
                .println("Computing time with ArrayDeque used as Queue/Deque: " + timer1.elapsedTime() + " millisec.");

        Stopwatch timer2 = new Stopwatch();
        System.out.println("Survivor's position: " + game.playWithLL(size, rotation));
        System.out
                .println("Computing time with LinkedList used as Queue/Deque: " + timer2.elapsedTime() + " millisec.");

        Stopwatch timer3 = new Stopwatch();
        System.out.println("Survivor's position: " + game.playWithLLAt(size, rotation));
        System.out
                .println("Computing time with LinkedList (remove with index) : " + timer3.elapsedTime() + " millisec.");
    }

}
