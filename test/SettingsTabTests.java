import org.junit.Test;
import static org.junit.Assert.*;

public class SettingsTabTests {

    @Test
    public void testTimeNotificationOptions() {
        SettingsTab settingsTab = new SettingsTab();
        String[] expectedOptions = {"Weekly", "Monthly", "Bi-Weekly", "Off"};
        assertArrayEquals(expectedOptions, settingsTab.getNotifsMenuOptions());
    }
}