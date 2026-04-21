import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphsTab extends JPanel {
    
    private ExpenseManager expenseManager;
    private ChartPanel chartPanel;
    private JPanel chartContainer;
    private String currentChartType = "Pie";
    
    // Define constant colors for each category
    private static final Color FOOD_COLOR = new Color(255, 99, 132);
    private static final Color TRANSPORT_COLOR = new Color(54, 162, 235);
    private static final Color ENTERTAINMENT_COLOR = new Color(255, 206, 86);
    private static final Color BILLS_COLOR = new Color(75, 192, 192);
    private static final Color OTHER_COLOR = new Color(153, 102, 255);
    
    // Store expense totals for use in both charts
    private double foodTotal = 0;
    private double transportTotal = 0;
    private double entertainmentTotal = 0;
    private double billsTotal = 0;
    private double otherTotal = 0;
    private double totalExpenses = 0;
    
    public GraphsTab(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        
        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Constants.APP_COLOR);
        JLabel title = new JLabel("Expense Graphs");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constants.APP_COLOR);
        
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshChart());
        
        JButton barChartButton = new JButton("Bar Chart");
        barChartButton.addActionListener(e -> showBarChart());
        
        JButton pieChartButton = new JButton("Pie Chart");
        pieChartButton.addActionListener(e -> showPieChart());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(barChartButton);
        buttonPanel.add(pieChartButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Create container for charts
        chartContainer = new JPanel(new BorderLayout());
        chartContainer.setBackground(Constants.APP_COLOR);
        add(chartContainer, BorderLayout.CENTER);
        
        // Create initial pie chart
        showPieChart();
    }
    
    private void calculateExpenses() {
        // Reset totals
        foodTotal = 0;
        transportTotal = 0;
        entertainmentTotal = 0;
        billsTotal = 0;
        otherTotal = 0;
        
        // Get expenses and aggregate by category
        List<Expense> expenses = expenseManager.getExpenses();
        
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();
            
            if (category.equals("Food")) {
                foodTotal += amount;
            } else if (category.equals("Transport")) {
                transportTotal += amount;
            } else if (category.equals("Entertainment")) {
                entertainmentTotal += amount;
            } else if (category.equals("Bills")) {
                billsTotal += amount;
            } else {
                otherTotal += amount;
            }
        }
        
        totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
    }
    
    private void showPieChart() {
        currentChartType = "Pie";
        updatePieChart();
    }
    
    private void showBarChart() {
        currentChartType = "Bar";
        updateBarChart();
    }
    
    private void updatePieChart() {
        calculateExpenses();
        
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        // Add to dataset (only if > 0)
        if (foodTotal > 0) dataset.setValue("Food", foodTotal);
        if (transportTotal > 0) dataset.setValue("Transport", transportTotal);
        if (entertainmentTotal > 0) dataset.setValue("Entertainment", entertainmentTotal);
        if (billsTotal > 0) dataset.setValue("Bills", billsTotal);
        if (otherTotal > 0) dataset.setValue("Other", otherTotal);
        
        JFreeChart chart = ChartFactory.createPieChart(
            String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
            dataset,
            true,  // legend
            true,  // tooltips
            false  // urls
        );
        
        // Customize chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Constants.APP_COLOR);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
        plot.setLabelBackgroundPaint(new Color(255, 255, 255, 200));
        
        // Set fixed colors for each category
        if (foodTotal > 0) plot.setSectionPaint(0, FOOD_COLOR);
        if (transportTotal > 0) plot.setSectionPaint(1, TRANSPORT_COLOR);
        if (entertainmentTotal > 0) plot.setSectionPaint(2, ENTERTAINMENT_COLOR);
        if (billsTotal > 0) plot.setSectionPaint(3, BILLS_COLOR);
        if (otherTotal > 0) plot.setSectionPaint(4, OTHER_COLOR);
        
        // Update chart panel
        updateChartPanel(chart);
        
        // Check warning
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses);
    }
    
    private void updateBarChart() {
        calculateExpenses();
        
        CategoryDataset dataset = createBarChartDataset();
        
        // Create bar chart - following the BarChart example pattern
        JFreeChart barChart = ChartFactory.createBarChart(
            String.format("Expense Distribution (Total: $%.2f)", totalExpenses), // Chart title
            "Category",// X-axis label
            "Amount ($)",// Y-axis label
            dataset,// Dataset
            PlotOrientation.VERTICAL,// Orientation
            true,// Include legend
            true,// Tooltips
            false// URLs
        );
        
        // Customize the chart appearance
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Constants.APP_COLOR);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);//Color is being chosen
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        
        // Set custom colors for bars
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        int index = 0;
        if (foodTotal > 0) {
            renderer.setSeriesPaint(index++, FOOD_COLOR);
        }
        if (transportTotal > 0) {
            renderer.setSeriesPaint(index++, TRANSPORT_COLOR);
        }
        if (entertainmentTotal > 0) {
            renderer.setSeriesPaint(index++, ENTERTAINMENT_COLOR);
        }
        if (billsTotal > 0) {
            renderer.setSeriesPaint(index++, BILLS_COLOR);
        }
        if (otherTotal > 0) {
            renderer.setSeriesPaint(index++, OTHER_COLOR);
        }
        
        // Update chart panel
        updateChartPanel(barChart);
        
        // Check warning
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses);
    }
    
    private CategoryDataset createBarChartDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // The row key is the category name, column key is always "Amount"
        if (foodTotal > 0) {
            dataset.addValue(foodTotal, "Food", "Amount");
        }
        if (transportTotal > 0) {
            dataset.addValue(transportTotal, "Transport", "Amount");
        }
        if (entertainmentTotal > 0) {
            dataset.addValue(entertainmentTotal, "Entertainment", "Amount");
        }
        if (billsTotal > 0) {
            dataset.addValue(billsTotal, "Bills", "Amount");
        }
        if (otherTotal > 0) {
            dataset.addValue(otherTotal, "Other", "Amount");
        }
        
        return dataset;
    }
    
    private void updateChartPanel(JFreeChart chart) {
        if (chartPanel != null) {
            chartContainer.remove(chartPanel);
        }
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Constants.APP_COLOR);
        chartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 400));
        
        // Add to container
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
    
    public void refreshChart() {
        if (currentChartType.equals("Pie")) {
            updatePieChart();
        } else {
            updateBarChart();
        }
    }
}