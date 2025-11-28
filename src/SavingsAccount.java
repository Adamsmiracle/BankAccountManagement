import java.util.Objects;

public class SavingsAccount extends Account {

    // --- Private Fields
    private final double interestRate;
    private final double minimumBalance;
    private final TransactionManager manager = TransactionManager.getInstance();

    // Status can be managed by the parent class, but is included here for clarity



    public SavingsAccount(Customer customer, double initialDeposit) {
        // Call the parent class constructor to set customer and generate account number
        super(customer);
        this.interestRate = 0.035;
        this.minimumBalance = 500.00;
        this.setStatus("Active");

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




//    Displaying the account detail;
    @Override
    public void displayAccountDetails() {
       System.out.println("✔ Account created successfully!");
       System.out.println("Account Number: "+ getAccountNumber());
       System.out.println("Customer: "+ getCustomer().getName());
       System.out.println("Account Type: "+ getAccountType());
    System.out.printf("Initial Balance: $%,.2f\n", getBalance());
    System.out.printf("Interest Rate: %.1f%%\n", getInterestRate());
    System.out.printf("Minimum Balance: $%,.2f\n", getMinimumBalance());
    System.out.printf("Status: "+ getStatus());
    }



    @Override
    public Transaction deposit(double amount) {
        // 1. VALIDATION: Check for positive amount
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return null;
        }

        // 2. UPDATE ACCOUNT BALANCE
        this.setBalance(this.getBalance() + amount);

        // 3. CREATE and LOG TRANSACTION
        Transaction newTransaction = new Transaction(
                this.getAccountNumber(),
                "Deposit",
                amount,
                this.getBalance()
        );

        // Use shared TransactionManager instance
        manager.addTransaction(newTransaction);
        System.out.printf("✅ Deposit of $%,.2f successful. New balance: $%,.2f\n", amount, this.getBalance());
        return newTransaction;
    }



    @Override
    public Transaction withdraw(double amount) {

        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return null; // Failure: return null
        }
        double resultingBalance = this.getBalance() - amount;

        if (resultingBalance < this.minimumBalance) {
            System.out.printf(
                    " Withdrawal failed. Resulting balance ($%.2f)" +
                            " would violate the minimum balance requirement ($%.2f).\n",
                    resultingBalance,
                    this.minimumBalance
            );
            return null;
        }

        // SUCCESS PATH
        super.setBalance(resultingBalance);

        // 4. CREATE and LOG TRANSACTION
        Transaction newTransaction = new Transaction(
                this.getAccountNumber(),
                "Withdrawal",
                amount,
                resultingBalance // This is the balance AFTER the update
        );
        manager.addTransaction(newTransaction);
        return newTransaction;
    }


    //    Calculating the interestRate;
    public double calculateInterest() {
        return this.interestRate * super.getBalance();
    }


    // --- Polymorphic Implementation of Transactable.processTransaction ---


    public double getInterestRate() {
        return interestRate*100;
    }

    public double getMinimumBalance() {
        return minimumBalance;
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

