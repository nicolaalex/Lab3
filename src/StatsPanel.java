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

class ChartPanel extends JPanel {
    public ChartPanel(List<Artist> artists) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        artists.stream().collect(Collectors.groupingBy(Artist::getNationality, Collectors.summingInt(Artist::getWorks)))
                .forEach(dataset::setValue);
        JFreeChart chart = ChartFactory.createBarChart("Works by Nationality", "Nationality", "Works", dataset, PlotOrientation.VERTICAL, false, true, false);
        add(new ChartPanel(chart));
    }
}