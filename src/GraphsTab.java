import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphsTab extends JPanel {
    
    private ExpenseManager expenseManager;
    private ChartPanel chartPanel;
    
    // Define constant colors for each category
    private static final Color FOOD_COLOR = new Color(255, 99, 132);      // Red/Pink
    private static final Color TRANSPORT_COLOR = new Color(54, 162, 235);  // Blue
    private static final Color ENTERTAINMENT_COLOR = new Color(255, 206, 86); // Yellow
    private static final Color BILLS_COLOR = new Color(75, 192, 192);      // Teal
    private static final Color OTHER_COLOR = new Color(153, 102, 255);     // Purple
    
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
        JButton refreshButton = new JButton("Refresh Pie Chart");
        refreshButton.addActionListener(e -> updatePieChart());
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Create initial chart
        updatePieChart();
    }
    
    private void updatePieChart() {
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        // Get expenses and aggregate by category
        List<Expense> expenses = expenseManager.getExpenses();
        
        double foodTotal = 0;
        double transportTotal = 0;
        double entertainmentTotal = 0;
        double billsTotal = 0;
        double otherTotal = 0;
        
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
        
        // Add to dataset (only if > 0)
        if (foodTotal > 0) dataset.setValue("Food", foodTotal);
        if (transportTotal > 0) dataset.setValue("Transport", transportTotal);
        if (entertainmentTotal > 0) dataset.setValue("Entertainment", entertainmentTotal);
        if (billsTotal > 0) dataset.setValue("Bills", billsTotal);
        if (otherTotal > 0) dataset.setValue("Other", otherTotal);
        
        double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
        
        // Create chart
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
        
        // Set fixed colors for each category (only if they exist in the dataset)
        if (foodTotal > 0) {
            plot.setSectionPaint(0, FOOD_COLOR);
        }
        if (transportTotal > 0) {
            plot.setSectionPaint(1, TRANSPORT_COLOR);
        }
        if (entertainmentTotal > 0) {
            plot.setSectionPaint(2, ENTERTAINMENT_COLOR);
        }
        if (billsTotal > 0) {
            plot.setSectionPaint(3, BILLS_COLOR);
        }
        if (otherTotal > 0) {
            plot.setSectionPaint(4, OTHER_COLOR);
        }
        
        // Remove old chart panel and add new one
        if (chartPanel != null) {
            remove(chartPanel);
        }
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Constants.APP_COLOR);
        chartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 400));
        
        add(chartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        
        // Check if entertainment expenses exceed bills and show warning
        Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses);
    }
    
    public void refreshChart() {
        updatePieChart();
    }
}