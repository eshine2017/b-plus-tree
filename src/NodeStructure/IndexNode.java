package NodeStructure;

import java.util.ArrayList;

/**
 * IndexNode is used for DataNode searching, does not have data pair
 */
public class IndexNode extends Node {

    private ArrayList<Double> keys;     // list of keys, for ith key, its left child is i and right child i+1
    private ArrayList<Node> children;   // list of children

    /** constructor from lists of keys and children */
    public IndexNode(int m, ArrayList<Double> keys, ArrayList<Node> children) {
        this.m = m;
        n = keys.size();
        this.keys = keys;
        this.children = children;
    }

    /** constructor from a key and its child */
    public IndexNode(int m, double key, Node node) {
        this.m = m;
        n = 1;
        keys = new ArrayList<>();
        keys.add(key);
        children = new ArrayList<>();
        children.add(node);
    }

    /** get all the keys */
    public ArrayList<Double> getKeys() {
        return keys;
    }

    /** get all the children */
    public ArrayList<Node> getChildren() {
        return children;
    }

    /*
    use binary search to find the index of entry in keys,
    whose value is just larger than given key
    */
    private int searchKey(double key) {
        int left = 0;
        int right = keys.size() - 1;
        while (left < right) {
            int mid = (left + right)/2;
            if (key < keys.get(mid)) {
                right = mid - 1;
            } else left = mid + 1;
        }
        return left;
    }

    /**
     * insert the given node to the first place of children list
     * @param node
     */
    public void addToFirst(Node node) {
        children.add(0, node);
    }

    /**
     * search the corresponding child which should take this pair
     * @param pair
     * @return the address of corresponding child
     */
    public Node search(Pair pair) {
        double key = pair.getKey();
        if (key >= keys.get(keys.size() - 1))
            return children.get(children.size() - 1);
        int index = searchKey(key);
        return children.get(index);
    }

    /**
     * split a overfull index node
     * @return a new index node with the new index node as its child
     */
    public IndexNode split() {

        // move the right half keys and children to new index node
        ArrayList<Double> newKeys = new ArrayList<>();
        ArrayList<Node> newChildren = new ArrayList<>();
        newChildren.add(children.get(children.size() - 1));
        children.remove(children.size() - 1);
        for (int i = keys.size() - 1; i > m/2; i--) {
            newKeys.add(0, keys.get(i));
            newChildren.add(0, children.get(i));
            keys.remove(i);
            children.remove(i);
        }

        // new index to store the new keys and children
        IndexNode newNode = new IndexNode(m, newKeys, newChildren);

        // create a new index node to merge with parent
        double key = keys.get(keys.size() - 1);
        keys.remove(keys.size() - 1);
        return new IndexNode(m, key, newNode);

    }

    /**
     * merge with a newly created node which has only one key and one child
     * @param node
     */
    public void mergeWith(IndexNode node) {
        double key = node.getKeys().get(0);
        Node child = node.getChildren().get(0);
        int index = searchKey(key);
        keys.add(index, key);
        children.add(index, child);
    }

}
