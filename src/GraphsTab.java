// import org.jfree.chart.ChartFactory;
// import org.jfree.chart.ChartPanel;
// import org.jfree.chart.JFreeChart;
// import org.jfree.chart.plot.PiePlot;
// import org.jfree.chart.plot.CategoryPlot;
// import org.jfree.chart.plot.PlotOrientation;
// import org.jfree.data.category.DefaultCategoryDataset;
// import org.jfree.data.general.DefaultPieDataset;
// import javax.swing.*;
// import java.awt.*;
// import java.util.List;

// public class GraphsTab extends JPanel {
    
//     private ExpenseManager expenseManager;
//     private ChartPanel chartPanel;
//     private ChartPanel secondChartPanel;  
//     private JPanel chartContainer;
//     private String currentChartType = "Both";
//     private JLabel totalExpensesLabel;
//     private UserSettings userSettings;  
    
//     // Define constant colors for each category
//     private static final Color FOOD_COLOR = new Color(255, 99, 132);
//     private static final Color TRANSPORT_COLOR = new Color(54, 162, 235);
//     private static final Color ENTERTAINMENT_COLOR = new Color(255, 206, 86);
//     private static final Color BILLS_COLOR = new Color(75, 192, 192);
//     private static final Color OTHER_COLOR = new Color(153, 102, 255);
    
//     public GraphsTab(ExpenseManager expenseManager, UserSettings userSettings) { 
//         this.expenseManager = expenseManager;
//         this.userSettings = userSettings;
//         setLayout(new BorderLayout());
//         setBackground(Constants.APP_COLOR);
        
//         // Create title panel
//         JPanel titlePanel = new JPanel();
//         titlePanel.setBackground(Constants.APP_COLOR);
//         JLabel title = new JLabel("Expense Graphs");
//         title.setFont(new Font("Arial", Font.BOLD, 24));
//         titlePanel.add(title);
        
//         totalExpensesLabel = new JLabel("Total Expenses: $0.00");
//         totalExpensesLabel.setFont(new Font("Arial", Font.PLAIN, 14));
//         titlePanel.add(totalExpensesLabel);
        
//         add(titlePanel, BorderLayout.NORTH);
        
//         // Create button panel
//         JPanel buttonPanel = new JPanel();
//         buttonPanel.setBackground(Constants.APP_COLOR);
        
//         JButton refreshButton = new JButton("Refresh");
//         refreshButton.addActionListener(e -> refreshChart());
        
//         buttonPanel.add(refreshButton);
//         add(buttonPanel, BorderLayout.SOUTH);
        
//         // Create container for charts
//         chartContainer = new JPanel();
//         chartContainer.setBackground(Constants.APP_COLOR);
//         add(chartContainer, BorderLayout.CENTER);
        
//         // Create initial chart based on saved preference
//         refreshChart();
//     }
    
//     private double[] calculateExpenses() {
//         double foodTotal = 0, transportTotal = 0, entertainmentTotal = 0, billsTotal = 0, otherTotal = 0;
        
//         List<Expense> expenses = expenseManager.getExpenses();
        
//         for (Expense expense : expenses) {
//             String category = expense.getCategory();
//             double amount = expense.getAmount();
            
//             if (category.equals("Food")) {
//                 foodTotal += amount;
//             } else if (category.equals("Transport")) {
//                 transportTotal += amount;
//             } else if (category.equals("Entertainment")) {
//                 entertainmentTotal += amount;
//             } else if (category.equals("Bills")) {
//                 billsTotal += amount;
//             } else {
//                 otherTotal += amount;
//             }
//         }
        
//         double totalExpenses = foodTotal + transportTotal + entertainmentTotal + billsTotal + otherTotal;
//         totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
//         return new double[]{foodTotal, transportTotal, entertainmentTotal, billsTotal, otherTotal, totalExpenses};
//     }
    
//     private void showPieChart() {
//         currentChartType = "Pie";
//         updatePieChart();
//     }
    
