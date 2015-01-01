package adminClient;
import javax.swing.*;


public class AdminClient{	//panel administratora 


		private AdminClientView view;
		private AdminClientController controller;
		private JFrame frame =null;
	
		public AdminClient() {
			view = new AdminClientView();
			controller = new AdminClientController(view);
		}
		private void createAndShowGUI(){
			frame = new JFrame("Panel Administracyjny");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		public static void main(String str[]) {
			final AdminClient AdminClient=new AdminClient();
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					AdminClient.createAndShowGUI();
				}
			});
		}
}