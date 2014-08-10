/*
ID: 
LANG: JAVA
TASK: palsquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class palsquare 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("palsquare.out")));

        int B = Integer.parseInt(f.readLine());
        
        for (int i=1; i<= 300; i++) {
        	if (isPal(convertB(i*i, B)))
        		out.write(convertB(i, B) + " " + convertB(i*i, B) + "\n");
        }
        
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static boolean isPal(String s)
    {
    	for (int i=0; i<s.length()/2; i++) {
    		if (s.charAt(i) != s.charAt(s.length()-1-i))
    			return false;
    	}
    	return true;
    }
    
    private static String convertB(int N, int B)
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
