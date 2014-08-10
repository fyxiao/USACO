/*
ID: 
LANG: JAVA
TASK: dualpal
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;

public class dualpal 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("dualpal.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int count = 0;
        for (int i=S+1; count < N; i++) {
        	if (isDualPal(i)) {
        		count++;
        		out.write(i + "\n");
        	}
        }
        
        f.close();
        out.close();
        System.exit(0);
    }
    
    public static boolean isDualPal(int n) 
    {
    	int count = 0;
    	for (int i=2; i<=10; i++) {
    		if (isPal(convertB(n, i))) {
    			count++;
    			if(count ==2)
    				return true;
    		}
    	}
    	return false;
    }
    
    public static boolean isPal(String s) 
    {
    	for (int i=0; i<s.length()/2; i++) {
    		if (s.charAt(i) != s.charAt(s.length()-1-i))
    			return false;
    	}
    	return true;
    }
    
    public static String convertB(int N, int B) 
    {
    	String res = "";
    	while (N > 0) {
    		char add = (char) ((N%B > 9) ? (N%B-10 + 'A') : (N%B + '0'));
    		res = add + res;
    		N /= B;
    	}
    	return res;
    }
}
