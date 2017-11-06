import NodeStructure.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class treesearch {

    /* execute the cmd and return output message */
    private static String executeCMD(BPlusTree tree, String cmd) {

        // split cmd line
        int i = cmd.indexOf('(');
        int j = cmd.indexOf(',');
        int k = cmd.indexOf(')');

        // recognize method and parameters
        String method = cmd.substring(0, i);
        double val1; // val1 should be key
        String val2; // val2 is key for search and String for insert

        if (j == -1) { // no second parameter -> search(key)
            val1 = Double.parseDouble(cmd.substring(i+1, k));
            val2 = null;
        } else { // insert(key, val) or search(key1, key2)
            val1 = Double.parseDouble(cmd.substring(i+1, j));
            val2 = cmd.substring(j+1, k);
        }
        //System.out.println("method: " + method + ", val1: " + val1 + ", val2: " +val2);

        // switch method and return output
        String msg = "";
        if (method.equals("Insert")) {
            tree.insert(val1, val2);
            return null;
        }
        else if (method.equals("Search")) {
            if (val2 == null) { // key search
                ArrayList<String> vals = tree.search(val1);
                if (vals.size() == 0) msg = "Null";
                else {
                    msg += vals.get(0);
                    for (int x = 1; x < vals.size(); x++) {
                        msg += "," + vals.get(x);
                    }
                }
            } else { // range search
                ArrayList<Pair> pairs = tree.search(val1, Double.parseDouble(val2));
                if (pairs.size() == 0) msg = "Null";
                else {
                    msg += pairs.get(0);
                    for (int x = 1; x < pairs.size(); x++) {
                        msg += "," + pairs.get(x);
                    }
                }
            }
        }
        else System.out.println("Wrong method!");

        return msg;

    }

    /** main program entrance */
    public static void main(String[] args) {

        try {

            // read commands from test.txt
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();

            // initialize B+ tree with order m
            int m = Integer.parseInt(line);
            //System.out.println(m);
            BPlusTree tree = new BPlusTree(m);

            // continue to read and execute other commands, write output file
            PrintWriter writer = new PrintWriter("output_file.txt", "UTF-8");
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String msg = executeCMD(tree, line);
                if (msg != null) {
                    System.out.println(msg);
                    writer.println(msg);
                }
            }

            writer.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