//     private void showBarChart() {
//         currentChartType = "Bar";
//         updateBarChart();
//     }
    
//     private void showBothCharts() {
//         currentChartType = "Both";
//         updateBothCharts();
//     }
    
//     private void updatePieChart() {
//         double[] totals = calculateExpenses();
//         double foodTotal = totals[0], transportTotal = totals[1];
//         double entertainmentTotal = totals[2], billsTotal = totals[3];
//         double otherTotal = totals[4], totalExpenses = totals[5];
        
//         DefaultPieDataset dataset = new DefaultPieDataset();
        
//         if (foodTotal > 0) dataset.setValue("Food", foodTotal);
//         if (transportTotal > 0) dataset.setValue("Transport", transportTotal);
//         if (entertainmentTotal > 0) dataset.setValue("Entertainment", entertainmentTotal);
//         if (billsTotal > 0) dataset.setValue("Bills", billsTotal);
//         if (otherTotal > 0) dataset.setValue("Other", otherTotal);
        
//         JFreeChart chart = ChartFactory.createPieChart(
//             String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
//             dataset, true, true, false);
        
//         PiePlot plot = (PiePlot) chart.getPlot();
//         plot.setBackgroundPaint(Constants.APP_COLOR);
//         plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
//         if (foodTotal > 0) plot.setSectionPaint("Food", FOOD_COLOR);
//         if (transportTotal > 0) plot.setSectionPaint("Transport", TRANSPORT_COLOR);
//         if (entertainmentTotal > 0) plot.setSectionPaint("Entertainment", ENTERTAINMENT_COLOR);
//         if (billsTotal > 0) plot.setSectionPaint("Bills", BILLS_COLOR);
//         if (otherTotal > 0) plot.setSectionPaint("Other", OTHER_COLOR);
        
//         // Clear container and show single chart
//         chartContainer.removeAll();
//         chartContainer.setLayout(new BorderLayout());
//         updateChartPanel(chart);
        
//         Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
//     }
    
//     private void updateBarChart() {
//         double[] totals = calculateExpenses();
//         double foodTotal = totals[0], transportTotal = totals[1];
//         double entertainmentTotal = totals[2], billsTotal = totals[3];
//         double otherTotal = totals[4], totalExpenses = totals[5];
        
//         DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//         if (foodTotal > 0) dataset.addValue(foodTotal, "Amount", "Food");
//         if (transportTotal > 0) dataset.addValue(transportTotal, "Amount", "Transport");
//         if (entertainmentTotal > 0) dataset.addValue(entertainmentTotal, "Amount", "Entertainment");
//         if (billsTotal > 0) dataset.addValue(billsTotal, "Amount", "Bills");
//         if (otherTotal > 0) dataset.addValue(otherTotal, "Amount", "Other");
        
//         JFreeChart barChart = ChartFactory.createBarChart(
//             String.format("Expense Distribution (Total: $%.2f)", totalExpenses),
//             "Category", "Amount ($)", dataset,
//             PlotOrientation.VERTICAL, true, true, false);
        
//         CategoryPlot plot = (CategoryPlot) barChart.getPlot();
//         plot.setBackgroundPaint(Constants.APP_COLOR);
        
//         // Clear container and show single chart
//         chartContainer.removeAll();
//         chartContainer.setLayout(new BorderLayout());
//         updateChartPanel(barChart);
        
//         Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
//     }
    
//     private void updateBothCharts() {
//         double[] totals = calculateExpenses();
//         double foodTotal = totals[0], transportTotal = totals[1];
//         double entertainmentTotal = totals[2], billsTotal = totals[3];
//         double otherTotal = totals[4], totalExpenses = totals[5];
        
