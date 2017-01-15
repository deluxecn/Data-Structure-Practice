/**
 * Octagon class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Octagon class.
 * @author Luxiao Ding
 */
public class Octagon extends Shape {
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
    public Octagon(double newSide) {
        area = 2 * (1 + Math.sqrt(2)) * newSide * newSide;
        perimeter = 8 * newSide;
        side = newSide;
    }

    /**
     * Get side value.
     * @return side value in double
     */
    public double getSide() {
        return side;
    }

    /**
     * Get its area.
     * @return area value in double.
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
     * Returns String representatino of Octagon object.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Octagon " + df.format(area)
        + " " + df.format(perimeter);
    }

}
