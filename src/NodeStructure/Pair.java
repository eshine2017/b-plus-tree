package NodeStructure;

/**
 * Key-value pair
 */
public class Pair {

    int key;
    String data;

    /** constructor */
    public Pair(int key, String data) {
        this.key = key;
        this.data = data;
    }

    @Override
    /** return (key,data) pair representation */
    public String toString() {
        return "(" + key + "," + data + ")";
    }

}
