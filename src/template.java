//import java.util.Scanner;
//
//public class Mfain {
//    // Manager classes use composition to hold and manipulate data [cite: 478]
//    private static AccountManager accountManager = AccountManager.getInstance();
//    private static TransactionManager transactionManager = new TransactionManager();
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        // 1. Seed initial data as required by US-1
//        seedInitialData();
//
//        // 2. Start the main application loop [cite: 446]
//        runMainMenu();
//
//        // 3. Close resources and exit [cite: 321, 323]
//        scanner.close();
//        System.out.println("Thank you for using Bank Account Management System! \nGoodbye!");
//    }
//
//    /**
//     * Initializes the minimum required accounts (3 Savings, 2 Checking).
//     */
//    private static void seedInitialData() {
//        // Example of creating and adding accounts, linking to Customers [cite: 468]
//        // Customers are created first
//        RegularCustomer john = new RegularCustomer("John Smith", 45, "+1-555-1001", "12 Main St");
//        RegularCustomer sarah = new RegularCustomer("Sarah Johnson", 38, "+1-555-1002", "34 Elm Ave");
//        RegularCustomer michael = new RegularCustomer("Michael Chen", 51, "+1-555-1003", "56 Pine Ln");
//        RegularCustomer emily = new RegularCustomer("Emily Brown", 29, "+1-555-1004", "78 Birch Rd");
//        RegularCustomer david = new RegularCustomer("David Wilson", 58, "+1-555-1005", "90 Cedar Ct");
//
//        // Accounts are created and added to the AccountManager
//        accountManager.addAccount(new SavingsAccount(john, 5250.00)); // ACC001 [cite: 52, 57]
//        accountManager.addAccount(new CheckingAccount(sarah, 3450.00)); // ACC002 [cite: 62, 66]
//        accountManager.addAccount(new SavingsAccount(michael, 15750.00)); // ACC003 [cite: 70, 72]
//        accountManager.addAccount(new CheckingAccount(emily, 890.00)); // ACC004 [cite: 79, 81]
//        accountManager.addAccount(new SavingsAccount(david, 25300.00)); // ACC005 [cite: 88, 90]
//
//        // Add sample transactions to ACC001 for history view [cite: 286]
//        // In a real scenario, transactions are created during processTransaction,
//        // but here we seed them to match the screenshot [cite: 299-309]
//        transactionManager.addTransaction(new Transaction("ACC001", "DEPOSIT", 1500.00, 6750.00));
//        transactionManager.addTransaction(new Transaction("ACC001", "WITHDRAWAL", 750.00, 5250.00));
//        transactionManager.addTransaction(new Transaction("ACC001", "DEPOSIT", 2000.00, 6000.00));
//        transactionManager.addTransaction(new Transaction("ACC001", "WITHDRAWAL", 500.00, 4000.00));
//
//    }
//
//    /**
//     * Runs the main menu loop for the application.
//     */
//    private static void runMainMenu() {
//        int choice = -1;
//        do {
//            displayMenu();
//            // Basic input validation [cite: 453]
//            if (scanner.hasNextInt()) {
//                choice = scanner.nextInt();
//                scanner.nextLine(); // Consume newline
//                executeChoice(choice);
//            } else {
//                System.out.println("\n*** Invalid input. Please enter a number (1-5). ***\n");
//                scanner.nextLine(); // Consume invalid input
//                choice = -1;
//            }
//        } while (choice != 5);
//    }
//
//    /**
//     * Displays the main menu options to the user [cite: 34-39].
//     */
//    private static void displayMenu() {
//        System.out.println("\n=============================================");
//        System.out.println("      BANK ACCOUNT MANAGEMENT MAIN MENU");
//        System.out.println("=============================================");
//        System.out.println("1. Create Account");     // US-2 [cite: 356]
//        System.out.println("2. View Accounts");      // US-1 [cite: 326]
//        System.out.println("3. Process Transaction"); // US-3 [cite: 382]
//        System.out.println("4. View Transaction History"); // US-4 [cite: 418]
//        System.out.println("5. Exit");               // US-5 [cite: 438]
//        System.out.print("Enter choice: "); // [cite: 41]
//    }
//
//    /**
//     * Executes the functionality based on the user's menu choice[cite: 445].
//     */
//    private static void executeChoice(int choice) {
//        switch (choice) {
//            case 1:
//                // Logic for US-2: Create Account
//                // Delegates to a method to collect customer/account details and call accountManager.addAccount()
//                System.out.println("\n--- ACCOUNT CREATION ---"); // [cite: 105]
//                // TODO: Implement createAccount logic here (or in a separate helper class)
//                break;
//            case 2:
//                // Logic for US-1: View Accounts
//                // Calls accountManager.viewAllAccounts()
//                System.out.println("\n--- ACCOUNT LISTING ---"); // [cite: 46]
//                accountManager.viewAllAccounts();
//                break;
//            case 3:
//                // Logic for US-3: Process Transaction
//                // Delegates to a method to find account, ask for deposit/withdrawal details,
//                // call account.withdraw/deposit, and transactionManager.addTransaction()
//                System.out.println("\n--- PROCESS TRANSACTION ---"); // [cite: 188]
//                // TODO: Implement processTransaction logic here
//                break;
//            case 4:
//                // Logic for US-4: View Transaction History
//                // Delegates to a method to ask for account number and call transactionManager.viewTransactionsByAccount()
//                System.out.println("\n--- VIEW TRANSACTION HISTORY ---"); // [cite: 266]
//                // TODO: Implement viewTransactionHistory logic here
//                break;
//            case 5:
//                // Exits the loop, allowing the main method to finish [cite: 321]
//                System.out.println("Exiting application...");
//                break;
//            default:
//                System.out.println("\n*** Invalid choice. Please select an option between 1 and 5. ***\n");
//        }
//
//        // Wait for user before showing the menu again (mimics the console output style) [cite: 99]
//        if (choice != 5 && choice >= 1 && choice <= 4) {
//            System.out.print("\nPress Enter to continue...");
//            scanner.nextLine();
//        }
//    }
//}