import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * DirectoryDriver class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */
public class DirectoryDriver {

    /**
     * add button.
     */
    private JButton addButton;
    /**
     * delete button.
     */
    private JButton deleteButton;
    /**
     * id button.
     */
    private JButton idButton;
    /**
     * first name button.
     */
    private JButton firstNameButton;
    /**
     * last name button.
     */
    private JButton lastNameButton;

    /**
     * text field buttons.
     */
    private JTextField tf1, tf2, tf3, tf4, tf5, tf6;

    /**
     * text area button.
     */
    private JTextArea ta;

    /**
     * Directory dir.
     */
    private Directory dir = new Directory();

    /**
     * Constructor for DirectoryDriver.
     */
    public DirectoryDriver() {
        JFrame frame = new JFrame("Student Directory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        ActionListener listener = new MyActionListener();
        KeyListener keyListener = new MyKeyListener();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER));

        TitledBorder title1 = BorderFactory.createTitledBorder("Add a new student");
        TitledBorder title2 = BorderFactory.createTitledBorder("Delete a student");
        TitledBorder title3 = BorderFactory.createTitledBorder("Search student(s)");
        TitledBorder title4 = BorderFactory.createTitledBorder("Results");
        panel1.setBorder(title1);
        panel2.setBorder(title2);
        panel3.setBorder(title3);
        panel4.setBorder(title4);

        JLabel label1 = new JLabel("First Name:");
        JLabel label2 = new JLabel("Last Name:");
        JLabel label3 = new JLabel("Andrew ID:");
        JLabel label4 = new JLabel("Phone Number:");
        JLabel label5 = new JLabel("Andrew ID:");
        JLabel label6 = new JLabel("Search Key:");

        tf1 = new JTextField("", 8);
        tf2 = new JTextField("", 8);
        tf3 = new JTextField("", 8);
        tf4 = new JTextField("", 8);
        tf5 = new JTextField("", 15);
        tf6 = new JTextField("", 15);
        ta = new JTextArea(20, 70);
        ta.setEditable(false);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        idButton = new JButton("By Andrew ID");
        firstNameButton = new JButton("By First Name");
        lastNameButton = new JButton("By Last Name");

        tf1.addActionListener(listener);
        tf2.addActionListener(listener);
        tf3.addActionListener(listener);
        tf4.addActionListener(listener);
        tf5.addActionListener(listener);
        tf6.addKeyListener(keyListener);
        addButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        idButton.addActionListener(listener);
        firstNameButton.addActionListener(listener);
        lastNameButton.addActionListener(listener);

        panel1.add(label1);
        panel1.add(tf1);
        panel1.add(label2);
        panel1.add(tf2);
        panel1.add(label3);
        panel1.add(tf3);
        panel1.add(label4);
        panel1.add(tf4);
        panel1.add(addButton);

        panel2.add(label5);
        panel2.add(tf5);
        panel2.add(deleteButton);

        panel3.add(label6);
        panel3.add(tf6);
        panel3.add(idButton);
        panel3.add(firstNameButton);
        panel3.add(lastNameButton);

