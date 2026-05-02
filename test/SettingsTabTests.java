/*import src.UserSettings;
import src.MainFrame;
import src.Expense;
import src.Constants;
import src.SettingsTab;
import src.Notifications;
import src.ExpenseManager;*/

import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;

public class SettingsTabTests {

     /*  @Test
     public void testTimeNotificationOptions() {
         UserSettings userSettings = new UserSettings("Off", "Pie", true);
         SettingsTab settingsTab = new SettingsTab(userSettings);
         String[] expectedOptions = {"Weekly", "Monthly", "Bi-Weekly", "Off"};
         assertArrayEquals(expectedOptions, settingsTab.getNotifsMenuOptions());
     }
         */
    @Test
    public void testDefaultPreferences() {
        UserSettings userSettings = new UserSettings("Off", "Pie", true);
        assertEquals("Off", userSettings.getTimeNotifications());
        assertEquals("Pie", userSettings.getChartPreference());
        assertTrue(userSettings.isLightMode());
    }
    @Test
    public void testNonDefaultPreferences() {
        UserSettings userSettings = new UserSettings("Weekly", "Bar", false);
        userSettings.setTimeNotifications("Monthly");
        userSettings.setChartPref("Bar");
        userSettings.setLightMode(false);

        assertEquals("Monthly", userSettings.getTimeNotifications());
        assertEquals("Bar", userSettings.getChartPreference());
        assertFalse(userSettings.isLightMode());
    }
    @Test
    public void triggerWeeklyNotification() {
        UserSettings userSettings = new UserSettings("Weekly", "Pie", true);
        userSettings.setTimeNotifications("Weekly");
        Notifications notifications = new Notifications(userSettings, new ExpenseManager());
        notifications.checkAndShowNotification();
    }
    @Test
    public void triggerMonthlyNotification() {
        UserSettings userSettings = new UserSettings("Monthly", "Pie", true);
        userSettings.setTimeNotifications("Monthly");
        Notifications notifications = new Notifications(userSettings, new ExpenseManager());
        notifications.checkAndShowNotification();
    }
    @Test
    public void triggerBiWeeklyNotification() {
        UserSettings userSettings = new UserSettings("Bi-Weekly", "Pie", true);
        userSettings.setTimeNotifications("Bi-Weekly");
        Notifications notifications = new Notifications(userSettings, new ExpenseManager());
        notifications.checkAndShowNotification();
    }
    @Test
    public void DarkModeToggle() {
        UserSettings userSettings = new UserSettings("Off", "Pie", false);
        userSettings.setLightMode(false);
        assertFalse(userSettings.isLightMode());
        assertEquals(Color.getHSBColor(172, 85, 93), Constants.APP_COLOR);
    }
}