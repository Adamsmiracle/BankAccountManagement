public class AccountManager {
    private  Account[] accounts = new Account[50];
    private int accountCount = 0;
    private double totalBalance = 0;



//    Method to add an account to the accounts array
    public void addAccount(Account account){
        if (accountCount < accounts.length){
            accounts[accountCount] = account;
            accountCount++;
        }
    }


//    linear search through the Accounts array to find the
    public Account findAccount(String accountNumber){
        for(int i = 0; i < accountCount; i++){
            if (accounts[i].getAccountNumber().equalsIgnoreCase(accountNumber)){
                return accounts[i];
            }
        }
        return null;
    }


    public void viewAllAccounts() {
        System.out.println("\n ACCOUNT LISTING ");
        System.out.println("-".repeat(83));
        System.out.printf("| %-8s | %-25s | %-12s | %-14s | %-8s |\n",
                "ACC NO", "CUSTOMER NAME", "TYPE", "BALANCE", "STATUS");
        System.out.println("-".repeat(83));

        // Iterate only up to the actual number of accounts stored [cite: 409]
        for (int i = 0; i < accountCount; i++) {
            Account account = accounts[i];
            totalBalance += account.getBalance();

            // Line 1: Main Account Details
            System.out.printf("| %-8s | %-25s | %-12s | $%,-13.2f | %-8s |\n",
                    account.getAccountNumber(),
                    account.getCustomer().getName(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getStatus()
            );

//            Line two of the output formatter
            if (account instanceof SavingsAccount savingsAccount) {
                System.out.printf("| %-8s | Interest Rate: %.1f%% | Min Balance: $%,.2f |\n",
                        "",
                        savingsAccount.getInterestRate(),
                        savingsAccount.getMinimumBalance()
                );
            } else if (account instanceof CheckingAccount checkingAccount) {
                System.out.printf("| %-8s | Overdraft Limit: $%,.2f | Monthly Fee: $%,.2f |\n",
                        "",
                        checkingAccount.getOverDraftLimit(),
                        checkingAccount.getMonthlyFee()
                );
            }
            System.out.println("-".repeat(83));
        }

        // Display required totals
        System.out.printf("\nTotal Accounts: %d\n", getAccountCount()); //
        System.out.printf("Total Bank Balance: $%,.2f\n", getTotalBalance()); //
    }


    public double getTotalBalance(){
        return totalBalance;
    }

    public int getAccountCount() {
        return accountCount;
    }
}