        JScrollPane scroller = new JScrollPane(ta);
        panel4.add(scroller);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);

        frame.setContentPane(panel);
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                tf6.requestFocus();
            }
        });
        frame.setVisible(true);
    }

    /**
     * Private nested class used to provide KeyPressed() method.
     * @author Luxiao Ding
     */
    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            String searchKey = tf6.getText();
            if (e.getSource() == tf6 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (searchKey == null || searchKey.length() == 0) {
                    ta.append("Error: Search key missing!\n\n");
                } else if (dir.searchByAndrewId(searchKey) != null) {
                    ta.append("Match. Searching result(s):\n");
                    ta.append(dir.searchByAndrewId(searchKey).toString());
                    ta.append("\n\n");
                    tf6.setText(null);
                } else {
                    ta.append("No Matches. No entries for this Andrew ID: ");
                    ta.append(searchKey);
                    ta.append("\n\n");
                    tf6.setText(null);
                    return;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * Private nested class used to provide actionPerformed() method.
     * @author Luxiao Ding
     */
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = tf1.getText().trim();
            String lastName = tf2.getText().trim();
            String andrewId1 = tf3.getText().trim();
            String phoneNumber = tf4.getText().trim();
            String andrewId2 = tf5.getText().trim();
            String searchKey = tf6.getText().trim();
            if (e.getSource() == addButton) {
                if (andrewId1 == null || andrewId1.length() == 0) {
                    ta.append("Error: Andrew ID missing!\n\n");
                } else if (firstName == null || firstName.length() == 0) {
                    ta.append("Error: First Name missing!\n\n");
                } else if (lastName == null || lastName.length() == 0) {
                    ta.append("Error: Last Name missing!\n\n");
                } else if (phoneNumber == null || phoneNumber.length() == 0) {
                    ta.append("Error: Phone Number missing!\n\n");
                } else if (dir.searchByAndrewId(andrewId1) == null) {
                    Student s = new Student(andrewId1);
                    s.setFirstName(firstName);
                    s.setLastName(lastName);
                    s.setPhoneNumber(phoneNumber);
                    dir.addStudent(s);
                    ta.append("Add a new entry:\n");
                    ta.append(s.toString());
                    ta.append("\n\n");
                    tf1.setText(null);
                    tf2.setText(null);
                    tf3.setText(null);
                    tf4.setText(null);
                } else {
                    ta.append("Error: Data already contains an entry for this Andrew ID!\n\n");
                    return;
                }
            }
            if (e.getSource() == deleteButton) {
                if (andrewId2 == null || andrewId2.length() == 0) {
                    ta.append("Error: Andrew ID missing!\n\n");
                } else if (dir.searchByAndrewId(andrewId2) != null) {
                    ta.append("Match. The follwing entry was deleted:\n");
                    ta.append(dir.searchByAndrewId(andrewId2).toString());
                    ta.append("\n\n");
                    dir.deleteStudent(andrewId2);
                    tf5.setText(null);
                } else {
                    ta.append("No Matches. No entries for this Andrew ID: ");
                    ta.append(andrewId2);
                    ta.append("\n\n");
                    tf5.setText(null);
                    return;
                }
            }
            if (e.getSource() == idButton) {
                if (searchKey == null || searchKey.length() == 0) {
                    ta.append("Error: Search key missing!\n\n");
                } else if (dir.searchByAndrewId(searchKey) != null) {
                    ta.append("Match. Found entry: \n");
                    ta.append(dir.searchByAndrewId(searchKey).toString());
                    ta.append("\n\n");
                    tf6.setText(null);
                } else {
                    ta.append("No Matches. No entries for this Andrew ID: ");
                    ta.append(searchKey);
                    ta.append("\n\n");
                    tf6.setText(null);
                    return;
                }
            }
            if (e.getSource() == firstNameButton) {
                if (searchKey == null || searchKey.length() == 0) {
                    ta.append("Error: Search key missing!\n\n");
                } else if (dir.searchByFirstName(searchKey).size() != 0) {
                    ta.append("Match. Searching result(s):");
                    List<Student> list = dir.searchByFirstName(searchKey);
                    for (Student i: list) {
                        ta.append("\n");
                        ta.append(i.toString());
                    }
                    ta.append("\n\n");
                    tf6.setText(null);
                } else {
                    ta.append("No Matches. No entries for this first name: ");
                    ta.append(searchKey);
                    ta.append("\n\n");
                    tf6.setText(null);
                    return;
                }
            }
            if (e.getSource() == lastNameButton) {
                if (searchKey == null || searchKey.length() == 0) {
                    ta.append("Error: Search key missing!\n\n");
                } else if (dir.searchByLastName(searchKey).size() != 0) {
                    ta.append("Match. Searching result(s):");
                    List<Student> list = dir.searchByLastName(searchKey);
                    for (Student i: list) {
                        ta.append("\n");
                        ta.append(i.toString());
                    }
                    ta.append("\n\n");
                    tf6.setText(null);
                } else {
                    ta.append("No Matches - No entries for this last name: ");
                    ta.append(searchKey);
                    ta.append("\n\n");
                    tf6.setText(null);
                    return;
                }
            }
        }

    }

    /**
     * Main method that instantiates DriverDirectory object.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DirectoryDriver dd = new DirectoryDriver();
        if (args.length > 0) {
            boolean header = true;
            BufferedReader br = null;
            String line = null;
            try {
                br = new BufferedReader(new FileReader(args[0]));
                while ((line = br.readLine()) != null) {
                    if (header) {
                        header = false;
                        continue;
                    }
                    String[] str = line.split(",");
                    Student s = new Student(str[2].substring(1, str[2].length() - 1));
                    s.setFirstName(str[0].substring(1, str[0].length() - 1));
                    s.setLastName(str[1].substring(1, str[1].length() - 1));
                    s.setPhoneNumber(str[3].substring(1, str[3].length() - 1));
                    try {
                        dd.dir.addStudent(s);
                    } catch (IllegalArgumentException ex) {
                        continue;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
