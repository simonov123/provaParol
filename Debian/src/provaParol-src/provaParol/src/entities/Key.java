package entities;

public class Key {
	private Integer id;
	private String title;
	private String user;
	private String password;
	
	public String getinfo(String param) {
		switch(param) {
		case "title":return this.title;
		case "user":return this.user;
		case "password":return this.password;
		default:return null;
		}
	}
	public Integer gid() {
		return this.id;
	}
	public void setdata(String param,String data) {
		switch(param) {
		case "title":this.title=data;
		case "user":this.user=data;
		case "password":this.password=data;
		default:break;
		}
	}
	public void setid(Integer ide) {
		this.id=ide;
	}

}
