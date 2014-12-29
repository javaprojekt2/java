package serverFTP;
import java.io.*;
import java.net.*;

public class FTPserver {
	
		@SuppressWarnings("resource")
		public void go(){
			
			try{
				ServerSocket serverSock = new ServerSocket(21);
				
				while(true){
					
					Socket sock = serverSock.accept();
					PrintWriter writer = new PrintWriter(sock.getOutputStream());
					writer.close();
				}
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		public static void main(String[] args){
			FTPserver server = new FTPserver();
			server.go();
		}
}

