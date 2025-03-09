import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class FemaleArtistsVisualization {
    private static final String DATA_FILE = "femaleartists.csv";
    private static List<Artist> artists;

    public static void main(String[] args) {
        artists = loadArtists(DATA_FILE);
        consoleTest(artists);
        SwingUtilities.invokeLater(() -> createAndShowGUI(artists));
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

    private static void consoleTest(List<Artist> artists) {
        if (!artists.isEmpty()) {
            System.out.println("First artist: " + artists.get(0));
            System.out.println("10th artist: " + (artists.size() >= 10 ? artists.get(9) : "No 10th artist"));
        }
        System.out.println("Total number of artists: " + artists.size());
    }

    private static void createAndShowGUI(List<Artist> artists) {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        TablePanel tablePanel = new TablePanel(artists);
        StatsPanel statsPanel = new StatsPanel(artists);
        ChartPanel chartPanel = new ChartPanel(artists);
        DetailsPanel detailsPanel = new DetailsPanel(tablePanel.getTable());

        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.SOUTH);
        frame.add(chartPanel, BorderLayout.EAST);
        frame.add(detailsPanel, BorderLayout.WEST);

        frame.setVisible(true);
    }
}