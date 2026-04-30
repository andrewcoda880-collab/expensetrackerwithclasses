
public class UserSettings {
    private String  timeNotifications = "Weekly";
    private String chartPref = "Pie";
    private boolean lightMode = true;
    
    public UserSettings(String timeNotifications, String chartPref, boolean lightMode) {
        this.timeNotifications = timeNotifications;
        this.chartPref = chartPref;
        this.lightMode = lightMode;
    }
    public void savePreferences() {
        switch(chartPref) {
            case "Bar":
            case "Line":
            case "Pie":
                break;
            default:
                chartPref = "Pie";
                break;
        }

        switch(timeNotifications) {
            case "Weekly":
            case "Monthly":
            case "Bi-Weekly":
            case "Off":
                break;
            default:
                timeNotifications = "Off";
                break;
        }
    }

    public void savePreferences(String timeNotifications, String chartPref, boolean lightMode) {
        setTimeNotifications(timeNotifications);
        setChartPref(chartPref);
        setLightMode(lightMode);
    }

    public void setTimeNotifications(String timeNotifications) {
        this.timeNotifications = timeNotifications;
    }

    public void setChartPref(String chartPref) {
        this.chartPref = chartPref;
    }

    public void setLightMode(boolean lightMode) {
        this.lightMode = lightMode;
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