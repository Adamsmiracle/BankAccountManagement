public class Main{
    private static AccountService accountService;


    public static void main(String[] args) throws InterruptedException {
        accountService = new AccountService();

//        MAIN LOOP
        seedInitialData();
        runMainMenu();

    }


    private static void runMainMenu() {
        int choice;
        do {
            mainMenu();
            choice = InputUtils.readInt("Enter choice: ");
            executeChoice(choice);
        } while (choice != 5);
    }



    private static void executeChoice(int choice) {
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                accountService.viewAllAccounts();
                break;
            case 3:
                processTransactionMain();

                break;

            case 4:
                // Ask for account number, show account details and transaction history
                System.out.println("\n VIEW TRANSACTION HISTORY ");
                System.out.println("-".repeat(25));
                String acct = InputUtils.getValidAccountNumber();
                System.out.println("\n");
                Account found = accountService.findAccount(acct);
                if (found == null) {
                    System.out.println("Account not found.");
                } else {

                    System.out.println("Account: " + found.getAccountNumber() + " - " + found.getCustomer().getName());
                    System.out.println("Account Type: " + found.getAccountType());
                    System.out.printf("Current Balance: $%,.2f\n", found.getBalance());
                    System.out.println();
                    accountService.getTransactions(acct);
                }
                break;
            case 5:
                // Exits the loop, allowing the main method to finish [cite: 321]
                System.out.println("Exiting application...");
                break;
            default:
                System.out.println("\nInvalid choice. Please select an option between 1 and 5.\n");
        }

        // Wait for user before showing the menu again (mimics the console output style) [cite: 99]
        if (choice != 5 && choice >= 1 && choice <= 4) {
            InputUtils.readLine("\nPress Enter to continue...");
        }
    }


    private static void seedInitialData() throws InterruptedException {
        RegularCustomer John = new RegularCustomer("John Smith", 45, "+1-555-1001", "12 Main St");
        RegularCustomer Adams = new RegularCustomer("Kofi Adams", 54, "+233-232-4545", "Dormaa Ahenkro");
        RegularCustomer Yaw = new RegularCustomer("Yaw Stephen", 24, "+233-333-4343", "Accra Central");
        PremiumCustomer Miracle = new PremiumCustomer("Adams Miracle", 26, "+233-537-6024", "Kotei Kumasi");
        PremiumCustomer Ronja = new PremiumCustomer("Ronja Scott", 33, "+49-443-3222", "liepzip Germany");


//        creating accounts for the customers
        accountService.addAccount(new SavingsAccount(John, 500.00));
        accountService.addAccount(new CheckingAccount(Adams, 300.00));
        accountService.addAccount(new SavingsAccount(Yaw, 790.34));
        accountService.addAccount(new SavingsAccount(Miracle, 2300.89));
        accountService.addAccount(new SavingsAccount(Ronja, 5000.00));


//      Making some transactions using the transaction manager
        // Apply transactions to ACC001 to demonstrate transaction history
        Account acc001 = accountService.findAccount("ACC001");
        if (acc001 != null) {
            acc001.deposit(1000.00);
            Thread.sleep(1000);
            acc001.deposit(1500.00);
            Thread.sleep(100);
            acc001.withdraw(750.00);
            Thread.sleep(100);
            acc001.deposit(2000.00);
            Thread.sleep(100);
            acc001.withdraw(500.00);
        }

    }



