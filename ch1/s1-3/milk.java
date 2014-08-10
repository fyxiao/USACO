/*
ID: 
LANG: JAVA
TASK: milk
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {
	private static class MilkComparator implements Comparator<int[]> 
	{
		@Override
		public int compare(int[] a, int[] b) 
		{
			return a[0] - b[0];
		}
	}
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("milk.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] milk = new int[M][2];
        for (int i=0; i<M; i++) {
        	st = new StringTokenizer(f.readLine());
        	milk[i][0] = Integer.parseInt(st.nextToken());
        	milk[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(milk, new MilkComparator());
        
        int count = 0, cost = 0;
        for (int i=0; i<M; i++) {
        	while (count < N && milk[i][1] > 0) {
        		count++;
        		cost += milk[i][0];
        		milk[i][1] --;
        	}
        	if (count == N)
        		break;
        }

		out.write(cost + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
}
