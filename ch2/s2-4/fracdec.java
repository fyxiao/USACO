/*
ID: 
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

public class fracdec {
	
	public static int n, d, whole, rem;
	public static ArrayList<Integer> list = new ArrayList<Integer>();
	public static HashSet remainders = new HashSet();
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		String output;
		// terminating decimal representation
		if(n%d==0) {
			out.write((double)n/d + "\n");
		}
		else {
			whole = n/d;
			rem = (n - whole*d)*10;
			int rep = rem/d;
			while(!remainders.contains(rem) && rem!=0) {
				list.add(rem);
				remainders.add(rem);
				rem = (rem - rep*d)*10;
				rep = rem/d;
			}
			// output
			out.write(whole + "");
			out.write(".");
			int count = (whole + "").length() + 1;
			for(int i=0; i<list.size(); i++) {
				if(list.get(i)==rem) {
					if(count==74) {
						out.write("(" + list.get(i)/d);
						count = 0;
					}
					else if(count==75) {
						out.write("(" + "\n");
						count = 1;
						out.write(list.get(i)/d + "");
					}
					else if(count==76) {
						count = 2;
						out.write("\n");
						out.write("(" + list.get(i)/d);
					}
					else {
						out.write("(" + list.get(i)/d);
						count+=2;
					}
				}
				else {
					if(count==75) {
						out.write(list.get(i)/d + "\n");
						count = 0;
					}
					else if(count==76) {
						count = 1;
						out.write("\n");
						out.write(list.get(i)/d + "");
					}
					else {
						out.write(list.get(i)/d + "");
						count++;
					}
				}
			}
			if(rem!=0) {	
				out.write(")" + "\n");
			}
			else {
				out.write("\n");
			}
			
		}

		out.close();
		System.exit(0);
	}
}
