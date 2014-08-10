/*
ID: 
LANG: JAVA
TASK: clocks
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;

public class clocks 
{	
	private static int[] clocks = new int[9];
	private static int[] bestMoves = new int[9];
	private static int bestMovesVal = 109; // longest path is (1+2+...+8)*3
	
	private static boolean isTwelve(int[] a) {
		for (int i=0; i<9; i++) {
			if (a[i]%12 != 0) return false;
		}
		return true;
	}
	
	private static int[][] moveArr = { 
		{3, 3, 0, 3, 3, 0, 0, 0, 0},
		{3, 3, 3, 0, 0, 0, 0, 0, 0},
		{0, 3, 3, 0, 3, 3, 0, 0, 0},
		{3, 0, 0, 3, 0, 0, 3, 0, 0},
		{0, 3, 0, 3, 3, 3, 0, 3, 0},
		{0, 0, 3, 0, 0, 3, 0, 0, 3},
		{0, 0, 0, 3, 3, 0, 3, 3, 0},
		{0, 0, 0, 0, 0, 0, 3, 3, 3},
		{0, 0, 0, 0, 3, 3, 0, 3, 3}
	};
	
	private static void solve(int[] moves, int k) {
		if (k==9) {
			if (isTwelve(clocks)) {
				int val = 0;
				for (int i=0; i<9; i++)
					val += moves[i];
				if (bestMovesVal == 0 || val < bestMovesVal) {
					bestMovesVal = val;
					for (int i=0; i<9; i++)
						bestMoves[i] = moves[i];
				}
			}
			return;
		}
		
		for (int i=3; i>=0; i--) {
			moves[k] = i;
			for (int j=0; j<9; j++)
				clocks[j] += i * moveArr[k][j];
			solve(moves, k+1);
			for (int j=0; j<9; j++)
				clocks[j] -= i * moveArr[k][j];
		}
	}
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("clocks.out")));
        
        
        for (int i=0; i<3; i++) {
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	for (int j=0; j<3; j++)
        		clocks[3*i+j] = Integer.parseInt(st.nextToken());
        }
        
        int[] moves = new int[9];
        solve(moves, 0);
        
        String output = "";
        for (int i=0; i<9; i++) {
        	for (int j=0; j<bestMoves[i]; j++) {
        		output = output + (i+1) + " ";
        	}
        }
        
        if (output.length() > 0)
        	output = output.substring(0, output.length()-1);
        output += "\n";
        out.write(output);
        System.out.print(output);
        
        f.close();
        out.close();
        System.exit(0);
    }
}
