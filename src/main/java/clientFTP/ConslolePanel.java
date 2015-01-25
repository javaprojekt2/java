package clientFTP;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import command.CommandInterface;

public class ConslolePanel extends JPanel implements KeyListener {

	private int lineNumber = 0;
	private static final String askUser = "Hello";
	private String response;
	private StringBuffer password = new StringBuffer();
	protected CommandTextArea commandArea;
	public static String prompt = "Enter message>";
	public static final String askPassword = "password:";
	private static final long serialVersionUID = 1L;
	protected ClientFTPController controller;

	public ConslolePanel() {
		super(new GridBagLayout());
		commandArea = new CommandTextArea(40, 30);
		commandArea.setText(askUser);
		commandArea.addKeyListener(this);

		// commandArea.addMouseListener(this);

		JScrollPane scrollPane = new JScrollPane(commandArea);
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
	}

	public ClientFTPController getController() {
		return controller;
	}

	public void setController(ClientFTPController controller) {
		this.controller = controller;
	}

	public CommandTextArea getCommandArea() {
		return commandArea;
	}

	public void keyPressed(KeyEvent arg0) {
		// check whether the position of the cursor when the back space was
		// pressed
		// If it was near the prompt disable the back space.

		if (arg0.getKeyCode() == '\b') {
			double currentPosition = commandArea.getCaret().getDot();
			int lastPromptIndex = getPromptIndex();
			if ((currentPosition) == lastPromptIndex) {
				commandArea.getInputMap().put(CommandTextArea.bspKey,
						"noSuchAction");
			}
		}
		// send the command to the controller
		if (arg0.getKeyCode() == 10) {
			lineNumber++;
			String line = getCurrentLine();
			getController().performAction(line);
			putPrompt();
		}
	}

	public void keyReleased(KeyEvent arg0) {

		commandArea.setCaretPosition(commandArea.getText().length());
		// if (arg0.getKeyCode()==10){
		// if(lineNumber==1){
		// commandArea.append(askPassword);
		// }
		// }
	}

	public void keyTyped(KeyEvent arg0) {

		commandArea.getInputMap().remove(CommandTextArea.bspKey);
		// read password and show * on the screen

	}

	private String getCurrentLine() {
		String line = null;
		String wholeText = commandArea.getText();
		int promptIndex = getPromptIndex();
		if (wholeText == null) {
			return "";
		}

		line = wholeText.substring(promptIndex, wholeText.length());

		return line;
	}

	private int getPromptIndex() {
		String wholeText = commandArea.getText();
		return wholeText.lastIndexOf(prompt) + prompt.length();
	}

	public void showText(String message) {
		commandArea.append(message);
	}

	public void receiveResponse(CommandInterface command) {

		response = command.getResponse();

		if(response.contains("226 Transfer complete")) controller.disposeAborWindow();
		showText("\n" + response);
		

	}
	
	public void putText(String text) {

	if(text.contains("226 Transfer complete")) controller.disposeAborWindow();
		showText("\n" + text);
		//showText("\n" + prompt);

	}
	public void putPrompt() {

		
		showText("\n" + prompt);
		//showText("\n" + prompt);

	}



}
