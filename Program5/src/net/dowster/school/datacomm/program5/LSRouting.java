package net.dowster.school.datacomm.program5;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Scanner;

public class LSRouting
{
   public static void main(String[] arrrrgs)
   {
      Pair<Integer, Integer> network[][];
      int startRouter = -1;

      Scanner inputScanner = new Scanner(System.in);

      int networkSize = inputScanner.nextInt();
      network = new Pair[networkSize][networkSize];

      // Clear the rest of the first line.
      inputScanner.nextLine();

      while(inputScanner.hasNextLine()) {
         int[] currentLine =
               Arrays.stream(inputScanner.nextLine().split(" "))
                     .mapToInt(Integer::parseInt).toArray();

         startRouter = currentLine[0];

         for(int link = 1; link < currentLine.length - 1; link++ )
            network[currentLine[0] - 1][currentLine[link] - 1] = new Pair(currentLine[link], currentLine[++link]);
      }

      sortNetwork(network, startRouter);


      System.out.println("Yo we done, start at: " + startRouter);
   }

   private static void sortNetwork(Pair<Integer, Integer>[][] network, int startRouter)
   {
      // loop
         // find w not in N` such that D(w) is a minimum
         // add w to N`
         // update D(v) for each neighbor V of w and not in N`:
            //D(v) = min( D(v), D(w) + c(w,v) )
         /* new cost to v is either old cost to v or known least path
            least path cost to w plus cost from w to v */
      // until N` = N
   }
}
