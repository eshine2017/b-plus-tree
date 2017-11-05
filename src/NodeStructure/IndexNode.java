package NodeStructure;

import java.util.ArrayList;

/**
 * IndexNode is used for DataNode searching, does not have data pair
 */
public class IndexNode extends Node {

    ArrayList<Integer> keys;    // list of keys, for ith key, its left child is i and right child i+1
    ArrayList<Node> children;   // list of children

    /** constructor */
    public IndexNode() {

    }

}
