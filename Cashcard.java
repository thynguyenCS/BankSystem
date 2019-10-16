
public class Cashcard {
	private String passcode;
	private String expDate;
	public Cashcard(){
		passcode = "";
		expDate = "";
	}
	public void setPass(String e){
		passcode = e;
	}
	public void setexpDate(String e){
		expDate = e;
	}
	public String getExpDate(){
		return expDate;
	}
	public String getPassCode(){
		return passcode;
	}
	public boolean checkPasscode(String s){
		return passcode.equals(s);
	}
	public boolean checkExp(String s){
		if(expDate.compareTo(s)<0)
			return true;
		else
			return false;
	}
}
