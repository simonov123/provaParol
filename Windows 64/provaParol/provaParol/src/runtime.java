
import entities.*;
import java.sql.*;

import org.eclipse.swt.widgets.Display;
public class runtime {
	private User logged;
	public void run() throws SQLException {
		System.out.println("backend runtime");
		Display display=new Display();
		login_runtime lr=new login_runtime();
		logged=lr.execute(display);
		if(this.logged!=null) {
		System.out.println("Utente loggato:"+logged.gsar("name"));
	    runtime_portachiavi session=new runtime_portachiavi();
	    session.start(logged,display);
	    }
		if(this.logged==null) {
			System.err.println("ERRORE");
		}
		
		
		
		
		
	}

}
