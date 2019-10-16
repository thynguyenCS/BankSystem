
public class Account {
private String accId;
private double bal;
private Cashcard card;
public Account(String id){
	accId = id;
	bal = 0;
	card = new Cashcard();
}
public void setAccId(String id){
	accId = id;
}
public void setBalance (double b){
	bal = b;
}
public void setup(String pc, String exp, double balance){
	card.setPass(pc);
	card.setexpDate(exp);
	bal = balance;
}
public String getAccId(){
	return accId;
}
public double getBalance (){
	return bal;
}
public Cashcard getCard(){
	return card;
}
public String getBankId(){
	return String.valueOf(accId.charAt(0));
}
}
