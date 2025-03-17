// StatsPanel that shows aggregate statistics about the filtered artists

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    private JLabel totalWorksLabel;
    private JLabel avgWorksLabel;
    private JLabel uniqueNationalitiesLabel;

    public StatsPanel(List<Artist> artists) {
        setLayout(new GridLayout(3, 1));
        totalWorksLabel = new JLabel();
        avgWorksLabel = new JLabel();
        uniqueNationalitiesLabel = new JLabel();
        add(totalWorksLabel);
        add(avgWorksLabel);
        add(uniqueNationalitiesLabel);
        updateData(artists);
    }

    // Update the stats when filters are applied
    public void updateData(List<Artist> artists) {
        int totalWorks = artists.stream().mapToInt(Artist::getWorks).sum();
        double avgWorks = artists.stream().mapToInt(Artist::getWorks).average().orElse(0);
        long uniqueNationalities = artists.stream().map(Artist::getNationality).distinct().count();

        totalWorksLabel.setText("Total Works: " + totalWorks);
        avgWorksLabel.setText("Average Works per Artist: " + avgWorks);
        uniqueNationalitiesLabel.setText("Unique Nationalities: " + uniqueNationalities);
    }
}
