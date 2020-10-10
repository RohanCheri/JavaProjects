package JavaProjects.EE_Code;

import java.io.IOException;

public class GraphRunner {
    public static void main(String[] args) throws IOException, InterruptedException {
        int size = 350;
        double proportionBlocked = 0.8;
        int blockages = (int)(size * size * proportionBlocked);

        StringBuilder aStar = new StringBuilder(), bFS = new StringBuilder();

        for(int i = 0; i < 5; i++){
            GraphGeneration.main(new String[] {size + "", blockages + ""});

            A_Star.main(new String[] {""});
            aStar.append(A_Star.outString);

            BFS.main(new String[] {""});
            bFS.append(BFS.outString);
        }

        System.out.println("A Star Info:");
        System.out.println(aStar);

        System.out.println("\nBFS Info:");
        System.out.println(bFS);
    }
}
