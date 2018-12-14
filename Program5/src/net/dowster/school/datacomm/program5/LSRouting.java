package net.dowster.school.datacomm.program5;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Stream;

public class LSRouting
{
   public static void main(String[] arrrrgs)
   {
      Pair<Integer, Integer> network[][];
      int startRouter = -1;

      Scanner inputScanner = new Scanner(System.in);

      int networkSize = inputScanner.nextInt();
      network = new Pair[networkSize][networkSize];
      for(int router = 0; router < networkSize; router++)
         network[router][router] = new Pair<>(router + 1, 0);

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

      printPath(network, startRouter);
   }

   /**
    * Prints out all the shortest paths from startRouter to all other routers.
    *
    * @param net
    * @param start
    */
   private static void printPath(Pair<Integer, Integer>[][] net, int start)
   {
      for(int dest = 1; dest <= net.length; dest++)
      {
         System.out.print("Shortest path to router " + dest + ":");
         getPath(net, start, dest)
               .forEach((hop) -> System.out.print(" " + hop));
         System.out.println(": cost = " + net[start - 1][dest - 1].getValue());
      }
   }

   /**
    * Sorts the network and builds the shortest path from router strt to all
    * other routers on the network.
    *
    * The result of this is that net[strt - 1] is an array of the shortest
    * paths. All other net[n] arrays are unsorted.
    *
    * @param net
    * @param strt
    */
   private static void sortNetwork(Pair<Integer, Integer>[][] net, int strt)
   {
      // loop
      List<Integer> visited = new ArrayList<>();
      visited.add(strt - 1);
      while(visited.size() < net.length)
      {
         // find w not in N` such that D(w) is a minimum
         int minCost = Integer.MAX_VALUE;
         int w = -1;
         for(int index = 0; index < net.length; index++)
         {
            if(
                  !visited.contains(index) &&
                  net[strt - 1][index] != null &&
                  net[strt - 1][index].getValue() < minCost)
            {
               minCost = net[strt - 1][index].getValue();
               w = index;
            }
         }
         // add w to N`
         visited.add(w);
         // update D(v) for each neighbor V of w and not in N`:
            //D(v) = min( D(v), D(w) + c(w,v) )
         for(int V = 0; V < net.length; V++)
         {
            if(
                  !visited.contains(V) &&
                  net[w][V] != null &&
                  (
                     net[strt-1][V] == null ||
                     net[strt-1][V].getValue() >
                        net[strt-1][w].getValue() + net[w][V].getValue())) {
               net[strt-1][V] = new Pair<>(w + 1,
                     net[strt - 1][w].getValue() + net[w][V].getValue());
            }
         }
         /* new cost to v is either old cost to v or known least path
            least path cost to w plus cost from w to v */
      // until N` = N
      }
   }

   /**
    * This should get the path from start to dest (in that order).
    *
    * Since net[start - 1][dest - 1] contains the last router before dest this
    * will need to work backwards.
    *
    * IE: secondLast = net[start - 1][dest - 1].getKey();
    *     thirdLast  = net[start - 1][secondLast - 1];
    * End condition should be when:
    *     net[start - 1][nLast - 1] == nLast;
    *
    * @param network
    * @param start
    * @param dest
    * @return
    */
   private static Stream<Integer> getPath(Pair<Integer,Integer>[][] network, int start, int dest) {
      // If the start == the dest we don't need to search the network, router
      // should have a zero cost path to itself. If not, get off my network.
      if(start == dest)
         return Arrays.stream(new Integer[] {start});

      // Was going to use a stack, but Deque is recommended as it maintains the
      // LIFO nature of a stack, which is needed here.
      Deque<Integer> path = new ArrayDeque<>();
      int hop = dest;
      path.push(dest);

      while(network[start - 1][hop - 1].getKey() != hop)
      {
         hop = network[start - 1][hop - 1].getKey();
         path.push(hop);
      }
      path.push(start);
      return path.stream();
   }

}
