/*
ID: 
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.StringTokenizer;

public class gift1 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(
        		new FileWriter("gift1.out")));
        
        int N = Integer.parseInt(f.readLine());
        String[] names = new String[N]; // used to maintain order
        
        HashMap<String, Integer> hm = new HashMap<String, Integer>(N);
        
        for (int i=0; i<N; i++) {
        	names[i] = f.readLine();
        	hm.put(names[i], 0);
        }
        
        for (int i=0; i<N; i++) {
        	String giver = f.readLine();
        	StringTokenizer st = new StringTokenizer(f.readLine());
        	int amnt = Integer.parseInt(st.nextToken());
        	int numRec = Integer.parseInt(st.nextToken());
        	int amntRec = (numRec == 0) ? 0 : amnt/numRec;
        	hm.put(giver, hm.get(giver) - numRec * amntRec);
        	for (int j=0; j<numRec; j++) {
        		String receiver = f.readLine();
        		hm.put(receiver, hm.get(receiver) + amntRec);
        	}
        }
        
        for (int i=0; i<N; i++)
        	out.write(names[i] + " " + hm.get(names[i]) + "\n");

        f.close();
        out.close();
        System.exit(0);
    }
}
