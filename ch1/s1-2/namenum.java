/*
ID: 
LANG: JAVA
TASK: namenum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

public class namenum 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("namenum.out")));
        BufferedReader fd = new BufferedReader(new FileReader("dict.txt"));
       
        ArrayList<String> dict = new ArrayList<String>();
        while (fd.ready())
        	dict.add(fd.readLine());
       
        long num = Long.parseLong(f.readLine());
        boolean found = false;
        for (int i=0; i<dict.size(); i++) {
        	String s = dict.get(i);
        	if (num == convert(s)) {
        		out.write(s + "\n");
        		found = true;
        	}
        }
        
        if (!found)
        	out.write("NONE\n");
        
        System.out.println(convert("KRISTOPHER"));
        
        f.close();
        fd.close();
        out.close();
        System.exit(0);
    }
    
    private static long convert(String s)
    {
    	long res = 0;
    	for (int i=0; i<s.length(); i++) {
    		int digit = (int)(s.charAt(i) - 'A');
    		res = res * 10 + 2 + (digit < 16 ? digit : digit-1)/3;
    	}
    	return res;
    }
}
