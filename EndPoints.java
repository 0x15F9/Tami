import java.util.ArrayList;


class Main {

	public static ArrayList[] ConcatEndPoints(ArrayList[] EndPoints, int row, int column, int k, int board[][], int start[])
	//row and column are for the possible endpoints to be checked
	//k is the row in arraylist, where we must insert coordinates
	{
		Boolean check = true;
		try{
			int rowstart, colstart, rowend, colend;
			if (row > start[0]){
				rowstart = start[0];
				rowend = row+1;
			}
			else
			{
				rowstart = row-1;
				rowend = start[0];
			}

			if (column > start[1]){
				colstart = start[1];
				colend = column+1;
			}
			else
			{
				colstart = column-1;
				colend = start[1];
			}
			if (board[row][column] == 7){
				for (int i = rowstart; i < rowend-1; i++){
					for (int j = colstart; j < colend-1; j++){
						if (board[i][j] != 7){
							check = false;
						}
					}					
				}
				if (check == true){
					EndPoints[k].add(row);
					EndPoints[k].add(column);
				}
			}
			else{
				EndPoints[k].add(0);
				EndPoints[k].add(0);
				}
		}
		catch(Exception e){
			EndPoints[k].add(0);
			EndPoints[k].add(0);
		}

		return EndPoints;
	}

	public static ArrayList[] CalculateEndingPoints(ArrayList<Integer> SP, int[] dice, int board[][])
	{
	// takes an arraylist of starting points, Dice in an array, a 2D array with  the board
		dice[0]-=1;
		dice[1]-=1;
		int row=0, column=0, temp=0;
		int n = ((SP.size()+1)/2);//n is the number of fucking starting points

		ArrayList <Integer>[] EndPoints = new ArrayList[n]; 
		
		for (int i = 0; i < n; i++) { //Initialise an arraylist for each index inside the fucking array
    	EndPoints[i] = new ArrayList<Integer>();
		} 
		
		for (int i=0; i<SP.size(); i=i+2){//Each starting point
			int[] start = {SP.get(i), SP.get(i+1)};
			int k;
			k=i/2;
			for(int j=0; j<2; j++){
				try{
					row = dice[0] + SP.get(i);
					column = dice[1] + SP.get(i+1);
					ConcatEndPoints(EndPoints, row, column, k, board, start);

					row = SP.get(i) - dice[0];
					column = SP.get(i+1) - dice[1];
					ConcatEndPoints(EndPoints, row, column, k, board, start);

					row = SP.get(i) + dice[0];
					column = SP.get(i+1) - dice[1];
					ConcatEndPoints(EndPoints, row, column, k, board, start);

					row = SP.get(i) - dice[0];
					column = SP.get(i+1) + dice[1];
					ConcatEndPoints(EndPoints, row, column, k, board, start);
				}
				catch(Exception e){
					ConcatEndPoints(EndPoints, 0, 0, k, board, start);
				}

				//swapping dice values
				temp=dice[0];
				dice[0]=dice[1];
				dice[1]=temp;
			}
		}
		
		return EndPoints;
	}
