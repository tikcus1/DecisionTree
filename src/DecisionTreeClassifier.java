import javax.xml.crypto.Data;
import java.util.ArrayList;

public class DecisionTreeClassifier {

    DNode Root = new DNode();

    private int[][] Data;
    private int[] labels;
    private int[][] Data_test;
    private int[] labels_test;
    RandomForest randomForest;
    int treeCount = 12;
    public DecisionTreeClassifier(int[][] data, int[] labels, int[][] data_test, int[] labels_test) {
        Data = data;
        this.labels = labels;
        this.Data_test = data_test;
        this.labels_test = labels_test;

        randomForest = new RandomForest(data, labels, treeCount);
        System.out.println(accuracy(labels_test, predictAll(data_test, 100)));
        /*
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

//        for (int i = 0; i < ((DNode)Root.getChildren().get(1)).getChildren().size(); i++) {
//            System.out.println(((DNode)Root.getChildren().get(1)).getChildren().get(i).getClass());
//        }


         */
        //// Graphic(root);
    }

    int predict(int[] train_test, int deep) {
//        Node root = Root;
        Node root;

        int[] predict = new int[treeCount];
        for (int i = 0; i < treeCount; i++) {
            root = randomForest.getRoots()[i];
            while(root instanceof DNode) {
                root = ((DNode) root).getChildren().get(train_test[((DNode) root).future_index]);
            }
//            System.out.println("predict:"+((LeafNode)root).getValue());
            predict[((LeafNode)root).getValue()]++;
        }
//        while(root instanceof DNode) {
//            root = ((DNode) root).getChildren().get(train_test[((DNode) root).future_index]);
//        }
//        System.out.println("predict:"+((LeafNode)root).getValue());

//        return ((LeafNode)root).getValue();
        int max_index = 0;
        int maxPredicted = 0;
        for (int i = 0; i < treeCount; i++) {
            if(predict[i] > maxPredicted){
                max_index = i;
            }
        }
        return max_index;
    }
    int[] predictAll(int[][] train_test, int deep) {
        int[] label_predicted = new int[train_test.length];

        for (int i = 0; i < label_predicted.length; i++) {
            label_predicted[i] = predict(train_test[i], deep);
        }

        return label_predicted;
    }
    float accuracy(int[] label, int[] label_predicted) {
        float accuracy = 0;
        for (int i = 0; i < label.length; i++) {
//            System.out.println(label[i] +"=="+label_predicted[i]);
            if(label[i] == label_predicted[i]){
                accuracy ++;
            }
        }
        accuracy /= label.length;
        return accuracy;
    }
}
