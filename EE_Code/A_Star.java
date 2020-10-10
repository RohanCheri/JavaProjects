package JavaProjects.EE_Code;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class A_Star {
    public static int size;
    public static String outString = "";

    public static void main(String[] args) throws IOException {
        Scanner fGraphName = new Scanner(new File("src\\JavaProjects\\EE_Code\\Generated_Graphs\\Graph_Name.txt"));
        String graphName = fGraphName.next();
        fGraphName.close();

        FastReader in = new FastReader("src\\JavaProjects\\EE_Code\\Generated_Graphs\\" + graphName);
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
		Path fPath = new Path(0, 0);
		fPath.pathTo.add(new Integer[] {0, 0});
        queue.add(fPath);

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

        if(args.length > 0){
            outString = String.format("%-8d %-8d", operations, System.currentTimeMillis() - cTime);
        }
        else{
            System.out.println("Elapsed Time: " + (System.currentTimeMillis() - cTime));

            if(finalPath == null) {
                System.out.println("Cannot Reach Endpoint");
            }
            else {
                System.out.println("Num Steps: " + finalPath.pathTo.size());
            }

            System.out.println("Operations: " + operations);

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

            if(finalPath != null){
                for(Integer[] pos : finalPath.pathTo){
                    for(int a = boundaries[pos[0]] + 1; a < boundaries[pos[0] + 1]; a++){
                        for(int b = boundaries[pos[1]] + 1; b < boundaries[pos[1] + 1]; b++){
                            image.setRGB(b, a, greenRGB);
                        }
                    }
                }
            }

            ImageIO.write(image, "png", new File("src\\JavaProjects\\EE_Code\\Generated_Graphs\\" + graphName.substring(0, graphName.length() - 4) + "_A_Star_Path" + ".png"));
        }
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
