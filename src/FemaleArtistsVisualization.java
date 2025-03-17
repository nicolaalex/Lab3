import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FemaleArtistsVisualization {
    private static final String DATA_FILE = "src/femaleartists.csv";
    private static List<Artist> artists;
    private static List<Artist> filteredArtists;

    public static void main(String[] args) {
        artists = loadArtists(DATA_FILE);
        filteredArtists = new ArrayList<>(artists);
        consoleTest(artists);
        SwingUtilities.invokeLater(FemaleArtistsVisualization::createAndShowGUI);
    }

    private static List<Artist> loadArtists(String fileName) {
        List<Artist> artistList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String name = parts[0].trim();
                    String country = parts[1].trim();
                    int birthYear = parseYear(parts[2].trim());
                    Integer deathYear = parts[3].trim().isEmpty() ? null : parseYear(parts[3].trim());
                    String gender = parts[4].trim();
                    int works = parts[5].trim().isEmpty() ? 0 : Integer.parseInt(parts[5].trim());

                    artistList.add(new Artist(name, birthYear, deathYear, country, works, gender));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    private static int parseYear(String yearString) {
        if (yearString.isEmpty()) return 0;
        try {
            return Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing year: " + yearString);
            return 0;
        }
    }

    private static void consoleTest(List<Artist> artists) {
        if (!artists.isEmpty()) {
            System.out.println("First artist: " + artists.get(0));
        }
        if (artists.size() >= 10) {
            System.out.println("10th artist: " + artists.get(9));
        } else {
            System.out.println("There are less than 10 artists in the data.");
        }
        System.out.println("Total number of artists: " + artists.size());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        TablePanel tablePanel = new TablePanel(filteredArtists);
        StatsPanel statsPanel = new StatsPanel(filteredArtists);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());

        JComboBox<String> worksFilter = new JComboBox<>(new String[]{"All", "10", "50", "100", "200"});
        worksFilter.setSelectedIndex(0);
        worksFilter.addActionListener(e -> applyFilters(tablePanel, statsPanel, filterPanel));

        JComboBox<String> nationalityFilter = new JComboBox<>(getUniqueNationalities());
        nationalityFilter.setSelectedIndex(0);
        nationalityFilter.addActionListener(e -> applyFilters(tablePanel, statsPanel, filterPanel));

        JComboBox<String> birthYearFilter = new JComboBox<>(new String[]{"All", "1800", "1900", "2000", "2010"});
        birthYearFilter.setSelectedIndex(0);
        birthYearFilter.addActionListener(e -> applyFilters(tablePanel, statsPanel, filterPanel));

        filterPanel.add(new JLabel("Filter by Number of Works:"));
        filterPanel.add(worksFilter);
        filterPanel.add(new JLabel("Filter by Nationality:"));
        filterPanel.add(nationalityFilter);
        filterPanel.add(new JLabel("Filter by Birth Year:"));
        filterPanel.add(birthYearFilter);

        frame.add(new JLabel("Female Artists Visualization", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(filterPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private static String[] getUniqueNationalities() {
        Set<String> nationalities = artists.stream()
                .map(Artist::getNationality)
                .collect(Collectors.toSet());
        List<String> sortedList = new ArrayList<>(nationalities);
        Collections.sort(sortedList);
        sortedList.add(0, "All");
        return sortedList.toArray(new String[0]);
    }

    private static void applyFilters(TablePanel tablePanel, StatsPanel statsPanel, JPanel filterPanel) {
        JComboBox<String> worksFilter = (JComboBox<String>) filterPanel.getComponent(1);
        JComboBox<String> nationalityFilter = (JComboBox<String>) filterPanel.getComponent(3);
        JComboBox<String> birthYearFilter = (JComboBox<String>) filterPanel.getComponent(5);

        String selectedWorks = (String) worksFilter.getSelectedItem();
        String selectedNationality = (String) nationalityFilter.getSelectedItem();
        String selectedBirthYear = (String) birthYearFilter.getSelectedItem();

        filteredArtists = artists.stream()
                .filter(artist -> selectedWorks.equals("All") || artist.getWorks() >= Integer.parseInt(selectedWorks))
                .filter(artist -> selectedNationality.equals("All") || artist.getNationality().equals(selectedNationality))
                .filter(artist -> {
                    int birthYear = artist.getBirthYear();
                    if (selectedBirthYear.equals("All")) return true;
                    switch (selectedBirthYear) {
                        case "1800": return birthYear >= 1800 && birthYear <= 1899;
                        case "1900": return birthYear >= 1900 && birthYear <= 1999;
                        case "2000": return birthYear >= 2000 && birthYear <= 2009;
                        case "2010": return birthYear >= 2010;
                        default: return true;
                    }
                })
                .collect(Collectors.toList());

        tablePanel.updateData(filteredArtists);
        statsPanel.updateData(filteredArtists);
    }
}
