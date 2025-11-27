import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

//    Static fields
    private static int transactionCounter;

//    Private fields;
    private final String transactionId;
    private String accountNumber;
    private String type;
    private double amount;
    private double balanceAfter;
        private final LocalDateTime timestamp;
        private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

    public Transaction(String accountNumber, String type, double amount, double balanceAfter) {
        transactionCounter++;
        this.transactionId = String.format("TXN%03d", transactionCounter);
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();

    }

    //    METHODS
    public void displayTransactionDetails() {
        // Format the amount to show +/- sign and 2 decimal places
        String sign = this.type.equals("Deposit") ? "+" : "-";
        String formattedAmount = String.format("%s$%.2f", sign, this.amount);
        String formattedBalance = String.format("$%.2f", this.balanceAfter);

        // Formatting columns for console output
        System.out.printf("| %-6s | %-20s | %-10s | %-12s | %-10s |\n",
                transactionId,
                getFormattedTimestamp(),
                type,
                formattedAmount,
                formattedBalance);
    }

    public String getFormattedTimestamp() {
        return this.timestamp.format(TIMESTAMP_FORMATTER);
    }




//    GETTERS AND SETTERS
    public static int getTransactionCounter() {
        return transactionCounter;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }




}
