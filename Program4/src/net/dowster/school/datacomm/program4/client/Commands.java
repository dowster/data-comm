package net.dowster.school.datacomm.program4.client;

import net.dowster.school.datacomm.program4.FileReceiverThread;
import net.dowster.school.datacomm.program4.FileSenderThread;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import static net.dowster.school.datacomm.program4.Dictionary.*;

public class Commands
{
   public static void Send(Connection connection, String fileName, PrintWriter logWriter) throws IOException
   {
      // Tell the server that we want to "PUT fileName"
      connection.getSocketWriter().print(Put.PUT + " ");
      connection.getSocketWriter().println(fileName);

      // Somewhere to store the port that's returned
      int port;
      if(connection.getInputScanner().next().equals(Put.PORT)) {
         port = connection.getInputScanner().nextInt();
      } else
         return; // Just stop if we don't Get a port back, something's wrong

      connection.getInputScanner().nextLine();

      // Create the new transfer socket for this file.
      Socket transferSocket = new Socket(connection.getSocket().getInetAddress(), port);
      File transferFile = new File(FTPClient.getFileDir(), fileName);

      // Setup the receiver thread to write to the file outside the main GUI
      FileSenderThread fileSenderThread = new FileSenderThread(transferSocket, transferFile, logWriter);
      fileSenderThread.start();
   }

   // Client requesting the file for the GET command
   public static void Get(Connection connection, String fileName, PrintWriter logWriter) throws IOException
   {
      // Tell the server that we want to "GET: fileName"
      connection.getSocketWriter().print(Get.GET + " ");
      connection.getSocketWriter().println(fileName);

      // Somewhere to store the port that's returned
      int port;

      if (connection.getInputScanner().hasNext(Get.PORT))
      {
         connection.getInputScanner().next();
         port = connection.getInputScanner().nextInt();
      } else
      {
         logWriter.print(connection.getInputScanner().nextLine());
         return; // Just stop if we don't Get a port back, something's wrong
      }

      connection.getInputScanner().nextLine();

      // Create the new transfer socket for this file.
      Socket transferSocket = new Socket(connection.getSocket().getInetAddress(), port);
      File transferFile = new File(FTPClient.getFileDir(),fileName);

      // Setup the receiver thread to write to the file outside the main GUI
      FileReceiverThread fileReceiverThread = new FileReceiverThread(transferSocket, transferFile, logWriter);
      fileReceiverThread.start();
   }

   public static Vector<File> List(Connection connection, PrintWriter logWriter) {
      Vector<File> fileList = new Vector<File>();

      connection.getSocketWriter().println(List.LIST);

      if(connection.getInputScanner().hasNextLine() && connection.getInputScanner().nextLine().contains(List.BEGIN))
      {
         while (connection.getInputScanner().hasNextLine())
         {
            String name = connection.getInputScanner().nextLine();
            if (name.contains(List.END))
               break;
            else
               fileList.add(new File(name));
         }
      }

      return fileList;
   }
}
