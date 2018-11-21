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

public class Put extends Command
{
   private static Semaphore portSetSem = new Semaphore(1);

   private static int PUT_PORT = 5775;

   private String fileName;

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
