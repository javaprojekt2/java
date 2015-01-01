package adminClient;
import java.sql.*;

import javax.swing.JOptionPane;

import commands.AddUserCommand;
import commands.AddUserToGroupCommand;

public class AdminClientController {

		private AdminClientView view;
		private Connection con = null;
		private Statement st = null;
		
		public AdminClientController(AdminClientView view){
				this.view = view;	
		}
		public void addUser(String login, String password){
			//dodaæ wyjatek - nie wypelnione pola
			try {
				AddUserCommand newUser= new AddUserCommand();
				newUser.setLogin(login);
				newUser.setPassword(password);
				newUser.setStatement(st);
				newUser.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//dodac okienka, ze wszystko ok + jakies bledy, np. nie ma takiej grupy, uzytkownika
		}
		public void addToGroup(String login, String groupName){
			
			try{
				AddUserToGroupCommand atGroup = new AddUserToGroupCommand();
				atGroup.setLogin(login);
				atGroup.setGroupName(groupName);
				atGroup.setStatement(st);
				atGroup.execute();
				
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		public void getConnect() throws Exception{
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost/root", "root", "");
			    st = con.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
