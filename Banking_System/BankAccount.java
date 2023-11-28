package Banking_System;

class BankAccount {

    // Task 1: To create LinkedList of all accounts
    // Declaring all the necessary variables to create a bank account
    String name, address, socialSecurityNumber;
    int bankUserID;
    double balance;

    BankAccount next;

    // initializing using constructor
    public BankAccount(String name, String address, String socialSecurityNumber, double initialDeposit,
            int bankUserID) {
        this.name = name;
        this.address = address;
        this.socialSecurityNumber = socialSecurityNumber;
        this.bankUserID = bankUserID;
        this.balance = initialDeposit;
        this.next = null;
    }

    // To display the details
    public String toString() {
        return this.name + " " + this.address + " " + this.socialSecurityNumber + "  " + this.bankUserID + " "
                + this.balance;
    }
}
