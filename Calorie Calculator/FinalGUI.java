import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * FinalGUI class for 08-671 final exam.
 * @author Luxiao Ding (id: luxiaod) 10/20/2016
 */
public class FinalGUI {
    private JTextField tf1, tf2, tf3, tf4;
    private JButton btn;
    private JTextArea ta;
    private HashMap<String, String> activityMap;
    private HashMap<String, Integer> calorieMap;
    private List<FinalData> list;
    private static final String header = "        Date          Activity            Hours              Calories\n";

    public FinalGUI() {
        list = new LinkedList<FinalData>();
        ActionListener listener = new MyActionListener();
        activityMap = new HashMap<String, String>();
        activityMap.put("aerobics", "Aerobics");
        activityMap.put("basketball", "Basketball");
        activityMap.put("cricket", "Cricket");
        activityMap.put("cycling", "Cycling");
        activityMap.put("golf", "Golf");
        activityMap.put("rugby", "Rugby");
        activityMap.put("running", "Running");
        activityMap.put("soccer", "Soccer");
        activityMap.put("swimming", "Swimming");
        activityMap.put("walking", "Walking");
        
        calorieMap = new HashMap<String, Integer> ();
        calorieMap.put("aerobics", 457);
        calorieMap.put("basketball", 422);
        calorieMap.put("cricket", 352);
        calorieMap.put("cycling", 281);
        calorieMap.put("golf", 317);
        calorieMap.put("rugby", 704);
        calorieMap.put("running", 563);
        calorieMap.put("soccer", 493);
        calorieMap.put("swimming", 422);
        calorieMap.put("walking", 211);

        // set.add();
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);

        JFrame frame = new JFrame("Daily Calorie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 600);

        JPanel pane = new JPanel();
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();
        JPanel pane3 = new JPanel();
        JPanel pane4 = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane1.setLayout(new FlowLayout(FlowLayout.CENTER));
        pane2.setLayout(new FlowLayout(FlowLayout.CENTER));
        pane3.setLayout(new FlowLayout(FlowLayout.CENTER));
        pane4.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel label1 = new JLabel("Date:");
        JLabel label2 = new JLabel("Activity:");
        JLabel label3 = new JLabel("Hours:");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date d = new Date();
        tf1 = new JTextField(sdf.format(d), 8);
        tf2 = new JTextField("", 15);
        tf3 = new JTextField("", 8);
        tf4 = new JTextField("", 40);
        tf4.setEditable(false);

        btn = new JButton("I did this!");
        btn.addActionListener(listener);

        ta = new JTextArea(25, 75);
        ta.setFont(font);
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.append(header);

        pane1.add(label1);
        pane1.add(tf1);
        pane1.add(label2);
        pane1.add(tf2);
        pane1.add(label3);
        pane1.add(tf3);

        pane2.add(btn);

        pane3.add(tf4);

        JScrollPane scroller = new JScrollPane(ta);
        pane4.add(scroller);

        pane.add(pane1);
        pane.add(pane2);
        pane.add(pane3);
        pane.add(pane4);

        frame.setContentPane(pane);
        frame.setVisible(true);
    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == btn){
                String strDate = tf1.getText() == null ? null : tf1.getText().trim();
                String strActivity = tf2.getText() == null ? null : tf2.getText().trim().toLowerCase();
                String strHours = tf3.getText() == null ? null : tf3.getText().trim();
                int year = 0, month = 0, day = 0;
                int hours = 0, calories = 0;
                boolean error = false;
                StringBuilder sb = new StringBuilder();
                if (strDate == null || strDate.length() == 0) {
                    error = true;
                    sb.append("missing date");
                } else {
                    String[] str = strDate.split("/");
                    if (str.length != 3) {
                        error = true;
                        sb.append("invald date");
                    } else {
                        try {
                            year = Integer.parseInt(str[2]);
                            month = Integer.parseInt(str[0]);
                            day = Integer.parseInt(str[1]);
                        } catch (NumberFormatException ex) {
                            error = true;
                            sb.append("invald date");
                        }
                        if (year < 0 || month < 1 || month > 12 || day < 1
                                || day > 31) {
                            error = true;
                            sb.append("invald date");
                        }
                    }
                } 
                if (strActivity == null || strActivity.length() == 0) {
                    if (error) {
                        sb.append(", missing activity");
                    } else {
                        error = true;
                        sb.append("missing activity");
                    }
                } else {
                    if (!activityMap.containsKey(strActivity)) {
                        if (error) {
                            sb.append(", invalid activity");
                        } else {
                            error = true;
                            sb.append("invalid activity");
                        }
                    }
                }
                if (strHours == null || strHours.length() == 0) {
                    if (error) {
                        sb.append(", missing hours");
                    } else {
                        error = true;
                        sb.append("missing hours");
                    }
                } else {
                    try {
                        hours = Integer.parseInt(strHours);
                    } catch (NumberFormatException ex) {
                        if (error) {
                            sb.append(", invald hours");
                        } else {
                            error = true;
                            sb.append("invald hours");
                        }
                    }
                    if (hours < 1 || hours > 20) {
                        if (error) {
                            sb.append(", invald hours");
                        } else {
                            error = true;
                            sb.append("invald hours");
                        }
                    }
                }
                if (error) {
                    tf4.setText(sb.toString());
                    return;
                }
                Calendar c = Calendar.getInstance();
                c.set(year, month - 1, day);
                Date d = new Date(c.getTimeInMillis());
                calories = hours * calorieMap.get(strActivity);
                FinalData fd = new FinalData(d, activityMap.get(strActivity),
                        hours, calories);
                list.add(fd);
                ta.setText(null);
                ta.append(header);
                Collections.sort(list);
                String lastDate = null;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                int sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    if (lastDate == null) {
                        lastDate = list.get(i).dateString();
                    }
                    if (lastDate.equals(list.get(i).dateString())) {
                        sum += list.get(i).getCalories();
                    } else {
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append("  ").append(lastDate)
                                .append("          Daily Total                  ")
                                .append(String.format("%,d", sum))
                                .append("\n\n");
                        ta.append(sb1.toString());
                        sum = list.get(i).getCalories();
                        lastDate = list.get(i).dateString();
                    }
                    ta.append(list.get(i).toString());
                    ta.append("\n");
                    if (i == list.size() - 1) {
                        StringBuilder sb1 = new StringBuilder();
                        sb1.append("  ").append(lastDate)
                                .append("          Daily Total                  ")
                                .append(String.format("%,d  ", sum))
                                .append("\n");
                        ta.append(sb1.toString());
                    }
                }
                tf2.setText(null);
                tf3.setText(null);
            }
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new FinalGUI();
    }

}
