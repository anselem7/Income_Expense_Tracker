package Income_expense_tracker;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;


// The ExpenseIncomeTracker class extends the Jframe to create the main application window

public class IncomeExpensesTracker extends JFrame {

    private final IncomeExpenseTable model;
    private final JTable table;
    private final JTextField dateField;
    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String> typeCombobox;
    private final JButton addButton;
    private final JLabel balanceLabel;
    private double balance; //The calulated balance from the income and expense

    public IncomeExpensesTracker() {

        // Applied flatdarklaf  look and feel for modern appearance
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set FlatDarkLaf LookAndFeel");

        }

        //  Color schemes for swing components
        UIManager.put("TextField.foreground", Color.white);
        UIManager.put("TextField.background", Color.LIGHT_GRAY);
        UIManager.put("TextField.caretForeground", Color.white);
        UIManager.put("ComboBox.foreground", Color.CYAN);
        UIManager.put("ComboBox.selectionForeground", Color.white);
        UIManager.put("ComboBox.selectionBackground", Color.black);
        UIManager.put("Button.foreground", Color.white);
        UIManager.put("Button.background", Color.GREEN.darker());
        UIManager.put("Label.foreground", Color.white);

        // set the default font for the entire application
        Font customFont = new Font("arial", Font.PLAIN, 18);
        UIManager.put("Label.font", customFont);
        UIManager.put("TextField.font", customFont);
        UIManager.put("ComboBox.font", customFont);
        UIManager.put("Button.font", customFont);

        // Initialize the table model and balance variable
        balance = 0.0;
        model = new IncomeExpenseTable();

        // Create a table and setup a scrollpane to display the data
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Create input fields and components for adding new entries
        dateField = new JTextField(10);
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        typeCombobox = new JComboBox<>(new String[]{"Expense", "Income"});

        // Attach an ActionListener to the 'add' button to handle new entries addition
        addButton = new JButton("Add");
        addButton.addActionListener(e -> addEntry());
        balanceLabel = new JLabel("Balance : HUF " + balance);

        // Create input panels to handle input components
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Date"));
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Type"));
        inputPanel.add(typeCombobox);

        inputPanel.add(addButton);

        // create a bottom panel to display the balance
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(balanceLabel);
        setLayout(new BorderLayout());

        // set the layout of the main frame and add components to their right positions
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // set the title, default close operation and visibility of the mainframe
        setTitle("Income and Expenses Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
        // A method to handle adding new entries into the table
    private void addEntry() {
        // Get input values from input fields
        String date = dateField.getText();
        String description = descriptionField.getText();
        String amounts = amountField.getText();
        String type = (String) typeCombobox.getSelectedItem();
        double amount;

        // To validate input values
        if (amounts.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter an amount", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;

        }

        try {
            amount = Double.parseDouble(amounts);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entry", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;

        }
        // Change expenses into negative values
        if(type.equals("Expense")) {
                amount *= -1;

            }

        // Create a new entry and add it to the table

            IncomeExpenseEntry entry = new IncomeExpenseEntry(date, description, amount, type);
            model.addEntry(entry);
            balance += amount;
            balanceLabel.setText("Balance: HUF " + balance);

            clearInputFields();
    }

        // Method to clear the input fields
        private void clearInputFields()
        {
            dateField.setText("");
            descriptionField.setText("");
            amountField.setText("");
            typeCombobox.setSelectedIndex(0);

        }


    }


