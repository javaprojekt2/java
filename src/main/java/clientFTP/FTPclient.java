package clientFTP;
import java.io.*;
import java.net.*;

public class FTPclient {

	@SuppressWarnings("resource")
	public void go(){
		try{
			Socket s = new Socket(); //sprawdzic jakie maja byc parametry do polaczenia
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader (streamReader);
			// cos tam
			reader.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
