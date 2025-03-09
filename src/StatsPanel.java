/*
// StatsPanel that shows aggregate statistics

import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    public StatsPanel(List<Artist> artists) {
        setLayout(new GridLayout(3, 1));
        int totalWorks = artists.stream().mapToInt(Artist::getWorks).sum();
        double avgWorks = artists.stream().mapToInt(Artist::getWorks).average().orElse(0);
        long uniqueNationalities = artists.stream().map(Artist::getNationality).distinct().count();
        add(new JLabel("Total Works: " + totalWorks));
        add(new JLabel("Average Works per Artist: " + avgWorks));
        add(new JLabel("Unique Nationalities: " + uniqueNationalities));
    }
}
*/