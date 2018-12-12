package net.dowster.school.datacomm.program5;

import java.util.Arrays;
import java.util.Scanner;

public class LSRouting
{
   public static void main(String[] arrrrgs)
   {
      int network[][];
      int startRouter;

      Scanner inputScanner = new Scanner(System.in);

      int networkSize = inputScanner.nextInt();
      network = new int[networkSize][networkSize];

      // Clear the rest of the first line.
      inputScanner.nextLine();

      while(inputScanner.hasNextLine()) {
         int[] currentLine =
               Arrays.stream(inputScanner.nextLine().split(" "))
                     .mapToInt(Integer::parseInt).toArray();

         startRouter = currentLine[0];

         for(int link = 1; link < currentLine.length - 1; link++ )
            network[currentLine[0] - 1][currentLine[link]] = currentLine[++link];
      }

      System.out.println("Yo we done");
   }
}
