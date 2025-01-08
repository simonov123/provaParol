import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;

import entities.User;
public class user_runtime {
	private static String host="jdbc:mysql://localhost:3306/";
	private String usr;
	private String passw;
	public boolean login(String username,String password) throws SQLException {
		try(Connection conn=DriverManager.getConnection(host, username, password)){
			Statement stat=conn.createStatement();
			return true;
		} 
		
	}
	public boolean register(String username,String password,String password_utente) throws IOException, InterruptedException, SQLException {
		 try {
			 String command2 = "CREATE DATABASE IF NOT EXISTS keydb_" + username + "; "
	                    + "CREATE USER '" + username + "'@'localhost' IDENTIFIED BY '" + password + "'; "
	                    + "CREATE TABLE keydb_" + username + ".data ("
	                    + "id INT(10) PRIMARY KEY, "
	                    + "title VARCHAR(64), "
	                    + "user VARCHAR(64), "
	                    + "password VARCHAR(118)); "
	                    + "GRANT ALL PRIVILEGES ON keydb_" + username + ".data TO '" + username + "'@'localhost'; "
	                    + "FLUSH PRIVILEGES;";
			  String command = "echo " + password_utente + " | sudo -S mysql -u root -e \"" + command2 + "\"";

	            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c",command );
	            processBuilder.redirectErrorStream(true);  
	            Process process = processBuilder.start();
	            InputStream inputStream = process.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }
	            int exitCode = process.waitFor();
	            if (exitCode != 0) {
	                System.err.println("Errore: Il comando ha restituito il codice " + exitCode);
	            } else {
	                System.out.println("Comando eseguito correttamente.");
	                return true;
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
		 
		return false;
	    }
	public boolean delusr(User utente,String rootpw) throws IOException, InterruptedException {
		String command2="DROP DATABASE keydb_"+utente.gsar("name")+";"
		+"DROP USER "+utente.gsar("name")+"@localhost;";
		String command="echo " + rootpw + " | sudo -S mysql -u root -e \"" + command2 + "\"";
		  ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c",command );
          processBuilder.redirectErrorStream(true);  
          Process process = processBuilder.start();
          InputStream inputStream = process.getInputStream();
          BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
          String line;
          while ((line = reader.readLine()) != null) {
              System.out.println(line);
          }
          int exitCode = process.waitFor();
          if (exitCode != 0) {
              System.err.println("Errore: Il comando ha restituito il codice " + exitCode);
          } else {
              System.out.println("Comando eseguito correttamente.");
              return true;
          }

      
      return false;
		
	}


	

}
