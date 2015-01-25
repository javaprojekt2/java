package clientFTP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import command.CommandInterface;
import command.CommandInterpreter;
import command.LISTCommandGUI;
import command.NORMALCommand;
import command.PASVCommandGUI;

public class ClientFTPController {

	private boolean isConnected = false;
	private boolean pasv = false;
	private JFrame view = null;
	private JFrame IPPanel = null;
	private Socket echoSocket = null;
	private ServerSocket dataServerSocket;
	private Socket dataSocket;
	private PrintWriter connectionOut = null;// dawne out
	private BufferedReader connectionIn = null;// dawne in
	private OutputStream dataOut = null;
	private InputStream dataIn = null;
	private ConslolePanel consolePanel;
	private CommandTextArea commandArea;
	private List<String> List=null;
	private String pwd;
	private OknoAbor window;
	

	public ClientFTPController() {
		try {
			pwd=new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JFrame getView(){
		return view;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public void setVisibleFrame(boolean state) {
		
		
		IPPanel.dispose();
		
		view = new ClientFTPView(this);
	
		
	
		// controller.connect();
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.pack();
		view.setVisible(state);
		
	}

	public void setIPPanel(JFrame IPPanel) {
		this.IPPanel = IPPanel;
	}

	public boolean connect(String IP) {
		
		try {
			echoSocket = new Socket(IP, 21);
			setDataServerSocket(new ServerSocket(echoSocket.getLocalPort() + 1));
			connectionOut = new PrintWriter(echoSocket.getOutputStream(), true);
			connectionIn = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));

		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	
		return true;

	}

	public ConslolePanel getConsolePanel() {
		return consolePanel;
	}

	public void setConsolePanel(ConslolePanel consolePanel) {
		consolePanel.setController(this);
		commandArea = consolePanel.getCommandArea();
		this.consolePanel = consolePanel;
		try {
			consolePanel.putText(connectionIn.readLine());
			consolePanel.putPrompt();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Sesja wygas³a.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	public void performAction(String line) {
		CommandInterface command = getCommand(line);
		
		

		view.revalidate();
		
		if(command.getName().equals("STOR")||command.getName().equals("APPE")||command.getName().equals("RETR")){
			window=new OknoAbor(this,"Przerwij","Przesy³anie pliku...");
			window.setVisible(true);
			view.setEnabled(false);
			
		}
	
		
		
		try {
			command.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		
		
		if(command.getName().equals("PASS")||command.getName().equals("CWD")){
			
			listGUI();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			((ClientFTPView) view).setListModel();
		}

	

	}

	public synchronized CommandInterface getCommand(String ftpCommandString) {
		CommandInterface clientCommand = null;
		ftpCommandString.trim();
		// return an UNKNOWN command for any unexpected String.
		String[] commandArray = ftpCommandString.split("\\s+");
		// read the command "CMD"
		String cmd = commandArray[0];
		// pass the command to the COmmand Factory to create the Command
		clientCommand = CommandInterpreter.getCommandInterpreter()
				.createCommand(cmd.toUpperCase());
		String[] paramArray = new String[commandArray.length - 1];
		System.arraycopy(commandArray, 1, paramArray, 0, paramArray.length);
		// To avoid confusion mark the parameter as null if there is no
		// parameter
		if (paramArray.length == 0) {
			paramArray = null;
		}
		clientCommand.setParameter(paramArray);
		clientCommand.setQuestion(ftpCommandString.trim());
		return clientCommand;
	}

	public PrintWriter getConnectionOut() {
		return connectionOut;
	}

	public void setConnectionOut(PrintWriter connectionOut) {
		this.connectionOut = connectionOut;
	}

	public BufferedReader getConnectionIn() {
		return connectionIn;
	}

	public void setConnectionIn(BufferedReader connectionIn) {
		this.connectionIn = connectionIn;
	}

	public void loginUser(String login, String password) {
		CommandInterface UserCommand = null;
		CommandInterface PassCommand = null;
		UserCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"USER");

		UserCommand.setQuestion("USER " + login);
		PassCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"PASS");

		PassCommand.setQuestion("PASS " + password);
		consolePanel.putText("USER " + login);
		try {
			UserCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		
		//consolePanel.receiveResponse(UserCommand);

		try {
			PassCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();

		//consolePanel.receiveResponse(PassCommand);
		consolePanel.putPrompt();

	}
	
	public void list(){
		CommandInterface ListCommand = null;
		ListCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"LIST");
		ListCommand.setQuestion("LIST");
		try {
			ListCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();
		consolePanel.putPrompt();
	}
	
	public void listGUI(){
		
		CommandInterface ListCommand = null;
		ListCommand = new PASVCommandGUI("PASVGUI");
		ListCommand.setQuestion("PASV");
		try {
			ListCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ListCommand = null;
		ListCommand = new LISTCommandGUI("LISTGUI");
		ListCommand.setQuestion("LIST");
		try {
			ListCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		//consolePanel.putPrompt();
	}
	
	public void pwd(){
		CommandInterface PwdCommand = null;
		PwdCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"PWD");
		PwdCommand.setQuestion("PWD");
		try {
			PwdCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();
		consolePanel.putPrompt();
	}
	
	public void quit(){
		CommandInterface QuitCommand = null;
		QuitCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"QUIT");
		QuitCommand.setQuestion("QUIT");
		try {
			QuitCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}

	}
	


	public boolean getIsPasv() {
		return pasv;
	}

	public void setIsPasv(boolean pasv) {
		this.pasv = pasv;
	}

	public ServerSocket getDataServerSocket() {
		return dataServerSocket;
	}

	private void setDataServerSocket(ServerSocket serverSocket) {
		// TODO Auto-generated method stub
		this.dataServerSocket = serverSocket;
	}
	
	public void setDataSocket(Socket socket) {
		// TODO Auto-generated method stub
		this.dataSocket = socket;
	}
	
	public Socket getDataSocket(){
		return dataSocket;
	}

	public OutputStream getDataOut() {
		return dataOut;
	}

	public void setDataOut(OutputStream dataOut) {
		this.dataOut = dataOut;
	}

	public InputStream getDataIn() {
		return dataIn;
	}

	public void setDataIn(InputStream dataIn) {
		this.dataIn = dataIn;
	}

	public void Error(String string) {
		JOptionPane.showMessageDialog(view, string, "Uwaga",
				JOptionPane.WARNING_MESSAGE);
		System.exit(0);

	}
	
	public void getNewList() {
		// TODO Auto-generated method stub
		List=new ArrayList<String>();
		List.add(".. directory");

	}
	
	public void add(String tekst){
		List.add(tekst);
	}
	
	public String[] getList(){
		
		if(List==null||List.isEmpty()){
			String[] tablica=new String[1];
			tablica[0]="";
			return tablica;
		}
		String[] tablica=new String[List.size()];
		
		
		for(int i=0;i<tablica.length;i++){
			tablica[i]=List.get(i);
		}
		return tablica;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	public void cwd(String string) {
		
		CommandInterface QuitCommand = null;
		QuitCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"CWD");
		System.out.println(string);
		QuitCommand.setQuestion("CWD "+string);
		try {
			QuitCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String i:List){
			System.out.println(i);
		}
		((ClientFTPView) view).setListModel();
		consolePanel.putPrompt();
	}

	public void newDir(String dir) {
		
		CommandInterface DirCommand = null;
		DirCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"MKD");
		DirCommand.setQuestion("MKD "+dir);
		try {
			DirCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();
		consolePanel.putPrompt();
		
	}

	public void put(String text) {
		CommandInterface PutCommand = null;
		PutCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"STOR");
		PutCommand.setQuestion("STOR "+text);
		String[]lista=new String[1];
		lista[0]=text;
		
		PutCommand.setParameter(lista);
		try {
			window=new OknoAbor(this,"Przerwij","Przesy³anie pliku...");
			window.setVisible(true);
			view.setEnabled(false);
			PutCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
	
		/*listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();*/
		consolePanel.putPrompt();
	
		
	}

	public void get(String text) {
		CommandInterface GetCommand = null;
		GetCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"RETR");
		GetCommand.setQuestion("RETR "+text);
		String[]lista=new String[1];
		lista[0]=text;
		
		GetCommand.setParameter(lista);
		try {
			window=new OknoAbor(this,"Przerwij","Przesy³anie pliku...");
			window.setVisible(true);
			view.setEnabled(false);
			GetCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).restartLocalFilesList();
		/*listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();*/
		
		consolePanel.putPrompt();
	}

	public void abor() {
		view.setEnabled(true);
		CommandInterface aborCommand = null;
		aborCommand = CommandInterpreter.getCommandInterpreter().createCommand(
				"ABOR");
		aborCommand.setQuestion("ABOR");
		
		try {
			aborCommand.execute(this);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(view, "Problem z po³¹czeniem.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
			System.exit(0);

		}
		/*listGUI();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClientFTPView) view).setListModel();*/
		consolePanel.putPrompt();
		
	}
	
	
	public void disposeAborWindow(){
	
		
			
			window.dispose();
			view.setEnabled(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	





}
