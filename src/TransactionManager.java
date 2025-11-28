// import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Comparator;

public class TransactionManager {

    // Singleton instance so all parts of the app record/query the same transactions
    private static final TransactionManager INSTANCE = new TransactionManager();

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

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
        sortTransaction();
        System.out.println("\nTRANSACTION HISTORY FOR ACCOUNT: " + accountNumber);

        // 1. Prepare Header
        System.out.println("-".repeat(78));
        System.out.printf("| %-6s | %-20s | %-10s | %-12s | %-10s |\n",
                "ID", "TIMESTAMP", "TYPE", "AMOUNT", "BALANCE AFTER");
        System.out.println("-".repeat(78));

        // 2. Iterate and Filter
        boolean foundTransactions = false;

        int matchedCount = 0;
        double totalDeposits = 0.0;
        double totalWithdrawals = 0.0;

        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];

            if (t.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                // Print the details directly using the table format
                System.out.printf("| %-6s | %-20s | %-10s | $%-11.2f | $%-9.2f |\n",
                        t.getTransactionId(),
                        t.getFormattedTimestamp(),
                        t.getType().toUpperCase(),
                        t.getAmount(),
                        t.getBalanceAfter()
                );

                matchedCount++;
                foundTransactions = true;

                if (t.getType().equalsIgnoreCase("DEPOSIT") || t.getType().equalsIgnoreCase("Deposit")) {
                    totalDeposits += t.getAmount();
                } else if (t.getType().equalsIgnoreCase("WITHDRAWAL") || t.getType().equalsIgnoreCase("Withdrawal")) {
                    totalWithdrawals += t.getAmount();
                }
            }
        }
        System.out.println("-".repeat(78));

        // 3. Handle No Transactions Found
        if (!foundTransactions) {
            System.out.println("| No transactions found for this account.                             |");
            System.out.println("-".repeat(78));
        } else {
            // Print summary totals similar to screenshot: count, total deposits, total withdrawals, net change
            System.out.printf("\nTotal Transactions: %d\n", matchedCount);
            System.out.printf("Total Deposits: $%,.2f\n", totalDeposits);
            System.out.printf("Total Withdrawals: $%,.2f\n", totalWithdrawals);
            double net = totalDeposits - totalWithdrawals;
            System.out.printf("Net Change: $%,.2f\n", net);
            System.out.println("Total Bank Transactions "+ transactionCount);
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


    public int getTransactionCount(){
        return transactionCount;
    }


    public void sortTransaction() {
        Arrays.sort(transactions, 0, transactionCount,
                Comparator.comparing(Transaction::getTimestamp).reversed()
        );
    }


}