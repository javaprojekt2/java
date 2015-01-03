package adminClient;
import javax.swing.*;

public class AdminClient{	//panel administratora 


		private AdminClientView view;
		private AdminClientController controller;
	
	
		public AdminClient() {
			controller = new AdminClientController();
			view = new AdminClientView(controller);
			controller.setView(view);
		}
		private void createAndShowGUI(){
			
			view.frame.setVisible(true);
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