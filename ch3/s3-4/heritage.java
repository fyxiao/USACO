/*
ID: 
LANG: JAVA
TASK: heritage
*/

import java.io.*;
import java.util.*;

public class heritage {
	public static String inorder, preorder, postorder;
	public static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		// three orders
		inorder=f.readLine();
		preorder=f.readLine();
		postorder="";
		n=inorder.length();
		computepost(0, n);
		
		System.out.println(postorder);
		out.write(postorder+"\n");
		out.close();
		System.exit(0);
	}
	
	// recursive method to form the post-order, index is root
	public static void computepost(int index, int tail) {
		postorder=preorder.charAt(index)+postorder;

		int right=-1;
		for(int i=index+1; i<tail; i++) {
			if(isGreater(i, index)) {
				right=i;
				break;
			}
		}
		// compute right node
		if(right!=-1) {
			computepost(right, tail);		
		}
		// compute left
		if(right>index+1) {
			computepost(index+1, right);
		}
		else if(right==-1 && index+1<tail) {
			computepost(index+1, tail);
		}
	}
	
	// returns true if letter at index a of pre is greater than index b
	public static boolean isGreater(int a, int b) {
		if(inorder.indexOf(preorder.charAt(a))>inorder.indexOf(preorder.charAt(b))) {
			return true;
		}
		else {
			return false;
		}
		
	}

}
