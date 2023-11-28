package Banking_System;

//main method
public class Assignment1 {
    public static void main(String ar[]) throws Exception {
        BankOfOrangeCounty ocBank = new BankOfOrangeCounty();
        BankOfOrangeCounty laBank = new BankOfOrangeCounty();

        ocBank.addUser("xyz", "Irvine", "1234", 100);
        ocBank.addUser("klm", "Banglore", "1202", 10);
        ocBank.addUser("pqr", "NewPort", "2345", 10);
        ocBank.addUser("abc", "Santa Ana", "3456", 10);
        ocBank.addUser("abc", "Santa Ana", "3456", 10);
        ocBank.payUserToUser(7, 8, 10);

        laBank.addUser("pqr", "Tustin", "1200", 100);
        laBank.addUser("abc", "Lake Forest", "4123", 1000);

        // BankOfOrangeCounty mergedBank = BankOfOrangeCounty.mergeBanks(ocBank,
        // laBank);
        // ocBank.mergeAccounts(1, 2);
        // ocBank.deleteUser(4);
        // ocBank.payUserToUser(1, 2, 1);
        // System.out.println(ocBank.getMedianID());
        System.out.println(ocBank);
        // System.out.println(laBank);
        // System.out.println(mergedBank);
    }
}
