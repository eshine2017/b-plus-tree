package NodeStructure;

/**
 * Key-value pair defined to store data
 */
public class Pair implements Comparable<Pair> {

    private double key;
    private String data;

    /** constructor */
    public Pair(double key, String data) {
        this.key = key;
        this.data = data;
    }

    /** return the key of this pair */
    public double getKey() {
        return key;
    }

    /** return the data of this pair */
    public String getValue() {
        return data;
    }

    /** comparable interface for Pair */
    public int compareTo(Pair that) {
        if (this.key < that.key) return -1;
        else if (that.key < this.key) return 1;
        else return 0;
    }

    @Override
    /** return (key,data) pair representation */
    public String toString() {
        return "(" + key + "," + data + ")";
    }

}
