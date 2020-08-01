package JavaProjects.EE_Code;

import java.io.*;
import java.util.*;

public class A_Star {
    public static int size;
    public static void main(String[] args) throws FileNotFoundException {
        FastReader in = new FastReader("src\\JavaProjects\\EE_Code\\Generated_Graphs\\Generated_Graph1.txt");
        size = in.nextInt();

        // input is a graph in the form of 1s and 0s
        // 0s are empty and 1s are blocked
        // goal is to go top left to top right

        int[][] graph = new int[size][size];
        for(int i = 0; i < size; i++) {
            StringBuffer s = new StringBuffer(in.next());
            for(int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(s.substring(j, j + 1));
            }
        }

        long cTime = System.currentTimeMillis();

        boolean[][] visited = new boolean[size][size];

        for(int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }

        int operations = 0;
        Path finalPath = null;
		PriorityQueue<Path> queue = new PriorityQueue<Path>();
        queue.add(new Path(0, 0));

        while(!queue.isEmpty()) {
            operations++;
            Path next = queue.poll();
            int cRow = next.pos[0], cCol = next.pos[1];

            if(cRow == 0 && cCol == size - 1) {
                finalPath = next;
                break;
            }

            if(!visited[cRow][cCol]) {
                visited[cRow][cCol] = true;

                if(cRow - 1 >= 0 && !visited[cRow - 1][cCol] && graph[cRow - 1][cCol] == 0) {
                    Path newP = new Path(cRow - 1, cCol);
                    newP.setPath(next);
                    queue.add(newP);
                }

                if(cRow + 1 < size && !visited[cRow + 1][cCol] && graph[cRow + 1][cCol] == 0) {
                    Path newP = new Path(cRow + 1, cCol);
                    newP.setPath(next);
                    queue.add(newP);
                }

                if(cCol - 1 >= 0 && !visited[cRow][cCol - 1] && graph[cRow][cCol - 1] == 0) {
                    Path newP = new Path(cRow, cCol - 1);
                    newP.setPath(next);
                    queue.add(newP);
                }

                if(cCol + 1 < size && !visited[cRow][cCol + 1] && graph[cRow][cCol + 1] == 0) {
                    Path newP = new Path(cRow, cCol + 1);
                    newP.setPath(next);
                    queue.add(newP);
                }
            }
        }

        System.out.println("Elapsed Time: " + (System.currentTimeMillis() - cTime));

        if(finalPath == null) {
            System.out.println("Cannot Reach Enpoint");
        }
        else {
            System.out.println("Num Steps: " + finalPath.pathTo.size());
        }

        System.out.println("Operations: " + operations);
    }

    static class Path implements Comparable<Path>{
        int[] pos;
        int distance;
        ArrayList<Integer[]> pathTo;

        public Path(int row, int col) {
            this.pos = new int[] {row, col};
            this.distance = row + (size - col);
            pathTo = new ArrayList<Integer[]>();
        }

        public void setPath(Path p) {
            pathTo.addAll(p.pathTo);
            pathTo.add(new Integer[] {pos[0], pos[1]});
        }

        public int compareTo(Path o) {
            return this.calcEstDist() - o.calcEstDist();
        }

        public int calcEstDist() {
            int dist = 0;
            if(pathTo != null) {
                dist += pathTo.size();
            }

            dist += distance;
            return dist;
        }

        @Override
        public String toString() {
            return pos[0] + " " + pos[1];
        }
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
                catch (IOException  e)
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
