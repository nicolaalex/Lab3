// Main application class that loads the artist data from a CSV file and processes it

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class FemaleArtistsVisualization {
    private static final String DATA_FILE = "src/femaleartists.csv";
    private static List<Artist> artists; // List to hold all artists
    private static List<Artist> filteredArtists; // List to hold filtered artists based on user input

    public static void main(String[] args) {
        // Load artists data from the CSV file and store it in the artists list
        artists = loadArtists(DATA_FILE);
        filteredArtists = new ArrayList<>(artists); // Initialize filteredArtists with all artists
        SwingUtilities.invokeLater(() -> createAndShowGUI()); // Start the GUI
    }

    // Method to load artist data from the CSV file into a list of Artist objects
    private static List<Artist> loadArtists(String fileName) {
        List<Artist> artistList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip header line in the CSV file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split the line by commas
                if (parts.length == 6) { // Ensure the line contains correct number of columns
                    // Extract data and create an Artist object
                    String name = parts[0].trim();
                    String country = parts[1].trim().isEmpty() ? " " : parts[1].trim();
                    int birthYear = parseYear(parts[2].trim());
                    Integer deathYear = parts[3].trim().isEmpty() ? null : parseYear(parts[3].trim());
                    String gender = parts[4].trim();
                    int works = parts[5].trim().isEmpty() ? 0 : Integer.parseInt(parts[5].trim());

                    artistList.add(new Artist(name, birthYear, deathYear, country, works, gender)); // Add the artist to the list
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print any IO exceptions for debugging
        }
        return artistList; // Return the list of artists
    }

    // Method to parse a year string into an integer, returns 0 if invalid
    private static int parseYear(String yearString) {
        if (yearString.isEmpty()) return 0; // If year string is empty, return 0
        try {
            return Integer.parseInt(yearString); // Try to parse the year
        } catch (NumberFormatException e) {
            return 0; // Return 0 if the year string is invalid
        }
    }

    // Method to create and display the GUI
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Create components for the table, stats, filters, and details panel
        DetailsPanel detailsPanel = new DetailsPanel(); // show selected artist details
        TablePanel tablePanel = new TablePanel(filteredArtists, detailsPanel);
        StatsPanel statsPanel = new StatsPanel(filteredArtists);
        FilterPanel filterPanel = new FilterPanel(tablePanel, statsPanel, artists);
        ChartPanel chartPanel = new ChartPanel(filteredArtists);

        // Add a listener to update the details panel when a row in the table is selected
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensure final selection
                int selectedRow = tablePanel.getTable().getSelectedRow();
                if (selectedRow >= 0) {
                    Artist selectedArtist = filteredArtists.get(selectedRow);
                    detailsPanel.updateDetails(selectedArtist);
                }
            }
        });

        // Set up layout to display table and chart together
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Add the filter panel above the stats panel
        topPanel.add(filterPanel, BorderLayout.NORTH);  // Place the filter panel here
        topPanel.add(tablePanel, BorderLayout.CENTER);  // Table panel in the center
        topPanel.add(statsPanel, BorderLayout.EAST);    // Stats panel on the right

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(chartPanel, BorderLayout.CENTER);
        bottomPanel.add(detailsPanel, BorderLayout.WEST); // Display artist details on the left side

        // Add components to the main frame
        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(topPanel, BorderLayout.CENTER); // Top part: Filter + Table + Stats
        frame.add(bottomPanel, BorderLayout.SOUTH); // Bottom part: Chart + Details

        frame.setVisible(true);
    }
}
