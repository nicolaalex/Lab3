import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class FemaleArtistsVisualization {
    private static final String DATA_FILE = "femaleartists.csv";
    private static List<Artist> artists;

    public static void main(String[] args) {
        artists = loadArtists(DATA_FILE);
        consoleTest(artists); // A. Console Test
        SwingUtilities.invokeLater(() -> createAndShowGUI(artists)); // B. GUI Test
    }

    private static List<Artist> loadArtists(String fileName) {
        List<Artist> artistList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    int birthYear = Integer.parseInt(parts[1].trim());
                    Integer deathYear = parts[2].trim().isEmpty() ? null : Integer.parseInt(parts[2].trim());
                    String nationality = parts[3].trim();
                    int works = Integer.parseInt(parts[4].trim());
                    artistList.add(new Artist(name, birthYear, deathYear, nationality, works));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    // Console Test for Part A
    private static void consoleTest(List<Artist> artists) {
        // A1: Print out the attributes of the first data item
        if (!artists.isEmpty()) {
            System.out.println("First artist: " + artists.get(0));
        }

        // A2: Print out the attributes of the 10th item (if available)
        if (artists.size() >= 10) {
            System.out.println("10th artist: " + artists.get(9));
        } else {
            System.out.println("There are less than 10 artists in the data.");
        }

        // A3: Display the total number of entries in the data
        System.out.println("Total number of artists: " + artists.size());
    }

    // GUI Test for Part B
    private static void createAndShowGUI(List<Artist> artists) {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        // Create TablePanel
        TablePanel tablePanel = new TablePanel(artists);

        // Add TablePanel to the frame
        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH); // B2: Application has a clear title
        frame.add(tablePanel, BorderLayout.CENTER); // B1: Data appears in TablePanel

        frame.setVisible(true);
    }
}
