/**
 * Circle class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Circle class.
 * @author Luxiao Ding
 */
public class Circle extends Shape {
    /**
     * Instance variable for area.
     */
    private double area;
    /**
     * Instance variable for perimeter.
     */
    private double perimeter;
    /**
     * Instance variable for radius.
     */
    private double radius;

    /**
     * Constructor to create Circle with radius.
     * @param newRadius radius value of circle
     */
    public Circle(double newRadius) {
        area = Math.PI * newRadius * newRadius;
        perimeter = 2 * Math.PI * newRadius;
        radius = newRadius;
    }

    /**
     * Instance method to get radius.
     * @return double radius value
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Get its area value.
     * @return area value in double.
     */
    public double getArea() {
        return area;
    }

    /**
     * Get the perimeter.
     * @return perimeter value in double.
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Overridden toString method.
     * @return String representation of Circle object.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Circle " + df.format(area) + " " + df.format(perimeter);
    }

}
