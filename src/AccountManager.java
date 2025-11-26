public class AccountManager {
    private final Account[] accounts = new Account[50];
    private int accountCount = 0;



//    Method to add an account to the accounts array
    public void addAccount(Account account){
        if (accountCount < accounts.length){
            accounts[accountCount] = account;
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


}
