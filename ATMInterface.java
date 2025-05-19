import java.util.Scanner;

class UserAccount {
    private double balance;

    public UserAccount(double startingMoney)
    {
        balance = startingMoney;
    }

    public boolean addMoney(double amount) 
    {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        else{
            return false;
        }
    
    }

    public boolean getMoney(double amount)
    {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        else {
            return false;
        }
    }

    public double showBalance() {
        return balance;
    }
}

class ATM {
    private UserAccount account;
    private Scanner scanner;

    public ATM(UserAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void startSession() {
        System.out.println("Hello! Welcome to the ATM Interface.");
        System.out.println("Let's manage your bank account money safely and simply.\n");

        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    doWithdraw();
                    break;
                case 2:
                    doDeposit();
                    break;
                case 3:
                    doCheckBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Have a great day!");
                    return;
                default:
                    System.out.println("That option doesn’t exist. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Deposit the Money");
        System.out.println("3. Balance Enquiry");
        System.out.println("4. Exit the ATM");
        System.out.print("Please enter your choice (1-4): ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please type a number between 1 and 4: ");
            scanner.next(); 
        }
        return scanner.nextInt();
    }

    private void doWithdraw() {
        System.out.print("How much would you like to withdraw? ₹ ");
        double amount = scanner.nextDouble();

        if (account.getMoney(amount)) {
            System.out.println("You have taken ₹" + amount + " from your account.");
        } else {
            System.out.println("Cannot withdraw. Maybe the amount is too high or invalid.");
        }
    }

    private void doDeposit() {
        System.out.print("How much would you like to deposit? ₹ ");
        double amount = scanner.nextDouble();

        if (account.addMoney(amount)) {
            System.out.println("₹" + amount + " has been added to your account.");
        } else {
            System.out.println("Deposit failed. Please enter a valid amount.");
        }
    }

    private void doCheckBalance() {
        System.out.printf("Your current balance is ₹ %.2f\n", account.showBalance());
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // Create a new account with ₹10000
        UserAccount myAccount = new UserAccount(10000.0);

        // Connect the ATM to this account
        ATM atm = new ATM(myAccount);

        // Start the session
        atm.startSession();
    }
}
