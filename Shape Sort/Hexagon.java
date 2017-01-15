/**
 * Hexagon class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Hexagon class.
 * @author Luxiao Ding
 */
public class Hexagon extends Shape {
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
     * Constructor with side.
     * @param newSide side value
     */
    public Hexagon(double newSide) {
        area = 3 * Math.sqrt(3) * 0.5 * newSide * newSide;
        perimeter = 6 * newSide;
        side = newSide;
    }

    /**
     * Get the value of side.
     * @return side value in double
     */
    public double getSide() {
        return side;
    }

    /**
     * Get the its area.
     * @return area value in double
     */
    public double getArea() {
        return area;
    }

    /**
     * Get its perimeter.
     * @return perimeter value in double.
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Returns String representation of Hexagon object.
     * @return String representation of Hexagon object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Hexagon " + df.format(area)
        + " " + df.format(perimeter);
    }

}
