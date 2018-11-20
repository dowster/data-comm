package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GetFile extends Command
{

   public GetFile(Scanner inputScanner, PrintWriter socketWriter)
   {
      super(inputScanner, socketWriter);
   }
}
