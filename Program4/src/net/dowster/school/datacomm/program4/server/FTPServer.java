package net.dowster.school.datacomm.program4.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Cipher server for program 2. Encrypts a message and sends it back to the
 * client.
 */
public class FTPServer {

   private static final int SERVER_PORT = 5721;
   public static final File LOG_PATH = new File("program4","logs");
   public static final File LOG_FILE = new File(LOG_PATH,"log.txt");

   private FileWriter logFileWriter;
   private PrintWriter logPrintWriter;

   private ServerSocket serverSocket;

   /**
    * Create the server object and start it up.
    * @param args unused
    * @throws IOException Someting very bad happened and we're just gonna shut
    *                     it all down. mmkay?
    */
   public static void main(String[] args) throws IOException
   {
      FTPServer server = new FTPServer();
      server.run();
   }

   /**
    * Creates an instance of server, sets up the socket connection and
    * opens the log file.
    *
    * @throws IOException if issue creating the socket connection or opening
    *                     the log file.
    */
   private FTPServer() throws IOException {
      serverSocket = new ServerSocket();
      serverSocket.bind(new InetSocketAddress(SERVER_PORT));

      LOG_PATH.mkdirs();

      logFileWriter = new FileWriter(LOG_FILE, true);
      logPrintWriter = new PrintWriter(logFileWriter, true);
   }

   public static File GetFileDir()
   {
      File dir = new File(".\\ServerFiles\\");
      if(!dir.exists())
         dir.mkdirs();

      return dir;
   }

   /**
    * Run the socket server handling code. Each connection will Get its own
    * serverThread to run on.
    * @throws IOException
    */
   private void run()
   {
      Socket socket;
      try
      {
         while ((socket = serverSocket.accept()) != null)
         {
            FTPThread serverThread = new FTPThread(socket, logPrintWriter);
            serverThread.start();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace(logPrintWriter);
      }
   }
}
