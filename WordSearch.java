import java.util.*; //imports ArrayList, Random,
import java.io.*;//imports File and FileNotFoundException
import java.util.Scanner;

public class WordSearch{

  private char[][]data;
  private int rows;
  private int cols;
  /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
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
    String returnValue = "";
    for (int i = 0; i <data.length; i++){
      for (int a = 0; a <data[i].length; a++){
        returnValue += data[i][a] + " " ;
      }
      returnValue += "\n";
    }
    return returnValue;
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

  public boolean addWordDiagonal(String word,int row, int col){
    int length = word.length()
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
}
