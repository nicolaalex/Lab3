/*import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

class ChartPanel extends JPanel {
    public ChartPanel(List<Artist> artists) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        artists.stream().collect(Collectors.groupingBy(Artist::getNationality, Collectors.summingInt(Artist::getWorks)))
                .forEach((nationality, works) -> dataset.setValue(works, "Works", nationality));
        JFreeChart chart = ChartFactory.createBarChart(
                "Works by Nationality", "Nationality", "Works", dataset, PlotOrientation.VERTICAL, false, true, false);
        add(new org.jfree.chart.ChartPanel(chart));
    }
}
*/