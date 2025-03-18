import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel birthYearLabel;
    private JLabel deathYearLabel;
    private JLabel nationalityLabel;
    private JLabel worksLabel;
    private JLabel genderLabel;

    public DetailsPanel() {
        setLayout(new GridLayout(6, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Artist Details"));

        add(new JLabel("Name:"));
        nameLabel = new JLabel();
        add(nameLabel);

        add(new JLabel("Birth Year:"));
        birthYearLabel = new JLabel();
        add(birthYearLabel);

        add(new JLabel("Death Year:"));
        deathYearLabel = new JLabel();
        add(deathYearLabel);

        add(new JLabel("Nationality:"));
        nationalityLabel = new JLabel();
        add(nationalityLabel);

        add(new JLabel("Works:"));
        worksLabel = new JLabel();
        add(worksLabel);

        add(new JLabel("Gender:"));
        genderLabel = new JLabel();
        add(genderLabel);
    }

    public void updateDetails(Artist artist) {
        if (artist != null) {
            nameLabel.setText(artist.getName());
            birthYearLabel.setText(String.valueOf(artist.getBirthYear()));
            deathYearLabel.setText(artist.getDeathYear() != null ? String.valueOf(artist.getDeathYear()) : "N/A");
            nationalityLabel.setText(artist.getNationality());
            worksLabel.setText(String.valueOf(artist.getWorks()));
            genderLabel.setText(artist.getGender());
        } else {
            nameLabel.setText("");
            birthYearLabel.setText("");
            deathYearLabel.setText("");
            nationalityLabel.setText("");
            worksLabel.setText("");
            genderLabel.setText("");
        }
    }
}
