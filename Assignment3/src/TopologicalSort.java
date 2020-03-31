import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    private int numNodes;
    private int numEdges;
    private int[][] edgeList;
    private AdjacencyList adjList;

    public int getNumNodes() {return numNodes; }
    public int getNumEdges() { return numEdges; }

    TopologicalSort(int nNodes, int nEdges, int[][] edges){
        if(nNodes <= 0)
            throw new IllegalArgumentException("Graph must have a positive, non-zero number of nodes");
        if(nEdges < 0)
            throw new IllegalArgumentException("Graph cannot have a negative number of edges");
        numEdges = nEdges;
        numNodes = nNodes;
        if(!checkEdgeList(nEdges, edges)){
            throw new IllegalArgumentException("Attempted to create a topological sort of invalid edge list");
        }
        edgeList = new int[nEdges + 1][2];
        for(int i = 1; i <= nEdges; i++){
            edgeList[i][0] = edges[i - 1][0];
            edgeList[i][1] = edges[i - 1][1];
        }
        adjList = new AdjacencyList(nNodes, nEdges, edges);
    }

    private boolean checkEdgeList(int nEdges, int[][] edges){
        if(edges == null || edges.length != nEdges)
            return false;
        for(int[] edge : edges){
            if(edge == null || edge.length != 2)
                return false;
            if(!(1 <= edge[0] || edge[0] <= numNodes) || !(1 <= edge[1] || edge[1] <= numNodes))
                return false;
        }
        return true;
    }

    private int[] computeInDegrees(){
        int[] inDegs = new int[numNodes + 1];

        for(int edge[] : edgeList){
            inDegs[edge[1]]++;
        }
        int node = 1;

        return inDegs;
    }

    public ArrayList<Integer> topoSort(){
        ArrayList<Integer> sorted = new ArrayList<>(numNodes);
        int[] inDegs = computeInDegrees();
        int visitedNodes = 0;
        Queue<Integer> sources = new ArrayDeque<>();
        for(int i = 1; i <= numNodes; i++){
            if(inDegs[i] == 0) {
                sources.add(i);
            }
        }
        while(!sources.isEmpty()) {
            int curNode = sources.remove();
            sorted.add(curNode);
            visitedNodes++;
            for (int node : adjList.getOutNeighbors(curNode)) {
                inDegs[node]--;
                if (inDegs[node] == 0)
                    sources.add(node);
            }
        }
        if(visitedNodes != numNodes){
            return null;
        }
        if(!checkTopologicalSort(numNodes, numEdges, edgeList, sorted)){
            System.out.println("Topological sort is incorrect.");
        }
        return sorted;
    }

    //--------------------------------------------------------------------
    // Method to check the correctness of a claimed topological sort of the
    // nodes of a digraph.  Returns true if correct, and false otherwise.

    private static boolean checkTopologicalSort(int numNodes, int numEdges, int[][] edgeList, List<Integer> topoSortedList) {
        // Check that the topologically sorted list contains numNodes nodes.
        if (topoSortedList.size() != numNodes) {
            return false;
        } // if

        // Record the position in the topologically sorted list of each node.
        // Also, check that each node has a correct label, and that no node
        // appears more than once in the list.
        int[] positionInList = new int[numNodes + 1];
        positionInList[0] = -1;    // node 0 does not exist, so it has no position in the list
        boolean[] alreadySeen = new boolean[numNodes + 1];   // alreadySeen[0] will be ignored
        for (int pos = 0; pos < numNodes; pos++) {   // pos is the position in the list
            int node = topoSortedList.get(pos);
            if (node < 1 || node > numNodes) {
                return false;
            } // if
            positionInList[node] = pos;
            if (alreadySeen[node]) {
                return false;
            } // if
            alreadySeen[node] = true;
        } // for pos

        // For each edge, check that it goes from a node with an earlier position
        // in the list to a node with a later position.
        for (int j = 0; j < numEdges; j++) {
            int x = edgeList[j][0];
            int y = edgeList[j][1];
            if (positionInList[x] > positionInList[y]) {
                return false;
            } // if
        } // for j

        return true;
    }  // checkTopologicalSort(int,int,int[][],ArrayList<Integer>)
    //--------------------------------------------------------------------
}
