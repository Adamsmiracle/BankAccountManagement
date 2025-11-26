public class AccountManager {
    private final Account[] accounts = new Account[50];
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



public void viewAllAccounts(){

        System.out.println("ACCOUNT LISTING");
        System.out.println("-".repeat(60));
    System.out.printf("| %-6s | %-24s | %-10s | %-12s | %-8s |\n",
            "ACC NO", "CUSTOMER NAME", "TYPE", "BALANCE", "STATUS");

        for (Account account: accounts){
            totalBalance += account.getBalance();
            double interestOrOverDraftLimit = account.getAccountType().equalsIgnoreCase("Saving")? ((SavingsAccount) account).calculateInterest():
                    ((CheckingAccount) account).getOverDraftLimit();
            System.out.printf("| %-6s | %-24s | %-10s | $%-11.2f | %-8s |\n",
                    account.getAccountNumber(),
                    account.getCustomer().getName(),
                    account.getAccountType(),
                    account.getBalance(),
                    account.getStatus()
            );
            System.out.printf("| %-6s | %-34s | %-20s |\n",
                    "",
                    account.getAccountType().equalsIgnoreCase("Saving")?
                            "Interest Rate: " + interestOrOverDraftLimit: "Overdraft: " + interestOrOverDraftLimit,
                    account.getAccountType().equalsIgnoreCase("Saving")?
                            "Min Balance: " + account.getBalance(): "Monthly Fee: " + ((CheckingAccount) account).applyMonthlyFee());
            System.out.println("-".repeat(60));

        }
    }


    public double getTotalBalance(){
        return totalBalance;
    }

    public int getAccountCount() {
        return accountCount;
    }
}
