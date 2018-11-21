package net.dowster.school.datacomm.program4.server.commands;

import net.dowster.school.datacomm.program4.server.FTPServer;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import static net.dowster.school.datacomm.program4.Dictionary.List.BEGIN;
import static net.dowster.school.datacomm.program4.Dictionary.List.END;

public class List extends Command
{

   public List(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
   }

   @Override
   public void execute() {
      this.respond();
   }

   public void respond() {
      File dir = FTPServer.GetFileDir();

      socketWriter.println(BEGIN);

      logWriter.println("Listing files.");

      for (File file: dir.listFiles())
      {
         if(file.isFile()) {
            socketWriter.println(file.getName());
         }
      }

      logWriter.println("Listed files.");

      socketWriter.println(END);

   }
}
