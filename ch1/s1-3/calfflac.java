/*
ID: 
LANG: JAVA
TASK: calfflac
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class calfflac 
{
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz" + 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("calfflac.out")));
        StringBuilder sb = new StringBuilder();
        while (f.ready()) {
        	sb.append(f.readLine());
        	sb.append("\n");
        }
        String orig = sb.toString();
        sb = new StringBuilder();
        for (int i=0; i<orig.length(); i++) {
        	if (isValid(orig.charAt(i)))
        		sb.append(orig.charAt(i));
        }
        String normalized = sb.toString().toLowerCase();
        int res = 0, index = -1;
        for (int i=0; i<normalized.length(); i++) {
        	int len = maxPal(normalized, i);
        	if (len > res) {
        		res = len;
        		index = i;
        	}
        }
        
        out.write(res + "\n" + getOriginal(orig, index+1, res) + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static String getOriginal(String s, int nc, int len) 
    {
    	int l, r;
    	if (len%2 == 0) {
    		l = nc - (len-2)/2;
    		r = nc + 1 + (len-2)/2;
    	} else {
    		l = nc - (len-1)/2;
    		r = nc + (len-1)/2;
    	}
    	StringBuilder sb = new StringBuilder();
    	int nValid = 0;
    	int i;
    	for (i=0; i<s.length(); i++) {
    		char c = s.charAt(i);
    		if (isValid(c))
    			nValid++;
    		if (nValid >= l && nValid <r)
    			sb.append(s.charAt(i));
    		if (nValid ==r) {
    			sb.append(s.charAt(i));
    			break;
    		}
    	}
    	return sb.toString();
    }
    
    private static int maxPal(String s, int c) 
    {
    	// even
    	int e = 0, l = c, r = c+1;
    	if (l < s.length()-2 && s.charAt(l) == s.charAt(r)) {
	    	while (l>=0 && r<s.length() && s.charAt(l--) == s.charAt(r++))
	    		e+=2;
    	}
    	// odd
    	int o = 1;
    	l = c-1;
    	r = c+1;
    	while (l>=0 && r<s.length() && s.charAt(l--) == s.charAt(r++))
    		o+=2;
    	return Math.max(e, o);
    }

    private static boolean isValid(char c) 
    {
    	return (alphabet.indexOf(c) >= 0) ? true : false;
    }
    
}
