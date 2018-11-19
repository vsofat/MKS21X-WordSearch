import java.util.*; //imports ArrayList, Random, Scanner
import java.io.*;//imports File and FileNotFoundException

public class WordSearch{

  private char[][]data;
  private Random randgen;
  private int rows;
  private int cols;
  private static boolean key;
  private static int seed;
  private ArrayList<String>wordsToAdd; //all words from a text file get added to wordsToAdd that haven't been added yet
  private ArrayList<String>wordsAdded; //once a word is added it is moved to this ArrayList
  /**Initialize the grid to the size specified
  *and fill all of the positions with '_'
  *@param row is the starting height of the WordSearch
  *@param col is the starting width of the WordSearch
  */
  public static void main(String[] args) { //main function, allows for user interaction and instructs user on how to use class;
    String message = "\n Hi! The format of your input was incorrect. Please make sure the format is: java WordSearch [rows columns filename [seed [Key]]].";
    if (args.length < 3) {
      System.out.println(message);
    }
    else {
      int rows = 0;
      int cols = 0;
      seed = (int)(Math.random()*100000);
      try {
        rows = Integer.valueOf(args[0]);
        cols = Integer.valueOf(args[1]);
      } catch (IllegalArgumentException e) {
        System.out.println(message);
        System.exit(0);
      }
      String fileName = args[2];
      key = false;
      if (args.length >= 4) {
        try {
          seed = Integer.valueOf(args[3]);
        } catch (IllegalArgumentException e) {
          System.out.println(message);
          System.exit(0);
        }
      }
      if (args.length == 5 && args[4].equals("key")) {
        key = true;
      }
      WordSearch wordsearch = new WordSearch(rows, cols, fileName, seed, key);
      System.out.println(wordsearch);
      wordsearch.printWordList();
    }
  }
  // WordSearch
  public WordSearch(int rows,int cols, String filename){
    try{
      Scanner in = new Scanner(new File(filename));
      while(in.hasNext()){
        String upperWord = in.next().toUpperCase();
        wordsToAdd.add(upperWord);
      }
    }
    catch (FileNotFoundException e){
      System.out.println("File not found: " + filename);
      System.exit(1);
    }
    data = new char[rows][cols];
    for (int i = 0; i <data.length; i++){
      for (int a = 0; a <data[i].length; a++){
        data[i][a] = '_';
      }
    }
  }
  public WordSearch(int rows, int cols, String filename, int randseed, boolean key){
    data = new char[rows][cols];
    this.rows = rows;
    this.cols = cols;
    this.seed = randseed;
    this.key = key;
    clear();
    wordsToAdd = new ArrayList<String>();
    randgen = new Random(seed);
    try{
        Scanner in = new Scanner(new File(filename));
        while(in.hasNext()){
        String upperWord = in.next().toUpperCase();
        wordsToAdd.add(upperWord);
      }
    }
    catch (FileNotFoundException e){
      	    System.out.println("File not found: " + filename);
            System.exit(1);
  	}
    if(key){
      clear();
      addAllWords();
    }
    else{
      addAllWords();
      fillRest();
    }
  }
  /**Set all values in the WordSearch to underscores'_'*/
  // Clear
  private void clear(){
    for(int counter = 0; counter < data.length; counter++){
      for(int counter2 = 0; counter2 < data[0].length; counter2++){
        data[counter][counter2] = '_';
      }
    }
  }
  /**Each row is a new line, there is a space between each letter
  *@return a String with each character separated by spaces, and rows
  *separated by newlines.
  */
  // toString
  public String toString(){
    String result = "";
    for (int counter = 0; counter < data.length; counter++) {
      result += '|'; // Devin helped me debug this
      for (int counter2 = 0; counter2 < data[counter].length; counter2++) {
        result += data[counter][counter2] + " ";
      }
      result += '|';
      result += "\n";
    }
    result += "\nThe seed you used was " + seed + ".\n"; // seed constructors not added yet
    return result;
  }
  public void printWordList() {
    System.out.println(wordsAdded + "\n");
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from left to right, must fit on the WordGrid, and must
  *have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  * or there are overlapping letters that do not match, then false is returned
  * and the board is NOT modified.
  */
  // Horizontal
  public boolean addWordHorizontal(String word,int row, int col){
    int length = word.length();
    if (row >= data[0].length || col + length >= data.length){
      return false;
    }
    for(int i = 0; i < length; i++){
      data[row][col+i]=word.charAt(i);
    }
    return true;
  }
  /**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added from top to bottom, must fit on the WordGrid, and must
  *have a corresponding letter to match any letters that it overlaps.
  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.
  *@return true when the word is added successfully. When the word doesn't fit,
  *or there are overlapping letters that do not match, then false is returned.
  *and the board is NOT modified.
  */
  // Vertical
  public boolean addWordVertical(String word,int row, int col){
    int length = word.length();
    if (row + length >= data[0].length || length >= data.length){
      return false;
    }
    for(int i = 0; i < length; i++){
      data[row + i][col]=word.charAt(i);
    }
    return true;
  }
  // same idea as horizontal just a diff direction
  // Diagonal
  public boolean addWordDiagonal(String word,int row, int col){
    int length = word.length();
    if (row + length > data.length || col + length > data[0].length){
      return false;
    } // checks if word fits
    for (int i = 0; i < length; i ++){
      int x = row;
      int y = col;
      if (data[x][y] != '_' && data[x][y] != word.charAt(i)){
        return false;
      }
      x++;
      y++;
    }
    for (int i = 0; i < length; i ++){
      int x1 = row;
      int x2 = col;
      data[x1][x2] = word.charAt(i);
      x1 ++;
      x2 ++;
    }
    return true;
    /**Attempts to add a given word to the specified position of the WordGrid.
    *The word is added from top left to bottom right, must fit on the WordGrid,
    *and must have a corresponding letter to match any letters that it overlaps.
    *
    *@param word is any text to be added to the word grid.
    *@param row is the vertical locaiton of where you want the word to start.
    *@param col is the horizontal location of where you want the word to start.
    *@return true when the word is added successfully. When the word doesn't fit,
    *or there are overlapping letters that do not match, then false is returned.
    */
  }
  private boolean fillRest(){
    String OtherChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    for(int counter = 0; counter < rows; counter++){
      for(int counter2 = 0; counter2 < cols; counter2++){
        if(data[counter][counter2] == '_'){
          data[counter][counter2] = OtherChars.charAt(randgen.nextInt(OtherChars.length()));
        }
      }
    }
    return true;
  }
  // Add words
  private boolean addWord(int row, int col, String word, int rInc, int cInc){
    if((rInc == 0) && (cInc == 0)){
      return false;
    }
    try{
      int r = row;
      int c = col;
      for(int counter = 0; counter < word.length(); counter++){
        if(((data[r][c] != word.charAt(counter)) &&
        (data[r][c] != '_'))){
          return false;
        }
        if(word.charAt(counter) < 10){
          return false;
        }
        r+= rInc;
        c+= cInc;
      }
      r = row;
      c = col;
      for(int counter = 0; counter < word.length(); counter++){
        data[r][c] = word.charAt(counter);
        r+= rInc;
        c+= cInc;
      }
      return true;
    }
    catch(IndexOutOfBoundsException x){
      return false;
    }}

    private boolean addAllWords(){
      for (int c = 0; c < wordsToAdd.size(); c++) {
        String word = wordsToAdd.get(c);
        boolean pause = false;
        int counter = 0, randomRows, randomCols, randomRInc, randomCInc;
        while (!pause && counter <= 300) {
          randomRows = randgen.nextInt(rows);
          randomCols = randgen.nextInt(cols);
          randomCInc = randgen.nextInt(3) - 1;
          randomRInc = randgen.nextInt(3) - 1;
          if (addWord(randomRows, randomCols, word, randomRInc, randomCInc)) {
            wordsToAdd.remove(c);
            wordsAdded.add(word);
            pause = true;
            c -= 1;
          }
          counter += 1;
        }
      }
      return true;
    }}
