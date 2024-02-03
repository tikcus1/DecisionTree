import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Main {
//    public static float[][] Data ;
    public static int x; //sample
    public static int y; //attribute
//    public static int[] labels;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] data = readData(8878, 17, "C:\\Users\\alipour\\Desktop\\test_DS\\feature_train.csv");
        int[] label = readLabel(8878, "C:\\Users\\alipour\\Desktop\\test_DS\\label_train.csv");

        Tree tree = new Tree();

        tree.data = data;
        tree.labels = label;
        tree.x = 8878;
        tree.y = 17;


        Node root  = new Node();
        ArrayList<Integer> train_data = new ArrayList<>();

        for (int i = 0; i < 8878; i++) {
            train_data.add(i);
        }
        root.trainData = train_data;

        System.out.println("entropy root:" + tree.entropy(root));

        ArrayList<Integer> features = new ArrayList<>();
        for (int i = 0; i < 17; i++) {
            features.add(i);
        }
        tree.createTree(root, features, 0);
    }
    private static int[][] readData(int rows, int cols, String filePathData) throws FileNotFoundException {
        int[][] data = new int[rows][cols];
        File file = new File(filePathData);

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
            for (int i = 0; sc.hasNextLine(); i++) {
                String[] line = sc.nextLine().trim().split(",");
    //            System.out.println(line.length);
                for (int j = 0; j < line.length; j++) {
    //                System.out.println(j);
                    data[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        return data;
    }
    private static int[] readLabel(int rows, String filePathLabel) throws FileNotFoundException{
        int[] label = new int[rows];
        File file = new File(filePathLabel);

        try (Scanner sc = new Scanner(file)) {
            sc.nextLine();
//            System.out.println(sc.nextLine());
//            String[] line = sc.nextLine().trim().split(",");
            for (int i = 0; sc.hasNextLine(); i++) {
//                System.out.println(line[i]);
//                label[i] = Float.parseFloat(line[i]);
                label[i] = Integer.parseInt(sc.nextLine());
            }
        }
        return label;
    }

}