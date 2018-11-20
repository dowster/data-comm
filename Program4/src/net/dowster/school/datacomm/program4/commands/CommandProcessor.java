package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.util.Scanner;

public class CommandProcessor
{
   public static Command createCommand(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      switch (inputScanner.next())
      {
         case "LIST:": return new ListFiles(inputScanner, socketWriter, logWriter);
         case "GET:": return new GetFile(inputScanner, socketWriter, logWriter);
         case "PUT:": return new PutFile(inputScanner, socketWriter, logWriter);
      }
      return null;
   }
}
