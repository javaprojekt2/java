package adminClient;
import java.sql.*;

import javax.swing.JOptionPane;

import commands.AddUserCommand;
import commands.AddUserToGroupCommand;

public class AdminClientController {

		private AdminClientView view;
		private Connection con = null;
		private Statement st = null;
		
		public AdminClientController(){
	
		}
		public void addUser(String login, String password){
			//dodaæ wyjatek - nie wypelnione pola
			if (login.equals("") || password.equals("")){
				JOptionPane.showMessageDialog(view.frame, "Wype³nij wszystkie pola.",
					     "Uwaga", JOptionPane.WARNING_MESSAGE);
			}
			try {
				AddUserCommand newUser= new AddUserCommand();
				newUser.setLogin(login);
				newUser.setPassword(password);
				try {
					getConnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				newUser.setStatement(st);
				boolean x = newUser.execute();
				if(x){
					JOptionPane.showMessageDialog(view.frame, "Dodano u¿ytownika.",
						     "OK", JOptionPane.WARNING_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(view.frame, "U¿ytkownik ju¿ istnieje.",
						     "Ostrze¿enie", JOptionPane.WARNING_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//dodac okienka, ze wszystko ok + jakies bledy, np. nie ma takiej grupy, uzytkownika
		}
		public void addToGroup(String login, String groupName){
			if (login.equals("") || groupName.equals("")){
				JOptionPane.showMessageDialog(view.frame, "Wype³nij wszystkie pola.",
					     "Uwaga", JOptionPane.WARNING_MESSAGE);
			}
			try{
				AddUserToGroupCommand newGroup = new AddUserToGroupCommand();
				newGroup.setLogin(login);
				newGroup.setGroupName(groupName);
				
				try {
					getConnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
				newGroup.setStatement(st);
				boolean y = newGroup.execute();
				if(y){
					JOptionPane.showMessageDialog(view.frame, "Dodano u¿ytkownika do grupy.",
						     "OK", JOptionPane.WARNING_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(view.frame, "Nie ma takiego u¿ytkownika lub grupy.",
						     "Ostrze¿enie", JOptionPane.WARNING_MESSAGE);
				}
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		public void getConnect() throws Exception{
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost/nowa", "root", "");
			    st = con.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void setView(AdminClientView view2) {
			this.view = view2;
			
		}
		
}
