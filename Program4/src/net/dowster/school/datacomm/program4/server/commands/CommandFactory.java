package net.dowster.school.datacomm.program4.server.commands;

import java.io.PrintWriter;
import java.util.Scanner;

import static net.dowster.school.datacomm.program4.Dictionary.List.LIST;
import static net.dowster.school.datacomm.program4.Dictionary.Get.GET;
import static net.dowster.school.datacomm.program4.Dictionary.Put.PUT;

/**
 * Creates a command object given the command text sent to the server.
 */
public class CommandFactory
{
   /**
    * Creates a command object w/ the first string in the input scanner.
    *
    * @param inputScanner input from the control connection.
    * @param socketWriter writer to the control connection.
    * @param logWriter writer to wherever the log output is supposed to go.
    * @return command object if the command string is valid, else null.
    */
   public static Command createCommand(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      switch (inputScanner.next())
      {
         case LIST: return new List(inputScanner, socketWriter, logWriter);
         case GET: return new Get(inputScanner, socketWriter, logWriter);
         case PUT: return new Put(inputScanner, socketWriter, logWriter);
      }
      return null;
   }
}
