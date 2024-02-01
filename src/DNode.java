import java.util.ArrayList;

public class DNode extends Node{
    private ArrayList<Node> children;
    int future_index;

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public DNode() {
        children = new ArrayList<>();
        future_index = -1;
    }

    public DNode(ArrayList<Node> children, int future_index) {
        this.children = children;
        this.future_index = future_index;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getFuture_index() {
        return future_index;
    }
}
