package net.dowster.school.datacomm.program4.commands;

import net.dowster.school.datacomm.program4.*;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class GetFile extends Command
{

   private static Set<Integer> portsInUse = new HashSet<Integer>();
   private static Semaphore    portSetSem = new Semaphore(1);

   private static int MIN_PORT = 5750;
   private static int MAX_PORT = 5799;

   private String fileName;

   public GetFile(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
      fileName = inputScanner.next();
   }

   public GetFile(ClientConnection clientConnection, PrintWriter logWriter, String fileName)
   {
      super(clientConnection, logWriter);
      this.fileName = fileName;
   }

   @Override
   public void execute() {
      try
      {
         this.send();
      } catch (IOException | InterruptedException e)
      {
         e.printStackTrace(logWriter);
         socketWriter.println("ERROR");
      }
   }

   public void request() throws IOException
   {
      // Tell the server that we want to "GET: fileName"
      socketWriter.print("GET: ");
      socketWriter.println(fileName);

      // Somewhere to store the port that's returned
      int port;
      if(inputScanner.next().equals("PORT:")) {
         port = inputScanner.nextInt();
      } else
         return; // Just stop if we don't get a port back, something's wrong

      // Create the new transfer socket for this file.
      Socket transferSocket = new Socket(socket.getInetAddress(), port);
      File transferFile = new File(FTPClient.getFileDir() + "\\" + fileName);

      // Setup the receiver thread to write to the file outside the main GUI
      FileReceiverThread fileReceiverThread = new FileReceiverThread(transferSocket, transferFile, logWriter);
      fileReceiverThread.start();
   }

   public void send() throws IOException, InterruptedException
   {
      File toSend = new File(FTPServer.GetFileDir() + "\\" + fileName);

      if(!toSend.exists()) {
         socketWriter.println("File: \"" + fileName + "\" does not exist!");
         return;
      }

      int port = MIN_PORT;


      portSetSem.acquire();
      for(; port <= MAX_PORT; port++) {
         if(!portsInUse.contains(port) && portsInUse.add(port))
            break;
      }
      portSetSem.release();

      if(port > MAX_PORT) {
         socketWriter.println("ERROR");
         return;
      }

      socketWriter.println("PORT: " + port);
      ServerSocket transferSocket = new ServerSocket();
      transferSocket.bind(new InetSocketAddress(port));

      Socket socket = transferSocket.accept();

      FileSenderThread serverThread = new FileSenderThread(socket, toSend, logWriter);
      serverThread.start();

      transferSocket.close();

      portSetSem.acquire();
      portsInUse.remove(port);
      portSetSem.release();
   }
}
