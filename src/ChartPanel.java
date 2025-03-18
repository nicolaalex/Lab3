// Chart Panel that displays the number of work

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ChartPanel extends JPanel {

    private JFreeChart chart;

    public ChartPanel(List<Artist> artists) {
        // Create a simple chart based on artists' data
        DefaultCategoryDataset dataset = createDataset(artists);
        chart = createChart(dataset);

        // Add JFreeChart's ChartPanel to this JPanel
        org.jfree.chart.ChartPanel chartPanel = new org.jfree.chart.ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        chartPanel.setMouseWheelEnabled(true);

        // Add listener for chart clicks
        chartPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle the chart click event
                System.out.println("Chart clicked at: " + e.getPoint());

            }
        });

        // Add chart panel to this JPanel
        this.add(chartPanel, BorderLayout.CENTER);
    }

    // Method to create the dataset for the chart
    private DefaultCategoryDataset createDataset(List<Artist> artists) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Use number of works as data
        for (Artist artist : artists) {
            dataset.addValue(artist.getWorks(), "Number of Works", artist.getName());
        }

        return dataset;
    }

    // Method to create the chart from the dataset
    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Female Artists",       // Chart title
                "Artist",               // X-Axis Label
                "Number of Works",      // Y-Axis Label
                dataset,                // Dataset
                PlotOrientation.VERTICAL, // Chart orientation
                true,                    // Include legend
                true,                    // Tooltips
                false                    // URLs
        );
    }
}
