/**
 * Square class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Square class.
 * @author Luxiao Ding
 */
public class Square extends Rectangle {
    /**
     * Instance variable for area.
     */
    private double area;
    /**
     * Instance variable for perimeter.
     */
    private double perimeter;
    /**
     * Instance variable for side.
     */
    private double side;

    /**
     * Constructor for Square with side.
     * @param newSide side value
     */
    public Square(double newSide) {
        super(newSide, newSide);
        side = newSide;
        area = side * side;
        perimeter = 4 * side;
    }

    /**
     * Get side value.
     * @return side value in double
     */
    public double getSide() {
        return side;
    }

    /**
     * Returns String representation of Square object.
     * @return String representatino of Square object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Square " + df.format(area) + " " + df.format(perimeter);
    }

}
