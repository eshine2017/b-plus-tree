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

    /*
    use binary search to find the index of pair,
    will just return the index it terminated if search miss
    */
    private int searchPair(double key) {
        int left = 0;
        int right = pairs.size() - 1;
        while (left < right) {
            int mid = (left + right)/2;
            if (key < pairs.get(mid).getKey()) {
                right = mid - 1;
            } else if (key > pairs.get(mid).getKey()) {
                left = mid + 1;
            } else return mid;
        }
        return left;
    }

    /**
     * search the matched pairs with given key, add values to list
     * @param key
     * @param vals the list which will have all values after search
     */
    public void search(double key, ArrayList<String> vals) {
        int index = searchPair(key);

        // add all matched values in the right
        DataNode node = this;
        int r = index + 1;
        while (true) {
            if (r >= node.pairs.size() && node.right != null) {
                node = node.right;
                r = 0;
            }
            if (r < node.pairs.size() && key == pairs.get(r).getKey()) {
                vals.add(pairs.get(r).getValue());
                r++;
            } else break;
        }

        // add all matched values in the left
        node = this;
        int l = index;
        while (true) {
            if (l < 0 && node.left != null) {
                node = node.left;
                l = node.pairs.size() - 1;
            }
            if (l >= 0 && key == pairs.get(l).getKey()) {
                vals.add(pairs.get(l).getValue());
                l--;
            } else break;
        }
    }

    /**
     * use key2 to get the first index, then access two links to get all pairs
     * @param key1
     * @param key2
     * @param res the resulting list of pairs
     */
    public void search(double key1, double key2, ArrayList<Pair> res) {
        int index = searchPair(key2);

        // add all matched pairs in the right
        DataNode node = this;
        int r = index + 1;
        while (true) {
            if (r >= node.pairs.size() && node.right != null) {
                node = node.right;
                r = 0;
            }
            if (r < node.pairs.size() && key1 <= pairs.get(r).getKey()
                    && key2 >= pairs.get(r).getKey()) {
                res.add(pairs.get(r));
                r++;
            } else break;
        }

        // add all matched pairs in the left
        node = this;
        int l = index;
        while (true) {
            if (l < 0 && node.left != null) {
                node = node.left;
                l = node.pairs.size() - 1;
            }
            if (l >= 0 && key1 <= pairs.get(l).getKey()
                    && key2 >= pairs.get(l).getKey()) {
                res.add(pairs.get(l));
                l--;
            } else break;
        }
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
