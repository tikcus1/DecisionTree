import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class RandomForest {
    int[][] data;
    int[] label;
    int dataCount;
    int featureCount;

    Tree[] trees;
    DNode[] roots;

    public RandomForest(int[][] data, int[] label, int treeCount) {
        this.data = data;
        this.label = label;
        dataCount = data.length;
        featureCount = data[0].length;
        trees = new Tree[treeCount];
        roots = new DNode[treeCount];

        ArrayList<Integer> train_data = new ArrayList<>();
        for (int i = 0; i < dataCount; i++) {
            train_data.add(i);
        }

        for (int i = 0; i < treeCount; i++) {
            trees[i] = makeTree();
            roots[i] = new DNode();
            roots[i].trainData = train_data;
            System.out.println("entropy root:" + trees[i].entropy(roots[i]));
            ArrayList<Integer> features = new ArrayList<>();
            int[] randomFeature = randomizeFeature();
            for (int j = 0; j < Math.pow(featureCount, 0.5)-1; j++) {
                features.add(randomFeature[j]);
            }

            trees[i].createTree(roots[i], features, 0);
        }
    }

    Tree makeTree(){

        int[][] randomData = new int[dataCount][];
        int[] randomLabel = new int[dataCount];
        int[] index = randomizeData();
        for (int i = 0; i < dataCount; i++) {
            randomData[i] = data[index[i]];
            randomLabel[i] = label[index[i]];
        }

        return new Tree(randomData, randomLabel);
    }


    int[] randomizeData(){
        int[] randomData = new int[dataCount];
        Random random = new Random();
        for (int i = 0; i < dataCount; i++) {
            randomData[i] = random.nextInt(dataCount);
        }
        return randomData;
    }

    int[] randomizeFeature(){
        int randomFeature_size = (int) Math.pow(featureCount, 0.5);
        int[] randomFeature = new int[randomFeature_size];
        Random random = new Random();

        for (int i = 0; i < randomFeature_size; i++) {//tekrari
            randomFeature[i] = random.nextInt(featureCount);
        }
        return randomFeature;
    }

    public DNode[] getRoots() {
        return roots;
    }
}
