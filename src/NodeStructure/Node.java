package NodeStructure;

import java.util.ArrayList;

/**
 * Abstract class Node for IndexNode and DataNode
 */
public abstract class Node {

    protected int m;                // order of this node
    protected boolean isDataNode;   // is it a data node?
    protected int n;                // current node size: n of keys/pairs

    /** void constructor */
    public Node() {

    }

    /** abstract search method using pair */
    public Node searchChild(Pair pair) {
        return null;
    }

    /** abstract search method using key */
    public Node searchChild(double key) {
        return null;
    }

    /** abstract search method */
    public void search(double key, ArrayList<String> vals) {

    }

    /** abstract insert method */
    public void insert(Pair pair) {

    }

    /** abstract split method */
    public IndexNode split() {
        return null;
    }

    /** abstract mergeWith method */
    public void mergeWith(IndexNode node) {

    }

    /** is this node a data node ? */
    public boolean isDataNode() {
        return isDataNode;
    }

    /** is this node overfull? */
    public boolean isOverfull() {
        return this.n >= m;
    }

}
