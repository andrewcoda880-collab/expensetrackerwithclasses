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
            
            switch (category) {
                case "Food":
                    foodTotal += amount;
                    break;
                case "Transport":
                    transportTotal += amount;
                    break;
                case "Entertainment":
                    entertainmentTotal += amount;
                    break;
                case "Bills":
                    billsTotal += amount;
                    break;
                default:
                    otherTotal += amount;
                    break;
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
    }
    
    public void refreshChart() {
        updatePieChart();
    }
}