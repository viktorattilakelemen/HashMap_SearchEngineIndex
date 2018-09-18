/**
* B Fuentes & Viktor Kelemen
* 5/9/18
* CP222: Matthew Whitehead
*/
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
* New Class for SearchEngineDriver
*/
  public class SearchEngineDriver {

    /**
    *Main method execution
    *@param args Unused
    */
    public static void main(String[] args) throws IOException {
        SearchEngineIndex sei = new SearchEngineIndex("wildanimalsonline.com/");
        sei.buildIndexMap();
        System.out.println("What word would you like to search? ");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        input = input.toLowerCase();    //make input lowercase
        //check if input is in hashmap keys
        HashMap<String, ArrayList<QueryResult>> index = sei.getIndexMap();
        if (index.containsKey(input)){
          ArrayList<QueryResult> queries = sei.getIndexMap().get(input);
          for (QueryResult query : queries)
              System.out.println(query.toString());
        }
        else {
          System.out.println("This word does not exist in any of these html files!");
        }
    }
}
