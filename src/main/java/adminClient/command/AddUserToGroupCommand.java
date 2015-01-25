package adminClient.command;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//import adminClient.exceptions.AddGroupException;


public class AddUserToGroupCommand implements Command {

	private String login;
	private String grupa;
	private Statement stmt = null;

	@Override
	public boolean execute() throws  SQLException {
		if (!sprawdz())
//			throw new AddGroupException();
		stmt.executeUpdate("INSERT INTO `usergroup`(`user_id`,`group_id`) VALUES ((SELECT `id` FROM `users` WHERE `username`='"+login+"'),(SELECT `id` FROM `groups` WHERE `group`='"+grupa+"'))");
		return true;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setGroupName(String grupa) {
		this.grupa = grupa;
	}

	private boolean sprawdz() throws SQLException {
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT `username` FROM `users` where `username`='"+login+"'");
		int iwiersz=0;
		while (rs.next()) iwiersz++;
		if (iwiersz==0) return false;
		rs = stmt.executeQuery("SELECT `group` FROM `groups` where `group`='"+grupa+"'");
		iwiersz=0;
		while (rs.next()) iwiersz++;
		if (iwiersz==0) return false;		
		return true;
	}

	public void setStatement(Statement stmt) {
		this.stmt = stmt;

	}

}