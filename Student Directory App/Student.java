/**
 * Student class.
 * @author Luxiao Ding (ID: luxiaod)
 */
public class Student {
    /**
     * Student andrew id.
     */
    private String id;
    /**
     * Student first name.
     */
    private String firstName;
    /**
     * Student last name.
     */
    private String lastName;
    /**
     * Student phoen number.
     */
    private String phone;

    /**
     * Constructor.
     * @param andrewId id
     */
    public Student(String andrewId) {
        id = andrewId;
    }

    /**
     * Get the andrew id.
     * @return student id
     */
    public String getAndrewId() {
        return id;
    }

    /**
     * Get student first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get student last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get student phone number.
     * @return phone number
     */
    public String getPhoneNumber() {
        return phone;
    }

    /**
     * Set student first name.
     * @param s first name
     */
    public void setFirstName(String s) {
        firstName = s;
    }

    /**
     * Set student last name.
     * @param s last name
     */
    public void setLastName(String s) {
        lastName = s;
    }

    /**
     * Set phone number.
     * @param s phone number
     */
    public void setPhoneNumber(String s) {
        phone = s;
    }

    /**
     * Clone Student object.
     * @return a cloned Student object.
     */
    public Student clone() {
        Student s = new Student(id);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setPhoneNumber(phone);
        return s;
    }

    /**
     * Return the string representation of Student object.
     * @return string representation of Student object
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (Andrew ID: " + id
                + ", Phone Number: " + phone + ")";
    }

}
