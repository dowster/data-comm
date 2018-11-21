package net.dowster.school.datacomm.program4.server.commands;

import net.dowster.school.datacomm.program4.server.FTPServer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static net.dowster.school.datacomm.program4.Dictionary.List.BEGIN;
import static net.dowster.school.datacomm.program4.Dictionary.List.END;

/**
 * Command to allow clients to get a list of files on the server.
 */
public class List extends Command
{
   /**
    * Create an instance of the List command, this should only be used by the
    * command factory.
    *
    * @param inputScanner input from the control connection.
    * @param socketWriter writer to the control connection.
    * @param logWriter writer to wherever the log output is supposed to go.
    */
   public List(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
   }

   @Override
   public void execute() {
      this.respond();
   }

   /**
    * Sends a list of files on the server to the client.
    */
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
   }
}
