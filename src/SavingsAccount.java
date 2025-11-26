import java.util.Objects;

public class SavingsAccount extends Account {

    // --- Private Fields
    private final double interestRate;
    private final double minimumBalance;

    // Status can be managed by the parent class, but is included here for clarity



    public SavingsAccount(Customer customer, double initialDeposit) {
        // Call the parent class constructor to set customer and generate account number
        super(customer);


        this.interestRate = 0.035;
        this.minimumBalance = 500.00;

        // Set initial balance. The super.deposit() method handles validation and update.
        if (initialDeposit >= this.minimumBalance ) {
            super.setBalance(initialDeposit);
        } else {
            System.err.println("Initial deposit must be positive and greater or equal to $500o.00 ");
        }
    }




    @Override
    public String getAccountType() {
        return "Savings";
    }



//    The overridden withdraw method
@Override
public boolean withdraw(double amount) {
    if (amount < this.minimumBalance){
        System.out.println("Insufficient funds.");
        return false;
    }
    super.setBalance(super.getBalance() - amount);
    return true;
}

//    Displaying the account detail;
    @Override
    public void displayAccountDetails() {
       System.out.println("✔ Account created successfully!");
       System.out.println("Account Number: "+ getAccountNumber());
       System.out.println("Customer: "+ getCustomer().getName());
       System.out.println("Account Type: "+ getAccountType());
       System.out.println("Initial Balance: "+ getBalance());
       System.out.println("Interest Rate: "+ getInterestRate());
       System.out.println("Minimum Balance: "+ getMinimumBalance());
    }


//    Calculating the interestRate;
    public double calculateInterest() {
        return this.interestRate * super.getBalance();
    }


    // --- Polymorphic Implementation of Transactable.processTransaction ---


    public double getInterestRate() {
        return interestRate;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public boolean deposit(double amount) {
        return false;
    }


    @Override
    public boolean processTransaction(double amount, String type) {
        if (amount < 0)
            return false;
        if (Objects.equals(type, "Deposit")){
            this.deposit(amount);
            return true;
        } else if (Objects.equals(type, "Withdrawal")) {
            this.withdraw(amount);
        }
        return false;
    }
}

