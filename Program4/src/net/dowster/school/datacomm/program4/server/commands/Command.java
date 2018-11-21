package net.dowster.school.datacomm.program4.server.commands;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Abstract class for creating FTP Server Commands.
 */
public abstract class Command
{

   protected PrintWriter socketWriter;
   protected PrintWriter logWriter;

   /**
    * Create an instance of the a command, this should only be used by the
    * command factory.
    *
    * @param socketWriter writer to the control connection.
    * @param logWriter writer to wherever the log output is supposed to go.
    */
   public Command ( PrintWriter socketWriter, PrintWriter logWriter) {

      this.socketWriter = socketWriter;
      this.logWriter = logWriter;
   }

   /**
    * Execute the commands specific behavior. This should be overridden by the
    * child class.
    */
   public void execute() {

   }
}
