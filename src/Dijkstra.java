
import java.util.*;
        import java.lang.*;
        import java.io.*;
    public class Dijkstra {

        private int size = -1;
        private int log10 = 0;
        private String numberFormat;


        public static int matrix[][];

        public static int V;

        int minDistance(int dist[], Boolean sptSet[]) {
            // Initialize min value
            int min = Integer.MAX_VALUE, min_index = -1;

            for (int v = 0; v < V; v++)
                if (sptSet[v] == false && dist[v] <= min) {
                    min = dist[v];
                    min_index = v;
                }

            return min_index;
        }


        void printSolution(int dist[], int n) {

            for (int i = 0; i < V; i++)
                for (int j=0;j<matrix[i].length; j++)
                {
                    if(i==j)
                    {
                        System.out.print( dist[i]+ "  ");
                    }
                    else if(j==matrix.length-1)
                    {
                        System.out.print("0");
                        System.out.println();
                    }
                    else
                    {
                        System.out.print("0  ");
                    }

                }


                System.out.println();
                System.out.println();

        }


        void dijkstra(int graph[][], int src) {
            int dist[] = new int[V];
            Boolean sptSet[] = new Boolean[V];

            // Initialize all distances as INFINITE and stpSet[] as false
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            // Distance of source vertex from itself is always 0
            dist[src] = 0;

            // Find shortest path for all vertices
            for (int count = 0; count < V - 1; count++) {

                int u = minDistance(dist, sptSet);

                // Mark the picked vertex as processed
                sptSet[u] = true;

                // Update dist value of the adjacent vertices of the
                // picked vertex.
                for (int v = 0; v < V; v++)


                    if (!sptSet[v] && graph[u][v] != 0 &&
                            dist[u] != Integer.MAX_VALUE &&
                            dist[u] + graph[u][v] < dist[v])
                        dist[v] = dist[u] + graph[u][v];
            }

            // print the constructed distance array
            printSolution(dist, V);
        }

        public Dijkstra(String filename) {
            try {
                readFile(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void readFile(String filename) throws IOException {

            BufferedReader buffer = new BufferedReader(new FileReader(filename));

            String line;
            int row = 0;

            while ((line = buffer.readLine()) != null) {
                String[] vals = line.trim().split("\\s+");

                // Lazy instantiation.
                if (matrix == null) {
                    size = vals.length;
                    matrix = new int[size][size];
                    log10 = (int) Math.floor(Math.log10(size * size)) + 1;
                    numberFormat = String.format("%%%dd", log10);
                }

                for (int col = 0; col < size; col++) {
                    if (Character.isDigit(vals[col].charAt(0))) {

                        matrix[row][col] = Integer.parseInt(vals[col]);
                    } else {
                        matrix[row][col] = 0;
                    }
                }

                row++;
            }
        }


        public static void main(String[] args) throws IOException {


            System.out.println("Enter you input file name");
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            Dijkstra t = new Dijkstra(input);

            V = matrix[0].length;




            System.out.println("Input adjacency matrix");
            System.out.println();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (j != 0 && i != 0 && matrix[i][j] == 0) {
                        System.out.print(" inf ");
                    } else {
                        System.out.print( " "+matrix[i][j]+"   ");
                    }
                    if (j == 4) {
                        System.out.println();
                    }


                }

            }
            System.out.println();
            System.out.println("Output adjacency matrix");
            System.out.println();

            t.dijkstra(matrix, 0);
        }
    }
