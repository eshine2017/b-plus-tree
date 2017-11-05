import NodeStructure.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Main B+ tree structure, supporting insert(key, value), search(key) and search(key1, key2)
 */
public class BPlusTree {

    private Node root;  // root of B+ tree, can be IndexNode or DataNode
    private int m;      // order of B+ tree

    /** construct a new B+ tree with order m */
    public BPlusTree(int m) {
        this.m = m;
    }

    /** insert a new key-val pair to the tree */
    public void insert(double key, String val) {
        Pair pair = new Pair(key, val);

        // if currently there is no data, just create a new DataNode as root
        if (root == null) root = new DataNode(m, pair);

        // else recursively insert from root to DataNode
        else insert(root, pair);
        if (root.isOverfull()) { // create a new root if it is overfull
            IndexNode temp = root.split();
            temp.addToFirst(root);
            root = temp;
        }
    }

    /* recursively insert a Pair to this subtree */
    private void insert(Node node, Pair pair) {

        // if current node is a data node, insert pair to it
        if (node.isDataNode()) {
            node.insert(pair);
        }

        // if current node is a index node, search next node
        else {
            Node nextNode = node.search(pair);
            insert(nextNode, pair);
            // if next node becomes overfull, split it and merge current node with new entry
            if (nextNode.isOverfull()) {
                IndexNode newNode = nextNode.split();
                node.mergeWith(newNode);
            }
        }

    }

    /** search for all values corresponding to given key */
    public ArrayList<String> search(double key) {
        return new ArrayList<>();
    }

    /** search for all the pairs whose key are between key1 and key2 */
    public ArrayList<Pair> search(double key1, double key2) {
        return null;
    }

}
