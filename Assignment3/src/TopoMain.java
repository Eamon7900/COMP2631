import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TopoMain {

    public static void main(String[] args) {
	    int[][] edges;
        int n, m;
        String inputPath, outputPath;
        Scanner input  = new Scanner(System.in);

        System.out.print("Input file: ");
        inputPath = input.nextLine();
        System.out.printf("\n Output file: ");
        outputPath = input.nextLine();
        input.close();

        File in0 = new File(inputPath);
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
            reader.close();
        } catch (FileNotFoundException fNfE){
	        fNfE.printStackTrace();
	        return;
        } catch (IOException IOe) {
	        IOe.printStackTrace();
	        return;
        }

	    TopologicalSort sort = new TopologicalSort(n,m, edges);
        ArrayList<Integer> sorted = sort.topoSort();

        try{
            File output = new File(outputPath);
            PrintWriter writer = new PrintWriter(output);
            if(sorted == null){
                writer.println("IMPOSSIBLE");
            } else {
                for(int i : sorted){
                    writer.printf("%d\n", i);
                }
            }
            writer.close();
        } catch (IOException IOe){
            IOe.printStackTrace();
        }

    }
}
