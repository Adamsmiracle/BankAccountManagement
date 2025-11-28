public class RegularCustomer extends Customer {
    public RegularCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Customer: "+ this.getCustomerId() + " " + this.getName());
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }
}