//         // Create Pie Chart
//         DefaultPieDataset pieDataset = new DefaultPieDataset();
//         if (foodTotal > 0) pieDataset.setValue("Food", foodTotal);
//         if (transportTotal > 0) pieDataset.setValue("Transport", transportTotal);
//         if (entertainmentTotal > 0) pieDataset.setValue("Entertainment", entertainmentTotal);
//         if (billsTotal > 0) pieDataset.setValue("Bills", billsTotal);
//         if (otherTotal > 0) pieDataset.setValue("Other", otherTotal);
        
//         JFreeChart pieChart = ChartFactory.createPieChart(
//             String.format("Pie Chart - Total: $%.2f", totalExpenses),
//             pieDataset, true, true, false);
        
//         PiePlot piePlot = (PiePlot) pieChart.getPlot();
//         piePlot.setBackgroundPaint(Constants.APP_COLOR);
//         piePlot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
//         if (foodTotal > 0) piePlot.setSectionPaint("Food", FOOD_COLOR);
//         if (transportTotal > 0) piePlot.setSectionPaint("Transport", TRANSPORT_COLOR);
//         if (entertainmentTotal > 0) piePlot.setSectionPaint("Entertainment", ENTERTAINMENT_COLOR);
//         if (billsTotal > 0) piePlot.setSectionPaint("Bills", BILLS_COLOR);
//         if (otherTotal > 0) piePlot.setSectionPaint("Other", OTHER_COLOR);
        
//         // Create Bar Chart
//         DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
//         if (foodTotal > 0) barDataset.addValue(foodTotal, "Amount", "Food");
//         if (transportTotal > 0) barDataset.addValue(transportTotal, "Amount", "Transport");
//         if (entertainmentTotal > 0) barDataset.addValue(entertainmentTotal, "Amount", "Entertainment");
//         if (billsTotal > 0) barDataset.addValue(billsTotal, "Amount", "Bills");
//         if (otherTotal > 0) barDataset.addValue(otherTotal, "Amount", "Other");
        
//         JFreeChart barChart = ChartFactory.createBarChart(
//             String.format("Bar Chart - Total: $%.2f", totalExpenses),
//             "Category", "Amount ($)", barDataset,
//             PlotOrientation.VERTICAL, true, true, false);
        
//         CategoryPlot barPlot = (CategoryPlot) barChart.getPlot();
//         barPlot.setBackgroundPaint(Constants.APP_COLOR);
        
//         // Clear container and set up split layout for both charts
//         chartContainer.removeAll();
//         chartContainer.setLayout(new GridLayout(2, 1, 10, 10));
        
//         ChartPanel pieChartPanel = new ChartPanel(pieChart);
//         pieChartPanel.setBackground(Constants.APP_COLOR);
//         pieChartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 250));
        
//         ChartPanel barChartPanel = new ChartPanel(barChart);
//         barChartPanel.setBackground(Constants.APP_COLOR);
//         barChartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 250));
        
//         chartContainer.add(pieChartPanel);
//         chartContainer.add(barChartPanel);
        
//         chartContainer.revalidate();
//         chartContainer.repaint();
        
//         Warning.checkEntertainmentVsBills(entertainmentTotal, billsTotal, totalExpenses, foodTotal, transportTotal, otherTotal);
//     }
    
//     private void updateChartPanel(JFreeChart chart) {
//         if (chartPanel != null && chartPanel.getParent() != null) {
//             // Remove old chart panel
//         }
        
//         chartPanel = new ChartPanel(chart);
//         chartPanel.setBackground(Constants.APP_COLOR);
//         chartPanel.setPreferredSize(new Dimension(Constants.WINDOW_WIDTH - 50, 400));
        
//         chartContainer.add(chartPanel, BorderLayout.CENTER);
//         chartContainer.revalidate();
//         chartContainer.repaint();
//     }
    
//     public void refreshChart() {
//         String preference = userSettings.getChartPreference();
        
//         if (preference.equals("Pie")) {
//             showPieChart();
//         } else if (preference.equals("Bar")) {
//             showBarChart();
//         } else {  // "Both" is the default
//             showBothCharts();
//         }
//     }
// }