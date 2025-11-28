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
        System.out.println("-".repeat(78));
        System.out.printf("| %-6s | %-20s | %-10s | %-12s | %-10s |\n",
                "ID", "TIMESTAMP", "TYPE", "AMOUNT", "BALANCE AFTER");
        System.out.println("-".repeat(78));

        boolean foundTransactions = false;

        int matchedCount = 0;
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];

            if (t.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                System.out.printf("| %-6s | %-20s | %-10s | $%-11.2f | $%-9.2f |\n",
                        t.getTransactionId(),
                        t.getFormattedTimestamp(),
                        t.getType().toUpperCase(),
                        t.getAmount(),
                        t.getBalanceAfter()
                );

                matchedCount++;
                foundTransactions = true;

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
            System.out.printf("Total Deposits: $%,.2f\n",calculateTotalDeposits());
            System.out.printf("Total Withdrawals: $%,.2f\n", calculateTotalWithdrawals());
            double net = calculateTotalDeposits() - calculateTotalWithdrawals();
            System.out.printf("Net Change: $%,.2f\n", net);
        }
    }


    public double calculateTotalDeposits() {
        double totalDeposit = 0.0;
        for (int i = 0; i < transactionCount; i++) {
            Transaction t = transactions[i];
            if (t.getType().equalsIgnoreCase("DEPOSIT")) {
                totalDeposit += t.getAmount();
            }
        }
        return totalDeposit;
    }


    public double calculateTotalWithdrawals() {
        double totalWithdrawals = 0.0;
        for (int i = 0; i < getTransactionCount(); i++) {
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