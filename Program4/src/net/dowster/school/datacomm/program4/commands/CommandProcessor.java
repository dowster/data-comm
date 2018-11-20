package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommandProcessor
{
   public static Command createCommand(Scanner inputScanner, PrintWriter socketWriter)
   {
      switch (inputScanner.next())
      {
         case "LIST": return new ListFiles(inputScanner, socketWriter);
         case "GET": return new GetFile(inputScanner, socketWriter);
         case "PUT": return new PutFile(inputScanner, socketWriter);
      }
      return null;
   }
}
