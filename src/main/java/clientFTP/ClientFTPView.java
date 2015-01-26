package clientFTP;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

/**
*
* @author Dominik
*/
public class ClientFTPView extends javax.swing.JFrame implements WindowListener {

	
	private ClientFTPController controller;
   /**
    * Creates new form ClientFTPView
    */
   public ClientFTPView(ClientFTPController controller) {

	   this.controller=controller;
       initComponents();
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {

       panelGlowny = new javax.swing.JPanel();//same
       panelLogowania = new javax.swing.JPanel();//same
       tekstLogin = new javax.swing.JTextField();//loginTekst
       przysciskZaloguj = new javax.swing.JButton();//same
       etykietaLogin = new javax.swing.JLabel();//login
       etykietaHaslo = new javax.swing.JLabel();//haslo
       tekstHaslo = new javax.swing.JPasswordField();//hasloTekst
       tekstKomenda = new javax.swing.JTextField();//JScrollPane4
       pWyslij = new javax.swing.JButton();//
       etykietaKonsola = new javax.swing.JLabel();//oknoKonsola
       panelOpcje = new javax.swing.JPanel();//same
       przyciskGet = new javax.swing.JButton();//same
       przyciskPut = new javax.swing.JButton();//same
       przyciskDir = new javax.swing.JButton();//same
       //przyciskList = new javax.swing.JButton();//wywalic
       przyciskPwd = new javax.swing.JButton();//same
       
       ListTransferHandler arrayListHandler =
               new ListTransferHandler(controller);
       
       
       jScrollPane2 = new javax.swing.JScrollPane();
       listaFolderySerwer = new javax.swing.JList();
       listaFolderySerwer.setDragEnabled(true);
       listaFolderySerwer.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       listaFolderySerwer.setTransferHandler(arrayListHandler);
       
       listaFolderySerwer.setCellRenderer(new FileRenderer1());
       panelSuwakFolderyClient = new javax.swing.JPanel(new BorderLayout());
       File f = new File(controller.getPwd());
       File parentF=f.getParentFile();
       
       File[] lista=f.listFiles(new TextFileFilter());
       File[] newList=new File[lista.length+1];
       for(int i=0;i<lista.length;i++){
    	   newList[i]=lista[i];
       }
       newList[lista.length]=parentF;
       //FileList fl = new FileList();
       jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server files", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 2, 14)));
       
       //final Component c1 = fl.getGui(f.listFiles(new TextFileFilter()),true);

       fileList = new JList(newList);
       fileList.setDragEnabled(true);
       fileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
       fileList.setTransferHandler(arrayListHandler);
       
       
       fileList.setCellRenderer(new FileRenderer(controller));
       fileList.setVisibleRowCount(35);
       final JScrollPane c1= new JScrollPane(fileList);
       c1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Local files", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 2, 14)));
       c1.setPreferredSize(new Dimension(4*80, 4*100));
       panelSuwakFolderyClient.add(c1,BorderLayout.WEST);
		
       panelSuwakFolderyClient.setBorder(new EmptyBorder(3,3,3,3));
       
       
    
       
       
       MouseListener mouseListener = new MouseAdapter() {
    	    public void mouseReleased(MouseEvent e) {
    	        
    	   
    	    	String item =  listaFolderySerwer.getSelectedValue();
    	       String [] lista=item.split("\\s+");
    	    	if(lista.length>1&&lista[1].equals("directory")){
    	    	   controller.cwd(lista[0]);
    	    	   
    	       }
    	    	

    	         
    	    }
    	   
    	    
    	};
    	 listaFolderySerwer.addMouseListener(mouseListener);
       
       
       
    	 MouseListener mouseListener1 = new MouseAdapter() {
       	    public void mouseReleased(MouseEvent e) {
       	        
       	   
       	    	File file = (File) fileList.getSelectedValue();
       	    	if(file.isDirectory()) {
       	    		controller.setPwd(file.getPath());
       	    		File[] all=file.listFiles();
       	    		DefaultListModel  model = new DefaultListModel();
       	    	fileList.setModel(model);
           
                  // ;
       	    		for(int i=0;i<all.length;i++){
       	    			model.addElement(all[i]);
       	    		}
       	    		
       	    		
       	    		model.addElement(file.getParentFile());
       	    		
       	    		
       	    	}
                // add selectedItem to your second list.
               
                  
       	    	
       	       }

       	   
       	    
       	    
       	};
       
    	 
    	fileList.addMouseListener(mouseListener1);
       
       
       
       
       
       
       addWindowListener(this);
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 
       panelLogowania.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Logowanie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 2, 14))); // NOI18N

       tekstLogin.setText("anonymous");
    

       przysciskZaloguj.setText("Zaloguj");
       przysciskZaloguj.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               przysciskZalogujActionPerformed(evt);
           }
       });

       etykietaLogin.setText("Login:");

       etykietaHaslo.setText("Has�o:");

       javax.swing.GroupLayout panelLogowaniaLayout = new javax.swing.GroupLayout(panelLogowania);
       panelLogowania.setLayout(panelLogowaniaLayout);
       panelLogowaniaLayout.setHorizontalGroup(
           panelLogowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(panelLogowaniaLayout.createSequentialGroup()
               .addContainerGap()
               .addComponent(etykietaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(tekstLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(18, 18, 18)
               .addComponent(etykietaHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(tekstHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
               .addComponent(przysciskZaloguj)
               .addContainerGap())
       );
       panelLogowaniaLayout.setVerticalGroup(
           panelLogowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(panelLogowaniaLayout.createSequentialGroup()
               .addGap(26, 26, 26)
               .addGroup(panelLogowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(etykietaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(tekstLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(etykietaHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(tekstHaslo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(przysciskZaloguj))
               .addContainerGap(26, Short.MAX_VALUE))
       );
      
      
      // controller.setConsolePanel(consolePanel);
       
       panelKonsola.setLayout(new java.awt.GridLayout());
       panelKonsola.add(tekstKomenda,etykietaKonsola);//panel konsola - dodac pole tekstowe, etykieta i button - actionlistener(metoda z kontrollera performaction - wysylam string z pola tekstowego)
       panelKonsola.add(pWyslij, etykietaKonsola);

       pWyslij.addActionListener(new java.awt.event.ActionListener() {
    	   public void actionPerformed(java.awt.event.ActionEvent evt) {
               przyciskSendActionPerformed(evt);
           }
       });
                                                

      
       panelOpcje.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opcje", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 2, 14))); // NOI18N
       przyciskGet.setText("Get");
       przyciskGet.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               przyciskGetActionPerformed(evt);
           }
       });

       przyciskPut.setText("Put");
       przyciskPut.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               przyciskPutActionPerformed(evt);
           }
       });

       przyciskDir.setText("Dir");
       przyciskDir.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               przyciskDirActionPerformed(evt);
           }
       });

