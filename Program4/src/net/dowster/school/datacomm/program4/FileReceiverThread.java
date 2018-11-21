package net.dowster.school.datacomm.program4;

import java.io.*;
import java.net.Socket;

public class FileReceiverThread extends Thread
{
   private File transferFile;

   private Socket socket;
   private PrintWriter logWriter;

   /**
    * Creates an echo server thread given a socket and log writer for log writing
    *
    * @param socket to interact on
    * @param logWriter to write out error / log messages to
    * @throws IOException in case things go wrong.
    */
   public FileReceiverThread(Socket socket, File transferFile, PrintWriter logWriter)
   {
      this.socket = socket;
      this.transferFile = transferFile;
      this.logWriter = logWriter;
   }

   /**
    * Process the server thread until the client quits or says "quit"
    */
   @Override
   public void run()
   {
      try
      {
         OutputStream fos = new FileOutputStream(transferFile);
         InputStream sis  = socket.getInputStream();

         logWriter.println("Receiving the file...");

         byte [] buffer = new byte[1024];
         int readBytes;
         while((readBytes = sis.read(buffer)) > -1) {
            fos.write(buffer, 0, readBytes);
            fos.flush();
         }

         fos.close();
         socket.close();

         logWriter.println("Got the file: " + transferFile.getName());
         logWriter.println("Size: " + transferFile.length() + " Bytes.");
         logWriter.println("Data Connection Closed.");

      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
   }
}
