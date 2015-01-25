package clientFTP;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class CommandTextArea extends JTextArea implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final KeyStroke bspKey = KeyStroke.getKeyStroke("BACK_SPACE");
	public static final KeyStroke upArrowKey = KeyStroke.getKeyStroke("UP");
	public static final KeyStroke downArrowKey = KeyStroke.getKeyStroke("DOWN");
	public static final KeyStroke leftArrowKey = KeyStroke.getKeyStroke("LEFT");
	public static final KeyStroke rightArrowKey = KeyStroke
			.getKeyStroke("RIGHT");
	public static final KeyStroke enterKey = KeyStroke.getKeyStroke("ENTER");

	/**
	 * The constructor will create a JTextArea and also will set the TextArea
	 * with a look and feel of the Command Prompt.
	 * */
	public CommandTextArea(int i, int j) {
		super(i, j);

		setEditable(true);

		getInputMap().put(bspKey, "noSuchAction");
		getInputMap().put(upArrowKey, "noSuchAction");
		getInputMap().put(downArrowKey, "noSuchAction");
		getInputMap().put(leftArrowKey, "noSuchAction");
		getInputMap().put(rightArrowKey, "noSuchAction");
		getInputMap().put(enterKey, "noSuchAction");
	}



	public void setText(String text) {
		super.setText(text);
		setCaretPosition(getText().length());
	}

	public void append(String text) {
		super.append(text);
		// setCaretPosition(getText().length());
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
	
	}
}