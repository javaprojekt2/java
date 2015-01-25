package clientFTP;


import javax.swing.JFrame;

public class ClientFTP {
	
	
	private ClientFTPController controller; 
	private JFrame IPPanel=null;
	
	public ClientFTP() {
		controller = new ClientFTPController();
	}
	
	private void createAndShowGUI(){
		
		IPPanel=new IPPanel(controller);
		controller.setIPPanel(IPPanel);
		IPPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IPPanel.setVisible(true);
		//frame.setVisible(true); 
		
		
	}

	public static void main(String str[]) {
		final ClientFTP AdminClient=new ClientFTP();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AdminClient.createAndShowGUI();
			} 
		});
	}
	
	
}