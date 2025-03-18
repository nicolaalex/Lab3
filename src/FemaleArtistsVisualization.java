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
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Create components for the table, stats, and filters
        TablePanel tablePanel = new TablePanel(filteredArtists);
        StatsPanel statsPanel = new StatsPanel(filteredArtists);
        FilterPanel filterPanel = new FilterPanel(tablePanel, statsPanel, artists);

        // Add components to the frame
        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(filterPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
