import java.time.format.DateTimeFormatter;

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

//Get transaction by account number
    public Transaction[] getTransactionsByAccount(String accountNumber) {
        int matchCount = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getAccountNumber().equalsIgnoreCase(accountNumber)) {
                matchCount++;
            }
        }

        // 2. Initialize the result array with the exact size needed
        Transaction[] accountTransactions = new Transaction[matchCount];

        // If no transactions are found, return the empty array immediately
        if (matchCount == 0) {
            return accountTransactions;
        }

        // 3. Second Pass: Copy the matching transactions into the result array
        int resultIndex = 0;
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];

            if (t.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                accountTransactions[resultIndex] = t;
                resultIndex++;
            }
        }

        return accountTransactions;
    }



// Assuming Transaction has getters: getId(), getDateTime(), getType(), getAmount(), getBalanceAfter()
    public void viewTransactionsByAccount(String accountNumber) {
        System.out.println("\n--- TRANSACTION HISTORY FOR ACCOUNT: " + accountNumber + " ---");

        // 1. Prepare Header
        System.out.println("-".repeat(65));
        System.out.printf("| %-6s | %-20s | %-10s | %-12s | %-10s |\n",
                "ID", "TIMESTAMP", "TYPE", "AMOUNT", "BALANCE AFTER");
        System.out.println("-".repeat(65));

        // 2. Iterate and Filter
        boolean foundTransactions = false;

        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];

            if (t.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                // FIX: Print the details directly using the table format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
                System.out.printf("| %-6s | %-20s | %-10s | $%-11.2f | $%-9.2f |\n",
                        t.getTransactionId(),
                        "12 Nov 2025", // Assuming this returns a formatted string
                        t.getType().toUpperCase(),
                        t.getAmount(),
                        t.getBalanceAfter() // Assuming you have a getter for the balance after the transaction
                );
                foundTransactions = true;
            }
        }

        System.out.println("-".repeat(65));

        // 3. Handle No Transactions Found
        if (!foundTransactions) {
            System.out.println("| No transactions found for this account.                             |");
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