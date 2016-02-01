package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BruteForceSearch implements WordSearch {

  private final String fileName;
  private Map<String, Integer> result = new HashMap<String, Integer>();

  public BruteForceSearch(File file, String text) throws IOException {
    this.fileName = file.getName();
    this.result = search(file, text);

  }

  private Map<String, Integer> search(File file, String stringToLookFor)
      throws IOException {

    Map<String, Integer> tempResult = new HashMap<String, Integer>();

    FileInputStream fstream = new FileInputStream(file);
    BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
    String readLine = "";
    int count = 0;
    while ((readLine = in.readLine()) != null) {
      String[] words = readLine.split("\\W");
      for (String text : words) {
        if (text.equalsIgnoreCase(stringToLookFor)) {
          count++;
        }
      }
    }
    if (count != 0) {
      tempResult.put(stringToLookFor.toLowerCase(), count);
    }
    in.close();

    return tempResult;
  }

  public String getFile() {
    return fileName;
  }

  public int getCount(String word) {
    if (result.containsKey(word.toLowerCase())) {
      return result.get(word.toLowerCase());
    }
    return 0;
  }

}
