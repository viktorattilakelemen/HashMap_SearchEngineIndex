/**
* B Fuentes & Viktor Kelemen
* 5/9/18
* CP222: Matthew Whitehead
*/
import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
* New Class for SearchEngineIndex
*/
public class SearchEngineIndex {
    private HashMap<String, ArrayList<QueryResult>> index = new HashMap<String, ArrayList<QueryResult>>();
    private ArrayList<File> files = new ArrayList<File>();
    /**
    * New Constructor for SearchEngineIndex
    */
    public SearchEngineIndex(String top_level_filename){
        buildFileList(new File(top_level_filename));
    }
    /**
    * Method for buildFileList and build a list of all of the files
    * @param File to take in the current File
    */
    public void buildFileList(File current) {
        if (current.isFile()) {
            files.add(current);
        }
        else if (current.isDirectory()) {
            File [] files = current.listFiles();  // get an array of all the files
            for (int i = 0; i < files.length; i++){
                buildFileList(files[i]);
            }
        }
    }
    /**
    * Method for buildIndexMap and goes through all of the files and builds the hashmap
    * @throws IOException
    */
    public void buildIndexMap() throws IOException {
        for (File file : files){
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements elements = doc.body().select("*");
            ArrayList<String> document_words = new ArrayList<String>();
            for (Element element : elements){
                for (String ele : getStringsFromElement(element)){
                    if(ele.length() > 1){
                      document_words.add(ele);
                    }
                }
            }

            for(int i = 0; i < document_words.size(); i++) {
                QueryResult query = new QueryResult( file.getName(), getTextSnippet(document_words, i) );

                if(index.containsKey(document_words.get(i).toLowerCase()))
                    ( index.get(document_words.get(i).toLowerCase())).add(query);

                else{

                    ArrayList<QueryResult> query_list = new ArrayList<QueryResult>();
                    query_list.add(query);
                    index.put( document_words.get(i).toLowerCase(), query_list);
                }
            }
        }
    }
    /**
    * New Method for getIndexMap to return a HashMap
    * @return HashMap
    */
    public HashMap<String, ArrayList<QueryResult>> getIndexMap(){
      return index;
    }

    /**
    * New Method for getTextSnippet to get the words surrounding the searched word
    * @param ArrayList<String> to take in the words
    * @param Int to take in the index of what were you're at
    * @return String to return a string of the surrounfing words
    */
    public String getTextSnippet(ArrayList<String> words, int index){
        String neighbors = "";

        for(int i = Math.max(0, index-3); i<Math.min(words.size(), index+3); i++){
            neighbors = neighbors.concat(" ");
            neighbors = neighbors.concat(words.get(i));
        }
        return neighbors;
    }
    /**
    * New Method for getting Strings from the Element
    * @param Element e
    * @return ArrayList<String to return an ArrayList of type String
    */
    public ArrayList<String> getStringsFromElement(Element e) {
        String txt = e.ownText().trim();
        return ( new ArrayList<String>(Arrays.asList(txt.split("[\\s]"))) );
    }

    /**
    * Method to clean the text results
    * @param String text
    */
    public void cleanText(String txt){
      System.out.println();
      //  txt.replaceAll()
    }

}
