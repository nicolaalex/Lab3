// FilterPanel allows for the data to be filtered birth year, nationality, number of works

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class FilterPanel extends JPanel {
    private JComboBox<String> worksFilter, nationalityFilter, birthYearFilter; // Dropdowns for filtering
    private JButton removeFiltersButton; // Button to remove all filters
    private List<Artist> allArtists; // List of all artists
    private TablePanel tablePanel; // Table panel to display artists
    private StatsPanel statsPanel; // Stats panel to display statistics

    // Constructor that initializes the filter panel
    public FilterPanel(TablePanel tablePanel, StatsPanel statsPanel, List<Artist> artists) {
        this.tablePanel = tablePanel;
        this.statsPanel = statsPanel;
        this.allArtists = artists;

        setLayout(new FlowLayout());

        // Initialize dropdown filters
        worksFilter = new JComboBox<>(new String[]{"All", "10", "50", "100", "200"});
        nationalityFilter = new JComboBox<>(getUniqueNationalities()); // Get unique nationalities from artists
        birthYearFilter = new JComboBox<>(new String[]{"All", "Unknown", "1600-1699", "1700-1799", "1800-1899", "1900-1999", "2000-Present"});

        removeFiltersButton = new JButton("Remove All Filters");

        // Add action listeners for the dropdowns and reset button
        worksFilter.addActionListener(e -> applyFilters());
        nationalityFilter.addActionListener(e -> applyFilters());
        birthYearFilter.addActionListener(e -> applyFilters());
        removeFiltersButton.addActionListener(e -> resetFilters());

        // Add components to the filter panel
        add(new JLabel("Works:"));
        add(worksFilter);
        add(new JLabel("Nationality:"));
        add(nationalityFilter);
        add(new JLabel("Birth Year:"));
        add(birthYearFilter);
        add(removeFiltersButton);
    }

    // Method to apply selected filters and update the table and stats
    private void applyFilters() {
        List<Artist> filteredArtists = allArtists.stream()
                .filter(this::filterByWorks)
                .filter(this::filterByNationality)
                .filter(this::filterByBirthYear)
                .collect(Collectors.toList());

        // Update the table and statistics based on filtered artists
        tablePanel.updateData(filteredArtists);
        statsPanel.updateData(filteredArtists);
    }

    // Filter by number of works
    private boolean filterByWorks(Artist artist) {
        String selected = (String) worksFilter.getSelectedItem();
        if ("All".equals(selected)) return true; // If "All" is selected, return true

        int minWorks = Integer.parseInt(selected); // Parse minimum works
        return artist.getWorks() >= minWorks;
    }

    // Filter by nationality
    private boolean filterByNationality(Artist artist) {
        String selected = (String) nationalityFilter.getSelectedItem();
        return "All".equals(selected) || artist.getNationality().equals(selected);
    }

    // Filter by birth year
    private boolean filterByBirthYear(Artist artist) {
        String selected = (String) birthYearFilter.getSelectedItem();
        int birthYear = artist.getBirthYear();

        switch (selected) {
            case "Unknown":
                return birthYear == 0;
            case "1600-1699":
                return birthYear >= 1600 && birthYear <= 1699;
            case "1700-1799":
                return birthYear >= 1700 && birthYear <= 1799;
            case "1800-1899":
                return birthYear >= 1800 && birthYear <= 1899;
            case "1900-1999":
                return birthYear >= 1900 && birthYear <= 1999;
            case "2000-Present":
                return birthYear >= 2000;
            default:
                return true; // "All" selected
        }
    }

    // Method to reset filters to their default values
    private void resetFilters() {
        worksFilter.setSelectedIndex(0);
        nationalityFilter.setSelectedIndex(0);
        birthYearFilter.setSelectedIndex(0);
        applyFilters(); // Reapply filters to reset the view
    }

    // Get nationalities from all artists for the nationality filter dropdown
    private String[] getUniqueNationalities() {
        List<String> nationalities = allArtists.stream()
                .map(Artist::getNationality)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        nationalities.add(0, "All"); // Add "All" option
        return nationalities.toArray(new String[0]); // Convert the list to an array
    }
}
