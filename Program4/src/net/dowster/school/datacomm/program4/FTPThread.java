package net.dowster.school.datacomm.program4;

import net.dowster.school.datacomm.program4.commands.Command;
import net.dowster.school.datacomm.program4.commands.CommandProcessor;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * Implementation of Thread which handles a server thread for the cipher
 * server.
 */
public class FTPThread extends Thread
{
   private Socket socket;
   private PrintWriter logWriter;

   private Scanner inputScanner;
   private PrintWriter socketWriter;

   /**
    * Creates an echo server thread given a socket and log writer for log writing
    *
    * @param socket to interact on
    * @param logWriter to write out error / log messages to
    * @throws IOException in case things go wrong.
    */
   public FTPThread(Socket socket, PrintWriter logWriter)
   {
      this.socket = socket;
      this.logWriter = logWriter;
      try
      {
         inputScanner = new Scanner(socket.getInputStream());
         inputScanner.reset();
         socketWriter = new PrintWriter(socket.getOutputStream(), true);
      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
   }

   /**
    * Process the server thread until the client quits or says "quit"
    */
   @Override
   public void run()
   {
      super.run();

      logWriter.println("[" + (new Date()).toString() + "] Connection from " +
            socket.getInetAddress() + " on Port: " + socket.getPort());

      while (inputScanner.hasNext())
      {
         Command command = CommandProcessor.createCommand(inputScanner, socketWriter);
         command.execute();
      }

      logWriter.println("[" + (new Date()).toString() + "] " +
            "Connection closed. Port: " + socket.getPort());

      try
      {
         socket.close();
      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
   }
}
