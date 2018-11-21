package net.dowster.school.datacomm.program4;

import java.io.*;
import java.net.Socket;

public class FileSenderThread extends Thread
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
   public FileSenderThread(Socket socket, File transferFile, PrintWriter logWriter)
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
         InputStream fis  = new FileInputStream(transferFile);
         OutputStream sos = socket.getOutputStream();

         byte [] buffer = new byte[1024];
         int readBytes;
         while((readBytes = fis.read(buffer)) > -1) {
            sos.write(buffer, 0, readBytes);
            sos.flush();
         }

         fis.close();
         sos.close();
         socket.close();

      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
   }
}
