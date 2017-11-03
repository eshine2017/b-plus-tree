package NodeStructure;

/**
 * DataNode is used to store and search pairs
 */
public class DataNode extends Node {

    int n;              // number of pairs
    Pair[] pairs;       // key-data pairs
    DataNode left;      // point to nearest left DataNode
    DataNode right;     // point to nearest right DataNode

    /** constructor */
    public DataNode() {

    }

}
