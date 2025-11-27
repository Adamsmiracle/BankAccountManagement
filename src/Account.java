public abstract class Account implements Transactable {
    public static int accountCounter = 0;

    //    private field
    private String accountNumber;
    private double balance;
    private String status = "inactive";
    private final Customer customer;


    public Account(Customer customer) {
        accountCounter++;
        this.customer = customer;
        this.accountNumber = String.format("ACC%03d", accountCounter);
        this.balance = 0.0;
    }






    public  Transaction deposit(double amount) {
        return null;
    }


    public boolean withdraw(double amount){
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(amount+ " has been withdrawn balance balance is: "+ balance );
                return true;
            }
        }
        return false;
    }



//    GETTERS
    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }



//    SETTERS
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setStatus(String status) {
        this.status = status;
    }


//    ABSTRACT METHODS

    public abstract String getAccountType();


    //    The overridden withdraw method
    public abstract Transaction withdraw(double amount, TransactionManager manager);

    public abstract void displayAccountDetails();

    public abstract Transaction deposit(double amount, TransactionManager manager);
}
