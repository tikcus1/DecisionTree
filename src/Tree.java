import java.util.ArrayList;

public class Tree {

    int depth;
    static int[][] data;
    static int[] labels;
    static int x, y;//x: rows_cnt, y: column_cnt

    int getDepth() {
        return depth;
    }
    void createTree(Node root, ArrayList<Integer> features) {
        if(entropy(root) == 0 || features.size() == 0){
            System.out.println("pure");
            return;
        }
        ArrayList<Integer> trainData = root.trainData;
        float max_IG = -1;
        int selected_IG = -1;
        DNode maxIG = null;

        for(int i = 0; i < features.size(); i++) {
            DNode dNode = setDnode_children(trainData, i);
            dNode.trainData = trainData;
            System.out.println(""+i+": "+iGain(dNode));

            if (iGain(dNode) > max_IG){
                maxIG = dNode;
                max_IG = iGain(dNode);
                selected_IG = i;
            }
        }
        System.out.println("max IG : " + max_IG);
        System.out.println("future index : "+ selected_IG);

        features.remove(selected_IG);
        for (int i = 0; i < maxIG.getChildren().size(); i++) {
            Node child = maxIG.getChildren().get(i);
//            System.out.println(entropy(child));
//            if(entropy(child) - 0.72192806 <= 0.00000001){
//                System.out.println("mat");
//                createTree(child);
//            }
//            System.out.println(i+" : "+ entropy(child));

            if(entropy(child) == 1){
                System.out.println(i);
            }
            createTree(child, features);
        }
    }


    public float entropy(Node node) {
        float entropy = 0;
        float[] p = {0, 0, 0};
        for (int i = 0; i < node.trainData.size(); i++) {
            p[labels[node.trainData.get(i)]]++ ;
        }

        if(node.trainData.size() != 0){
            for (int i = 0; i < 3; i++) {
                p[i] /= node.trainData.size();
            }
        }

        for (int i = 0; i < 3; i++) {
            if(p[i] != 0){
                entropy -= p[i] * (Math.log(p[i]) / Math.log(2));
            }
        }
        if(entropy < 0.0000001){
            return 0;
        }
        return entropy;
    }

    float iGain(DNode dnode) {
        float igain = entropy(dnode);
        float sigma = 0;
        for (int i = 0; i < dnode.getChildren().size() ; i++) {
            Node child = dnode.getChildren().get(i);
            sigma += child.trainData.size() * entropy(child);
        }
        igain -= sigma / dnode.trainData.size();
        if(igain < 0.0000001){
            return 0;
        }
        return igain;
    }

    DNode setDnode_children(ArrayList<Integer> trainData, int future_index){
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            children.add(new Node());
        }

        for (int i : trainData){//1   3   5   7
            try {
                children.get(data[i][future_index]).trainData.add(i);
            }catch (Exception exception){
                children.add(data[i][future_index], new Node());
                children.get(data[i][future_index]).trainData.add(i);
            }
        }
        return new DNode(children, future_index);
    }

    Boolean isPure(Node node){
        int label = 0;
        if(node.trainData.size() != 0){
            label = labels[node.trainData.get(0)];
        }
        for (int i = 1; i < node.trainData.size(); i++) {
            if(label != labels[node.trainData.get(i)]){
//                System.out.println("pure:"+i);
                return false;
            }
        }
        return true;
    }
}
