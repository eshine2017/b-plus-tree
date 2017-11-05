package NodeStructure;

import java.util.ArrayList;

/**
 * DataNode is used to store and search pairs
 */
public class DataNode extends Node {

    private ArrayList<Pair> pairs;  // key-data pairs
    private DataNode left;          // point to nearest left DataNode
    private DataNode right;         // point to nearest right DataNode

    /** constructor from a new pair */
    public DataNode(int m, Pair pair) {
        this.m = m;
        isDataNode = true;
        n = 1;
        pairs = new ArrayList<>();
        pairs.add(pair);
    }

    /** constructor from a list of pairs, left node and right node*/
    public DataNode(int m, ArrayList<Pair> pairs, DataNode left, DataNode right) {
        this.m = m;
        isDataNode = true;
        n = pairs.size();
        this.pairs = pairs;
        this.left = left;
        this.right = right;
    }

    /** insert a new pair using binary search to maintain order */
    public void insert(Pair pair) {
        int left = 0;
        int right = pairs.size() - 1;
        while (left < right) {
            int mid = (left + right)/2;
            if (pair.compareTo(pairs.get(mid)) < 0) {
                right = mid - 1;
            } else left = mid + 1;
        }
        pairs.add(left, pair);
    }

    /**
     * split a overfull data node
     * @return a new index node with the new data node as its child
     */
    public IndexNode split() {

        // move the right half children to new data node
        ArrayList<Pair> newPairs = new ArrayList<>();
        for (int i = pairs.size() - 1; i >= m/2; i--) {
            newPairs.add(0, pairs.get(i));
            pairs.remove(i);
        }

        // reset double linked list pointers
        DataNode left = this;
        DataNode right = this.right;
        DataNode newNode = new DataNode(m, newPairs, left, right);
        this.right = newNode;

        // create a new index node to merge with parent
        return new IndexNode(m, newPairs.get(0).getKey(), newNode);

    }

}
