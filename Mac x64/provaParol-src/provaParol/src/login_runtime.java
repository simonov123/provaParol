import entities.*;

import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
public class login_runtime {
	private User utente_loggato;
	private String username;
	private String password;
	public User execute(Display display) {
		System.out.println(Locale.getDefault().toString());
		ResourceBundle bundle=ResourceBundle.getBundle("messages", Locale.getDefault());
		Shell shell=new Shell(display);
		shell.setText(bundle.getString("fun.login"));
		shell.setSize(300, 200);
		shell.setLayout(new GridLayout(2, false));
		Label usn=new Label(shell,SWT.CENTER);
		usn.setText(bundle.getString("cmp.usr"));
		Text usin=new Text(shell,SWT.CENTER|SWT.BORDER);
		Label pas=new Label(shell,SWT.CENTER);
		pas.setText(bundle.getString("cmp.pwd"));
		Text pasin=new Text(shell,SWT.CENTER|SWT.PASSWORD|SWT.BORDER);
		Button ok=new Button(shell,SWT.CENTER);
		ok.setText(bundle.getString("butt.log"));
		Button reg=new Button(shell,SWT.CENTER);
		reg.setText(bundle.getString("butt.new"));
		ok.addListener(SWT.Selection, event->{
			this.username=usin.getText();
			this.password=pasin.getText();
			user_runtime userun=new user_runtime();
			try {
				if((userun.login(this.username,this.password))==true) {
					utente_loggato=new User();
					utente_loggato.update(this.username, this.password);
					System.out.println("connessione riuscita   utente"+ utente_loggato.gsar("name"));
					shell.close();
				}
			} catch (SQLException e) {
				Shell shell2=new Shell(display);
				shell2.setText(bundle.getString("err.err"));
				shell2.setSize(300, 100);
				shell2.setLayout(new GridLayout(1, false));
				Label err=new Label(shell2,SWT.CENTER);
				err.setText(bundle.getString("err.conn"));
				shell2.open();
				 while (!shell2.isDisposed()) {
			            if (!display.readAndDispatch()) {
			                display.sleep();
			            }
			        }
				
			}
			
		});
		reg.addListener(SWT.Selection, event->{
			Shell shell3=new Shell(display);
			shell3.setText(bundle.getString("fun.reg"));
			shell3.setSize(300, 200);
			shell3.setLayout(new GridLayout(2, false));
			Label usn2=new Label(shell3,SWT.CENTER);
			usn2.setText(bundle.getString("cmp.usr"));
			Text usin2=new Text(shell3,SWT.CENTER|SWT.BORDER);
			Label pas2=new Label(shell3,SWT.CENTER);
			pas2.setText(bundle.getString("cmp.pwd"));
			Text pasin2=new Text(shell3,SWT.CENTER|SWT.PASSWORD|SWT.BORDER);
			Label pas3=new Label(shell3,SWT.CENTER);
			pas3.setText(bundle.getString("cmp.rootp"));
			Text pasin3=new Text(shell3,SWT.CENTER|SWT.PASSWORD|SWT.BORDER);
			Button ok2=new Button(shell3,SWT.CENTER);
			ok2.setText(bundle.getString("fun.reg"));
			Button show=new Button(shell3,SWT.CENTER);
			show.setText(bundle.getString("new.show"));
			ok2.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					String username=usin2.getText();
					String password=pasin2.getText();
					String password_utente=pasin3.getText();
					user_runtime userun=new user_runtime();
					try {
						if(userun.register(username, password,password_utente)==true) {
							Shell shell4=new Shell(display);
							shell4.setText(bundle.getString("msg.success"));
							shell4.setSize(300, 100);
							shell4.setLayout(new GridLayout(1, false));
							Label err=new Label(shell4,SWT.CENTER);
							err.setText(bundle.getString("msg.created"));
							shell4.open();
							 while (!shell4.isDisposed()) {
						            if (!display.readAndDispatch()) {
						                display.sleep();
						            }
						        }
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			show.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					 if (pasin2.getEchoChar() == '\0' ) {
			                pasin2.setEchoChar('*');
			                pasin3.setEchoChar('*');
			                show.setText(bundle.getString("new.show"));
			            } else {
			                pasin2.setEchoChar('\0');
			                pasin3.setEchoChar('\0');
			                show.setText(bundle.getString("hide.hide"));
			            }
					
				}
				
			});
			shell3.open();
			
			 while (!shell3.isDisposed()) {
		            if (!display.readAndDispatch()) {
		                display.sleep();
		            }
		        }
			
		});
		shell.open();
		
		 while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
	        }
		   return this.utente_loggato;
		 
		
	}
	

}
