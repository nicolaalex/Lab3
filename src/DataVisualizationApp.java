import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DataVisualizationApp {
    private static final String DATA_FILE = "data.txt"; // Sample file
    private static List<DataItem> data;

    public static void main(String[] args) {
        // Load data from file
        data = loadData(DATA_FILE);

        // Console Test (Part A)
        consoleTest(data);

        // GUI Test (Part B)
        SwingUtilities.invokeLater(() -> createAndShowGUI(data));
    }

    private static List<DataItem> loadData(String fileName) {
        List<DataItem> dataItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int value = Integer.parseInt(parts[1].trim());
                    String category = parts[2].trim();
                    dataItems.add(new DataItem(name, value, category));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataItems;
    }

    private static void consoleTest(List<DataItem> data) {
        System.out.println("First item: " + data.get(0));
        System.out.println("10th item: " + (data.size() >= 10 ? data.get(9) : "No 10th item"));
        System.out.println("Total number of entries: " + data.size());
    }

    private static void createAndShowGUI(List<DataItem> data) {
        // Create the JFrame
        JFrame frame = new JFrame("Data Visualization Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create the TablePanel
        TablePanel tablePanel = new TablePanel(data);
        frame.add(tablePanel, BorderLayout.CENTER);

        // Create the StatsPanel
        StatsPanel statsPanel = new StatsPanel(data);
        frame.add(statsPanel, BorderLayout.SOUTH);

        // Create the ChartPanel
        ChartPanel chartPanel = new ChartPanel(data);
        frame.add(chartPanel, BorderLayout.EAST);

        // Create the DetailsPanel
        DetailsPanel detailsPanel = new DetailsPanel(tablePanel.getTable());
        frame.add(detailsPanel, BorderLayout.WEST);

        // Add a title
        JLabel titleLabel = new JLabel("Data Visualization Tool", JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        // Display the frame
        frame.setVisible(true);
    }
}
