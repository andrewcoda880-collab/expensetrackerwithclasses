import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.renderer.category.BarRenderer;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GraphsTab extends JPanel {
    
    private ExpenseManager expenseManager;
    private ChartPanel chartPanel;
    private JPanel chartContainer;
    private String currentChartType = "Both";
    private JLabel totalExpensesLabel;
    private UserSettings userSettings;
    
    // Define constant colors for each category
    private static final Color FOOD_COLOR = new Color(255, 99, 132);      // Pink/Red
    private static final Color TRANSPORT_COLOR = new Color(54, 162, 235); // Blue
    private static final Color ENTERTAINMENT_COLOR = new Color(255, 206, 86); // Yellow/Gold
    private static final Color BILLS_COLOR = new Color(75, 192, 192);     // Teal
    private static final Color OTHER_COLOR = new Color(153, 102, 255);    // Purple
    
    public GraphsTab(ExpenseManager expenseManager, UserSettings userSettings) { 
        this.expenseManager = expenseManager;
        this.userSettings = userSettings;
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        
        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Constants.APP_COLOR);
        JLabel title = new JLabel("Expense Graphs");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        
        totalExpensesLabel = new JLabel("Total Expenses: $0.00");
        totalExpensesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titlePanel.add(totalExpensesLabel);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constants.APP_COLOR);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshChart());
        
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Create container for charts
        chartContainer = new JPanel();
        chartContainer.setBackground(Constants.APP_COLOR);
        add(chartContainer, BorderLayout.CENTER);
        
        // Create initial chart based on saved preference
        refreshChart();
    }
    
    private Map<String, Double> calculateExpensesByCategory() {
        Map<String, Double> categoryTotals = new HashMap<>();
        categoryTotals.put("Food", 0.0);
        categoryTotals.put("Transport", 0.0);
        categoryTotals.put("Entertainment", 0.0);
        categoryTotals.put("Bills", 0.0);
        categoryTotals.put("Other", 0.0);
        
        List<Expense> expenses = expenseManager.getExpenses();
        
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();
            
            if (category.equals("Food")) {
                categoryTotals.put("Food", categoryTotals.get("Food") + amount);
            } else if (category.equals("Transport")) {
                categoryTotals.put("Transport", categoryTotals.get("Transport") + amount);
            } else if (category.equals("Entertainment")) {
                categoryTotals.put("Entertainment", categoryTotals.get("Entertainment") + amount);
            } else if (category.equals("Bills")) {
                categoryTotals.put("Bills", categoryTotals.get("Bills") + amount);
            } else if (category.equals("Other")) {
                categoryTotals.put("Other", categoryTotals.get("Other") + amount);
            }
        }
        
        return categoryTotals;
    }
    
    private void showPieChart() {
        currentChartType = "Pie";
        updatePieChart();
    }
    
    private void showBarChart() {
        currentChartType = "Bar";
        updateBarChart();
    }
    
    private void showBothCharts() {
        currentChartType = "Both";
        updateBothCharts();
    }
    
    private void updatePieChart() {
        Map<String, Double> categoryTotals = calculateExpensesByCategory();
        double foodTotal = categoryTotals.get("Food");
        double transportTotal = categoryTotals.get("Transport");
        double entertainmentTotal = categoryTotals.get("Entertainment");
        double billsTotal = categoryTotals.get("Bills");
        double otherTotal = categoryTotals.get("Other");
        double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
        
        totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        // Only add categories with positive values
        if (foodTotal > 0) dataset.setValue("Food", foodTotal);
        if (transportTotal > 0) dataset.setValue("Transport", transportTotal);
        if (entertainmentTotal > 0) dataset.setValue("Entertainment", entertainmentTotal);
        if (billsTotal > 0) dataset.setValue("Bills", billsTotal);
        if (otherTotal > 0) dataset.setValue("Other", otherTotal);
        
        JFreeChart chart = ChartFactory.createPieChart(
            String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
            dataset, true, true, false);
        
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Constants.APP_COLOR);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
        plot.setShadowPaint(null);
        plot.setLabelGap(0.02);
        
        // Set custom colors for each section using the dataset keys
        if (foodTotal > 0) plot.setSectionPaint(0, FOOD_COLOR);
        if (transportTotal > 0) plot.setSectionPaint(1, TRANSPORT_COLOR);
        if (entertainmentTotal > 0) plot.setSectionPaint(2, ENTERTAINMENT_COLOR);
        if (billsTotal > 0) plot.setSectionPaint(3, BILLS_COLOR);
        if (otherTotal > 0) plot.setSectionPaint(4, OTHER_COLOR);
        
        // Clear container and show single chart
        chartContainer.removeAll();
        chartContainer.setLayout(new BorderLayout());
        updateChartPanel(chart);
        
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
    }
    
    private void updateBarChart() {
        Map<String, Double> categoryTotals = calculateExpensesByCategory();
        double foodTotal = categoryTotals.get("Food");
        double transportTotal = categoryTotals.get("Transport");
        double entertainmentTotal = categoryTotals.get("Entertainment");
        double billsTotal = categoryTotals.get("Bills");
        double otherTotal = categoryTotals.get("Other");
        double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
        
        totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
        // Create dataset with separate series for each category (so each gets its own color)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Each category gets its own series name, which creates separate colored bars
        if (foodTotal > 0) dataset.addValue(foodTotal, "Food", "Food");
        if (transportTotal > 0) dataset.addValue(transportTotal, "Transport", "Transport");
        if (entertainmentTotal > 0) dataset.addValue(entertainmentTotal, "Entertainment", "Entertainment");
        if (billsTotal > 0) dataset.addValue(billsTotal, "Bills", "Bills");
        if (otherTotal > 0) dataset.addValue(otherTotal, "Other", "Other");
        
        JFreeChart barChart = ChartFactory.createBarChart(
            String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
            "Category", "Amount ($)", dataset,
            PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Constants.APP_COLOR);
        plot.setRangeGridlinePaint(Color.GRAY);
        
        // Set colors for each series (each bar gets its own color)
        org.jfree.chart.renderer.category.BarRenderer renderer = 
            (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();
        
        int seriesIndex = 0;
        if (foodTotal > 0) {
            renderer.setSeriesPaint(seriesIndex++, FOOD_COLOR);
        }
        if (transportTotal > 0) {
            renderer.setSeriesPaint(seriesIndex++, TRANSPORT_COLOR);
        }
        if (entertainmentTotal > 0) {
            renderer.setSeriesPaint(seriesIndex++, ENTERTAINMENT_COLOR);
        }
        if (billsTotal > 0) {
            renderer.setSeriesPaint(seriesIndex++, BILLS_COLOR);
        }
        if (otherTotal > 0) {
            renderer.setSeriesPaint(seriesIndex++, OTHER_COLOR);
        }
        
        // Clear container and show single chart
        chartContainer.removeAll();
        chartContainer.setLayout(new BorderLayout());
        updateChartPanel(barChart);
        
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
    }
    
    private void updateBothCharts() {
        Map<String, Double> categoryTotals = calculateExpensesByCategory();
        double foodTotal = categoryTotals.get("Food");
        double transportTotal = categoryTotals.get("Transport");
        double entertainmentTotal = categoryTotals.get("Entertainment");
        double billsTotal = categoryTotals.get("Bills");
        double otherTotal = categoryTotals.get("Other");
        double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
        
        totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
        // Create Pie Chart
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        if (foodTotal > 0) pieDataset.setValue("Food", foodTotal);
        if (transportTotal > 0) pieDataset.setValue("Transport", transportTotal);
        if (entertainmentTotal > 0) pieDataset.setValue("Entertainment", entertainmentTotal);
        if (billsTotal > 0) pieDataset.setValue("Bills", billsTotal);
        if (otherTotal > 0) pieDataset.setValue("Other", otherTotal);
        
        JFreeChart pieChart = ChartFactory.createPieChart(
            String.format("Pie Chart - Total: $%.2f", totalExpenses),
            pieDataset, true, true, false);
        
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        piePlot.setBackgroundPaint(Constants.APP_COLOR);
        piePlot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
        piePlot.setShadowPaint(null);
        
        // Set custom colors for pie chart sections
        if (foodTotal > 0) piePlot.setSectionPaint(0, FOOD_COLOR);
        if (transportTotal > 0) piePlot.setSectionPaint(1, TRANSPORT_COLOR);
        if (entertainmentTotal > 0) piePlot.setSectionPaint(2, ENTERTAINMENT_COLOR);
        if (billsTotal > 0) piePlot.setSectionPaint(3, BILLS_COLOR);
        if (otherTotal > 0) piePlot.setSectionPaint(4, OTHER_COLOR);
        
        // Create Bar Chart with separate series for each category
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        
        // Each category gets its own series name for different colored bars
        if (foodTotal > 0) barDataset.addValue(foodTotal, "Food", "Food");
        if (transportTotal > 0) barDataset.addValue(transportTotal, "Transport", "Transport");
        if (entertainmentTotal > 0) barDataset.addValue(entertainmentTotal, "Entertainment", "Entertainment");
        if (billsTotal > 0) barDataset.addValue(billsTotal, "Bills", "Bills");
        if (otherTotal > 0) barDataset.addValue(otherTotal, "Other", "Other");
        
        JFreeChart barChart = ChartFactory.createBarChart(
            String.format("Bar Chart - Total: $%.2f", totalExpenses),
            "Category", "Amount ($)", barDataset,
            PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot barPlot = (CategoryPlot) barChart.getPlot();
        barPlot.setBackgroundPaint(Constants.APP_COLOR);
        barPlot.setRangeGridlinePaint(Color.GRAY);
        
        // Set colors for each bar (each series gets a different color)
        org.jfree.chart.renderer.category.BarRenderer barRenderer = 
            (org.jfree.chart.renderer.category.BarRenderer) barPlot.getRenderer();
        
        int seriesIndex = 0;
        if (foodTotal > 0) {
            barRenderer.setSeriesPaint(seriesIndex++, FOOD_COLOR);
        }
        if (transportTotal > 0) {
            barRenderer.setSeriesPaint(seriesIndex++, TRANSPORT_COLOR);
        }
        if (entertainmentTotal > 0) {
            barRenderer.setSeriesPaint(seriesIndex++, ENTERTAINMENT_COLOR);
        }
        if (billsTotal > 0) {
            barRenderer.setSeriesPaint(seriesIndex++, BILLS_COLOR);
        }
        if (otherTotal > 0) {
            barRenderer.setSeriesPaint(seriesIndex++, OTHER_COLOR);
        }
        
        // Clear container and set up split layout for both charts
        chartContainer.removeAll();
        chartContainer.setLayout(new GridLayout(2, 1, 10, 10));
        
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setBackground(Constants.APP_COLOR);
        pieChartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 250));
        
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBackground(Constants.APP_COLOR);
        barChartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 250));
        
        chartContainer.add(pieChartPanel);
        chartContainer.add(barChartPanel);
        
        chartContainer.revalidate();
        chartContainer.repaint();
        
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
    }
    
    private void updateChartPanel(JFreeChart chart) {
        if (chartPanel != null && chartPanel.getParent() != null) {
            chartContainer.remove(chartPanel);
        }
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Constants.APP_COLOR);
        chartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 400));
        
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
    
    public void refreshChart() {
        String preference = userSettings.getChartPreference();
        
        if (preference.equals("Pie")) {
            showPieChart();
        } else if (preference.equals("Bar")) {
            showBarChart();
        } else {  // "Both" is the default
            showBothCharts();
        }
    }
}