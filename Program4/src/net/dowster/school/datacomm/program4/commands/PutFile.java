package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PutFile extends Command
{

   public PutFile(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      super(inputScanner, socketWriter, logWriter);
   }
}
