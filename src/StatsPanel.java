// StatsPanel that shows aggregate statistics about the filtered artists

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    private JLabel totalWorksLabel;
    private JLabel avgWorksLabel;
    private JLabel uniqueNationalitiesLabel;

    // Constructor to initialize the stats panel
    public StatsPanel(List<Artist> artists) {
        setLayout(new GridLayout(3, 1)); // Use GridLayout for the labels
        totalWorksLabel = new JLabel();
        avgWorksLabel = new JLabel();
        uniqueNationalitiesLabel = new JLabel();
        add(totalWorksLabel);
        add(avgWorksLabel);
        add(uniqueNationalitiesLabel);
        updateData(artists); // Update the stats initially
    }

    // Method to update statistics when the filters are applied
    public void updateData(List<Artist> artists) {
        int totalWorks = artists.stream().mapToInt(Artist::getWorks).sum(); // Total number of works
        double avgWorks = artists.stream().mapToInt(Artist::getWorks).average().orElse(0); // Average number of works per artist
        long uniqueNationalities = artists.stream().map(Artist::getNationality).distinct().count(); // Count of unique nationalities

        // Update the labels with the stats
        totalWorksLabel.setText("Total Works: " + totalWorks);
        avgWorksLabel.setText("Average Works per Artist: " + avgWorks);
        uniqueNationalitiesLabel.setText("Unique Nationalities: " + uniqueNationalities);
    }
}
