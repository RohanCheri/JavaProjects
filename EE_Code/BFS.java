package JavaProjects.EE_Code;

import java.io.*;
import java.util.*;

public class BFS {
    static int size;

    public static void main(String[] args) throws FileNotFoundException {
        A_Star.FastReader in = new A_Star.FastReader("src\\JavaProjects\\EE_Code\\Generated_Graphs\\Generated_Graph1.txt");
        size = in.nextInt();

        int[][] graph = new int[size][size];
        for(int i = 0; i < graph.length; i++){
            StringBuffer s = new StringBuffer(in.next());
            for(int j = 0; j < graph.length; j++){
                graph[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        long cTime = System.currentTimeMillis();

        boolean[][] visited = new boolean[size][size];
        for(int i = 0; i < graph.length; i++){
            Arrays.fill(visited[i], false);
        }

        ArrayDeque<Integer[]> queue = new ArrayDeque<Integer[]>();
        ArrayDeque<ArrayList<Integer[]>> paths = new ArrayDeque<ArrayList<Integer[]>>();
        ArrayList<Integer[]> fPath = null;

        paths.add(new ArrayList<Integer[]>());
        queue.add(new Integer[] {0, 0});
        int operations = 0;

        while(!queue.isEmpty()){
            ArrayList<Integer[]> cPath = paths.poll();
            Integer[] curr = queue.poll();

            int cRow = curr[0], cCol = curr[1];

            if(cRow == 0 && cCol == size - 1){
                fPath = cPath;
                break;
            }

            if(!visited[cRow][cCol]){
                visited[cRow][cCol] = true;

                if(cRow - 1 >= 0 && !visited[cRow - 1][cCol] && graph[cRow - 1][cCol] == 0){
                    ArrayList<Integer[]> nPath = new ArrayList<Integer[]>();
                    nPath.addAll(cPath);
                    nPath.add(new Integer[] {cRow - 1, cCol});
                    paths.add(nPath);
                    queue.add(new Integer[] {cRow - 1, cCol});
                }

                if(cRow + 1 < size && !visited[cRow + 1][cCol] && graph[cRow + 1][cCol] == 0){
                    ArrayList<Integer[]> nPath = new ArrayList<Integer[]>();
                    nPath.addAll(cPath);
                    nPath.add(new Integer[] {cRow + 1, cCol});
                    paths.add(nPath);
                    queue.add(new Integer[] {cRow + 1, cCol});
                }

                if(cCol - 1 >= 0 && !visited[cRow][cCol - 1] && graph[cRow][cCol - 1] == 0){
                    ArrayList<Integer[]> nPath = new ArrayList<Integer[]>();
                    nPath.addAll(cPath);
                    nPath.add(new Integer[] {cRow, cCol - 1});
                    paths.add(nPath);
                    queue.add(new Integer[] {cRow, cCol - 1});
                }

                if(cCol + 1 < size && !visited[cRow][cCol + 1] && graph[cRow][cCol + 1] == 0){
                    ArrayList<Integer[]> nPath = new ArrayList<Integer[]>();
                    nPath.addAll(cPath);
                    nPath.add(new Integer[] {cRow, cCol + 1});
                    paths.add(nPath);
                    queue.add(new Integer[] {cRow, cCol + 1});
                }
            }

            operations++;
        }

        System.out.println("Elapsed Time: " + (System.currentTimeMillis() - cTime));

        if(fPath == null){
            System.out.println("Cannot Reach Endpoint");
        }
        else{
            System.out.println("Num Steps: " + fPath.size());
        }

        System.out.println("Num Operations: " + operations);
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(String fileName) throws FileNotFoundException {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}
