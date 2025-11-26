import java.util.Objects;

public class CheckingAccount extends Account{

    private double overDraftLimit;
    private double monthlyFee;


    public CheckingAccount(Customer customer, double InitialDeposite) {
        super(customer);
        this.overDraftLimit = 1000.00;
        this.monthlyFee = 10.00;
    }

//    overriding the displayAccountDetailMethod
    @Override
    public void displayAccountDetails() {
    System.out.println("This is the mockup of the display account detail: " + super.getAccountNumber() + " Balance: " +
            super.getBalance() + "CHECKING ACCOUNT");
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

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    @Override
    public boolean processTransaction(double amount, String type) {
        if (amount < 0)
            return  false;
        if (Objects.equals(type, "Deposit")){
            this.setBalance(this.getBalance() + amount);
            return true;
        } else if (Objects.equals(type, "Withdrawal")) {
            if(this.getBalance() - amount >= -overDraftLimit) {
                this.setBalance(this.getBalance() - amount);
                return true;
            }
        }
        return false;
    }
}
