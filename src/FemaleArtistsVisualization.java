// Main application class that loads the artist data from a CSV file and processes it

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class FemaleArtistsVisualization {
    private static final String DATA_FILE = "src/femaleartists.csv";

    private static List<Artist> artists;

    public static void main(String[] args) {
        artists = loadArtists(DATA_FILE);
        consoleTest(artists); // Console Test
        SwingUtilities.invokeLater(() -> createAndShowGUI(artists)); // GUI Test
    }

    // Loads the artist data from the file
    private static List<Artist> loadArtists(String fileName) {
        List<Artist> artistList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {  // Ensure all columns are present
                    String name = parts[0].trim();
                    String country = parts[1].trim();

                    // Parse the birth year and checks for empty strings
                    int birthYear = parseYear(parts[2].trim());

                    // Parse the death year (it can be empty)
                    Integer deathYear = parts[3].trim().isEmpty() ? null : parseYear(parts[3].trim());

                    String gender = parts[4].trim();

                    // Parse the number of artworks and checks for empty strings
                    int works = parts[5].trim().isEmpty() ? 0 : Integer.parseInt(parts[5].trim());

                    artistList.add(new Artist(name, birthYear, deathYear, country, works));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    // Helper method to parse the year. Returns 0 if the input is empty or invalid
    private static int parseYear(String yearString) {
        if (yearString.isEmpty()) {
            return 0; // Default value for missing year
        }
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing year: " + yearString);
            return 0; // Default value for invalid year
        }
    }


    // Console Test
    private static void consoleTest(List<Artist> artists) {
        // Print out the attributes of the first data item
        if (!artists.isEmpty()) {
            System.out.println("First artist: " + artists.get(0));
        }

        // Print out the attributes of the 10th item (if available)
        if (artists.size() >= 10) {
            System.out.println("10th artist: " + artists.get(9));
        } else {
            System.out.println("There are less than 10 artists in the data.");
        }

        // Display the total number of entries in the data
        System.out.println("Total number of artists: " + artists.size());
    }

    // GUI Test
    private static void createAndShowGUI(List<Artist> artists) {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Create TablePanel and StatsPanel
        TablePanel tablePanel = new TablePanel(artists);
        StatsPanel statsPanel = new StatsPanel(artists);

        // Add components to frame
        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.SOUTH); // Add StatsPanel

        frame.setVisible(true);
    }
}