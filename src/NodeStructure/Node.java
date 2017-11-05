package NodeStructure;

/**
 * Abstract class Node for IndexNode and DataNode
 */
public abstract class Node {

    protected int m;                // order of this node
    protected boolean isDataNode;   // is it a data node?
    protected int n;                // current node size

    /** void constructor */
    public Node() {

    }

    /** abstract insert method */
    public double insert(Pair pair) {
        return 0.0;
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
