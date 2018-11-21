package net.dowster.school.datacomm.program4.client;

import net.dowster.school.datacomm.program4.client.Utils.JTextAreaWriter;

import javax.swing.*;
import java.io.*;

/**
 * @author mattsteiner
 */
public class FTPClient extends javax.swing.JFrame
{
   DefaultListModel<String> serverFilesListModel = new DefaultListModel<String>();
   DefaultListModel<String> clientFilesListModel = new DefaultListModel<String>();

   PrintWriter logWriter;

   private Connection connection;

   /**
    * Creates new form FTPClient
    */
   public FTPClient()
   {
      initComponents();

      setSize(500, 550);
      this.getButton.setEnabled(false);
      this.putButton.setEnabled(false);

      logWriter = new PrintWriter(new JTextAreaWriter(this.serverLog));
   }

   public static File getFileDir()
   {
      File dir = new File(".\\ClientFiles\\");
      if (!dir.exists())
         dir.mkdirs();
      return dir;
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents()
   {

      hostLabel = new javax.swing.JLabel();
      serverAddressField = new javax.swing.JTextField();
      portLabel = new javax.swing.JLabel();
      serverPort = new javax.swing.JTextField();
      disconnectButton = new javax.swing.JButton();
      jScrollPane1 = new javax.swing.JScrollPane();
      clientFilesListView = new javax.swing.JList<>();
      jScrollPane2 = new javax.swing.JScrollPane();
      serverFilesListView = new javax.swing.JList<>();
      serverFilesLabel = new javax.swing.JLabel();
      clientFilesLabel = new javax.swing.JLabel();
      putButton = new javax.swing.JButton();
      getButton = new javax.swing.JButton();
      jLabel1 = new javax.swing.JLabel();
      jScrollPane3 = new javax.swing.JScrollPane();
      serverLog = new javax.swing.JTextArea();

      hostLabel.setText("Host");

      portLabel.setText("Port");

      disconnectButton.setText("Connect");
      disconnectButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            disconnectButtonActionPerformed(evt);
         }
      });

      clientFilesListView.setModel(clientFilesListModel);

      jScrollPane1.setViewportView(clientFilesListView);

      serverFilesListView.setModel(serverFilesListModel);

      jScrollPane2.setViewportView(serverFilesListView);

      serverFilesLabel.setText("Server Files");

      clientFilesLabel.setText("Client Files");

      putButton.setText("Put");
      putButton.setToolTipText("");
      putButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            putButtonActionPerformed(evt);
         }
      });

      getButton.setText("List");
      getButton.setToolTipText("");
      getButton.addActionListener(new java.awt.event.ActionListener()
      {
         public void actionPerformed(java.awt.event.ActionEvent evt)
         {
            getButtonActionPerformed(evt);
         }
      });

      jLabel1.setText("Output Messages");

      serverLog.setColumns(20);
      serverLog.setRows(5);
      jScrollPane3.setViewportView(serverLog);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                          .addGroup(layout.createSequentialGroup()
                                                .addComponent(hostLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(serverAddressField))
                                          .addGroup(layout.createSequentialGroup()
                                                .addComponent(portLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(serverPort, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(disconnectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                              .addGroup(layout.createSequentialGroup()
                                    .addComponent(serverFilesLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(clientFilesLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(100, 100, 100))
                              .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(0, 0, Short.MAX_VALUE))
                              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                          .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                                      .addComponent(getButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                      .addComponent(jScrollPane1)
                                                      .addComponent(putButton, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))))
                                    .addGap(20, 20, 20))))
      );
      layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(hostLabel)
                              .addComponent(serverAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(portLabel)
                              .addComponent(serverPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(disconnectButton))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(serverFilesLabel)
                              .addComponent(clientFilesLabel))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(putButton)
                              .addComponent(getButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(30, Short.MAX_VALUE))
      );

      hostLabel.getAccessibleContext().setAccessibleName("hostLabel");
   }// </editor-fold>//GEN-END:initComponents

   /**
    * @param args the command line arguments
    */
   public static void main(String args[])
   {
      /* Set the Nimbus look and feel */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
       * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
       */
      try
      {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
         {
            if ("Nimbus".equals(info.getName()))
            {
               javax.swing.UIManager.setLookAndFeel(info.getClassName());
               break;
            }
         }
      } catch (ClassNotFoundException ex)
      {
         java.util.logging.Logger.getLogger(FTPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (InstantiationException ex)
      {
         java.util.logging.Logger.getLogger(FTPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IllegalAccessException ex)
      {
         java.util.logging.Logger.getLogger(FTPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (javax.swing.UnsupportedLookAndFeelException ex)
      {
         java.util.logging.Logger.getLogger(FTPClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      //</editor-fold>

      /* Create and display the form */
      java.awt.EventQueue.invokeLater(() -> new FTPClient().setVisible(true));
   }

   private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt)
   {
      if (this.connection != null && this.connection.isConnected())
         disconnect();
      else
         connect();
   }

   private void putButtonActionPerformed(java.awt.event.ActionEvent evt)
   {
      String fileName = clientFilesListView.getSelectedValue();
      sendFile(fileName);
      updateFileLists();
   }

   private void sendFile(String fileName)
   {
      try
      {
         Commands.Send(connection, fileName, logWriter);
      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
      delayedListupdate();
   }

   private void updateFileLists()
   {
      listRemoteFiles();

      listLocalFiles();
   }

   private void listLocalFiles()
   {
      clientFilesListModel.removeAllElements();
      for (File file : getFileDir().listFiles())
      {
         if (file.isFile())
         {
            clientFilesListModel.addElement(file.getName());
         }
      }
   }

   private void listRemoteFiles()
   {
      serverFilesListModel.removeAllElements();
      for (File file : Commands.List(connection, logWriter))
         serverFilesListModel.addElement(file.getName());
   }

   private void getButtonActionPerformed(java.awt.event.ActionEvent evt)
   {
      String fileName = serverFilesListView.getSelectedValue();
      getFile(fileName);
   }

   private void getFile(String fileName)
   {
      try
      {
         Commands.Get(connection, fileName, logWriter);
      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
      delayedListupdate();
   }

   private void updateConnectionStatus()
   {
      if (this.connection != null && this.connection.isConnected())
      {
         this.disconnectButton.setText("Disconnect");
         this.getButton.setEnabled(true);
         this.putButton.setEnabled(true);
      } else
      {
         this.disconnectButton.setText("Connect");
         this.getButton.setEnabled(false);
         this.putButton.setEnabled(false);
      }
   }

   private void disconnect()
   {
      try
      {
         this.connection.disconnect();
         this.connection = null;
      } catch (IOException e)
      {
         this.connection = null;
      }
      updateConnectionStatus();
   }

   private void connect()
   {
      try
      {
         this.connection =
               new Connection(
                     this.serverAddressField.getText(),
                     Integer.decode(this.serverPort.getText()));
         logWriter.println("Connected to server");
         updateConnectionStatus();
         updateFileLists();
      } catch (IOException ex)
      {
         logWriter.println("Failed to connect server");
      }
   }

   //<editor-fold desc="Generated Code">
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel clientFilesLabel;
   private javax.swing.JList<String> clientFilesListView;
   private javax.swing.JButton disconnectButton;
   private javax.swing.JButton getButton;
   private javax.swing.JTextField serverAddressField;
   private javax.swing.JLabel hostLabel;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JTextArea serverLog;
   private javax.swing.JTextField serverPort;
   private javax.swing.JLabel portLabel;
   private javax.swing.JButton putButton;
   private javax.swing.JLabel serverFilesLabel;
   private javax.swing.JList<String> serverFilesListView;
   // End of variables declaration//GEN-END:variables

   private void delayedListupdate()
   {
      if (connection != null && connection.isConnected())
         updateFileLists();
   }
   //</editor-fold>
}