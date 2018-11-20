package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Command extends Thread
{
   protected Scanner inputScanner;
   protected PrintWriter socketWriter;
   protected PrintWriter logWriter;

   public Command (Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter) {
      this.inputScanner = inputScanner;
      this.socketWriter = socketWriter;
      this.logWriter = logWriter;
   }

   public void execute() {
      this.start();
   }
}
