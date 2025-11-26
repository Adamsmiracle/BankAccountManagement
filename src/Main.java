import java.util.Scanner;

public class Main{
    private static AccountManager accountManager;
    private static TransactionManager transactionManager;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args){
        accountManager = new AccountManager();
        transactionManager =  new TransactionManager();


        mainMenu();

    }


    private static void seedInitialData(){
        RegularCustomer John = new RegularCustomer("John Smith", 45, "+1-555-1001", "12 Main St");
        RegularCustomer Adams = new RegularCustomer("Kofi Adams", 54, "+233-232-4545", "Dormaa Ahenkro");
        RegularCustomer Yaw = new RegularCustomer("Yaw Stephen", 24, "+233-333-4343", "Accra Central");
        PremiumCustomer Miracle = new PremiumCustomer("Adams Miracle", 26, "+233-537-6024", "Kotei Kumasi");
        PremiumCustomer Ronja = new PremiumCustomer("Ronja Scott", 33, "+49-443-3222", "liepzip Germany");


//        creating accounts for the customers
        accountManager.addAccount(new SavingsAccount(John, 500.00));
        accountManager.addAccount(new CheckingAccount(Adams, 300.00));
        accountManager.addAccount(new SavingsAccount(Yaw, 790.34));
        accountManager.addAccount(new SavingsAccount(Miracle, 2300.89));
        accountManager.addAccount(new SavingsAccount(Ronja, 5000.00));


//      Making some transactions using the transaction manager
        transactionManager.addTransaction(new Transaction("ACC001", "Deposit", 1000.00, 454545));
        transactionManager.addTransaction(new Transaction("ACC001", "DEPOSIT", 1500.00, 6750.00));
        transactionManager.addTransaction(new Transaction("ACC001", "WITHDRAWAL", 750.00, 5250.00));
        transactionManager.addTransaction(new Transaction("ACC001", "DEPOSIT", 2000.00, 6000.00));
        transactionManager.addTransaction(new Transaction("ACC001", "WITHDRAWAL", 500.00, 4000.00));

    }



//    Welcome page (main menu)
    public static void mainMenu(){
        System.out.println("\n\n"+"-".repeat(65));
        System.out.println("||                   BANK ACCOUNT MANAGEMENT                  ||");
        System.out.println("-".repeat(65));
        System.out.println("1. Create Account");
        System.out.println("2. View Account");
        System.out.println("3. Process Transaction");
        System.out.println("4. View Transactions");
        System.out.println("5. Exit");
        System.out.println("\n");
        System.out.println("Enter choice: ");
    }

}
