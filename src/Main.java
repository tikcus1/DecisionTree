import java.util.ArrayList;
import java.util.Scanner;

public class Main {
//    public static float[][] Data ;
    public static int x; //sample
    public static int y; //attribute
//    public static int[] labels;
    public static void main(String[] args) {
        int[][] Data = new int[9][3] ;
        int[] label = new int[9] ;
        Scanner scanner = new Scanner(System.in) ;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                Data[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < 9; i++) {
            label[i] = scanner.nextInt();
        }
//        for (int i = 0; i < 3; i++) {
//            System.out.println(Data[i][0]);
//        }
        Tree.data = Data;
        Tree.labels = label;
        Tree.x = 9;
        Tree.y = 3;
        Tree tree = new Tree();

        Node root  = new Node();
        ArrayList<Integer> childs = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            childs.add(i);
        }
        root.trainData = childs;
//        System.out.println((int)(Math.log(4) / Math.log(2)));
//        System.out.println(-(4.0/9)*(Math.log(4.0/9) / Math.log(2)) - (5.0/9)*(Math.log(5.0/9) / Math.log(2)) );
        System.out.println("ebtropy root:" + tree.entropy(root));
        tree.createTree(root);
    }
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
}