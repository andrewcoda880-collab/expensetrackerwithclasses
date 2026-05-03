import java.awt.Color;

public final class Constants {
    private Constants() {}
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 600;
    public static final String APP_TITLE = "Expense Tracker";
    public static Color APP_COLOR = new Color(253, 248, 210); //171,95,14

    static void changeTheme(boolean LightMode) {
        if(LightMode) {
            APP_COLOR = new Color(253, 248, 210);
        } else {
            APP_COLOR = new Color(12, 14, 79);
        }
    }
}

