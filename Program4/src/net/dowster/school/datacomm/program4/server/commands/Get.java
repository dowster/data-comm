package net.dowster.school.datacomm.program4.server.commands;

import net.dowster.school.datacomm.program4.*;
import net.dowster.school.datacomm.program4.server.FTPServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import static net.dowster.school.datacomm.program4.Dictionary.Get.PORT;

public class Get extends Command
{
   private static Semaphore getPortSem = new Semaphore(1);

   private static int GET_PORT = 5750;

   private String fileName;

   public Get(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
      fileName = inputScanner.nextLine().trim();
   }

   @Override
   public void execute()
   {
      try
      {
         this.send();
      } catch (IOException | InterruptedException e)
      {
         e.printStackTrace(logWriter);
         socketWriter.println("ERROR");
      }
   }

   // Server sending the file for the GET command
   public void send() throws IOException, InterruptedException
   {
      File toSend = new File(FTPServer.GetFileDir(), fileName);

      if (!toSend.exists())
      {
         socketWriter.println("File: \"" + fileName + "\" does not exist!");
         return;
      }

      getPortSem.acquire();

      socketWriter.println(PORT + " " + GET_PORT);
      ServerSocket transferSocket = new ServerSocket();
      transferSocket.bind(new InetSocketAddress(GET_PORT));

      Socket socket = transferSocket.accept();

      logWriter.println("Data connection local port# " + socket.getLocalPort() +
            " remote port# " + socket.getPort());

      FileSenderThread serverThread = new FileSenderThread(socket, toSend, logWriter);
      serverThread.start();

      transferSocket.close();
      getPortSem.release();
   }
}
