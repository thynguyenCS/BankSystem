import java.text.SimpleDateFormat;
import java.util.*;

public class ATMSystem {
	public static ArrayList<Bank> bankSystem = new ArrayList<>();
	public static Scanner sc = new Scanner(System.in);
	public static String CURRENT_DATE = getCurrentDate();

	public static void main(String[] arg) {

		Bank bankA = new Bank("A");
		Bank bankB = new Bank("B");

		ATM atmA1 = new ATM(bankA, "1", 100);
		ATM atmA2 = new ATM(bankA, "2", 100);
		ATM atmB1 = new ATM(bankB, "1", 100);
		ATM atmB2 = new ATM(bankB, "2", 100);
		bankA.addATM(atmA1);
		bankA.addATM(atmA2);
		bankB.addATM(atmB1);
		bankB.addATM(atmB2);
		bankSystem.add(bankA);
		bankSystem.add(bankB);

		// adding customers into 2 banks
		Account a1 = bankA.addAccount("11");
		Account a2 = bankA.addAccount("12");
		Account b1 = bankB.addAccount("111");
		Account b2 = bankB.addAccount("112");
		Account b3 = bankB.addAccount("113");

		// customize account
		a1.setup("mypassword", "03/14/20", 60);
		a2.setup("123456", "12/09/19", 160);
		b1.setup("pizza", "11/14/21", 200);
		b2.setup("Disneyland", "02/02/18", 500);
		b3.setup("lalala", "01/01/19", 40);

		System.out.println("States of two banks");
		System.out.println("+++++++++++++++++++++");
		printBankState(bankA);
		printBankState(bankB);

		System.out.println("States of ATM");
		System.out.println("+++++++++++++++++++++");
		printAtmState(bankA);
		printAtmState(bankB);

		System.out.println("Enter your choice of ATM:");
		String atmInput = sc.next();
		Bank bank = getBankFromATM(atmInput);
		ATM myAtm = bank.specifyAtm(atmInput);
		Account acc = verifyCardAtATM(bank);		
		if(acc!= null){
		Account authorizedAcc = authorizeCard(acc);		
		withdraw(authorizedAcc, myAtm);
		}
		
	}// end main

	public static Account verifyCardAtATM(Bank bank) {
		System.out.println("Please insert your card");
		String cardInput = sc.next();
		Account associateAcc = new Account(cardInput);
		if (!String.valueOf(cardInput.charAt(0)).equals(bank.getBankId())) {
			associateAcc = null;
			System.out.println("Your card is not supported by this ATM. Returning card...");
		} else {
			associateAcc = bank.verifyCard(cardInput);
				if (associateAcc.getCard().checkExp(CURRENT_DATE)){
					System.out.println("This card is expired. Returning card...");
					//associateAcc = null;
				}
				else
					System.out.print("Card verified. ");
		} 
		return associateAcc;
	}

	public static Account authorizeCard(Account acc) {
		System.out.print("Please enter your password: ");
		String pw = sc.next();
		while (!acc.getCard().checkPasscode(pw)) {
			System.out.println("Invalid password. Enter your password:");
			pw = sc.next();
		}
		System.out.println("Card authorized. ");
		return acc;
	}

	public static void withdraw(Account acc, ATM atm) {
		System.out.println("Please enter amount you want to withdraw: ");
		double amount = sc.nextDouble();
		double limit = atm.getAccumulateLimit();
		double bal = acc.getBalance();		
		checkAmount(limit,amount,bal);		
		acc.setBalance(bal - amount);
		atm.setAccumulateLimit(amount);
		System.out.println("Amount withdrawed: $" + amount);
		System.out.println("Your account current balance: $" + acc.getBalance());
		System.out.println("Do you have more transactions? (Y/N) 1");
		char c;
		String ans = sc.next();
		c = ans.charAt(0);
		while (c == 'y' || c == 'Y') {
			checkAmount(limit,amount,bal);
		
			System.out.println("Do you have more transactions? (Y/N) 2");
			ans = sc.next();
			c = ans.charAt(0);
		}
			if (c == 'n' || c == 'N') {
				System.out.println("Quitting. Thanks for your business");
				return;
			}
		}
		
	

	public static void printBankState(Bank b) {
		System.out.println("Bank" + b.getBankId());
		ArrayList<Account> accList = b.getAccList();
		Iterator<Account> iter = accList.iterator();
		while (iter.hasNext()) {
			Account temp = iter.next();
			System.out.println("Bankid: " + temp.getBankId() + ", Account and cashcard: #" + temp.getAccId()
					+ " expires on " + temp.getCard().getExpDate() + ", password: " + temp.getCard().getPassCode());
		}
		System.out.println();
	}

	public static void printAtmState(Bank b) {
		ArrayList<ATM> al = b.getAtmList();
		Iterator<ATM> iter = al.iterator();
		while (iter.hasNext()) {
			ATM temp = iter.next();
			System.out.println("ATM " + temp.getAtmId());
			System.out.println("The maximum limit for withdrawal per cash card is $" + temp.getLimit() + " per day.");
		}
		System.out.println();

	}

	public static String getCurrentDate() {
		SimpleDateFormat sfd = new SimpleDateFormat("MM/dd/yy");
		return sfd.format(new Date());
	}

	public static Bank getBankFromATM(String id) {
		String bankId = String.valueOf(id.charAt(0));
		Iterator<Bank> iter = bankSystem.iterator();
		Bank found = new Bank(bankId);
		while (iter.hasNext()) {
			Bank temp = iter.next();
			if (temp.getBankId().equals(bankId)) {
				found = temp;
				break;
			} else {
				found = null;
			}
		}
		return found;
	}

	public static boolean isDigit(char c) {
		return Character.isDigit(c);
	}
	public static void checkAmount(double limit, double amount, double bal){
		if (amount > limit) {
			System.out
					.println("This amount exceeds the limit of daily withdrawal. Please enter another amount or quit");
			String input = sc.next();
			if (isDigit(input.charAt(0))) {
				amount = Double.parseDouble(input);
			} else {				
				System.out.println("Quitting. Thanks for your business");
				return;
			}
		
			if (amount > bal) {
				System.out.println("This amount exceeds your current balance. Please enter another amount or quit");
				String input1 = sc.next();
				if (isDigit(input1.charAt(0))) {
					amount = Double.parseDouble(input1);
				} else{
					System.out.println("Quitting. Thanks for your business");
					return;
				}
			}
		}
	}
}
