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
* New class for QueryResult
*/
public class QueryResult {
  private String filename;
  private String snipbit;
  /**
  * New Constructor for QueryResult
  * @param String filename
  * @param String snipbit
  */
  public QueryResult(String filename, String snipbit) {
    this.filename = filename;
    this.snipbit = snipbit;
  }
  /**
  * New Method for toSting
  * @return String
  */
  public String toString() {
    String output = "";
    output = output.concat("Filename: ");
    output = output.concat(this.filename);
    output= output.concat("\n");
    output = output.concat("Surrounding Text: ");
    output = output.concat(this.snipbit);
    output= output.concat("\n");
    return output;
  }

}
