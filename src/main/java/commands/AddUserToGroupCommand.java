package commands;
import java.sql.*;
import java.util.Random;

public class AddUserToGroupCommand implements Command{

		private String login;
		private String groupName;
		Statement statement = null;
		
		@Override
		public boolean execute() throws SQLException{
			if(check()){
				return false;
			}
			statement.executeUpdate("INSERT INTO `usergroup`(`user_id`,`group_id`) VALUES ((SELECT `id` FROM `users` WHERE `username`='"+login+"'),(SELECT `id` FROM `groups` WHERE `group`='"+groupName+"'))");
			return true;
		}
			
		public void setLogin(String login){
			this.login = login;
		}
		public void setGroupName(String groupName){
			this.groupName = groupName;
		}
		
		private boolean check() throws SQLException{ 
			ResultSet res = null;
			res = statement.executeQuery("SELECT `username` FROM `users` where `username`='"+login+"'");
			int line = 0;
			while(res.next()){
				line++;
				if (line == 0)
					return false;
			}
			res = statement.executeQuery("SELECT `group` FROM `groups` where `group`='"+groupName+"'");
			line = 0;
			while(res.next()){
					line++;
					if(line == 0)
						return false;
			}	
			return true;
		}
		
		public void setStatement(Statement st){
			this.statement = st;
		}
}
