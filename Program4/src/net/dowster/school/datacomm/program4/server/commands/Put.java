package net.dowster.school.datacomm.program4.server.commands;

import net.dowster.school.datacomm.program4.*;
import net.dowster.school.datacomm.program4.server.FTPServer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static net.dowster.school.datacomm.program4.Dictionary.Put.PORT;

/**
 * Command to allow clients to put files onto the server.
 */
public class Put extends Command
{
   private static Semaphore portSetSem = new Semaphore(1);
   private static int PUT_PORT = 5775;
   private String fileName;

   /**
    * Create an instance of the Put command, this should only be used by the
    * command factory.
    *
    * @param inputScanner input from the control connection.
    * @param socketWriter writer to the control connection.
    * @param logWriter writer to wherever the log output is supposed to go.
    */
   public Put(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
      fileName = inputScanner.nextLine().trim();
   }

   @Override
   public void execute() {
      try
      {
         this.receive();
      } catch (IOException | InterruptedException e)
      {
         e.printStackTrace(logWriter);
         socketWriter.println("ERROR");
      }
   }

   /**
    * Receive a file from the client.
    *
    * @throws IOException if there are issues with the IO streams
    * @throws InterruptedException if we are interrupted from the semaphore
    */
   public void receive() throws IOException, InterruptedException
   {
      File toReceive = new File(FTPServer.GetFileDir(), fileName);
      toReceive.createNewFile();

      portSetSem.acquire();

      socketWriter.println(PORT + " " + PUT_PORT);
      ServerSocket transferSocket = new ServerSocket();
      transferSocket.bind(new InetSocketAddress(PUT_PORT));

      Socket socket = transferSocket.accept();

      logWriter.println("Data connection local port# " + socket.getLocalPort() +
            " remote port# " + socket.getPort());

      FileReceiverThread receiverThread = new FileReceiverThread(socket, toReceive, logWriter);
      receiverThread.start();

      transferSocket.close();

      portSetSem.release();
   }
}
