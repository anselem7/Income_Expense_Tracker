package Income_expense_tracker;

/* IncomeExpenseEntry represents a single entry for income and expenses
* Each entry contains a date, description, amount and type
* */
public class IncomeExpenseEntry {

    private String date;
    private String description;
    private double amount;
    private String type; // the type of entry (expense or income)

    public IncomeExpenseEntry(String date, String description, double amount, String type ) {
        this.date=date;
        this.description=description;
        this.amount=amount;
        this.type=type;
    }

    public String getDate(){ return date; }
    public String getDescription(){ return description; }
    public double getAmount(){ return amount; }
    public String getType(){ return type; }
}
