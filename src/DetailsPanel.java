// Details Panel that shows information of specific artist

import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JTextArea detailsArea;



    public DetailsPanel() {
        setLayout(new BorderLayout());
        detailsArea = new JTextArea(10, 30);
        detailsArea.setEditable(false);
        add(new JScrollPane(detailsArea), BorderLayout.CENTER);

        // Title for Artist Details
        JLabel titleLabel = new JLabel("Artist Details (click on artist)", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        add(titleLabel, BorderLayout.NORTH);


    }

    // Update the details panel with the selected artist's information
    public void updateDetails(Artist artist) {
        String details = "Name: " + artist.getName() + "\n"
                + "Birth Year: " + artist.getBirthYear() + "\n"
                + "Death Year: " + (artist.getDeathYear() == null ? "N/A" : artist.getDeathYear()) + "\n"
                + "Country: " + artist.getNationality() + "\n"
                + "Number of Works: " + artist.getWorks() + "\n"
                + "Gender: " + artist.getGender();
        detailsArea.setText(details);
    }
}
