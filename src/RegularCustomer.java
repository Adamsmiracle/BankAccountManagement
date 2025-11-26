public class RegularCustomer extends Customer {
    public RegularCustomer(String name, int age, String contact, String address) {
        super(name, age, contact, address);
    }

    @Override
    public void displayCustomerDetails() {
        System.out.println("Display the Regular customer detail");
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }
}
