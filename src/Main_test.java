import java.util.ArrayList;
import java.util.Scanner;

public class Main_test {
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

        Tree tree = new Tree();

        tree.data = Data;
        tree.labels = label;
        tree.x = 9;
        tree.y = 3;


        Node root  = new Node();
        ArrayList<Integer> childs = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            childs.add(i);
        }
        root.trainData = childs;
//        System.out.println((int)(Math.log(4) / Math.log(2)));
//        System.out.println(-(4.0/9)*(Math.log(4.0/9) / Math.log(2)) - (5.0/9)*(Math.log(5.0/9) / Math.log(2)) );
        System.out.println("entropy root:" + tree.entropy(root));
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arrayList.add(i);
        }
        tree.createTree(root, arrayList, 0);
        System.out.println(tree.getDepth());
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
