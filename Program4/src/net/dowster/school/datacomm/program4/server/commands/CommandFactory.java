package net.dowster.school.datacomm.program4.server.commands;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.PrintWriter;
import java.util.Scanner;

import static net.dowster.school.datacomm.program4.Dictionary.List.LIST;
import static net.dowster.school.datacomm.program4.Dictionary.Get.GET;
import static net.dowster.school.datacomm.program4.Dictionary.Put.PUT;

public class CommandFactory
{
   public static Command createCommand(Scanner inputScanner, PrintWriter socketWriter, PrintWriter logWriter)
   {
      switch (inputScanner.next())
      {
         case LIST: return new List(inputScanner, socketWriter, logWriter);
         case GET: return new Get(inputScanner, socketWriter, logWriter);
         case PUT: return new Put(inputScanner, socketWriter, logWriter);
      }
      throw new NotImplementedException();
   }
}
