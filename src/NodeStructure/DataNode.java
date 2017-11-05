package NodeStructure;

import java.util.ArrayList;

/**
 * DataNode is used to store and search pairs
 */
public class DataNode extends Node {

    ArrayList<Pair> pairs;  // key-data pairs
    DataNode left;          // point to nearest left DataNode
    DataNode right;         // point to nearest right DataNode

    /** constructor from a new pair */
    public DataNode(int m, Pair pair) {
        this.m = m;
        pairs.add(pair);
        isDataNode = true;
        n=1;
    }

    /**
     * insert a new pair using binary search to maintain order
     * @return the smallest key after insertion
     */
    public double insert(Pair pair) {
        int left = 0;
        int right = pairs.size() - 1;
        while (left < right) {
            int mid = (left + right)/2;
            if (pair.compareTo(pairs.get(mid)) < 0) {
                right = mid - 1;
            } else left = mid + 1;
        }
        pairs.add(left, pair);
        return pairs.get(0).getKey();
    }

}
