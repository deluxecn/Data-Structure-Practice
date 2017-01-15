/**
 * Rectangle class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 */

import java.text.DecimalFormat;

/**
 * Rectangle class.
 * @author Luxiao Ding
 */
public class Rectangle extends Shape {
    /**
     * Instance variable for area.
     */
    private double area;
    /**
     * Instance variable for perimeter.
     */
    private double perimeter;
    /**
     * Instance variable for width.
     */
    private double width;
    /**
     * Instance variable for height.
     */
    private double height;

    /**
     * Constructor with width and height.
     * @param newWidth width value
     * @param newHeight height value
     */
    public Rectangle(double newWidth, double newHeight) {
        area = newWidth * newHeight;
        perimeter = 2 * (newWidth + newHeight);
        width = newWidth;
        height = newHeight;
    }

    /**
     * Get the value of width.
     * @return width value in double.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the value of height.
     * @return height value in double.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get its area value.
     * @return area value in double.
     */
    public double getArea() {
        return area;
    }

    /**
     * Get the perimeter value.
     * @return perimeter value in double.
     */
    public double getPerimeter() {
        return perimeter;
    }

    /**
     * Returns String representation of Rectangle object.
     * @return String representation of Rectangle object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.000");
        return "Rectangle " + df.format(area) + " " + df.format(perimeter);
    }

}
