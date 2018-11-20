package net.dowster.school.datacomm.program4.commands;

import net.dowster.school.datacomm.program4.ClientConnection;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Command extends Thread
{
   protected Socket socket;
   protected Scanner inputScanner;
   protected PrintWriter socketWriter;
   protected PrintWriter logWriter;

   public Command (Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter) {
      this.inputScanner = inputScanner;
      this.socketWriter = socketWriter;
      this.logWriter = logWriter;
   }

   public Command (ClientConnection clientConnection, PrintWriter logWriter) {
      this.socket = clientConnection.getSocket();
      this.inputScanner = clientConnection.getInputScanner();
      this.socketWriter = clientConnection.getPrinter();
      this.logWriter = logWriter;
   }

   public void execute() {
      this.start();
   }
}
