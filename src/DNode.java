import java.util.ArrayList;

public class DNode extends Node{
    private ArrayList<Node> children;
    int future_index;

    public DNode() {
        children = new ArrayList<>();
        future_index = -1;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getFuture_index() {
        return future_index;
    }
}
