package ooad;

public class UserAccount {
	
	private String passportID, firstName, lastName , dob, phone, email, password;
	private Integer uid;
	
	public UserAccount(Integer uid,String passportID, String firstName, String lastName , String dob, String phone, String email, String password){
		this.dob=dob; this.email=email; this.firstName=firstName; this.lastName=lastName; this.phone=phone; this.passportID=passportID; this.password=password;
		this.uid=uid;
	}
	

}
