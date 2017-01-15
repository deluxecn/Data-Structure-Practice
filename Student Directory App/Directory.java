import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Directory class.
 * @author Luxiao Ding (ID: luxiaod)
 */
public class Directory {

    /**
     * Id to Student hashmap.
     */
    private HashMap<String, Student> idMap;
    /**
     * First name to Student hashmap.
     */
    private HashMap<String, List<Student>> firstNameMap;
    /**
     * Last name to Student hashmap.
     */
    private HashMap<String, List<Student>> lastNameMap;
    /**
     * The number of student in the directory.
     */
    private int mapSize;

    /**
     * Constructor.
     */
    public Directory() {
        idMap = new HashMap<String, Student>();
        firstNameMap = new HashMap<String, List<Student>>();
        lastNameMap = new HashMap<String, List<Student>>();
    }

    /**
     * Add a student to the directory.
     * @param s student
     */
    public void addStudent(Student s) {
        if (s == null || s.getAndrewId() == null || s.getFirstName() == null || s.getLastName() == null) {
            throw new IllegalArgumentException();
        }
        if (idMap.containsKey(s.getAndrewId())) {
            throw new IllegalArgumentException();
        }
        Student clone = s.clone();

        idMap.put(s.getAndrewId(), clone);
        if (firstNameMap.containsKey(s.getFirstName())) {
            firstNameMap.get(s.getFirstName()).add(clone);
        } else {
            List<Student> list = new LinkedList<Student>();
            list.add(clone);
            firstNameMap.put(s.getFirstName(), list);
        }
        if (lastNameMap.containsKey(s.getLastName())) {
            lastNameMap.get(s.getLastName()).add(clone);
        } else {
            List<Student> list = new LinkedList<Student>();
            list.add(clone);
            lastNameMap.put(s.getLastName(), list);
        }
//        idMap.put(s.getAndrewId(), s);
//        if (firstNameMap.containsKey(s.getFirstName())) {
//            firstNameMap.get(s.getFirstName()).add(s);
//        } else {
//            List<Student> list = new LinkedList<Student>();
//            list.add(s);
//            firstNameMap.put(s.getFirstName(), list);
//        }
//        if (lastNameMap.containsKey(s.getLastName())) {
//            lastNameMap.get(s.getLastName()).add(s);
//        } else {
//            List<Student> list = new LinkedList<Student>();
//            list.add(s);
//            lastNameMap.put(s.getLastName(), list);
//        }
//        s = null;
        mapSize++;
    }

    /**
     * Remove one student from the directory.
     * @param andrewId id
     */
    public void deleteStudent(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException();
        }
        if (!idMap.containsKey(andrewId)) {
            throw new IllegalArgumentException();
        }
        Student s = idMap.get(andrewId);
        if (firstNameMap.containsKey(s.getFirstName())) {
            List<Student> list = firstNameMap.get(s.getFirstName());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAndrewId().equals(andrewId)) {
                    list.remove(i);
                    break;
                }
            }
        }
        if (lastNameMap.containsKey(s.getLastName())) {
            List<Student> list = lastNameMap.get(s.getLastName());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAndrewId().equals(andrewId)) {
                    list.remove(i);
                    break;
                }
            }
        }
        idMap.remove(andrewId);
        mapSize--;
    }

    /**
     * Get the list of students who have the given first name.
     * @param firstName first name
     * @return student list
     */
    public List<Student> searchByFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException();
        }
        if (firstNameMap.containsKey(firstName)) {
            List<Student> list = new LinkedList<Student>();
            for (Student s : firstNameMap.get(firstName)) {
                list.add(s.clone());
            }
            return list;
        } else {
            return new LinkedList<Student>();
        }
    }

    /**
     * Get the list of students who have the given last name.
     * @param lastName last name
     * @return student list
     */
    public List<Student> searchByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException();
        }
        if (lastNameMap.containsKey(lastName)) {
            List<Student> list = new LinkedList<Student>();
            for (Student s : lastNameMap.get(lastName)) {
                list.add(s.clone());
            }
            return list;
        } else {
            return new LinkedList<Student>();
        }
    }

    /**
     * Search a student with id.
     * @param andrewId id
     * @return student
     */
    public Student searchByAndrewId(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException();
        }
        if (idMap.containsKey(andrewId)) {
            return idMap.get(andrewId).clone();
        } else {
            return null;
        }
    }

    /**
     * Return the number of students in the directory.
     * @return student number
     */
    public int size() {
        return mapSize;
    }
}
