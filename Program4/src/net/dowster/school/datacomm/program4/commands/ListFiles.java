package net.dowster.school.datacomm.program4.commands;

import net.dowster.school.datacomm.program4.ClientConnection;
import net.dowster.school.datacomm.program4.FTPServer;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListFiles extends Command
{

   private static String BEGIN = "BEGIN";
   private static String END = "END";
   private static String LIST = "LIST: ";

   public ListFiles(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
   }

   public ListFiles(ClientConnection clientConnection, PrintWriter logWriter)
   {
      super(clientConnection, logWriter);
   }

   @Override
   public void execute() {
      this.respond();
   }

   public Vector<File> query() {
      Vector<File> fileList = new Vector<File>();

      socketWriter.println(LIST);
      logWriter.println("Querying server for file list...");
      if(inputScanner.hasNextLine() && inputScanner.nextLine().contains(BEGIN))
      {
         while (inputScanner.hasNextLine())
         {
            String name = inputScanner.nextLine();
            if (name.contains(END))
               break;
            else
               fileList.add(new File(name));
         }
      }

      logWriter.println("Got " + fileList.size() + " files.");

      return fileList;
   }

   public void respond() {
      File dir = FTPServer.GetFileDir();

      socketWriter.println(BEGIN);

      for (File file: dir.listFiles())
      {
         if(file.isFile()) {
            socketWriter.println(file.getName());
         }
      }

      socketWriter.println(END);
      socketWriter.flush();

   }
}
