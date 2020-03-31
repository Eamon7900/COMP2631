import java.io.*;

public class TopoMain {
    private static final String PATH_IN = "src/DataFiles/in6.txt";

    public static void main(String[] args) {
	    int[][] edges;
        int n, m;

        File in0 = new File(PATH_IN);
	    try {
            BufferedReader reader = new BufferedReader(new FileReader(in0));

            String[] graphSize = reader.readLine().split(" ");
            n = Integer.parseInt(graphSize[0]);
            m = Integer.parseInt(graphSize[1]);

            edges = new int[m][2];
            String curLine;
            for(int i = 0; i < m; i++){
                String[] edge = reader.readLine().split(" ");
                edges[i][0] = Integer.parseInt(edge[0]);
                edges[i][1] = Integer.parseInt(edge[1]);
            }
        } catch (FileNotFoundException fNfE){
	        fNfE.printStackTrace();
	        return;
        } catch (IOException IOe) {
	        IOe.printStackTrace();
	        return;
        }

        AdjacencyList adjList = new AdjacencyList(n, m, edges);
    }
}
