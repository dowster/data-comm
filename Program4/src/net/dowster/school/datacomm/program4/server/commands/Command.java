package net.dowster.school.datacomm.program4.server.commands;

import net.dowster.school.datacomm.program4.client.Connection;

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

   public Command (Connection connection, PrintWriter logWriter) {
      this.socket = connection.getSocket();
      this.inputScanner = connection.getInputScanner();
      this.socketWriter = connection.getSocketWriter();
      this.logWriter = logWriter;
   }

   public void execute() {
      this.start();
   }
}
