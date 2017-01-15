/**
 * Shape class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Shape class.
 * @author Luxiao Ding
 */
public abstract class Shape {
    /**
     * Instance variable for area.
     */
    private double area;
    /**
     * Instance variable for perimeter.
     */
    private double perimeter;

    /**
     * Constructor with no arguments.
     */
    public Shape() {
    };

    /**
     * Get the value of area.
     * @return area value in double
     */
    public abstract double getArea();

    /**
     * Get the value of perimeter.
     * @return perimeter value in double
     */
    public abstract double getPerimeter();

    /**
     * Returns String representation of Shape object.
     * @return String representation of Shape object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Shape " + df.format(area) + " " + df.format(perimeter);
    }

}
