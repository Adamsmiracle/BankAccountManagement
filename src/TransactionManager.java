
public class TransactionManager {

    private final Transaction[] transactions = new Transaction[200];
    private int transactionCount = 0;


    public void addTransaction(Transaction transaction) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = transaction;
            transactionCount++;
        } else {
            System.err.println("Transaction Manager storage limit reached. Cannot record new transaction.");
        }
    }

    public void viewTransactionsByAccount(String accountNumber) {
        System.out.println("\n--- TRANSACTION HISTORY FOR ACCOUNT: " + accountNumber + " ---");

        // 1. Prepare Header
        System.out.println("-".repeat(65));
        System.out.printf("| %-6s | %-20s | %-10s | %-12s | %-10s |\n",
                "ID", "TIMESTAMP", "TYPE", "AMOUNT", "BALANCE AFTER");
        System.out.println("-".repeat(65));

        // 2. Iterate and Filter
        boolean foundTransactions = false;

        // We iterate through all recorded transactions
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];

            // Check if the transaction belongs to the requested account
            if (t.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                t.displayTransactionDetails(); // Use the display method from Transaction class
                foundTransactions = true;
            }
        }

        System.out.println("-".repeat(65));

        // 3. Handle No Transactions Found
        if (!foundTransactions) {
            System.out.println("| No transactions found for this account.                            |");
            System.out.println("-".repeat(65));
        }
    }


    public double calculateTotalDeposits() {
        double totalDeposits = 0.0;
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];
            if (t.getType().equalsIgnoreCase("DEPOSIT")) {
                totalDeposits += t.getAmount();
            }
        }
        return totalDeposits;
    }


    public double calculateTotalWithdrawals() {
        double totalWithdrawals = 0.0;
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];
            if (t.getType().equalsIgnoreCase("WITHDRAWAL")) {
                totalWithdrawals += t.getAmount();
            }
        }
        return totalWithdrawals;
    }
}