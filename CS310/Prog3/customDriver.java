/**
 * Created by paultruongnguyen on 8/12/16.
 */

import java.util.Iterator;
import data_structures.*;

public class customDriver {
    public static void main(String args[] ) {
        String currentKey = "";
        DictionaryADT<String,String> tree = new BinarySearchTree<>();

        tree.add("b", "value of b");
        tree.add("a", "value of a");
        tree.add("c", "value of c");
        System.out.println(tree.size());
        Iterator<String> keyIter = tree.keys();
        while(keyIter.hasNext()) {
            currentKey = keyIter.next();
            System.out.println(currentKey);
        }


    }
}