//    Welcome page (main menu)
    public static void mainMenu(){
        System.out.println("\n\n"+"-".repeat(65));
        System.out.println("||                   BANK ACCOUNT MANAGEMENT                  ||");
        System.out.println("-".repeat(65));
        System.out.println("1. Create Account");
        System.out.println("2. View Account");
        System.out.println("3. Process Transaction");
        System.out.println("4. View Transactions History");
        System.out.println("5. Exit");
        System.out.println("\n");
        System.out.print("");

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
        name = InputUtils.readLine("Enter customer name: ");

//        input age checker
        age = InputUtils.readInt("Enter customer age: ");

        contact = InputUtils.readLine("Enter customer contact: ");

        address = InputUtils.readLine("Enter customer address: ");
        System.out.println("\n");

//        Selecting account type;
        System.out.println("Customer type: ");
        System.out.println("1. Regular Customer (Standard banking services");
        System.out.println("2. Premium Customer (Enhanced benefits, min balance %10,000");
        customerType = InputUtils.readInt("Select type (1-2): ");
        while (customerType != 1 &&  customerType != 2)
        {
            System.out.println("Invalid customer type");
            System.out.println("\n");
            customerType = InputUtils.readInt("Select type (1-2): ");
            System.out.println("\n");
        }

        System.out.println("Account type: ");
        System.out.println("1. Savings Account (Interest: 3.5% Min Balance: %500");
        System.out.println("2. Checking Account (Overdraft: $1,000, Monthly Fee: $10");
        accountType = InputUtils.readInt("Select Type: (1-2): ");
        System.out.println("\n");
        while (accountType != 1 &&  accountType != 2)
        {
            System.out.println("Invalid customer type");
            System.out.println("\n");
            accountType = InputUtils.readInt("Select type (1-2): ");
            System.out.println("\n");
        }

        // Read initial deposit as double with validation
        while (true) {
            try {
                String d = InputUtils.readLine("Enter initial deposit: ");
                initialDeposit = Double.parseDouble(d);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }

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
        accountService.addAccount(newAccount);
        System.out.println("\n");

        return false;
    }


    public static boolean processTransactionMain(){
        String accountNumber = "";
        String transactionType = "";
        double amount = 0.00;

        // 1. INPUT AND ACCOUNT RETRIEVAL
        System.out.println("\nPROCESS TRANSACTION");
        System.out.println("-".repeat(63));
        System.out.println("\n");

        // Calling the getAccountNumber utility
        accountNumber = InputUtils.getValidAccountNumber();

        Account account = accountService.findAccount(accountNumber);
        System.out.println("\n");
        if (account == null) {
            System.out.println("Account not found. Aborting transaction.");
            return false;
        }

        System.out.println("Account Details:");
        System.out.println("Customer: "+ account.getCustomer().getName());
        System.out.println("Account Type: "+ account.getAccountType());
        // Display the original balance before processing
        double previousBalance = account.getBalance();
        System.out.println("Current Balance: $"+ previousBalance);
        System.out.println("\n");

        // 2. TRANSACTION TYPE INPUT
        System.out.println("Transaction type: ");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("\n");
        int transactionTypeInput = InputUtils.readInt("Select type (1-2): ");
        while (transactionTypeInput != 1 &&  transactionTypeInput != 2)
        {
            System.out.println("Invalid customer type");
            System.out.println("\n");
            transactionTypeInput = InputUtils.readInt("Select type (1-2): ");
            System.out.println("\n");
        }


        if (transactionTypeInput == 1){
            transactionType = "Deposit";
        } else {
            transactionType = "Withdrawal";
        }

        // Read amount with validation
        while (true) {
            try {
                String s = InputUtils.readLine("Enter amount: $");
                amount = Double.parseDouble(s);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }

        // 3. PREVIEW & VALIDATE TRANSACTION
        double newBalance;
        if (transactionType.equalsIgnoreCase("Deposit")) {
            newBalance = previousBalance + amount;
        } else {
            newBalance = previousBalance - amount;
        }


        // Basic validation
        if (amount <= 0) {
            System.out.println("Amount must be positive. Aborting transaction.");
            return false;
        }

        // AccounType specific validation
        if (account instanceof SavingsAccount sa) {
            if (newBalance < sa.getMinimumBalance()) {
                System.out.printf("Withdrawal would violate minimum balance (%.2f). Aborting.\n", sa.getMinimumBalance());
                return false;
            }
        } else if (account instanceof CheckingAccount ca) {
            if (newBalance < -ca.getOverDraftLimit()) {
                System.out.println("Withdrawal would exceed overdraft limit. Aborting.");
                return false;
            }
        }

        // TRANSACTION CONFIRMATION (preview without constructing Transaction)
        System.out.println("\n");
        System.out.println("TRANSACTION CONFIRMATION");
        System.out.println("-".repeat(63));
        System.out.println("Transaction ID: (assigned on confirmation)");
        System.out.println("Account: " + accountNumber);
        System.out.println("Type: " + transactionType.toUpperCase());
        System.out.printf("Amount: $%,.2f\n", amount);
        System.out.printf("Previous Balance: $%,.2f\n", previousBalance);
        System.out.printf("New Balance: $%,.2f\n", newBalance);
        System.out.println("-".repeat(63));
        String confirm = InputUtils.readLine("Confirm transaction? (Y/N): ").trim();

        if (confirm.equalsIgnoreCase("Y")) {
            // Apply the appropriate transaction type via AccountService
            boolean ok = accountService.processTransaction(account, amount, transactionType);
            if (ok) {
                System.out.println("\u2713 Transaction completed successfully!\n");
            } else {
                System.out.println("Transaction failed during processing.");
            }
        } else {
            System.out.println("Transaction cancelled.");
        }


        return false;
    }




    // account number input and validation moved to InputUtils.getValidAccountNumber()

}



