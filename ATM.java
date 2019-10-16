public class ATM {
	public double limit;
	private Bank bank; 
	private String ATM_Id;
	private double accumulateLimit;
	public ATM(String id){
		ATM_Id = id;
	}
	public ATM(Bank b, String s, double d){
	ATM_Id = b.getBankId()+ s;	
	limit  = d;
	bank = b;
	accumulateLimit = d;
}	
	public String getAtmId(){
		return ATM_Id;
	}
	public double getLimit(){
		return limit;
	}
	public Bank findBank(){
		return bank;		
	}
	public void setAccumulateLimit(double amount){
		accumulateLimit -= amount;
	}
	public double getAccumulateLimit(){
		return accumulateLimit;
	}
	
}
