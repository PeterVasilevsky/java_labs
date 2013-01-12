package ru.spbstu.telematics.student_Vasilevsky.lab04.client;

import java.awt.Window;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.text.DefaultCaret;

/**
*
* @author Zeelony
*/
public class JavaWindowChat extends javax.swing.JFrame {
	
	private static Socket socket;
	private String nickname;
	private BufferedReader reader;
	private PrintWriter writer;

   /**
    * Creates new form NewJFrame
    */
   public JavaWindowChat() {
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
	   
	   
	   //ЕСЛИ ПРОГРАММА ЗАВЕРШАЕТСЯ, ТО ДИСКОННЕКТ
	   Runtime.getRuntime().addShutdownHook(new Thread()
	   {
		   @Override
		   public void run()
		   {
			   if (writer != null) {
				   writer.println("chat:exit");
				   writer.flush();
			   }
		   }
	   });
	   
	   //ЕСЛИ ЗАКРЫВАЕМ ОКНО, ТО ДИСКОННЕКТ!!!11
	   this.addWindowListener(new java.awt.event.WindowAdapter() {
		   public void windowClosing(WindowEvent winEvt) {
			   writer.println("chat:exit");
			   writer.flush();

		   }
	   });

       sbrChatArea = new javax.swing.JScrollPane();
       txtrChatArea = new javax.swing.JTextArea();
       lblUsername = new javax.swing.JLabel();
       lblServerAddress = new javax.swing.JLabel();
       txtUsername = new javax.swing.JTextField();
       txtServerAddress = new javax.swing.JTextField();
       btnConnect = new javax.swing.JButton();
       btnDisconnect = new javax.swing.JButton();
       sbrMyMessageArea = new javax.swing.JScrollPane();
       txtrMyMessageArea = new javax.swing.JTextArea();
       btnSend = new javax.swing.JButton();

       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       setTitle("Java Chat");
       setResizable(false);
       
       //автоскролл вниз у txtrChatArea
       DefaultCaret caretChatArea = (DefaultCaret)txtrChatArea.getCaret();
       caretChatArea.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

       txtrChatArea.setEditable(false);
       txtrChatArea.setColumns(20);
       txtrChatArea.setRows(5);
       sbrChatArea.setViewportView(txtrChatArea);

       lblUsername.setText("Username:");

       lblServerAddress.setText("Server address:");

       txtUsername.setText("");

       txtServerAddress.setText("127.0.0.1");

       btnConnect.setText("Connect");
       btnConnect.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnConnectActionPerformed(evt);
           }
       });

       btnDisconnect.setText("Disconnect");
       btnDisconnect.setEnabled(false);
       btnDisconnect.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnDisconnectActionPerformed(evt);
           }
       });

       txtrMyMessageArea.setColumns(20);
       txtrMyMessageArea.setRows(5);
       sbrMyMessageArea.setViewportView(txtrMyMessageArea);

       btnSend.setText("Send");
       btnSend.setEnabled(false);
       btnSend.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               btnSendActionPerformed(evt);
           }
       });

       org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
           .add(layout.createSequentialGroup()
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                   .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                       .add(15, 15, 15)
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                           .add(org.jdesktop.layout.GroupLayout.LEADING, lblUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .add(org.jdesktop.layout.GroupLayout.LEADING, lblServerAddress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                           .add(txtUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                           .add(txtServerAddress))
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                       .add(btnConnect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 117, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                       .add(btnDisconnect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                   .add(layout.createSequentialGroup()
                       .addContainerGap()
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                           .add(sbrChatArea)
                           .add(layout.createSequentialGroup()
                               .add(sbrMyMessageArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 342, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                               .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                               .add(btnSend, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
           .add(layout.createSequentialGroup()
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                   .add(layout.createSequentialGroup()
                       .add(15, 15, 15)
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(lblUsername)
                           .add(txtUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                           .add(lblServerAddress)
                           .add(txtServerAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                       .add(8, 8, 8))
                   .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                       .addContainerGap()
                       .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                           .add(org.jdesktop.layout.GroupLayout.TRAILING, btnConnect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                           .add(org.jdesktop.layout.GroupLayout.TRAILING, btnDisconnect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                       .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
               .add(sbrChatArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 176, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
               .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                   .add(sbrMyMessageArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                   .add(btnSend, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
               .addContainerGap())
       );

       pack();
   }// </editor-fold>

   private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {
	   try {
		   socket = new Socket(txtServerAddress.getText(), 6666);
		   nickname = txtUsername.getText();
		   
		   InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
		   reader = new BufferedReader(streamReader);
		   writer = new PrintWriter(socket.getOutputStream());
		   
		   writer.println(nickname);	//отправляем ник после подключения
		   writer.flush();
		   
		   if (nickname.isEmpty() || nickname.startsWith(" ")) {
			   txtrChatArea.setText("Username must not be empty or begin with space\n");
			   disconnect();
			   return;
		   }
		   
		   //очищаем арею
		   txtrChatArea.setText("");
           
           //запускаем поток чтения
           Thread threadIn = new Thread(new MessageReceiver(socket, txtrChatArea));
           threadIn.start();
           
           txtServerAddress.setEnabled(false);
           txtUsername.setEnabled(false);
           btnConnect.setEnabled(false);
           btnDisconnect.setEnabled(true);
           btnSend.setEnabled(true);
			
		} catch (UnknownHostException e) {
			btnConnect.setEnabled(true);
			txtrChatArea.setText("Unknow server name");
		} catch (IOException e) {
			btnConnect.setEnabled(true);
			txtrChatArea.setText("Connection refused");
		}
   }

   private void btnDisconnectActionPerformed(java.awt.event.ActionEvent evt) {
	   disconnect();
   }

   private void disconnect() {
	   writer.println("chat:exit");
	   writer.flush();
	   txtrChatArea.append("Disconnected");
	   txtServerAddress.setEnabled(true);
	   txtUsername.setEnabled(true);
	   btnConnect.setEnabled(true);
	   btnDisconnect.setEnabled(false);
	   btnSend.setEnabled(false);
   }

private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {
		String outMessage = txtrMyMessageArea.getText();
		if (outMessage.isEmpty() == false) {
			writer.println(outMessage);
			writer.flush();
			txtrMyMessageArea.setText("");	
		}
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String args[]) {
       /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
               new JavaWindowChat().setVisible(true);
           }
       });
   }
   
   
   // Variables declaration - do not modify
   private javax.swing.JButton btnConnect;
   private javax.swing.JButton btnDisconnect;
   private javax.swing.JButton btnSend;
   private javax.swing.JLabel lblServerAddress;
   private javax.swing.JLabel lblUsername;
   private javax.swing.JScrollPane sbrChatArea;
   private javax.swing.JScrollPane sbrMyMessageArea;
   private javax.swing.JTextField txtServerAddress;
   private javax.swing.JTextField txtUsername;
   private javax.swing.JTextArea txtrChatArea;
   private javax.swing.JTextArea txtrMyMessageArea;
   // End of variables declaration
}