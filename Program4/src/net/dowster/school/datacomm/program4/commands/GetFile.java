package net.dowster.school.datacomm.program4.commands;

import net.dowster.school.datacomm.program4.FileReceiverThread;
import net.dowster.school.datacomm.program4.FileSenderThread;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GetFile extends Command
{

   public static Set<Integer> portsInUse = new HashSet<Integer>();
   private static int MIN_PORT = 5750;
   private static int MAX_PORT = 5799;

   private String fileName;
   private PrintWriter logWriter;

   public GetFile(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
      fileName = inputScanner.nextLine();
   }

   public GetFile(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter, String fileName)
   {
      super(inputScanner, socketWriter, logWriter);
      this.fileName = fileName;
   }

   @Override
   public void execute() {
      try
      {
         this.send();
      } catch (IOException e)
      {
         e.printStackTrace(logWriter);
      }
   }

   public void request() throws IOException
   {
      socketWriter.print("GET: ");
      socketWriter.println(fileName);

      String address;
      int port;

      if(inputScanner.next().equals("PORT:")) {
         address = inputScanner.next("[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+");
         port = inputScanner.nextInt();
      } else
         return;

      Socket transferSocket = new Socket(address, port);
      File transferFile = new File("ClientFiles\\" + fileName);
      FileReceiverThread fileReceiverThread = new FileReceiverThread(transferSocket, transferFile, logWriter);
      fileReceiverThread.start();
   }

   public void send() throws IOException
   {
      File toSend = new File("Files\\" + fileName);

      int port = MIN_PORT;

      for(; port <= MAX_PORT; port++) {
         if(!portsInUse.contains(port))
            break;
      }

      if(port > MAX_PORT) {
         socketWriter.println("ERROR");
         return;
      }

      InetSocketAddress address = new InetSocketAddress(port);

      socketWriter.println("PORT: " + address.getAddress().toString() + " " + port);
      ServerSocket transferSocket = new ServerSocket();
      transferSocket.bind(new InetSocketAddress(port));

      Socket socket = transferSocket.accept();

      FileSenderThread serverThread = new FileSenderThread(socket, toSend, logWriter);
      serverThread.start();
   }
}
