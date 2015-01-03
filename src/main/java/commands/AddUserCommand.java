package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

public class AddUserCommand implements Command{

	private String login;
	private String password;
	private Statement statement = null;
	
	
	@Override
	public boolean execute() throws SQLException{
		if (check()){
			return false;
		}
		Random r = new Random();
		int salt = r.nextInt(10000);
		statement.executeUpdate("INSERT INTO `users`(`username`, `password`, `salt`) VALUES ('"+login+"',PASSWORD(CONCAT(PASSWORD('"+password+"'),'"+salt+"')),'"+salt+"')");
		statement.executeUpdate("INSERT INTO `groups` (`group`) VALUES('"+login+"')");
		AddUserToGroupCommand groupCommand = new AddUserToGroupCommand();
		groupCommand.setLogin(login);
		groupCommand.setGroupName(login);
		groupCommand.setStatement(statement);
		groupCommand.execute();
		return true;
	}
	
	public void setLogin(String login){
			this.login=login;
	}
	public void setPassword(String password){
			this.password=password;
	}
	public boolean check() throws SQLException{	
		boolean exist = false;
		String data = "SELECT username FROM users";
		ResultSet res = statement.executeQuery(data);
		while(res.next()){
			String name = res.getString("username");
	
			if(login.equals(name)){
				exist = true;
				//JOptionPane.showMessageDialog(this, "Username exists");
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