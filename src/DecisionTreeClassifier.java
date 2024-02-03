import javax.xml.crypto.Data;
import java.util.ArrayList;

public class DecisionTreeClassifier {

    DNode Root = new DNode();

    private int[][] Data;
    private int[] labels;

    public DecisionTreeClassifier(int[][] data, int[] labels) {
        Data = data;
        this.labels = labels;
        Tree tree = new Tree(data, labels);

        ArrayList<Integer> train_data = new ArrayList<>();

        for (int i = 0; i < tree.x; i++) {
            train_data.add(i);
        }

        Root.trainData = train_data;
//        Root  = new Node(train_data);
        System.out.println("entropy root:" + tree.entropy(Root));

        ArrayList<Integer> features = new ArrayList<>();
        for (int i = 0; i < tree.y; i++) {
            features.add(i);
        }

        tree.createTree(Root, features, 0);
        //// Graphic(root);
    }

    float predict(int[] train_test, int deep) {
        Node root = Root;
        while(root instanceof DNode) {
            System.out.println(train_test[((DNode) root).future_index]);
            System.out.println(((DNode) root).getChildren().get(train_test[((DNode) root).future_index]).getClass());
            root = ((DNode) root).getChildren().get(train_test[((DNode) root).future_index]);
        }
        System.out.println("predict:"+((LeafNode)root).getValue());
        return ((LeafNode)root).getValue();
    }
//    float[] predictAll(int[][] train_test, int deep) {
//
//    }
//    float accuracy(int[] label, int[] label_predicted) {
//
//    }
}
