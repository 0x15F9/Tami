import java.util.Scanner;

class board {
  public static void main(String[] args) {  
    Scanner arrays = new Scanner(System.in);
    int row, col;

    System.out.print("Input the rows of the array : ");
    row = arrays.nextInt();
    while (row < 12){
      System.out.println("ERROR: The minimum requirement number of rows must be 12!!! ");
      System.out.print("Input the rows of the array : ");
      row = arrays.nextInt();
    }
    row += 2;

    System.out.print("Input the columns of the array : ");
    col = arrays.nextInt();
    while (col < 12){
      System.out.println("ERROR: The minimum requirement number of columns must be 12!!! ");
      System.out.print("Input the columns of the array : ");
      col = arrays.nextInt();
    }
    col += 2;

    int arr[][] = new int[row][col];

    
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        arr[i][j] = 7;
        arr[0][j] = 0;
        arr[row-1][j] = 0;
        arr[i][0] = 0;
        arr[i][col-1] = 0;
      }
      System.out.println();
    }

    arr[1][1] = 1;
    arr[row-2][col-2] = 2;

    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
    arrays.close();
 }
}