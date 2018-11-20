package net.dowster.school.datacomm.program4.commands;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ListFiles extends Command
{

   public ListFiles(Scanner inputScanner, PrintWriter socketWriter)
   {
      super(inputScanner, socketWriter);
   }
}
