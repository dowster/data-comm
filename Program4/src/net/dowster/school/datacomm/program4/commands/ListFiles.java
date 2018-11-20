package net.dowster.school.datacomm.program4.commands;

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

   public Vector<File> query() {
      Vector<File> fileList = new Vector<File>();

      socketWriter.println(LIST);

      if(inputScanner.hasNext() && inputScanner.next().equals(BEGIN))

      while(inputScanner.hasNext()) {
         String name = inputScanner.next();
         if(name.equals(END))
            break;
         else
            fileList.add(new File(name));
      }

      return fileList;
   }

   public void respond() {
      File dir = new File("Files");

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
