/*
ID: 
LANG: JAVA
TASK: friday
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class friday 
{
	private static int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
        		"friday.out")));
        int N = Integer.parseInt(f.readLine());
        
        // 0 is Saturday, 6 is Friday
        int[] counts = new int[7];
        int cur13 = 0;
        
        for (int i = 1900; i < 1900+N; i++) {
        	counts[cur13]++;
        	// adjust February accordingly
        	months[1] = isLeap(i) ? 29 : 28;
        	for (int j = 0; j < 11; j++) {
        		cur13 = (cur13 + months[j])%7;
        		counts[cur13]++;
        	}
        	cur13 = (cur13 + months[11])%7;
        }
        
        out.write(getRep(counts) + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static boolean isLeap(int n)
    {
    	if(n%4 != 0) return false;
    	if (n%100 == 0 && n%400 != 0) return false;
    	return true;
    }
    
    private static String getRep(int[] a)
    {
    	StringBuilder rep = new StringBuilder();
    	for (int i = 0; i < a.length; i++)
    		rep.append(a[i] + " ");
    	rep.deleteCharAt(rep.length()-1);
    	return rep.toString();
    }
}
