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
//    public static void main(String[] args) {
//        int[][] Data = new int[9][3] ;
//        int[] label = new int[9] ;
//        Scanner scanner = new Scanner(System.in) ;
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 3; j++) {
//                Data[i][j] = scanner.nextInt();
//            }
//        }
//        for (int i = 0; i < 9; i++) {
//            label[i] = scanner.nextInt();
//        }
////        for (int i = 0; i < 3; i++) {
////            System.out.println(Data[i][0]);
////        }
//        Tree.data = Data;
//        Tree.labels = label;
//        Tree.x = 9;
//        Tree.y = 3;
//        Tree tree = new Tree();
//
//        Node root  = new Node();
//        ArrayList<Integer> childs = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            childs.add(i);
//        }
//        root.trainData = childs;
////        System.out.println((int)(Math.log(4) / Math.log(2)));
////        System.out.println(-(4.0/9)*(Math.log(4.0/9) / Math.log(2)) - (5.0/9)*(Math.log(5.0/9) / Math.log(2)) );
//        System.out.println("entropy root:" + tree.entropy(root));
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            arrayList.add(i);
//        }
//        tree.createTree(root, arrayList);
//    }
//13 15 20 0 0 1
    //1 1 1 1 1 1 1 1 1 0 0 0 0 1 1 1 1 1
    //1 0 0 0 0 1 0 1 1 1 0 1 0 0 1 0 1 1
    //1 0 1 0 1 1 0 0 0 1 0 1 0 0 1 0 1 1
    /*
            1 0 1
            0 1 1
            1 1 1
            0 1 1
            1 1 1
            1 0 0
            0 1 0
            0 0 1
            0 0 0

            1 0 1 0 0 1 0 1 1
    */

public static void main(String[] args) throws FileNotFoundException {
    int[][] data = readData(8878, 17, "C:\\Users\\M A T I N\\Desktop\\ZAHRA\\Data\\feature_train.csv");
    int[] label = readLabel(8878, "C:\\Users\\M A T I N\\Desktop\\ZAHRA\\Data\\label_train.csv");
    Tree.data = data;
        Tree.labels = label;
        Tree.x = 8878;
        Tree.y = 16;
        Tree tree = new Tree();

        Node root  = new Node();
        ArrayList<Integer> childs = new ArrayList<>();

        for (int i = 0; i < 8878; i++) {
            childs.add(i);
        }
        root.trainData = childs;

        System.out.println("entropy root:" + tree.entropy(root));

        ArrayList<Integer> arrayList = new ArrayList<>();
    for (int i = 0; i < 17; i++) {
        arrayList.add(i);
    }
        tree.createTree(root, arrayList);

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