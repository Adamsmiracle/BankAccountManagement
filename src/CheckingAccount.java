import java.util.Objects;

public class CheckingAccount extends Account{

    private double overDraftLimit;
    private double monthlyFee;


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
        System.out.println("Customer: "+ getCustomer().getName());
        System.out.println("Account Type: "+ getAccountType());
        System.out.println("Initial Balance: "+ getBalance());
        System.out.println("OverdraftLimit: "+ getOverDraftLimit());
        System.out.println("Monthly fee: "+ getMonthlyFee());
    }

    @Override
    public String getAccountType(){
        return "Checking";
    }



    @Override
    public boolean withdraw(double amount){
        if (super.getBalance() - amount >= -overDraftLimit) {
            super.withdraw(amount);
            return true;
        }
        return  false;
    }


    public boolean applyMonthlyFee(){
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
            this.withdraw(amount);
            return true;
            }
        return false;
    }
}
