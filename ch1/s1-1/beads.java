/*
ID: 
LANG: JAVA
TASK: beads
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class beads {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("beads.out")));
        
        String necklace = f.readLine();
        necklace = f.readLine();
        necklace += necklace;
        
        int ans = 0;
        for (int i=0; i<necklace.length(); i++)
        	ans = Math.max(ans, getMax(necklace, i));
        
        out.write(ans + "\n");
        f.close();
        out.close();
        System.exit(0);
    }
    
    private static int getMax(String s, int start) 
    {
    	char color = 'x';
    	int cur;
    	for (cur=start; cur<s.length(); cur++) {
    		char bead = s.charAt(cur);
    		if (bead != 'w') {
    			if (color == 'x')
    				color = s.charAt(cur);
    			else if (bead != color)
    				break;
    		}
    	}
    	
    	color = 'x';
    	for (; cur<s.length(); cur++) {
    		char bead = s.charAt(cur);
    		if (bead != 'w') {
    			if (color == 'x')
    				color = s.charAt(cur);
    			else if (bead != color)
    				break;
    		}    		
    	}
    	
    	int ans = cur - start;
    	return Math.min(ans, s.length()/2);
    }
}
