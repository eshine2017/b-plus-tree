package NodeStructure;

import java.util.ArrayList;

/**
 * IndexNode is used for DataNode searching, does not have data pair
 */
public class IndexNode extends Node {

    int n;                      // number of keys
    ArrayList<Integer> keys;    // list of keys, index starts from 1
    ArrayList<Node> pointers;   // list of pointers, index starts from 0

    /** constructor */
    public IndexNode() {

    }

}
