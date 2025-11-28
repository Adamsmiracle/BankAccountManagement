public abstract class Account implements Transactable {
    public static int accountCounter = 0;

    //    private field
    private String accountNumber;
    private double balance;
    private String status = "Active";
    private final Customer customer;


    public Account(Customer customer) {
        accountCounter++;
        this.customer = customer;
        this.accountNumber = String.format("ACC%03d", accountCounter);
    }






    public abstract  Transaction deposit(double amount);


    public abstract Transaction withdraw(double amount);



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

    public abstract void displayAccountDetails();

}
