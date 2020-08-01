package JavaProjects.EE_Code;

import java.io.*;
import java.util.*;

public class GraphGeneration {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        PrintStream out = new PrintStream(new File("src\\JavaProjects\\EE_Code\\Generated_Graphs\\Generated_Graph1.txt"));
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int numBlockages = in.nextInt();

        boolean works = false;

        while(!works) {
            int[][] graph = new int[size][size];
            boolean[][] vis = new boolean[size][size];
            for(int i = 0; i < graph.length; i++) {
                Arrays.fill(graph[i], 0);
                Arrays.fill(vis[i], false);
            }

            ArrayList<Integer[]> possiblePath = new ArrayList<Integer[]>();
            int startR = 0, startC = 0;
            possiblePath.add(new Integer[] {0, 0});

            while(!(startR == 0 && startC == size - 1)){
                int randomNum = (int)(Math.random() * 2);

                if(startC == size - 1){
                    randomNum = -1;
                }

                if(randomNum == -1){
                    startR -= 1;
                }
                else if(randomNum == 0){
                    startC += 1;
                }
                else if(randomNum == 1 && startR < size - 1){
                    startR += 1;
                }

                possiblePath.add(new Integer[] {startR, startC});
            }

            for(Integer[] arr : possiblePath){
                vis[arr[0]][arr[1]] = true;
            }

            int blockages = 0;
            while(blockages < numBlockages) {
                int row = (int)(Math.random() * size);
                int col = (int)(Math.random() * size);

                if(!vis[row][col]) {
                    vis[row][col] = true;
                    blockages++;
                    graph[row][col] = 1;
                }
            }

            boolean[][] visited = new boolean[size][size];
            for(int i = 0; i < visited.length; i++) {
                Arrays.fill(visited[i], false);
            }

            ArrayDeque<Integer[]> queue = new ArrayDeque<Integer[]>();
            queue.add(new Integer[] {0, 0});

            int operations = 0;
            while(!queue.isEmpty()) {
                Integer[] next = queue.poll();
                int cRow = next[0].intValue(), cCol = next[1].intValue();

                if(cRow == 0 && cCol == size - 1) {
                    works = true;
                    break;
                }

                if(!visited[cRow][cCol]) {
                    visited[cRow][cCol] = true;

                    if(cRow - 1 >= 0 && graph[cRow - 1][cCol] == 0) {
                        queue.add(new Integer[] {cRow - 1, cCol});
                    }

                    if(cRow + 1 < size && graph[cRow + 1][cCol] == 0) {
                        queue.add(new Integer[] {cRow + 1, cCol});
                    }

                    if(cCol - 1 >= 0 && graph[cRow][cCol - 1] == 0) {
                        queue.add(new Integer[] {cRow, cCol - 1});
                    }

                    if(cCol + 1 < size && graph[cRow][cCol + 1] == 0) {
                        queue.add(new Integer[] {cRow, cCol + 1});
                    }
                }

                operations++;
            }

            if(works) {
                out.println(size);
                for(int i = 0; i < graph.length; i++) {
                    StringBuilder s = new StringBuilder();
                    for(int j = 0; j < graph.length; j++) {
                        s.append(graph[i][j]);
                    }
                    out.println(s);
                }
            }
        }

        out.close();
    }
}
