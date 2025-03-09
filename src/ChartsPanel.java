class ChartPanel extends JPanel {
    private List<DataItem> data;

    public ChartPanel(List<DataItem> data) {
        this.data = data;
        setLayout(new BorderLayout());

        // Prepare chart data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (DataItem item : data) {
            dataset.addValue(item.getValue(), item.getCategory(), item.getName());
        }

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Data Visualization", // chart title
                "Category", // x-axis label
                "Value", // y-axis label
                dataset, // dataset
                PlotOrientation.VERTICAL,
                true, true, false);

        // Add chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        add(chartPanel, BorderLayout.CENTER);
    }
}
