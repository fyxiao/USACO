/*
ID: 
LANG: JAVA
TASK: transform
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class transform 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("transform.out")));

        int N = Integer.parseInt(f.readLine());
        String[] board = new String[N];
        String[] res = new String[N];
        
        for (int i=0; i<N; i++)
        	board[i] = f.readLine();
        for (int i=0; i<N; i++)
        	res[i] = f.readLine();

        for (int i=1; i<=3; i++) {
        	if (areSame(res, rotN(board, i))) {
        		out.write(i + "\n");
                f.close();
                out.close();
                System.exit(0);
        	}
        }
        
        if (areSame(res, reflect(board))) {
        	out.write("4\n");
            f.close();
            out.close();
            System.exit(0);
        }
        
        for (int i=1; i<=3; i++) {
        	if (areSame(res, rotN(reflect(board), i))) {
        		out.write("5\n");
                f.close();
                out.close();
                System.exit(0);
        	}
        }
        
        if (areSame(res, board)) {
        	out.write("6\n");
            f.close();
            out.close();
            System.exit(0);
        }
        
        out.write("7\n");
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static String[] rotN(String[] board, int N) 
    {
    	String[] res = board;
    	for (int i=0; i<N; i++)
    		res = rot90(res);
    	return res;
    }
    
    private static String[] rot90(String[] board) 
    {
    	int N = board.length;
    	String[] rot = new String[N];
    	for (int i=0; i<N; i++) {
    		String row = "";
    		for (int j=N-1; j>=0; j--)
    			row += board[j].charAt(i);
    		rot[i] = row;
    	}
    	return rot;
    }
    
    private static String[] reflect(String[] board) 
    {
    	int N = board.length;
    	String[] ref = new String[N];
    	for (int i=0; i<N; i++) {
    		ref[i] = reverse(board[i]);
    		if (ref[i].length() != board[i].length())
    			System.out.println("ERROR in reflect");
    	}
    	return ref;
    }
    
    private static String reverse(String s) 
    {
    	char[] sA = s.toCharArray();
    	int len = sA.length;
    	
    	for (int i=0; i<len/2; i++) {
    		char c = sA[i];
    		sA[i] = sA[len-1-i];
    		sA[len-1-i] = c;
    	}
    	return new String(sA);
    }
    
    private static boolean areSame(String[] b1, String[] b2) 
    {
    	for (int i=0; i<b1.length; i++) {
    		if (!b1[i].equals(b2[i]))
    			return false;
    	}
    	return true;
    }
}
