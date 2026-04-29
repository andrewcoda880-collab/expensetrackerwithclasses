
public class UserSettings {
    private String  timeNotifications;
    private String chartPref;
    private boolean lightMode;
    public UserSettings(String timeNotifications, String chartPref, boolean lightMode) {
        this.timeNotifications = timeNotifications;
        this.chartPref = chartPref;
        this.lightMode = lightMode;
    }
    public void savePreferences() {
        String chartType = getChartPreference();
        String notifsSet = getTimeNotifications();
        boolean lightMode = isLightMode();
        
        switch(chartType) {
            case "Bar":
                chartPref = "Bar";
                break;
            case "Line":
                chartPref = "Line";
                break;
            default:
                chartPref = "Pie";
                break;
            }
        switch(notifsSet) {
            case "Weekly":
                timeNotifications = "Weekly";
                break;
            case "Monthly":
                timeNotifications = "Monthly";
                break;
            case "Weekly & Monthly":
                timeNotifications = "Weekly & Monthly";
                break;
            default:
                timeNotifications = "None";
                break;
        }

        if(!lightMode) {
            lightMode = false; // Set to dark mode
        } else {
            lightMode = true; //Set to light mode
        }
    }
    public String getTimeNotifications() {
        return timeNotifications;
    }
    public String getChartPreference() {
        return chartPref;
    }
    public boolean isLightMode() {
        return lightMode;
    }
}