import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
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
    private JLabel totalExpensesLabel;
    
    // Define constant colors for each category
    private static final Color FOOD_COLOR = new Color(255, 99, 132);
    private static final Color TRANSPORT_COLOR = new Color(54, 162, 235);
    private static final Color ENTERTAINMENT_COLOR = new Color(255, 206, 86);
    private static final Color BILLS_COLOR = new Color(75, 192, 192);
    private static final Color OTHER_COLOR = new Color(153, 102, 255);
    
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
        
        totalExpensesLabel = new JLabel("Total Expenses: $0.00");
        totalExpensesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titlePanel.add(totalExpensesLabel);
        
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
    
    private double[] calculateExpenses() {
        double foodTotal = 0, transportTotal = 0, entertainmentTotal = 0, billsTotal = 0, otherTotal = 0;
        
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
        
        double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
        totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
        return new double[]{foodTotal, transportTotal, entertainmentTotal, billsTotal, otherTotal, totalExpenses};
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
        double[] totals = calculateExpenses();
        double foodTotal = totals[0], transportTotal = totals[1];
        double entertainmentTotal = totals[2], billsTotal = totals[3];
        double otherTotal = totals[4], totalExpenses = totals[5];
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        
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
        
        updateChartPanel(chart);
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses,foodTotal, transportTotal, otherTotal);
    }
    
    private void updateBarChart() {
        double[] totals = calculateExpenses();
        double foodTotal = totals[0], transportTotal = totals[1];
        double entertainmentTotal = totals[2], billsTotal = totals[3];
        double otherTotal = totals[4], totalExpenses = totals[5];
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (foodTotal > 0) dataset.addValue(foodTotal, "Amount", "Food");
        if (transportTotal > 0) dataset.addValue(transportTotal, "Amount", "Transport");
        if (entertainmentTotal > 0) dataset.addValue(entertainmentTotal, "Amount", "Entertainment");
        if (billsTotal > 0) dataset.addValue(billsTotal, "Amount", "Bills");
        if (otherTotal > 0) dataset.addValue(otherTotal, "Amount", "Other");
        
        JFreeChart barChart = ChartFactory.createBarChart(
            String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
            "Category", "Amount ($)", dataset,
            PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.setBackgroundPaint(Constants.APP_COLOR);
        
        updateChartPanel(barChart);
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses,foodTotal, transportTotal, otherTotal);
    }
    
    private void updateChartPanel(JFreeChart chart) {
        if (chartPanel != null) {
            chartContainer.remove(chartPanel);
        }
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Constants.APP_COLOR);
        chartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 400));
        
        chartContainer.add(chartPanel, BorderLayout.CENTER);
        chartContainer.revalidate();
        chartContainer.repaint();
    }
    
    
    public void refreshChart() {//functions of creating pie and bar chart
        if (currentChartType.equals("Pie")) {
            updatePieChart();
        } else {
            updateBarChart();
        }
    }
}