package net.dowster.school.datacomm.program4.commands;

import java.net.Socket;

public class CommandProcessor
{
   public static Command createCommand(String command, Socket socket)
   {
      switch (command)
      {
         case "LIST": return new ListFiles(socket);
         case "GET": return new GetFile(socket);
         case "PUT": return new PutFile(socket);
      }
      return null;
   }
}
