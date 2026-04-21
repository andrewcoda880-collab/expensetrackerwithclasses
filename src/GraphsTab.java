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
    
    // Check if entertainment expenses exceed bills and show warning if there is only if total expenses is over 1k
    BudgetWarning warning = Warning.createEntertainmentVsBillsWarning(entertainmentTotal, billsTotal,totalExpenses);
    
}