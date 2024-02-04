import java.util.ArrayList;

import static java.lang.Math.max;

public class Tree {

    int depth = 0;
    int[][] data;
    int[] labels;
    int x, y;//x: rows_cnt, y: column_cnt

    public Tree(int[][] data, int[] labels) {
        this.data = data;
        this.labels = labels;
        x = data.length;
        y = data[0].length;
    }

    int getDepth() {
        return depth;
    }
    Node createTree(DNode root, ArrayList<Integer> features, int deep) {
        depth = max(depth, deep);
        if (root.trainData.isEmpty()) {
            return null;
        }
        /*if(entropy(root) == 0 || features.isEmpty()){
            /*if (entropy(root) == 0) {
                System.out.println("pure");
            }
            else {
                System.out.println("nadarim");
            }

            root = new LeafNode(labels[root.trainData.get(0)]);
            return;
        }*/
        ArrayList<Integer> trainData = root.trainData;
        float max_IG = -1;
        int selected_IG = -1;
        DNode maxIG = null;

        for(int i = 0; i < features.size(); i++) {
            DNode dNode = setDnode_children(trainData, features.get(i));
            dNode.trainData = trainData;
//            System.out.println(i + ": " + iGain(dNode));

            if (iGain(dNode) > max_IG){
                maxIG = dNode;
                max_IG = iGain(dNode);
                selected_IG = i;
            }
        }
//        System.out.println("max IG : " + max_IG);
//        System.out.println("future index : " + maxIG.future_index);

//        System.out.println("maaaaaaaaaat: "+entropy(maxIG.getChildren().get(2)));

        /*for (int i = 0; i < features.size(); i++) {
            System.out.print(features.get(i) + " ");
        }*/

        features.remove(selected_IG);

        /*System.out.println();

        for (int i = 0; i < features.size(); i++) {
            System.out.print(features.get(i) + " ");
        }
        System.out.println();*/

        root.future_index = maxIG.future_index;
        for (int i = 0; i < maxIG.getChildren().size(); i++) {
//            if(maxIG.getChildren().get(i).trainData.contains(3)){
//                System.out.println("-->>"+i);
//            }

            if(maxIG.getChildren().get(i).trainData.size() != 0) {
                if (entropy(maxIG.getChildren().get(i)) == 0 || features.size() == 0) {
//                    if(maxIG.future_index == 16){
//                        System.out.println("child "+i+ "is Leaf ");
//                    }
//                    for (int j = 0; j < maxIG.getChildren().get(i).trainData.size(); j++) {
//                        System.out.println(maxIG.getChildren().get(i).trainData.get(j));
//                    }
//                    System.out.println("zah");
                    root.getChildren().add(new LeafNode(labels[maxIG.getChildren().get(i).trainData.get(0)]));
//                return new LeafNode(labels[maxIG.getChildren().get(i).trainData.get(0)]);
                } else {
//                    if(maxIG.future_index == 16){
//                        System.out.println("child "+i+ "isDnode ");
//                    }
//                    System.out.println(maxIG.future_index+" : "+i+" is Dnode");
                    DNode child = (DNode) maxIG.getChildren().get(i);
                    ArrayList<Integer> childFeature = new ArrayList<>(features);
                    root.getChildren().add(createTree(child, childFeature, deep + 1));
                }
            }else {
//                if(maxIG.future_index == 16){
//                    System.out.println("child "+i+" is empty");
//                }
                root.getChildren().add(new LeafNode(0));
            }
//            Node child = maxIG.getChildren().get(i);

//            System.out.println(entropy(child));
//            if(entropy(child) - 0.72192806 <= 0.00000001){
//                System.out.println("mat");
//                createTree(child);
//            }
//            System.out.println(i+" : "+ entropy(child));
//            if(entropy(child) == 1){
//                System.out.println(i);
//            }

//            createTree(child, features, deep + 1);
        }
        root.future_index = selected_IG;
        return root;
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
//        System.out.print(p[0]+" "+p[1]+" "+p[2]);

        for (int i = 0; i < 3; i++) {
            if(p[i] != 0){
                entropy -= p[i] * (Math.log(p[i]) / Math.log(2));
            }
        }
//        if(entropy < 0.0000001){
//            return 0;
//        }
        return entropy;
    }

    float iGain(DNode dnode) {
        float igain = entropy(dnode);
        float sigma = 0;
        for (int i = 0; i < dnode.getChildren().size() ; i++) {
            Node child = dnode.getChildren().get(i);
            sigma += child.trainData.size() * entropy(child);
//            System.out.println(i+"-->"+entropy(child));
        }
        igain -= sigma / dnode.trainData.size();
//        if(igain < 0.0000001){
//            return 0;
//        }
        return igain;
    }

    DNode setDnode_children(ArrayList<Integer> trainData, int future_index){
        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            children.add(new DNode());
        }

        for (int i : trainData){//1   3   5   7
            children.get(data[i][future_index]).trainData.add(i);
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
