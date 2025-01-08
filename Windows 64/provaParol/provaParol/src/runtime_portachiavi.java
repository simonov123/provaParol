import entities.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
public class runtime_portachiavi {
	private ArrayList <Key> keys;
	private ArrayList <String> keynames;
	public void start(User utente,Display display) throws SQLException {
		dbman manager=new dbman();
		keys=manager.getdata(utente);
		keynames=new ArrayList<String>();
		int index=0;
		for(index=0;index<keys.size();index++) {
			Key key=keys.get(index);
			if (!key.getinfo("title").equals("DELETED")){
			keynames.add(key.getinfo("title")+"  id:"+key.gid());}
			else {
				keynames.add(key.getinfo("title"));
			}
		}
		System.out.println(Locale.getDefault().toString());
		ResourceBundle bundle=ResourceBundle.getBundle("messages", Locale.getDefault());
	     Shell shell = new Shell(display);
	     shell.setSize(500, 400);
	     shell.setText(bundle.getString("app.name"));
	     shell.setLayout(new FillLayout());
	     List list=new List(shell,SWT.BORDER|SWT.V_SCROLL | SWT.H_SCROLL);
	     for(String key:keynames) {
	    	 if(!key.equals("DELETED")) {
	    	 list.add(key);}
	     }
	     ToolBar toolBar = new ToolBar(shell, SWT.VERTICAL);
	        toolBar.setBounds(0, 0, 500, 30);
	        ToolItem item1=new ToolItem(toolBar,SWT.PUSH);
	        item1.setText(bundle.getString("menù.new"));
	        ToolItem item2=new ToolItem(toolBar,SWT.PUSH);
	        item2.setText(bundle.getString("menù.del"));
	        ToolItem item3=new ToolItem(toolBar,SWT.PUSH);
	        item3.setText(bundle.getString("menù.rel"));
	        ToolItem item4=new ToolItem(toolBar,SWT.PUSH);
	        item4.setText(bundle.getString("menù.set"));
	        ToolItem item5=new ToolItem(toolBar,SWT.PUSH);
	        item5.setText(bundle.getString("menù.cred"));
	        ToolItem item6=new ToolItem(toolBar,SWT.PUSH);
	        item6.setText(bundle.getString("menù.ex"));
	        item1.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					Shell shell2=new Shell(display);
					shell2.setSize(300,200);
					shell2.setText(bundle.getString("new.title"));
					shell2.setLayout(new GridLayout(2,true));
					Label tit=new Label(shell2,SWT.CENTER);
					tit.setText(bundle.getString("new.tit"));
					Text titin=new Text(shell2,SWT.CENTER|SWT.BORDER);
					Label use=new Label(shell2,SWT.CENTER);
					use.setText(bundle.getString("cmp.usr"));
					Text usin=new Text(shell2,SWT.CENTER|SWT.BORDER);
					Label pas=new Label(shell2,SWT.CENTER);
					pas.setText(bundle.getString("cmp.pwd"));
					Text pasin=new Text(shell2,SWT.CENTER|SWT.PASSWORD|SWT.BORDER);
					Button ok=new Button(shell2,SWT.CENTER);
					ok.setText(bundle.getString("new.add"));
					Button show=new Button(shell2,SWT.CENTER);
					show.setText(bundle.getString("new.show"));
					ok.addListener(SWT.Selection, event->{
						dbman manager=new dbman();
						Key item=new Key();
						Integer size=keys.size();
						item.setdata("title", titin.getText());
						item.setdata("user", usin.getText());
						item.setdata("password", pasin.getText());
						item.setid(size+1);
						try {
							manager.additem(item, utente);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.clean();
						try {
							keys=manager.getdata(utente);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						keynames=new ArrayList<String>();
						int index=0;
						for(index=0;index<keys.size();index++) {
							Key key=keys.get(index);
							if (!key.getinfo("title").equals("DELETED")){
								keynames.add(key.getinfo("title") +"  id:"+key.gid());}
								else {
									keynames.add(key.getinfo("title"));
								}
						}
						for (String key : keynames) {
							if(!key.equals("DELETED")) {
				            list.add(key); }
				        }
						
					});
					show.addListener(SWT.Selection, event->{
						  if (pasin.getEchoChar() == '\0') {
				                pasin.setEchoChar('*');
				                show.setText(bundle.getString("new.show"));
				            } else {
				                pasin.setEchoChar('\0');
				                show.setText(bundle.getString("hide.hide"));
				            }
				        });
					
					
					  shell2.open();
				        while (!shell2.isDisposed()) {
				            if (!display.readAndDispatch()) {
				                display.sleep();
				            }
				        }
					
				}
				private void clean() {
					keys.clear();
					keynames.clear();
					list.removeAll();
					
				}
	        	
	        });
	        item3.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					this.clean();
					dbman manager=new dbman();
					try {
						keys=manager.getdata(utente);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					keynames=new ArrayList<String>();
					int index=0;
					for(index=0;index<keys.size();index++) {
						Key key=keys.get(index);
						if (!key.getinfo("title").equals("DELETED")){
							keynames.add(key.getinfo("title")+"  id:"+key.gid());}
							else {
								keynames.add(key.getinfo("title"));
							}
					}
					for (String key : keynames) {
						if(!key.equals("DELETED")) {
			            list.add(key); }
			        }
					
					
					
				}

