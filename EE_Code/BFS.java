package JavaProjects.EE_Code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class BFS {
    static int size;

    public static void main(String[] args) throws IOException {
        Scanner fGraphName = new Scanner(new File("src\\JavaProjects\\EE_Code\\Generated_Graphs\\Graph_Name.txt"));
        String graphName = fGraphName.next();
        fGraphName.close();

        A_Star.FastReader in = new A_Star.FastReader("src\\JavaProjects\\EE_Code\\Generated_Graphs\\" + graphName);
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

        ArrayList<Integer[]> startPath = new ArrayList<Integer[]>();
        startPath.add(new Integer[] {0, 0});
        paths.add(startPath);
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

        // Creating Image
        int blackRGB = Color.BLACK.getRGB();
        int greenRGB = Color.GREEN.getRGB();
        BufferedImage image = new BufferedImage(1000, 1000, 1);

        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++){
                image.setRGB(i, j, Color.WHITE.getRGB());
            }
        }

        int[] boundaries = new int[size + 1];

        for(int i = 0; i < boundaries.length; i++){
            boundaries[i] = (int)(i * (999.0 / size));

            for(int j = 0; j < 1000; j++){
                image.setRGB(j, boundaries[i], blackRGB);
                image.setRGB(boundaries[i], j, blackRGB);
            }
        }

        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[0].length; j++){
                if(graph[i][j] == 1){
                    for(int a = boundaries[i]; a < boundaries[i + 1]; a++){
                        for(int b = boundaries[j]; b < boundaries[j + 1]; b++){
                            image.setRGB(b, a, blackRGB);
                        }
                    }
                }
            }
        }

        if(fPath != null){
            for(Integer[] pos : fPath){
                for(int a = boundaries[pos[0]] + 1; a < boundaries[pos[0] + 1]; a++){
                    for(int b = boundaries[pos[1]] + 1; b < boundaries[pos[1] + 1]; b++){
                        image.setRGB(b, a, greenRGB);
                    }
                }
            }
        }

        ImageIO.write(image, "png", new File("src\\JavaProjects\\EE_Code\\Generated_Graphs\\" + graphName.substring(0, graphName.length() - 4) + "_BFS_Path" + ".png"));
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
