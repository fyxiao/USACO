/*
ID: 
LANG: JAVA
TASK: 
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.StringTokenizer;

public class barn1 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("barn1.out")));
        
        StringTokenizer st = new StringTokenizer(f.readLine());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int[] stalls = new int[C];
        int[] gaps = new int[C-1];
        
        for (int i=0; i<C; i++)
        	stalls[i] = Integer.parseInt(f.readLine());
        Arrays.sort(stalls);
        
        for (int i=1; i<C; i++)
        	gaps[i-1] = stalls[i] - stalls[i-1] - 1;
        Arrays.sort(gaps);
        
        int res = stalls[C-1] - stalls[0] + 1;
        for (int i=0; i<Math.min(M-1, C-1); i++)
        	res -= gaps[C-2-i];
        out.write(res + "\n");

        f.close();
        out.close();
        System.exit(0);
    }
}
