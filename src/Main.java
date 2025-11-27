import java.util.Scanner;

public class Main{
    private static AccountManager accountManager;
    private static TransactionManager transactionManager;
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args){
        accountManager = new AccountManager();
        transactionManager =  new TransactionManager();

//        MAIN LOOP
        mainMenu();
        createAccount();

    }


    private static void runMainMenu() {
        int choice = -1;
        do {
            mainMenu();
            // Basic input validation [cite: 453]
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                executeChoice(choice);
            } else {
                System.out.println("\n*** Invalid input. Please enter a number (1-5). ***\n");
                scanner.nextLine(); // Consume invalid input
                choice = -1;
            }
        } while (choice != 5);
    }

    private static void executeChoice(int choice) {
        switch (choice) {
            case 1:
                // Logic for US-2: Create Account
                // Delegates to a method to collect customer/account details and call accountManager.addAccount()
                System.out.println("\n--- ACCOUNT CREATION ---"); // [cite: 105]
                // TODO: Implement createAccount logic here (or in a separate helper class)
                createAccount();
                break;
            case 2:
                // Logic for US-1: View Accounts
                // Calls accountManager.viewAllAccounts()
                System.out.println("\n--- ACCOUNT LISTING ---"); // [cite: 46]
                accountManager.viewAllAccounts();
                break;
            case 3:
                // Logic for US-3: Process Transaction
                // Delegates to a method to find account, ask for deposit/withdrawal details,
                // call account.withdraw/deposit, and transactionManager.addTransaction()
                System.out.println("\n--- PROCESS TRANSACTION ---"); // [cite: 188]
                System.out.println("Select the type of transaction you are doing");
                System.out.println("1. Deposit");
                System.out.println("2. Withdrawal: ");
                int transactionType = Integer.parseInt(scanner.nextLine());
                if
                break;
            case 4:
                // Logic for US-4: View Transaction History
                // Delegates to a method to ask for account number and call transactionManager.viewTransactionsByAccount()
                System.out.println("\n--- VIEW TRANSACTION HISTORY ---"); // [cite: 266]
                // TODO: Implement viewTransactionHistory logic here
                break;
            case 5:
                // Exits the loop, allowing the main method to finish [cite: 321]
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("\n*** Invalid choice. Please select an option between 1 and 5. ***\n");
        }

        // Wait for user before showing the menu again (mimics the console output style) [cite: 99]
        if (choice != 5 && choice >= 1 && choice <= 4) {
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
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



//    implementing the account creation logic
    public static boolean createAccount(){
        String name;
        int age;
        String contact;
        String address;

        int customerType;
        int accountType;
        double initialDeposit;


        System.out.println("ACCOUNT CREATION");
        System.out.println("-".repeat(65));
        System.out.println("\n");

//        Taking the customer detail input
        System.out.print("Enter customer name: ");
        name = scanner.nextLine();

        System.out.print("Enter customer age: ");
        age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter customer contact: ");
        contact = scanner.nextLine();

        System.out.print("Enter customer address: ");
        address = scanner.nextLine();
        System.out.println("\n");

//        Selecting account type;
        System.out.println("Customer type: ");
        System.out.println("1. Regular Customer (Standard banking services");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance %10,000");
        System.out.println("Select type (1-2): ");
        customerType = Integer.parseInt(scanner.nextLine());

        System.out.println("Account type: ");
        System.out.println("1. Savings Account (Interest: 3.5% Min Balance: %500");
        System.out.println("2. Checking Account (Overdraft: $1,000, Monthly Fee: $10");
        System.out.println("Select Type: (1-2): ");
        accountType = Integer.parseInt(scanner.nextLine());
        System.out.println("\n");

        System.out.print("Enter initial deposit: ");
        initialDeposit = scanner.nextDouble();
        scanner.nextLine();

//        Create the customer;
        Customer customer = null;
        switch (customerType) {
            case 1:
                // RegularCustomer extends Customer
                customer = new RegularCustomer(name, age, contact, address);
                break;
            case 2:
                // PremiumCustomer extends Customer
                customer = new PremiumCustomer(name, age, contact, address);
                break;
            default:
                System.out.println("Invalid Customer type selected. Aborting account creation.");
                return false;
        }


//        Setting up the account;


        if(initialDeposit <= 0) {
            System.out.println("Initial deposit must be positive. Aborting account creation");
        }
        Account newAccount = null;
        switch (accountType) {
            case 1:
                newAccount = new SavingsAccount(customer, initialDeposit);
                break;
            case 2:
                newAccount = new CheckingAccount(customer, initialDeposit);
        }

        newAccount.displayAccountDetails();
        accountManager.addAccount(newAccount);
        accountManager.viewAllAccounts();

        return false;
    }

}
