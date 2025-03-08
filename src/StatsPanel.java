class StatsPanel extends JPanel {
    private JLabel countLabel;
    private JLabel avgLabel;
    private JLabel sumLabel;

    public StatsPanel(List<DataItem> data) {
        setLayout(new GridLayout(1, 3));

        int count = data.size();
        double avg = data.stream().mapToInt(DataItem::getValue).average().orElse(0);
        int sum = data.stream().mapToInt(DataItem::getValue).sum();

        countLabel = new JLabel("Count: " + count);
        avgLabel = new JLabel("Avg: " + avg);
        sumLabel = new JLabel("Sum: " + sum);

        add(countLabel);
        add(avgLabel);
        add(sumLabel);
    }
}