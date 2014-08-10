/*
ID:
LANG: JAVA
TASK: ride
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ride 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
        		"ride.out")));
        
        String output = (getNum(f.readLine()) == getNum(f.readLine())) ? "GO" : "STAY";
        out.write(output + "\n");

        f.close();
        out.close();
        System.exit(0);
    }
    
    private static int getNum(String name)
    {
    	int num = 1;
    	for (int i = 0; i < name.length(); i++)
    		num *= (name.charAt(i) - 'A' + 1);
    	return num%47;
    }
}
