import java.awt.Color;

public final class Constants {
    private Constants() {}
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 600;
    public static final String APP_TITLE = "Expense Tracker";
    public static Color APP_COLOR = Color.getHSBColor(171,95,14);

    static void changeTheme(boolean LightMode) {
        if(!LightMode) {
            APP_COLOR = Color.getHSBColor(172, 85, 93 );
        } else {
            APP_COLOR = Color.getHSBColor(231, 74, 35); 
        }
    }
}

