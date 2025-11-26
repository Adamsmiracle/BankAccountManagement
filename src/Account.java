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
        System.out.println("New Account created: " + this.accountNumber + " for " + customer.getName());

    }



    //    NORMAL METHODS
    public boolean deposite(double amount){
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }



    // --- Overridden Deposit (optional, but standard for clarity) ---
    // Note: The parent Account class already implements the deposit logic,
    // but we can call it explicitly for clean structure.
    public  boolean deposit(double amount) {
        if (amount > 0){
            this.balance += amount;
            return true;
        }
        System.out.println("The amount must be an integer and also greater than 0");
        return false;
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


    public abstract void displayAccountDetails();
}