				private void clean() {
					keys.clear();
					keynames.clear();
					list.removeAll();
					
				}
	        	
	        });
	        item2.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					Shell shell3=new Shell(display);
					shell3.setSize(400,100);
					shell3.setText(bundle.getString("del.title"));
					shell3.setLayout(new GridLayout(3,true));
					Label id=new Label(shell3,SWT.CENTER);
					id.setText(bundle.getString("del.cmp"));
					Text idin=new Text(shell3,SWT.CENTER|SWT.BORDER);
					Button ok=new Button(shell3,SWT.CENTER);
					ok.setText("ok");
					ok.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Integer ids=Integer.parseInt(idin.getText());
							dbman manager=new dbman();
							try {
								manager.delitem(ids, utente);
								this.clean();
								keys=manager.getdata(utente);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							keynames=new ArrayList<String>();
							int index=0;
							for(index=0;index<keys.size();index++) {
								Key key=keys.get(index);
								if (!key.getinfo("title").equals("DELETED")){
									keynames.add(key.getinfo("title")+"  id:"+key.gid());}
									else {
										keynames.add(key.getinfo("title"));
									}
							}
							for (String key : keynames) {
								if(!key.equals("DELETED")) {
					            list.add(key); }
					        }
							
						}
						private void clean() {
							keys.clear();
							keynames.clear();
							list.removeAll();
							
						}
						
						
					});
					shell3.open();
					  while (!shell3.isDisposed()) {
				            if (!display.readAndDispatch()) {
				                display.sleep();
				            }
				        }
					
					
				}
	        	
	        });
	        item4.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					System.out.println("impostazioni");
					Shell shell5=new Shell(display);
					shell5.setText(bundle.getString("set.title"));
					shell5.setSize(330, 100);
					shell5.setLayout(new GridLayout(1, false));
					Button reset=new Button(shell5,SWT.CENTER);
					Button delurs=new Button(shell5,SWT.CENTER);
					reset.setText(bundle.getString("set.reset"));
					delurs.setText(bundle.getString("set.delusr"));
					reset.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							dbman manager=new dbman();
							try {
								manager.dbreset(utente);
								this.clean();
								keys=manager.getdata(utente);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						private void clean() {
							keys.clear();
							keynames.clear();
							list.removeAll();
							
						}
						
					});
					delurs.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Shell shell6=new Shell(display);
							shell6.setText(bundle.getString("delusr.tit"));
							shell6.setSize(330, 100);
							shell6.setLayout(new GridLayout(3, false));
							Label root=new Label(shell6,SWT.CENTER);
							Text rootin=new Text(shell6,SWT.CENTER|SWT.PASSWORD);
							Button okin=new Button(shell6,SWT.CENTER);
							root.setText(bundle.getString("cmp.rootp"));
							okin.setText(bundle.getString("delusr.del"));
							okin.addSelectionListener(new SelectionListener() {

								@Override
								public void widgetDefaultSelected(SelectionEvent arg0) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void widgetSelected(SelectionEvent arg0) {
									user_runtime rnt=new user_runtime();
									try {
										if(rnt.delusr(utente,rootin.getText())==true);{
										this.clean();
										 MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
									        messageBox.setText(bundle.getString("msg.success"));
									        messageBox.setMessage(bundle.getString("msg.deleted"));
									        int response = messageBox.open(); 
									        shell6.close();
									        shell5.close();
									        shell.close();
									        }
									} catch (IOException | InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
								private void clean() {
									keys.clear();
									keynames.clear();
									list.removeAll();
									
								}
								
							});
							shell6.open();
							 while (!shell6.isDisposed()) {
						            if (!display.readAndDispatch()) {
						                display.sleep();
						            }
						        }
						}
						
					});
					shell5.open();
					 while (!shell5.isDisposed()) {
				            if (!display.readAndDispatch()) {
				                display.sleep();
				            }
				        }
				}
	        	
	        });
	        item5.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					Shell shell4=new Shell(display);
					shell4.setText(bundle.getString("cred.title"));
					shell4.setSize(330, 100);
					shell4.setLayout(new GridLayout(1, false));
					Label love=new Label(shell4,SWT.CENTER);
					love.setText("CHI HA CREATO IL MONDO ERA INNAMORATO \n Luciano Simone \n lucianosimone143@gmail.com");
					shell4.open();
					 while (!shell4.isDisposed()) {
				            if (!display.readAndDispatch()) {
				                display.sleep();
				            }
				        }
					
				}
	        	
	        });
	        item6.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					shell.close();
					
				}
	        	
	        });
	        list.addSelectionListener(new SelectionListener() {
	        	private boolean isFirstSelection = true;

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					if(this.isFirstSelection==true) {
						this.isFirstSelection=false;
					}
					else {
					Integer selected=Integer.parseInt(list.getItem(list.getSelectionIndex()).split(":")[1]);
					System.out.println("selected:"+(selected-1));
					Shell shell4=new Shell(display);
					shell4.setSize(250,300);
					shell4.setText(keys.get(selected-1).getinfo("title"));
					shell4.setLayout(new GridLayout(2,true));
					Label id=new Label(shell4,SWT.CENTER);
					id.setText("id:");
					Label id2=new Label(shell4,SWT.CENTER);
					id2.setText(""+(selected));
					Label tito=new Label(shell4,SWT.CENTER);
					tito.setText(bundle.getString("sel.tit")+" ");
					Text titolo=new Text(shell4,SWT.CENTER|SWT.BORDER);
					titolo.setText(keys.get(selected-1).getinfo("title"));
					Label nom=new Label(shell4,SWT.CENTER);
					nom.setText(bundle.getString("cmp.usr")+" ");
					Text nome=new Text(shell4,SWT.CENTER|SWT.BORDER);
					nome.setText(keys.get(selected-1).getinfo("user"));
					Label pas2=new Label(shell4,SWT.CENTER);
					pas2.setText(bundle.getString("cmp.pwd")+" ");
					Text password=new Text(shell4,SWT.CENTER|SWT.PASSWORD|SWT.BORDER);
					password.setText(keys.get(selected-1).getinfo("password"));
					Button agg=new Button(shell4,SWT.CENTER);
					agg.setText(bundle.getString("sel.upd"));
					Button del=new Button(shell4,SWT.CENTER);
					del.setText(bundle.getString("sel.del"));
					Button show=new Button(shell4,SWT.CENTER);
					show.setText(bundle.getString("new.show"));
					agg.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Integer ids=selected;
							dbman manager=new dbman();
							String[] values= {titolo.getText(),nome.getText(),password.getText()};
							try {
								manager.updateitem(ids, utente, values);
								this.clean();
								keys=manager.getdata(utente);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							keynames=new ArrayList<String>();
							int index=0;
							for(index=0;index<keys.size();index++) {
								Key key=keys.get(index);
								if (!key.getinfo("title").equals("DELETED")){
									keynames.add(key.getinfo("title")+"  id:"+key.gid());}
									else {
										keynames.add(key.getinfo("title"));
									}
							}
							for (String key : keynames) {
								if(!key.equals("DELETED")) {
					            list.add(key); }
					        }
							
							
						}
						private void clean() {
							keys.clear();
							keynames.clear();
							list.removeAll();
							
						}
							
						
						
					});
					del.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							Integer ids=selected;
							dbman manager=new dbman();
							try {
								manager.delitem(ids, utente);
								this.clean();
								keys=manager.getdata(utente);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							keynames=new ArrayList<String>();
							int index=0;
							for(index=0;index<keys.size();index++) {
								Key key=keys.get(index);
								if (!key.getinfo("title").equals("DELETED")){
									keynames.add(key.getinfo("title")+"  id:"+key.gid());}
									else {
										keynames.add(key.getinfo("title"));
									}
							}
							for (String key : keynames) {
								if(!key.equals("DELETED")) {
					            list.add(key); }
					        }
							
							shell4.close();
						}
						private void clean() {
							keys.clear();
							keynames.clear();
							list.removeAll();
							
						}
						
					});
					show.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							 if (password.getEchoChar() == '\0' ) {
					                password.setEchoChar('*');
					                show.setText(bundle.getString("new.show"));
					            } else {
					                password.setEchoChar('\0');
					                show.setText(bundle.getString("hide.hide"));
					            }
							
							
						}
						
					});
					
					 shell4.open();
				        while (!shell4.isDisposed()) {
				            if (!display.readAndDispatch()) {
				                display.sleep();
				            }
				        }
					
				}}
	        	
	        });
	     shell.open();
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch()) {
	                display.sleep();
	            }
	        }
	     
	
	
		
		
		
		
	}

}
