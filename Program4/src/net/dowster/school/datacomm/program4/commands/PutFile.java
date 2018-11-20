package net.dowster.school.datacomm.program4.commands;

import net.dowster.school.datacomm.program4.ClientConnection;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PutFile extends Command
{
   private String fileName;

   public PutFile(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
   }

   public PutFile(ClientConnection clientConnection, PrintWriter logWriter, String fileName)
   {
      super(clientConnection, logWriter);
      this.fileName = fileName;
   }
}
