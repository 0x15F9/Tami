import java.util.ArrayList;

class Main {

	public static ArrayList GenerateStartingPoints(int [][] board, int dice[], int turn, int player)
	{
		ArrayList <Integer> StartPoints = new ArrayList <Integer>();
		if (turn == 1){
			if (player == 1){
				StartPoints.add(1);//adding coordinates by pair
				StartPoints.add(1);
			}
			else{
				StartPoints.add((board.length)-2);//adding coordinates by pair
				StartPoints.add((board[0].length)-2);
			}
		}
		else
		{
			for (int i=1; i<board.length; i++){//passing through the rows
				for (int j=1; j<board[1].length; j++){//passing through columns
					if (board[i][j] == player){

						if (board[i][j+1] == 7){
							StartPoints.add(i);//adding coordinates by pair
							StartPoints.add(j+1);
						}
						if (board[i][j-1] == 7){
							StartPoints.add(i);//adding coordinates by pair
							StartPoints.add(j-1);
						}
						if (board[i+1][j] == 7){
							StartPoints.add(i+1);//adding coordinates by pair
							StartPoints.add(j);
						}
						if (board[i-1][j] == 7){
							StartPoints.add(i-1);//adding coordinates by pair
							StartPoints.add(j);
						}

					}
				}
			}

			System.out.println("Fuck that Shit");
		}

		return StartPoints;
	}
