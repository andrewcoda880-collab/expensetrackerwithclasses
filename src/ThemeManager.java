import java.util.ArrayList;
import java.util.List;

public class ThemeManager {
    private static final List<ThemeListener> listeners = new ArrayList<>();

    public static void register(ThemeListener listener) {
        listeners.add(listener);
    }

    public static void setTheme(boolean lightMode) {
        Constants.changeTheme(lightMode);

        for (ThemeListener l : listeners) {
            l.onThemeChanged();
        }
    }
}