package Banking_System;

public class BankOfOrangeCounty {
    private BankAccount head;

    public BankOfOrangeCounty() {
        head = null;
    }

    // To display the values
    public String toString() {
        BankAccount next = head;
        String returnValue = "";

        // looping through until the last element and returning the values
        while (next != null) {
            returnValue += next + "\n";

            next = next.next;
        }

        return returnValue;
    }

    // Task 2: To create a method to add users with unique ID
    public void addUser(String name, String address, String socialSecurityNumber, double initialDeposit) {
        int newUserID = findNextId();
        BankAccount newAccount = new BankAccount(name, address, socialSecurityNumber, initialDeposit, newUserID);

        if (head == null || newUserID < head.bankUserID) // To make sure the list is sorted at all times
        {
            newAccount.next = head;
            head = newAccount;
        } else {
            BankAccount current = head;

            while (current.next != null && current.next.bankUserID < newUserID) {
                current = current.next;
            }

            newAccount.next = current.next;
            current.next = newAccount;
        }
    }

    // Ovverinding method to addusers
    private void addUser(BankAccount user) {
        addUser(user.name, user.address, user.socialSecurityNumber, user.balance);
    }

    // Method to find the next available ID by incrementing 1 and looping through
    // all the elements in the list
    public int findNextId() {
        int nextAvailableID = 1;
        BankAccount current = head;

        while (current != null && current.bankUserID == nextAvailableID) {
            nextAvailableID++;
            current = current.next;
        }

        return nextAvailableID;
    }

    // Task 3: Method to delete users and free up bank user ID//
    public boolean deleteUser(int bankID) throws Exception {

        BankAccount last = head, prev = head;

        while (last != null) {
            if (last.bankUserID == bankID) {
                System.out.println(last);
                System.out.println("Deleted: " + last.bankUserID);
                prev.next = last.next;

                if (this.head.next == null) {
                    head = null;
                } else if (this.head == last) {
                    head = last.next;
                } else if (last.next == null) {
                }

                return true;
            }

            prev = last;
            last = last.next;
        }

        System.out.println("Invalid");

        return false;

    }

    // Task 4: Payment from Payer to Payee//
    public void payUserToUser(int payer_ID, int payee_ID, double amount) throws Exception {
        BankAccount payee = findUser(payee_ID);
        BankAccount payer = findUser(payer_ID);

        if (payee == null || payer == null) {
            throw new Exception("User does not exist");
        }

        if (payer.balance >= amount) {
            payer.balance -= amount;
            payee.balance += amount;
        } else {
            throw new Exception("Insufficient Balance!!");
        }
    }

    // method to find the user based on bank ID
    private BankAccount findUser(int userID) {
        BankAccount present = head;

        while (present != null) {
            if (present.bankUserID == userID) {
                return present;
            }
            present = present.next;
        }

        return null;
    }

    // Task 5: Method to get median node
    public double getMedianID() {
        if (head == null) {
            return 0.0; // Return 0 if list is empty
        }

        BankAccount oddPointer = head;
        BankAccount evenPointer = head;

        if (evenPointer != null && evenPointer.next != null) // making sure there is another pointer to avoid null
                                                             // pointer exception
        {
            evenPointer = evenPointer.next.next;
            oddPointer = oddPointer.next;
        }

        if (evenPointer == null) // Indicates even number of elements
        {
            return (oddPointer.bankUserID + oddPointer.next.bankUserID) / 2.0;
        } else {
            return oddPointer.bankUserID; // middle most of the list of odd elements
        }

    }

    // Task 6: To merge duplicate accounts of the same customer and delete the other

    public void mergeAccounts(int ID1, int ID2) throws Exception {
        BankAccount account1 = findUser(ID1);
        BankAccount account2 = findUser(ID2);

        if (!isDuplicateAccount(account1, account2)) {
            System.out.println("Account should have same details");

            return;
        }

        if (account1 != null && account2 != null) {
            if (account1.bankUserID < account2.bankUserID) {
                account1.balance += account2.balance;
                deleteUser(account2.bankUserID);
            } else {
                account2.balance += account1.balance;
                deleteUser(account1.bankUserID);
            }
        }
    }

    private boolean isDuplicateAccount(BankAccount account1, BankAccount account2) {
        if (account1.name.equals(account2.name)
                && account1.address.equals(account2.address)
                && account1.socialSecurityNumber.equals(account2.socialSecurityNumber)) {
            return true;
        } else
            return false;
    }

    // Task 7: Merge Banks Bank of LA and Bank of OC
    public static BankOfOrangeCounty mergeBanks(BankOfOrangeCounty ocBank, BankOfOrangeCounty laBank) {

        BankAccount node = laBank.head;

        if (node == null) {
            System.out.println("LA bank has no customers.");

            return ocBank;
        }

        while (node != null) {
            ocBank.addUser(node);

            node = node.next;
        }

        return ocBank;
    }

}