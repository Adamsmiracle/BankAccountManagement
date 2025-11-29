import java.util.Objects;

public class CheckingAccount extends Account{

    private double overDraftLimit;
    private double monthlyFee;
    private TransactionManager manager = TransactionManager.getInstance();


    public CheckingAccount(Customer customer, double InitialDeposit) {
        super(customer);
        this.overDraftLimit = 1000.00;
        this.monthlyFee = 10.00;
        this.setStatus("Active");
        setBalance(InitialDeposit);
    }

//    overriding the displayAccountDetailMethod
    @Override
    public void displayAccountDetails() {
        System.out.println("✔ Account created successfully!");
        System.out.println("Account Number: "+ getAccountNumber());
        System.out.println("Customer: "+ " " + getCustomer().getCustomerId() + " - " + getCustomer().getName() + " (" + getCustomer().getCustomerType() + ")");
        System.out.println("Account Type: "+ getAccountType());
        System.out.println("Initial Balance: "+ getBalance());
        System.out.println("OverdraftLimit: $" + String.format("%,.2f", getOverDraftLimit()));
        // Show monthly fee or indicate it's waived for Premium customers
        if (getCustomer() instanceof PremiumCustomer && ((PremiumCustomer) getCustomer()).hasWaivedFees()) {
            System.out.println("Monthly fee: Waived (Premium customer)");
        } else {
            System.out.printf("Monthly fee: $%,.2f\n", getMonthlyFee());
        }
        System.out.println("Status: "+ getStatus());
        System.out.println("\n");
    }


    @Override
    public Transaction deposit(double amount) {
        if (amount <= 0 ) {
            System.out.println("The amount must be a positive value");
            return null;
        }

        this.setBalance(this.getBalance() + amount);
        Transaction newTransaction = new Transaction(
                this.getAccountNumber(),
                "Deposit",
                amount,
                this.getBalance()
        );
        // Log transaction using shared manager
        manager.addTransaction(newTransaction);
        return newTransaction;

    }


    @Override
    public String getAccountType(){
        return "Checking";
    }


@Override
public Transaction withdraw(double amount){
    if (amount <= 0) {
        System.out.println("Withdrawal amount must be positive.");
        return null;
    }
    if (super.getBalance() - amount >= -overDraftLimit) {
        this.setBalance(this.getBalance() - amount);

        Transaction newTransaction = new Transaction(
            this.getAccountNumber(),
            "Withdrawal",
            amount,
            this.getBalance()
        );
        manager.addTransaction(newTransaction);
        
        System.out.printf("Withdrawal successful. New balance: $%,.2f\n", this.getBalance());
        return newTransaction;

    } else {
        System.out.printf("Withdrawal failed. Resulting balance ($%,.2f) would exceed the overdraft limit ($%,.2f).\n",
                (super.getBalance() - amount),
                overDraftLimit
        );
        return null;
    }
}


    public boolean applyMonthlyFee(){
        // Waive fee for Premium customers that have fees waived
        Customer c = getCustomer();
        if (c instanceof PremiumCustomer && ((PremiumCustomer) c).hasWaivedFees()) {
            System.out.println("Monthly fee waived for Premium customer.");
            return true;
        }

        if (super.getBalance() - monthlyFee >= -overDraftLimit) {
            super.setBalance(super.getBalance() - monthlyFee);
            return true;
        }
        return false;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    @Override
    public boolean processTransaction(double amount, String type) {
        if (amount < 0)
            return  false;
        if (Objects.equals(type, "Deposit")){
            this.deposit(amount);
            return true;
        } else if (Objects.equals(type, "Withdrawal")) {
            Transaction result = this.withdraw(amount);
            return result != null;
        } else if (Objects.equals(type, "Transfer")) {
            Transaction result = this.withdraw(amount);
            return result != null;
        }
        return false;
    }
}
