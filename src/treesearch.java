import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class treesearch {

    /** main program entrance */
    public static void main(String[] args) {
        try {
            // read commands from test.txt
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            // initialize B+ tree with order m
            int m = Integer.parseInt(line);
            System.out.println(m);
            // continue to read other commands and execute corresponding methods
            while (true) {
                line = br.readLine();
                if (line == null) break;
                // extract method name and parameters
                int i = line.indexOf('(');
                String cmd = line.substring(0, i);
                int j = line.indexOf(',');
                int k = line.indexOf(')');
                double key1;
                String val2;
                if (j == -1) {
                    key1 = Double.parseDouble(line.substring(i+1, k));
                    val2 = null;
                } else {
                    key1 = Double.parseDouble(line.substring(i+1, j));
                    val2 = line.substring(j+1, k);
                }
                System.out.println("cmd: " + cmd + ", val1: " + key1 + ", val2: " +val2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
