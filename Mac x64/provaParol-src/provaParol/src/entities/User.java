package entities;
import java.sql.*;

public class User {
	public String name;
	private String password;

	
	public void update(String aname,String apassword) {
		if(aname != null) {
			this.name=aname;
			}
		if(apassword != null) {
			this.password=apassword;
			}
	}
	public String gsar(String type) {
		switch(type) {
		case("name"):return this.name;
		case("password"):return this.password;
		default:return "";
			
		
		}
		
		
	}
	
	
	

}
