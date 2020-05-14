package pin;

public class Node {

    public int numberOfNodes;
    public int key[];
    public Node children[];
    public boolean isLeaf;
    String name;
    int T = 3;

    Node() {
        this.key = new int[2 * T - 1];;
        this.children = new Node[2 * T - 1];;
        this.isLeaf = true;
    }

    public int getValue(int index) {
        return key[index];
    }

    public Node getChild(int index) {
        return children[index];
    }
}
