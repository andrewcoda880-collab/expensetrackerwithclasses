
public class UserSettings {
    private String  timeNotifications = "Off";
    private String chartPref = "Pie";
    private boolean lightMode = true;
    private GraphsTab graphsTab;
    
    public UserSettings(String timeNotifications, String chartPref, boolean lightMode) {
        this.timeNotifications = timeNotifications;
        this.chartPref = chartPref;
        this.lightMode = lightMode;
    }
    public void changePreferences() {
        switch(chartPref) {
            case "Bar":
                graphsTab.showBarChart();
                break;
            case "Line":
                graphsTab.showLineChart();
                break;
            case "Pie":
                graphsTab.showPieChart();
                break;
            default:
                chartPref = "Pie";
                graphsTab.showPieChart();
                break;
        }

        switch(timeNotifications) {
            case "Weekly":
                Notifications.checkAndShowNotification();
                break;
            case "Monthly":
                Notifications.checkAndShowNotification();
                break;
            case "Bi-Weekly":
                Notifications.checkAndShowNotification();
                break;
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