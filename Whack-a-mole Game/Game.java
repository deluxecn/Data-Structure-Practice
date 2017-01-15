import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Game class.
 * @author Luxiao Ding 
 */
public class Game {
    /**
     * Start button.
     */
    private JButton startButton;
    /**
     * Buttons for moles.
     */
    private JButton[] buttons;
    /**
     * Time and score text fields.
     */
    private JTextField timeTF, scoreTF;
    /**
     * Time.
     */
    private static int time;
    /**
     * Score.
     */
    private static int score;
    /**
     * Down string.
     */
    private static final String DOWN_STRING = "   ";
    /**
     * Up string.
     */
    private static final String UP_STRING = ":-)";
    /**
     * Hit String.
     */
    private static final String HIT_STRING = ":-(";
    /**
     * Up color.
     */
    private static final Color UP_COLOR = Color.GREEN;
    /**
     * Down color.
     */
    private static final Color DOWN_COLOR = Color.GRAY;
    /**
     * Hit color.
     */
    private static final Color HIT_COLOR = Color.RED;
    /**
     * Random number generator.
     */
    private Random random = new Random();

    /**
     * Constructor for Game class.
     */
    public Game() {
        JFrame frame = new JFrame("Whack-a-mole");
        frame.setSize(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();
        pane1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pane2.setLayout(new GridLayout(8, 8));

        JLabel timeL = new JLabel("Time Left:");
        JLabel scoreL = new JLabel("Score:");

        timeTF = new JTextField("", 8);
        timeTF.setEditable(false);
        scoreTF = new JTextField("", 8);
        scoreTF.setEditable(false);

        ActionListener listener = new MyActionListener();
        startButton = new JButton("Start");
        startButton.addActionListener(listener);
        buttons = new JButton[64];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(DOWN_STRING);
            buttons[i].setBackground(DOWN_COLOR);
            buttons[i].setOpaque(true);
            buttons[i].addActionListener(listener);
            pane2.add(buttons[i]);
        }

        pane1.add(startButton);
        pane1.add(timeL);
        pane1.add(timeTF);
        pane1.add(scoreL);
        pane1.add(scoreTF);

        pane.add(pane1);
        pane.add(pane2);

        frame.setContentPane(pane);
        frame.setVisible(true);
    }

    /**
     * Private nested class used to provide actionPerformed() method.
     *
     * @author Luxiao Ding 
     *
     */
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            Thread[] moleThread = new Thread[buttons.length];
            if (e.getSource() == startButton) {
                startButton.setEnabled(false);
                time = 20;
                score = 0;
                timeTF.setText("20");
                scoreTF.setText("0");
                Thread timerThread = new Thread(new Timer());
                timerThread.start();
                for (int i = 0; i < buttons.length; i++) {
                    moleThread[i] = new Thread(new Mole(buttons[i]));
                    moleThread[i].start();
                }
            }
            if (time > 0) {
                for (int i = 0; i < buttons.length; i++) {
                    if (e.getSource() == buttons[i]) {
                        if (buttons[i].getText().equals(UP_STRING)) {
                            buttons[i].setText(HIT_STRING);
                            buttons[i].setBackground(HIT_COLOR);
                            score++;
                            scoreTF.setText(Integer.toString(score));
                        }
                        break;
                    }
                }
            }

        }
    }

    /**
     * Mole class for every mole.
     * @author Luxiao Ding (id: luxiaod)
     */
    private class Mole implements Runnable {
        /**
         * The button this mole present.
         */
        private JButton button;

        /**
         * Constructor.
         * @param button the button this mole connects with.
         */
        public Mole(JButton button) {
            this.button = button;
            button.setText(DOWN_STRING);
            button.setBackground(DOWN_COLOR);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                while (time > 0) {
                    if (button.getText().equals(DOWN_STRING)) {
                        int randomUpInterval = random.nextInt(8000);
                        Thread.sleep(randomUpInterval);
                        if (time > 0) {
                            button.setText(UP_STRING);
                            button.setBackground(UP_COLOR);
                            int randomUpTime = random.nextInt(4000) + 1;
                            Thread.sleep(randomUpTime);
                        }
                        button.setText(DOWN_STRING);
                        button.setBackground(DOWN_COLOR);
                        Thread.sleep(2000);
                    }
                }
                button.setText(DOWN_STRING);
                button.setBackground(DOWN_COLOR);
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }
        }

    }

    /**
     * Timer class.
     *
     * @author Luxiao Ding 
     */
    private class Timer implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                while (time > 0) {
                    Thread.sleep(1000);
                    time--;
                    timeTF.setText(Integer.toString(time));
                }
                Thread.sleep(5000);
                startButton.setEnabled(true);
                timeTF.setText("");
                scoreTF.setText("");
            } catch (InterruptedException e) {
                throw new AssertionError(e);
            }
        }

    }

    /**
     * @param args command line input.
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Game();
    }

}
