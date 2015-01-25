package adminClient.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

//import adminClient.exceptions.AddGroupException;
//import adminClient.exceptions.AddUserException;

public class AddUSERCommand implements Command {

	private String login;
	private String haslo;
	private Statement stmt = null;

	@Override
	public boolean execute() throws /*AddUserException,AddGroupException*/ SQLException {
		if (!check())
			return false;
		Random r = new Random();
		int salt = r.nextInt(10000);
		stmt.executeUpdate("INSERT INTO `users`(`username`, `password`, `salt`) VALUES ('"+login+"',PASSWORD(CONCAT(PASSWORD('"+haslo+"'),'"+salt+"')),'"+salt+"')");
		stmt.executeUpdate("INSERT INTO `groups` (`group`) VALUES('"+login+"')");
		AddUserToGroupCommand groupCommand = new AddUserToGroupCommand();
		groupCommand.setLogin(login);
		groupCommand.setGroupName(login);
		groupCommand.setStatement(stmt);
		groupCommand.execute();
		return true;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String haslo) {
		this.haslo = haslo;
	}

	private boolean check() throws SQLException {
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT username FROM users");

		while (rs.next()) {
			String name = rs.getString(1);
			if (name.equals(login))
				return false;
		}
		
		return true;
	}

	public void setStatement(Statement stmt) {
		this.stmt = stmt;

	}

}