//       przyciskList.setText("List");
//       przyciskList.addActionListener(new java.awt.event.ActionListener() {
//           public void actionPerformed(java.awt.event.ActionEvent evt) {
//               przyciskListActionPerformed(evt);
//           }
//       });

       przyciskPwd.setText("Pwd");
       przyciskPwd.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               przyciskPwdActionPerformed(evt);
           }
       });

       javax.swing.GroupLayout panelOpcjeLayout = new javax.swing.GroupLayout(panelOpcje);
       panelOpcjeLayout.setHorizontalGroup(
       	panelOpcjeLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(Alignment.TRAILING, panelOpcjeLayout.createSequentialGroup()
       			.addContainerGap()
       			.addComponent(przyciskGet, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
       			.addGap(18)
       			.addComponent(przyciskPut, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
       			.addGap(18)
       			.addComponent(przyciskDir, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
       			.addGap(18)
       			.addComponent(przyciskPwd, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
       			.addGap(19))
       );
       panelOpcjeLayout.setVerticalGroup(
       	panelOpcjeLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(Alignment.TRAILING, panelOpcjeLayout.createSequentialGroup()
       			.addContainerGap(32, Short.MAX_VALUE)
       			.addGroup(panelOpcjeLayout.createParallelGroup(Alignment.BASELINE)
       				.addComponent(przyciskPwd)
       				.addComponent(przyciskDir)
       				.addComponent(przyciskPut)
       				.addComponent(przyciskGet))
       			.addGap(23))
       );
       panelOpcje.setLayout(panelOpcjeLayout);


       listaFolderySerwer.setModel(new javax.swing.AbstractListModel() {
           String[] strings = controller.getList();
           public int getSize() { return strings.length; }
           public Object getElementAt(int i) { return strings[i]; }
       });
       jScrollPane2.setViewportView(listaFolderySerwer);

       javax.swing.GroupLayout panelGlownyLayout = new javax.swing.GroupLayout(panelGlowny);
       panelGlownyLayout.setHorizontalGroup(
       	panelGlownyLayout.createParallelGroup(Alignment.TRAILING)
       		.addGroup(panelGlownyLayout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(panelGlownyLayout.createParallelGroup(Alignment.TRAILING)
       				.addGroup(panelGlownyLayout.createSequentialGroup()
       					.addGap(27)
       					.addComponent(tekstKomenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
       					.addComponent(etykietaKonsola, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       				.addGroup(panelGlownyLayout.createSequentialGroup()
       					.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       					.addPreferredGap(ComponentPlacement.RELATED)
       					.addComponent(panelOpcje, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
       				.addGroup(panelGlownyLayout.createSequentialGroup()
       					.addComponent(panelSuwakFolderyClient, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
       					.addGap(52)
       					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
       					.addGap(8)))
       			.addGap(30))
       );
       panelGlownyLayout.setVerticalGroup(
       	panelGlownyLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(panelGlownyLayout.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(panelGlownyLayout.createParallelGroup(Alignment.LEADING)
       				.addComponent(panelOpcje, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       				.addComponent(panelLogowania, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
       			.addPreferredGap(ComponentPlacement.RELATED)
       			.addGroup(panelGlownyLayout.createParallelGroup(Alignment.LEADING)
       				.addComponent(panelSuwakFolderyClient, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
       				.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
       			.addGap(18)
       			.addGroup(panelGlownyLayout.createParallelGroup(Alignment.LEADING)
       				.addComponent(etykietaKonsola, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
       				.addComponent(tekstKomenda, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
       			.addGap(20))
       );
       panelGlowny.setLayout(panelGlownyLayout);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(panelGlowny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(panelGlowny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
       );
      
      
       pack();
   }// </editor-fold>    
   private void przysciskZalogujActionPerformed(java.awt.event.ActionEvent evt) {                                                 
      if(tekstLogin.getText().isEmpty()){
    	  JOptionPane.showMessageDialog(this, "Podaj login.",
					"Uwaga", JOptionPane.WARNING_MESSAGE);
      }
      else{
    	  controller.loginUser(tekstLogin.getText(),tekstHaslo.getText());	  
      }
   } 
                                          
   private void przyciskSendActionPerformed(java.awt.event.ActionEvent evt) {                                            
       // TODO add your handling code here:
	 controller.performAction(tekstKomenda.getSelectedText());
   } 
 
   private void przyciskGetActionPerformed(java.awt.event.ActionEvent evt) {                                            
       // TODO add your handling code here:
	   OknoDir dir=new OknoDir(controller,"GET");
	   dir.setVisible(true);
   }                                           

   private void przyciskPutActionPerformed(java.awt.event.ActionEvent evt) {                                            
       // TODO add your handling code here:
	   OknoDir dir=new OknoDir(controller,"PUT");
	   dir.setVisible(true);
   }                                           

   private void przyciskDirActionPerformed(java.awt.event.ActionEvent evt) { 
	   OknoDir dir=new OknoDir(controller,"DIR");
	   dir.setVisible(true);
	  
       // TODO add your handling code here:
   }                                           

   private void przyciskListActionPerformed(java.awt.event.ActionEvent evt) {   
	   controller.list();	
       // TODO add your handling code here:
   }                                            

   private void przyciskPwdActionPerformed(java.awt.event.ActionEvent evt) {   
	   controller.pwd();
       // TODO add your handling code here:
   } 
   
   public void setListModel(){
	   listaFolderySerwer.setModel(new javax.swing.AbstractListModel<String>() {
           /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		String[] strings = controller.getList();
           public int getSize() { return strings.length; }
           public String getElementAt(int i) { return strings[i]; }
       });
	
	 revalidate();
	   
   }
   
   public void  restartLocalFilesList(){
	   
	   File f = new File(controller.getPwd());
       File parentF=f.getParentFile();
       
       File[] lista=f.listFiles(new TextFileFilter());
       File[] newList=new File[lista.length+1];
       for(int i=0;i<lista.length;i++){
    	   newList[i]=lista[i];
       }
       newList[lista.length]=parentF;
       
       DefaultListModel  model = new DefaultListModel();
	    	fileList.setModel(model);
  
         // ;
	    		for(int i=0;i<newList.length;i++){
	    			model.addElement(newList[i]);
	    		}
   }
   
  
                                         
  private  JList fileList;
   private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JList<String> listaFolderySerwer;
   private javax.swing.JPanel panelSuwakFolderyClient;
   // Variables declaration - do not modify                     
   private javax.swing.JLabel etykietaHaslo;
   private javax.swing.JLabel etykietaLogin;
   private javax.swing.JPanel panelGlowny;
   private javax.swing.JPanel panelKonsola;
   private javax.swing.JPanel panelLogowania;
   private javax.swing.JPanel panelOpcje;
   private javax.swing.JButton przyciskDir;
   private javax.swing.JButton przyciskGet;
   private javax.swing.JButton przyciskList;
   private javax.swing.JButton przyciskPut;
   private javax.swing.JButton przyciskPwd;
   private javax.swing.JButton przysciskZaloguj;
   private javax.swing.JPasswordField tekstHaslo;
   private javax.swing.JTextField tekstLogin;
   private javax.swing.JLabel etykietaKonsola;
   private javax.swing.JTextField tekstKomenda;
   private javax.swing.JButton pWyslij;

   
   // End of variables declaration                   
@Override
public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowClosed(WindowEvent arg0) {

	// TODO Auto-generated method stub
	
}

@Override
public void windowClosing(WindowEvent arg0) {
	// TODO Auto-generated method stub
	controller.quit();
	
}

@Override
public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
