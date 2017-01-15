/**
 * Shapes sorting class.
 * @author Luxiao Ding (luxiaod@andrew.cmu.edu)
 *
 */
public class ShapeSortTest {

    /**
     * @param args input shapes
     */
    public static void main(String[] args) {
        int len = args.length;
        Shape[] shape = new Shape[len];
        for (int i = 0; i < len; i++) {
            switch (args[i].charAt(0)) {
            case 'C':
                shape[i] = new Circle(Integer.parseInt(args[i].substring(1)));
                break;
            case 'S':
                shape[i] = new Square(Integer.parseInt(args[i].substring(1)));
                break;
            case 'H':
                shape[i] = new Hexagon(Integer.parseInt(args[i].substring(1)));
                break;
            case 'O':
                shape[i] = new Octagon(Integer.parseInt(args[i].substring(1)));
                break;
            default:
                break;
            }

        }

        // Sort area in ascending order
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (shape[j].getArea() < shape[i].getArea()) {
                    Shape tempShape = shape[i];
                    shape[i] = shape[j];
                    shape[j] = tempShape;
                }
            }
            System.out.println(shape[i].toString());
        }


        // Sort perimeter in descending order
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (shape[j].getPerimeter() > shape[i].getPerimeter()) {
                    Shape tempShape = shape[i];
                    shape[i] = shape[j];
                    shape[j] = tempShape;
                }
            }
            System.out.println(shape[i].toString());
        }

    }

}
