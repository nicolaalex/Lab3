// Main application class that loads the artist data from a CSV file and processes it

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class FemaleArtistsVisualization {

    private static List<Artist> artists = new ArrayList<>();
    private static List<Artist> filteredArtists = new ArrayList<>();
    private static JPanel filterPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FemaleArtistsVisualization::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Female Artists Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Load data
        loadData();

        // Create the table and stats panels
        TablePanel tablePanel = new TablePanel(filteredArtists);
        StatsPanel statsPanel = new StatsPanel(filteredArtists);

        // Create filters
        filterPanel = createFilterPanel();

        // Set up layout
        frame.add(filterPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.pack();
        frame.setVisible(true);
    }

    private static void loadData() {
        // Example data for artists (replace with actual loading code)
        artists.add(new Artist("Artist 1", 1980, "USA", 50));
        artists.add(new Artist("Artist 2", 1990, "UK", 30));
        artists.add(new Artist("Artist 3", 1985, "Canada", 70));
        artists.add(new Artist("Artist 4", 1995, "USA", 20));
        artists.add(new Artist("Artist 5", 1980, "France", 60));

        // Initially, show all artists
        filteredArtists = new ArrayList<>(artists);
    }

    private static JPanel createFilterPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        // Filter by number of works (greater than or equal to)
        Integer[] workOptions = {20, 30, 50, 70};
        JComboBox<Integer> worksComboBox = new JComboBox<>(workOptions);
        worksComboBox.insertItemAt("All", 0); // Add "All" option
        worksComboBox.setSelectedIndex(0);

        worksComboBox.addActionListener(e -> applyFilters());

        // Filter by nationality
        JComboBox<String> nationalityComboBox = new JComboBox<>(new String[]{"All", "USA", "UK", "Canada", "France"});
        nationalityComboBox.setSelectedIndex(0);
        nationalityComboBox.addActionListener(e -> applyFilters());

        // Filter by birth year
        Integer[] birthYearOptions = {1980, 1985, 1990, 1995};
        JComboBox<Integer> birthYearComboBox = new JComboBox<>(birthYearOptions);
        birthYearComboBox.insertItemAt("All", 0); // Add "All" option
        birthYearComboBox.setSelectedIndex(0);
        birthYearComboBox.addActionListener(e -> applyFilters());

        // Add components to filter panel
        panel.add(new JLabel("Works Filter:"));
        panel.add(worksComboBox);
        panel.add(new JLabel("Nationality Filter:"));
        panel.add(nationalityComboBox);
        panel.add(new JLabel("Birth Year Filter:"));
        panel.add(birthYearComboBox);

        return panel;
    }

    private static void applyFilters() {
        JComboBox<Integer> worksFilter = (JComboBox<Integer>) ((JPanel) filterPanel.getComponent(1));
        JComboBox<String> nationalityFilter = (JComboBox<String>) ((JPanel) filterPanel.getComponent(3));
        JComboBox<Integer> birthYearFilter = (JComboBox<Integer>) ((JPanel) filterPanel.getComponent(5));

        Integer minWorks = (Integer) worksFilter.getSelectedItem();
        String selectedNationality = (String) nationalityFilter.getSelectedItem();
        Integer selectedBirthYear = (Integer) birthYearFilter.getSelectedItem();

        // Filter based on selected options
        filteredArtists = artists.stream()
                .filter(artist -> minWorks == null || artist.getWorks() >= minWorks)
                .filter(artist -> selectedNationality.equals("All") || artist.getNationality().equals(selectedNationality))
                .filter(artist -> selectedBirthYear == null || selectedBirthYear.equals(artist.getBirthYear())) // Filter by birth year
                .collect(Collectors.toList());

        // Update table and stats
        TablePanel tablePanel = new TablePanel(filteredArtists);
        StatsPanel statsPanel = new StatsPanel(filteredArtists);
        tablePanel.updateData(filteredArtists);
        statsPanel.updateData(filteredArtists);
    }

    // Artist class for the data model
    static class Artist {
        private String name;
        private int birthYear;
        private String nationality;
        private int works;

        public Artist(String name, int birthYear, String nationality, int works) {
            this.name = name;
            this.birthYear = birthYear;
            this.nationality = nationality;
            this.works = works;
        }

        public String getName() {
            return name;
        }

        public int getBirthYear() {
            return birthYear;
        }

        public String getNationality() {
            return nationality;
        }

        public int getWorks() {
            return works;
        }
    }

    // TablePanel for displaying artist data
    static class TablePanel extends JPanel {
        private JTable table;

        public TablePanel(List<Artist> artists) {
            setLayout(new BorderLayout());
            String[] columns = {"Name", "Birth Year", "Nationality", "Works"};
            Object[][] data = new Object[artists.size()][4];
            for (int i = 0; i < artists.size(); i++) {
                data[i][0] = artists.get(i).getName();
                data[i][1] = artists.get(i).getBirthYear();
                data[i][2] = artists.get(i).getNationality();
                data[i][3] = artists.get(i).getWorks();
            }
            table = new JTable(data, columns);
            add(new JScrollPane(table), BorderLayout.CENTER);
        }

        public void updateData(List<Artist> artists) {
            String[] columns = {"Name", "Birth Year", "Nationality", "Works"};
            Object[][] data = new Object[artists.size()][4];
            for (int i = 0; i < artists.size(); i++) {
                data[i][0] = artists.get(i).getName();
                data[i][1] = artists.get(i).getBirthYear();
                data[i][2] = artists.get(i).getNationality();
                data[i][3] = artists.get(i).getWorks();
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
        }
    }

    // StatsPanel for displaying aggregate statistics
    static class StatsPanel extends JPanel {
        public StatsPanel(List<Artist> artists) {
            setLayout(new BorderLayout());
            // Add some simple statistics (e.g., total works)
            int totalWorks = artists.stream().mapToInt(Artist::getWorks).sum();
            JLabel label = new JLabel("Total Works: " + totalWorks);
            add(label, BorderLayout.CENTER);
        }

        public void updateData(List<Artist> artists) {
            // Update statistics as required
            int totalWorks = artists.stream().mapToInt(Artist::getWorks).sum();
            JLabel label = new JLabel("Total Works: " + totalWorks);
            removeAll();
            add(label, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }
}
