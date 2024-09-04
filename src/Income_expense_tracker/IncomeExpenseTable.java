package Income_expense_tracker;

import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;



public class IncomeExpenseTable extends AbstractTableModel {

// Creating a list to store the entries(rows) in the table
    private final List<IncomeExpenseEntry> entries;

// Creating column names for the table
    private final String [] columnNames= {"Date","Description","Amount","Type"};

// Constructor to initialize the table model
    public IncomeExpenseTable(){
        entries = new ArrayList<>();
    }

    /* To add a new entry to the table
    *  @param entry The income or expense to add to the table
    * */

    public void addEntry (IncomeExpenseEntry entry){
        entries.add(entry);
//  Notifies that a new row needs to be added
        fireTableRowsInserted(entries.size()-1,entries.size()-1);
    }

    @Override
    public int getRowCount() {
        return entries.size() ;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IncomeExpenseEntry entry = entries.get(rowIndex);
//  Return the value for the cell based on the column index
        switch(columnIndex){
            case 0: return entry.getDate();
            case 1: return entry.getDescription();
            case 2: return entry.getAmount();
            case 3: return entry.getType();
            default: return null;


        }
    }
}
