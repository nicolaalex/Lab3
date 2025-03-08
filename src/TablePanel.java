class TablePanel extends JPanel {
    private JTable table;
    private List<DataItem> data;

    public TablePanel(List<DataItem> data) {
        this.data = data;
        setLayout(new BorderLayout());

        // Table data
        String[] columnNames = {"Name", "Value", "Category"};
        Object[][] tableData = data.stream()
                .map(item -> new Object[]{item.getName(), item.getValue(), item.getCategory()})
                .toArray(Object[][]::new);

        table = new JTable(tableData, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }
}