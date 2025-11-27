public class AccountService {
    private final AccountManager accountManager = AccountManager.getInstance();
    private final TransactionManager transactionManager = TransactionManager.getInstance();

    public void addAccount(Account account) {
        accountManager.addAccount(account);
    }

    public Account findAccount(String accountNumber) {
        return accountManager.findAccount(accountNumber);
    }

    public void viewAllAccounts() {
        accountManager.viewAllAccounts();
    }

    public Transaction[] getTransactions(String accountNumber) {
        return transactionManager.getTransactionsByAccount(accountNumber);
    }

    public void recordTransaction(Transaction transaction) {
        transactionManager.addTransaction(transaction);
    }

    public boolean processTransaction(Account account, double amount, String type) {
        if (type.equalsIgnoreCase("Deposit")) {
            Transaction t = account.deposit(amount);
            return t != null;
        } else {
            Transaction t = account.withdraw(amount);
            return t != null;
        }
    }
}
