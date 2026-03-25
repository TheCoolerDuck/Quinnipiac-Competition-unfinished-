package Source;

public class Camera {

    public static int X;
    public static int Y;

    public static double scale = 3;

    public static double getScale() {
        return scale;
    }

    public static void setScale(double scale) {
        Camera.scale = scale;
    }

    public static int getY() {
        return Y;
    }

    public static void setY(int y) {
        Y = y;
    }

    public static int getX() {
        return X;
    }

    public static void setX(int x) {
        X = x;
    }
}
