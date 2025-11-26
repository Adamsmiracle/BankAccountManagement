public class PremiumCustomer extends Customer {
    private double minimumBalance;

    public PremiumCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
    }


    // TODO: implement this when everything else works
    @Override
    public void displayCustomerDetails() {
        System.out.println("Premium customer details");
    }

    @Override
    public String getCustomerType() {
        return "Premium";
    }

//    Premium customers don't pay monthly fees
    public boolean hasWaivedFees() {
        return true;
    }

    public double getMinimumBalance() {
        return this.minimumBalance;
    }

    public boolean setMinimumBalance(double amount){
        if (amount > 0) {
            this.minimumBalance = amount;
            return true;
        }
        return false;
    }

}
