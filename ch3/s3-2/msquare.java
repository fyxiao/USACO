/*
ID: 
LANG: JAVA
TASK: msquare
*/

import java.util.*;
import java.io.*;

class State {
	String seq;
	int[] square;
	public State(String a, int[] b) {
		seq = a;
		square = b;
	}
}

public class msquare {
	public static int[] start = {1, 2, 3, 4, 5, 6, 7, 8};
	public static int[] target = new int[8];
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		for(int i=0; i<8; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		
		//HashSet<Integer> hs = new HashSet<Integer>();
		// start iterating
		State startState = new State("", start);
		ArrayList<State> states = new ArrayList<State>();
		states.add(startState);
		//hs.add(12345678);
		boolean[] seen = new boolean[8765433];
		seen[1234567] = true;
		boolean found = false;
		search:
		while(!found) {
			int size = states.size();
			for(int i=0; i<size; i++) {
				State cur = states.get(0);
				if(isSame(cur.square)) {
					System.out.println((cur.seq).length());
					System.out.println(cur.seq);
					out.write((cur.seq).length()+"\n");
					out.write(cur.seq+"\n");
					break search;
				}
				String oldseq = cur.seq;
				String seqA = oldseq + "A";
				String seqB = oldseq + "B";
				String seqC = oldseq + "C";
				int[] oldsquare = cur.square;
				int[] squareA = tranA(oldsquare);
				int[] squareB = tranB(oldsquare);
				int[] squareC = tranC(oldsquare);
				int a = encode(squareA)/10;
				int b = encode(squareB)/10;
				int c = encode(squareC)/10;
				states.remove(0);
				if(!seen[a]) {
					states.add(new State(seqA, squareA));
					seen[a]=true;
				}
				if(!seen[b]) {
					states.add(new State(seqB, squareB));
					seen[b]=true;
				}
				if(!seen[c]) {
					states.add(new State(seqC, squareC));
					seen[c]=true;
				}
			}
		}
		
		out.close();
		System.exit(0);
	}
	
	public static int[] tranA(int[] old) {
		int[] output = new int[8];
		for(int i=0; i<4; i++) {
			output[i] = old[7-i];
		}
		for(int i=4; i<8; i++) {
			output[i] = old[7-i];
		}
		return output;
	}
	
	public static int[] tranB(int[] old) {
		int[] output = new int[8];
		output[0] = old[3];
		for(int i=1; i<4; i++) {
			output[i] = old[(i-1)%4];
		}
		for(int i=4; i<7; i++) {
			output[i] = old[i+1];
		}
		output[7] = old[4];
		return output;
	}
	
	public static int[] tranC(int[] old) {
		int[] output = new int[8];
		output[0] = old[0];
		output[1] = old[6];
		output[2] = old[1];
		output[3] = old[3];
		output[4] = old[4];
		output[5] = old[2];
		output[6] = old[5];
		output[7] = old[7];
		return output;
	}
	
	public static boolean isSame(int[] a) {
		boolean isSame=true;
		for(int i=0; i<8; i++) {
			if(a[i]!=target[i]) {
				isSame = false;
				break;
			}
		}
		return isSame;
	}
	public static int encode(int[] a) {
		int code = 0;
		for(int i=0; i<8; i++) {
			code = code + a[i]*(int)Math.pow(10, 7-i);
		}
		return code;
	}
}