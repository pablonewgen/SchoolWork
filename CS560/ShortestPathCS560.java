/*
 * CS 560 Programming Assignment
 * Finds the shortest path through a 233-node, fixed-dimension hexagonal grid
 * Group members: Tyler Nishida, Joshua Robey, Pakie Seung, Paul Nguyen, Kevin Leonor
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class ShortestPathCS560 {

    public static void main(String[] args) {

        String filename = "INPUT.TXT";
        Dijkstra(createAdjacencyList(filename), 226);
    }

    // **************************************************
    /*
     * Our group's version of Dijkstra's algorithm. Follows same methodology
     * as version presented in class. Modifications include conforming algorithm
     * to our adjacency list and renaming variables for clarity.
     */
    static void Dijkstra(int[][] g, int start) {

        int i;
        int currentNode;                                            // Node currently visiting
        int destinationNode;                                        // Neighbor nodes
        int dist;                                                   // Distance from a node to start
        boolean[] visited = new boolean[234];                       // Nodes visited
        int[] distance = new int[234];                              // Weight cost from start
        int[] parent = new int[234];                                // Parent with lowest cost
        int bestSoFar = -1;
        Stack<Integer> path = new Stack<Integer>();

        // All nodes not yet visited, unreachable at start with no current pathways
        for (i = 1; i <= 233; i++) {
            visited[i] = false;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[start] = g[211][226];                              // Get weight of first node
        currentNode = destinationNode = start;

        while (!visited[8]) {
            visited[currentNode] = true;                            // Mark vertex as visited
            // DestinationNode sets to each of currentNode's neighbors; calculate distance from start vertex
            for(i = 0; i < g[currentNode].length; i++) {
                if (g[currentNode][i] != 0) {
                    destinationNode = i;
                    // if(calculated distance > known distance)
                    if (distance[destinationNode] > (distance[currentNode] + g[currentNode][destinationNode])) {
                        bestSoFar = destinationNode;
                        distance[destinationNode] = distance[currentNode]
                                + g[currentNode][destinationNode];  // Updating known with new shortest path
                        parent[destinationNode] = currentNode;      // Update previous vertex with current vertex
                    }                                               // end if
                }
            }                                                       // end for

            // Reset dist & currentNode; get the smallest known vertex haven't yet visited
            currentNode = start;
            dist = Integer.MAX_VALUE;
            for(i = 1; i <= 233; i++) {
                // if node has not been visited AND has the shortest distance
                if(!visited[i] && (dist > distance[i])) {
                    dist = distance[i];                             // Update new shortest distance
                    currentNode = i;                                // Set currentNode to closest one so far
                }
            }
        }                                                           // end while(!visited[])

        // Push path backwards; pops to print forward
        i = 8;
        while(i != 226) {
            path.push(i);
            i = parent[i];
        }
        System.out.println("226");                                  // start node
        while(!path.empty()) {
            System.out.println(path.pop());
        }
        System.out.println("MINIMAL-COST PATH COSTS: " + distance[8]);

        // Write to OUTPUT file
        try{
            PrintWriter writer = new PrintWriter("OUTPUT.TXT", "UTF-8");
            i = 8;
            while(i != 226) {
                path.push(i);
                i = parent[i];
            }
            writer.println("226"); //start node
            while(!path.empty()) {
                writer.println(path.pop());
            }
            writer.println("MINIMAL-COST PATH COSTS: " + distance[8]);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    } // End Dijkstra

    // **************************************************
    /*
     * Creates the adjacency list for the graph; assigns elements by looking at
     * the node in a chosen direction, skipping border nodes when needed. Passes in
     * the filepath of INPUT.TXT
     */
    static int[][] createAdjacencyList(String fname) {

        //Read input file
        File file = new File(fname);
        int[] inputVertices = new int[234];
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextInt()) {
                int lineNumber = scan.nextInt();
                int textWeight = scan.nextInt();
                inputVertices[lineNumber] = textWeight;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Instantiate graph object
        int[][] adjacencyList = new int[234][234];

        // down-right
        int i = 1;
        int j = 0;
        while (i != 8) {
            adjacencyList[i][i + 8] = inputVertices[i + 8];
            i++;
        }
        i = 9;
        while (i != 211) {
            for (j = 0; j < 14; j++) {
                if (i == 210) {
                    adjacencyList[i][i + 8] = inputVertices[i + 8];
                    i++;
                    break;
                }
                adjacencyList[i][i + 8] = inputVertices[i + 8];
                i++;
            }
            if (i == 211) {
                break;
            }
            i++;
        }
        while (i != 226) {
            if (i == 218) {
                i = 219;
            }
            adjacencyList[i][i + 8] = inputVertices[i + 8];
            i++;
        }

        // down
        i = 1;
        while (i != 219) {
            adjacencyList[i][i + 15] = inputVertices[i + 15];
            i++;
        }

        // down-left
        i = 2;
        while (i != 16) {
            adjacencyList[i][i + 7] = inputVertices[i + 7];
            i++;
        }
        i = 17;
        while (i != 225) {
            for (j = 0; j < 14; j++) {
                if (i == 210) {
                    adjacencyList[i][i + 7] = inputVertices[i + 7];
                    i++;
                    break;
                }
                adjacencyList[i][i + 7] = inputVertices[i + 7];
                i++;
            }
            if (i == 211) {
                break;
            }
            i++;
        }
        i = 212;
        while (i != 226) {
            adjacencyList[i][i + 7] = inputVertices[i + 7];
            i++;
        }

        // up-left
        i = 9;
        while (i != 16) {
            adjacencyList[i][i - 8] = inputVertices[i - 8];
            i++;
        }
        i = 17;
        while (i != 225) {
            for (j = 0; j < 14; j++) {
                if (i == 210) {
                    adjacencyList[i][i - 8] = inputVertices[i - 8];
                    i++;
                    break;
                }
                adjacencyList[i][i - 8] = inputVertices[i - 8];
                i++;
            }
            if (i == 211) {
                break;
            }
            i++;
        }
        i = 212;
        while (i != 234) {
            if (i == 226) {
                i = 227;
            }
            adjacencyList[i][i - 8] = inputVertices[i - 8];
            i++;
        }

        // up
        i = 16;
        while (i != 234) {
            adjacencyList[i][i - 15] = inputVertices[i - 15];
            i++;
        }

        // up-right
        i = 9;
        while (i != 23) {
            adjacencyList[i][i - 7] = inputVertices[i - 7];
            i++;
        }
        i = 24;
        while (i != 232) {
            for (j = 0; j < 14; j++) {
                if (i == 210) {
                    adjacencyList[i][i - 7] = inputVertices[i - 7];
                    i++;
                    break;
                }
                adjacencyList[i][i - 7] = inputVertices[i - 7];
                i++;
            }
            if (i == 211) {
                break;
            }
            i++;
        }
        i = 211;
        while (i != 233) {
            if (i == 218) {
                i = 219;
            }
            adjacencyList[i][i - 7] = inputVertices[i - 7];
            i++;
        }                                                           // end of graph instantiation
        return adjacencyList;
    }                                                               // end createAdjacencyList()
}

// end



