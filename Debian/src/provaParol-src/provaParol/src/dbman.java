import java.sql.*;
import java.util.ArrayList;

import entities.*;
public class dbman {
	private ArrayList<Key> data;
	private static String host="jdbc:mysql://localhost:3306/";
	private String dbopen="use keydb"+"_";
	public ArrayList<Key> getdata(User utente) throws SQLException {
		System.out.println("fetch data:"+utente.gsar("name"));
		if(dbopen.length()==10) {
		dbopen=dbopen+utente.gsar("name");}
		Connection conn=DriverManager.getConnection(host, utente.gsar("name"), utente.gsar("password"));
		Statement stat=conn.createStatement();
		stat.execute(dbopen);
		ResultSet res=stat.executeQuery("select * from data");
		data=new ArrayList<Key>();
		while(res.next()) {
			Key current=new Key();
			current.setid(res.getInt(1));
			current.setdata("title", res.getString(2));
			current.setdata("user", res.getString(3));
			current.setdata("password", res.getString(4));
			data.add(current);
			
		}
		return data;
	}
	public void additem(Key item,User utente) throws SQLException {
		dbopen=dbopen+utente.gsar("name");
		Connection conn=DriverManager.getConnection(host, utente.gsar("name"), utente.gsar("password"));
		Statement stat=conn.createStatement();
		stat.execute(dbopen);
		stat.executeUpdate("insert into data(id,title,user,password) values('"+item.gid()+"','"+item.getinfo("title")+"','"+item.getinfo("user")+"','"+item.getinfo("password")+"')");
		
	}
	public void delitem(Integer id,User utente) throws SQLException {
		dbopen=dbopen+utente.gsar("name");
		Connection conn=DriverManager.getConnection(host, utente.gsar("name"), utente.gsar("password"));
		Statement stat=conn.createStatement();
		stat.execute(dbopen);
		stat.execute("UPDATE data SET title='DELETED' WHERE id='" + id + "'");
	}
	public void updateitem(Integer id,User utente,String[] values) throws SQLException {
		dbopen=dbopen+utente.gsar("name");
		Connection conn=DriverManager.getConnection(host, utente.gsar("name"), utente.gsar("password"));
		Statement stat=conn.createStatement();
		stat.execute(dbopen);
		stat.execute("UPDATE data SET title='"+values[0]+"',user='"+values[1]+"',password='"+values[2]+"' WHERE id='"+id+"'");
		
	}
	public void dbreset(User utente) throws SQLException {
		dbopen=dbopen+utente.gsar("name");
		Connection conn=DriverManager.getConnection(host, utente.gsar("name"), utente.gsar("password"));
		Statement stat=conn.createStatement();
		stat.execute(dbopen);
		stat.execute("delete from data");
		
	}
	

}
