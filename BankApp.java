import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String name, String accNumber, double initialBalance) {
        accountHolderName = name;
        accountNumber = accNumber;
        balance = initialBalance;
    }

    // Deposit Method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw Method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Show Balance
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    // Show Account Info
    public void showAccountInfo() {
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        checkBalance();
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an account
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Account Number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter Initial Balance: ₹");
        double initialBalance = scanner.nextDouble();

        BankAccount account = new BankAccount(name, accNumber, initialBalance);

        int choice;
        do {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Account Info");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account.showAccountInfo();
                    break;
                case 2:
                    account.checkBalance();
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 5:
                    System.out.println("Thank you for using the Bank App.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
