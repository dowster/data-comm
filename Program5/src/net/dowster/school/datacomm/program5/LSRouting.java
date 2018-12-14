package net.dowster.school.datacomm.program5;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Console program which calculates the least cost routes for a specific router
 * on a network using link state routing algorithm.
 *
 * The input should be formatted with three types of lines:
 *    Type 1 - Network Size, occurs once:
 *       This line contains a single integer which is the number of routers in
 *       the network.
 *
 *    Type 2 - Neighbor Costs, can occur any number of times after type 1:
 *       This line starts with the integer ID of a router in the network. Then
 *       for each neighbor of the router the line contains the ID and cost pair
 *       for the link.
 *       Example: 5 7 19 8 20 3 1
 *                Router 5 has three neighbors:
 *                   Router 7 is a neighbor with link cost of 19
 *                   Router 8 is a neighbor with link cost of 20
 *                   Router 3 is a neighbor with link cost of 01
 *
 *    Type 3 - Start Router, must be the last line of the input:
 *       This line contains a single integer which is the router to sort and
 *       generate least cost paths for.
 */
public class LSRouting
{
   /**
    * Reads in network info, sorts the network around the start router, and then
    * prints the least cost paths to all other routers in the network.
    *
    * @param arrrrgs unused
    */
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
         int[] line =
               Arrays.stream(inputScanner.nextLine().split(" "))
                     .mapToInt(Integer::parseInt).toArray();

         startRouter = line[0];

         for(int link = 1; link < line.length - 1; link++ )
            network[line[0] - 1][line[link] - 1] =
                  new Pair(line[link], line[++link]);
      }

      sortNetwork(network, startRouter);
      printPath(network, startRouter);
   }

   /**
    * Prints out all the shortest paths from startRouter to all other routers.
    *
    * @param net The network matrix (with least cost paths calculated for start)
    * @param start The router to start at
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
    * @param net  the matrix of routers and path costs to sort
    * @param strt the router to calculate least cost paths for (could be any)
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
    * @param network the matrix of routers and route costs
    * @param start   the router to start the path at
    * @param dest    the router to end the path at
    * @return a stream of the router IDs along the path from start to end
    */
   private static Stream<Integer> getPath(
         Pair<Integer,Integer>[][] network, int start, int dest) {
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
