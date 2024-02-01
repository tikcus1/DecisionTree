import java.util.ArrayList;

public class Tree {

    int depth;
    static float[][] data;
    static int[] labels;
    static int x, y;

    int getDepth() {
        return depth;
    }
    DNode createTree(Node root) {
        ArrayList<Integer> trainData = root.trainData;
        float max_IG = -1;
        int selected_IG;
        for(int i = 0; i < y; i++) {
            if( > )
        }
    }
    public float Entropy(Node node) {
        float entropy = 0;
        int[] p = {0, 0, 0};
        for (int i = 0; i < x; i++) {
            p[labels[i]]++;
        }
        for (int i = 0; i < 3; i++) {
            entropy -= p[i] * Math.log10(p[i]);
        }
        return entropy;
    }

    float iGain(DNode dnode) {
        float igain = Entropy(dnode);
        float sigma = 0;
        for (int i = 0; i < dnode.getChildren().size(); i++) {
            Node child = dnode.getChildren().get(i);
            sigma += child.trainData.size() * Entropy(child);
        }
        igain -= sigma / dnode.trainData.size();
        return igain;
    }
}
