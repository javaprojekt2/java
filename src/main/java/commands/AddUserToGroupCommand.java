package commands;
import java.sql.*;
import java.util.Random;

public class AddUserToGroupCommand implements Command{

		private String login;
		private String groupName;
		Statement statement = null;
		
		@Override
		public boolean execute() throws SQLException{
			
			statement.executeUpdate("INSERT INTO `usergroup`(`user_id`,`group_id`) VALUES ((SELECT `id` FROM `users` WHERE `username`=$login),(SELECT `id` FROM `groups` WHERE `group`=$groupName)");
			return true;
		}
			
		public void setLogin(String login){
			this.login = login;
		}
		public void setGroupName(String groupName){
			this.groupName = groupName;
		}
		
		private boolean check() throws SQLException{ 
			boolean exist = false;
			String data = "SELECT username FROM users";
			ResultSet res = statement.executeQuery(data);
			while(res.next()){
				String name = res.getString("username");
				if(login.equals(name)){
					exist = true;
				}
				res.close();
				return exist;
			}
		return false;
		}
		
		public void setStatement(Statement st){
			this.statement = st;
		}
}
