import java.util.*;
public class Bank {
	private String bankId;
	private ArrayList <Account> accList = new ArrayList<>();
	private ArrayList <ATM> atms = new ArrayList<>();
	public Bank(String bank_id){
		bankId = bank_id;
	}
	public String getBankId(){
		return bankId;
	}
	public Account addAccount(String numAcc){
		String accId = bankId + numAcc;
		Account newAcc = new Account(accId);
		accList.add(newAcc);
		return newAcc;	
	}
	public ArrayList<Account> getAccList(){
		return accList;
	}
	public ArrayList<ATM> getAtmList(){
		return atms;
	}
	public void addATM(ATM atm){
		atms.add(atm);
	}
	public Account verifyCard(String cardId){
		Iterator<Account> iter = accList.iterator();
		Account foundAcc = new Account(cardId);
		while(iter.hasNext()){
			Account cur = iter.next();
			if(!cur.getAccId().equals(cardId)){
				//System.out.println("Check verify card in bank");
			}
			else{
				foundAcc = cur;
				break;
			}
			foundAcc = null;
		}	
		return foundAcc;		
	}
	
	public ATM specifyAtm(String atmId){
		Iterator<ATM> iter = atms.iterator();
	 ATM match = new ATM(atmId);
	 while(iter.hasNext()){
		 ATM cur = iter.next();
		 if(cur.getAtmId().equals(atmId)){
			 match = cur;
			 break;
		 }
		 else
			 match = null;
	 }
	 return match;
		
	}
	
	

	
	

	
	

}//end class
