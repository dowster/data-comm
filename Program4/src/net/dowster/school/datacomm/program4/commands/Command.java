package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Command extends Thread
{
   protected Scanner inputScanner;
   protected PrintWriter socketWriter;

   public Command (Scanner inputScanner, PrintWriter socketWriter) {
      this.inputScanner = inputScanner;
      this.socketWriter = socketWriter;
   }

   public void execute() {
      this.start();
   }
}
