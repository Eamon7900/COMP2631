import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdjacencyList {
    private int numNodes = 0;
    private ArrayList<ArrayList<Integer>> adjList;

    AdjacencyList(int nNodes, int nEdges, int[][] edges){
        numNodes = nNodes;
        adjList = new ArrayList<>();
        adjList.add(null);
        for(int i = 0; i < numNodes; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            adjList.get(edge[0]).add(edge[1]);
        }
        printAdjList(); //DEBUG
    }

    private void printAdjList(){
        int i = 1;
        for(ArrayList<Integer> node : adjList){
            if(node == null) continue;
            System.out.printf("%d: ", i++);
            for(int outNode : node){
                System.out.printf("%d ", outNode);
            }
            System.out.println();
        }
    }

    public int getNumNodes() {
        return numNodes;
    }

    public ArrayList<Integer> getOutNeighbors(int node){
        return (ArrayList<Integer>) (adjList.get(node).clone());
    }
}
